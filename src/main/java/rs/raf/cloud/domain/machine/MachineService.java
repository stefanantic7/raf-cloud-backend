package rs.raf.cloud.domain.machine;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import rs.raf.cloud.CloudApplication;
import rs.raf.cloud.domain.machine.actions.StartMachine;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public List<Machine> search() {
        return this.machineRepository.findAllByActiveIsTrue();
    }

    public Machine createMachine() {
        Machine machine = new Machine();
        machine.setActive(true);
        machine.setStatus(MachineStatusEnum.STOPPED);
        machine.setUid(UUID.randomUUID().toString());

        return this.machineRepository.save(machine);
    }

    public void destroyMachine(long id) throws Exception {
        Optional<Machine> optionalMachine = this.machineRepository.findById(id);
        if (optionalMachine.isEmpty()) {
            throw new Exception("Machine not found");
        }

        Machine machine = optionalMachine.get();
        machine.setActive(false);
        this.machineRepository.save(machine);
    }

    @Transactional(isolation= Isolation.SERIALIZABLE)
    public Machine start(long id) throws Exception {
        Optional<Machine> optionalMachine = this.machineRepository.findById(id);
        if (optionalMachine.isEmpty()) {
            throw new Exception("Machine not found");
        }

        Machine machine = optionalMachine.get();
        if (machine.getStatus() != MachineStatusEnum.STOPPED) {
            throw new Exception("Machine is not stopped");
        }

        machine.setStatus(MachineStatusEnum.STARTING);
        machineRepository.save(machine);

        rabbitTemplate.convertAndSend("startMachineQueue", machine);

        return machine;
    }

    @Transactional(isolation= Isolation.SERIALIZABLE)
    public Machine stop(long id) throws Exception {
        Optional<Machine> optionalMachine = this.machineRepository.findById(id);
        if (optionalMachine.isEmpty()) {
            throw new Exception("Machine not found");
        }

        Machine machine = optionalMachine.get();
        if (machine.getStatus() != MachineStatusEnum.RUNNING) {
            throw new Exception("Machine is not run");
        }

        machine.setStatus(MachineStatusEnum.STOPPING);
        machineRepository.save(machine);

        rabbitTemplate.convertAndSend("stopMachineQueue", machine);

        return machine;
    }
}

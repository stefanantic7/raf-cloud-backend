package rs.raf.cloud.domain.machine.servicce;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import rs.raf.cloud.domain.machine.enums.MachineStatusEnum;
import rs.raf.cloud.domain.machine.entity.Machine;
import rs.raf.cloud.domain.machine.repository.MachineRepository;
import rs.raf.cloud.domain.machine.request.CreateMachineRequest;
import rs.raf.cloud.domain.machine.request.SearchMachineRequest;
import rs.raf.cloud.domain.user.entity.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public List<Machine> search(User user, SearchMachineRequest searchMachineRequest) {
        return this.machineRepository.searchMachines(user, searchMachineRequest);
    }

    public Machine createMachine(User user, CreateMachineRequest createMachineRequest) {
        Machine machine = new Machine();
        machine.setUser(user);
        machine.setName(createMachineRequest.getName());
        machine.setActive(true);
        machine.setStatus(MachineStatusEnum.STOPPED);
        machine.setUid(UUID.randomUUID().toString());
        machine.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        return this.machineRepository.save(machine);
    }

    public void destroyMachine(String uid) throws Exception {
        Optional<Machine> optionalMachine = this.machineRepository.findByUid(uid);
        if (optionalMachine.isEmpty()) {
            throw new Exception("Machine not found");
        }

        Machine machine = optionalMachine.get();
        if (! machine.getStatus().equals(MachineStatusEnum.STOPPED)) {
            throw new Exception("Machine is not STOPPED");
        }

        machine.setActive(false);
        this.machineRepository.save(machine);
    }

    @Transactional(isolation= Isolation.SERIALIZABLE)
    public Machine start(String uid) throws Exception {
        Optional<Machine> optionalMachine = this.machineRepository.findByUid(uid);
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
    public Machine stop(String uid) throws Exception {
        Optional<Machine> optionalMachine = this.machineRepository.findByUid(uid);
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

    @Transactional(isolation= Isolation.SERIALIZABLE)
    public Machine restart(String uid) throws Exception {
        Optional<Machine> optionalMachine = this.machineRepository.findByUid(uid);
        if (optionalMachine.isEmpty()) {
            throw new Exception("Machine not found");
        }

        Machine machine = optionalMachine.get();
        if (machine.getStatus() != MachineStatusEnum.RUNNING) {
            throw new Exception("Machine is not run");
        }

        machine.setStatus(MachineStatusEnum.RESTARTING);
        machineRepository.save(machine);

        rabbitTemplate.convertAndSend("restartMachineQueue", machine);

        return machine;
    }
}

package rs.raf.cloud.domain.machine.actions;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import rs.raf.cloud.domain.machine.Machine;
import rs.raf.cloud.domain.machine.MachineRepository;
import rs.raf.cloud.domain.machine.MachineStatusEnum;

@Component
public class RestartMachine {

    @Autowired
    private MachineRepository machineRepository;

    @RabbitListener(queues = "restartMachineQueue")
    @Transactional(isolation= Isolation.SERIALIZABLE)
    public void handle(Machine machine) throws Exception {
        System.out.println("Restarting machine <" + machine.getUid() + ">");

        Thread.sleep(5000);

        machine.setStatus(MachineStatusEnum.STOPPED);
        this.machineRepository.save(machine);
        System.out.println("Stopped");

        Thread.sleep(5000);

        machine.setStatus(MachineStatusEnum.RUNNING);
        this.machineRepository.save(machine);

        System.out.println("Machine <" + machine.getUid() + "> has been restarted.");
        // TODO: Fire an event
    }

}

package rs.raf.cloud.domain.machine.action;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.raf.cloud.domain.machine.entity.Machine;
import rs.raf.cloud.domain.machine.repository.MachineRepository;
import rs.raf.cloud.domain.machine.enums.MachineStatusEnum;

@Component
public class StopMachine {

    @Autowired
    private MachineRepository machineRepository;

    @RabbitListener(queues = "stopMachineQueue")
    public void handle(Machine machine) throws Exception {
        System.out.println("Stopping machine <" + machine.getUid() + ">");

        Thread.sleep(10000);

        machine.setStatus(MachineStatusEnum.STOPPED);
        this.machineRepository.save(machine);

        System.out.println("Machine <" + machine.getUid() + "> has been stopped.");
        // TODO: Fire an event
    }

}

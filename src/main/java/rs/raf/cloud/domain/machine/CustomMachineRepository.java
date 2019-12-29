package rs.raf.cloud.domain.machine;

import java.util.List;

public interface CustomMachineRepository {
    List<Machine> searchMachines(MachineSearchQuery machineQueryModel);
}

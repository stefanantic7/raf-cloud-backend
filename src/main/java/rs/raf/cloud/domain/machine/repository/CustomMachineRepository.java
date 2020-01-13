package rs.raf.cloud.domain.machine.repository;

import rs.raf.cloud.domain.machine.request.SearchMachineRequest;
import rs.raf.cloud.domain.machine.entity.Machine;
import rs.raf.cloud.domain.user.entity.User;

import java.util.List;

public interface CustomMachineRepository {
    List<Machine> searchMachines(User user, SearchMachineRequest searchMachineRequest);
}

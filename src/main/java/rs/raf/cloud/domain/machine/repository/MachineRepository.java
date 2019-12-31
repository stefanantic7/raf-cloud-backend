package rs.raf.cloud.domain.machine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.raf.cloud.domain.machine.entity.Machine;

import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long>, CustomMachineRepository {
    List<Machine> findAllByActiveIsTrue();
}

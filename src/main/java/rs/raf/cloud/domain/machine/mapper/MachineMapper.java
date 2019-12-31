package rs.raf.cloud.domain.machine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import rs.raf.cloud.domain.machine.dto.MachineDto;
import rs.raf.cloud.domain.machine.entity.Machine;

import java.util.List;

@Mapper
public interface MachineMapper {
    MachineMapper instance = Mappers.getMapper( MachineMapper.class );

    MachineDto machineToMachineDto(Machine machine);

    List<MachineDto> machineListToMachineDtoList(List<Machine> machineList);
}

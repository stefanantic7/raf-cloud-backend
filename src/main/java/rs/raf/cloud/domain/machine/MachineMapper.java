package rs.raf.cloud.domain.machine;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MachineMapper {
    MachineMapper instance = Mappers.getMapper( MachineMapper.class );

    MachineDto machineToMachineDto(Machine machine);

    List<MachineDto> machineListToMachineDtoList(List<Machine> machineList);
}

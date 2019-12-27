package rs.raf.cloud.domain.machine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/machines")
public class MachineController {

    @Autowired
    MachineService machineService;

    @GetMapping
    public ResponseEntity<List<MachineDto>> get() {
        var machineList = this.machineService.search();
        var machineDtoList = MachineMapper.instance.machineListToMachineDtoList(machineList);

        return new ResponseEntity<>(machineDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MachineDto> create() {
        var machine = machineService.createMachine();
        var machineDto = MachineMapper.instance.machineToMachineDto(machine);

        return new ResponseEntity<>(machineDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable long id) throws Exception {
        this.machineService.destroyMachine(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<MachineDto> start(@PathVariable long id) throws Exception {
        var machine = this.machineService.start(id);
        var machineDto = MachineMapper.instance.machineToMachineDto(machine);

        return new ResponseEntity<>(machineDto, HttpStatus.OK);
    }

    @PostMapping("/{id}/stop")
    public ResponseEntity<MachineDto> stop(@PathVariable long id) throws Exception {
        var machine = this.machineService.stop(id);
        var machineDto = MachineMapper.instance.machineToMachineDto(machine);

        return new ResponseEntity<>(machineDto, HttpStatus.OK);
    }

}

package rs.raf.cloud.domain.machine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/machines")
public class MachineController {

    @Autowired
    MachineService machineService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @GetMapping
    public ResponseEntity<List<MachineDto>> search(@RequestBody @Valid MachineSearchQuery machineSearchQuery) {
        var machineList = this.machineService.search(machineSearchQuery);
        var machineDtoList = MachineMapper.instance.machineListToMachineDtoList(machineList);

        return new ResponseEntity<>(machineDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MachineDto> create(@RequestBody @Valid MachineCreateQuery machineCreateQuery) {
        var machine = machineService.createMachine(machineCreateQuery);
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

    @PostMapping("/{id}/restart")
    public ResponseEntity<MachineDto> restart(@PathVariable long id) throws Exception {
        var machine = this.machineService.restart(id);
        var machineDto = MachineMapper.instance.machineToMachineDto(machine);

        return new ResponseEntity<>(machineDto, HttpStatus.OK);
    }

}

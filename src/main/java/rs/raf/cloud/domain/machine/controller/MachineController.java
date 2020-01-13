package rs.raf.cloud.domain.machine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.raf.cloud.domain.machine.request.CreateMachineRequest;
import rs.raf.cloud.domain.machine.mapper.MachineMapper;
import rs.raf.cloud.domain.machine.request.SearchMachineRequest;
import rs.raf.cloud.domain.machine.servicce.MachineService;
import rs.raf.cloud.domain.machine.dto.MachineDto;
import rs.raf.cloud.domain.user.facade.auth.IAuthFacade;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/machines")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @Autowired
    private IAuthFacade authenticationFacade;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MachineDto> search(@Valid SearchMachineRequest searchMachineRequest) {
        var machineList = this.machineService.search(this.authenticationFacade.getUser(), searchMachineRequest);
        return MachineMapper.instance.machineListToMachineDtoList(machineList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MachineDto create(@RequestBody @Valid CreateMachineRequest createMachineRequest) {
        var machine = machineService.createMachine(this.authenticationFacade.getUser(), createMachineRequest);
        return MachineMapper.instance.machineToMachineDto(machine);
    }

    @DeleteMapping("/{uid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable String uid) throws Exception {
        this.machineService.destroyMachine(uid);
    }

    @PostMapping("/{uid}/start")
    @ResponseStatus(HttpStatus.OK)
    public MachineDto start(@PathVariable String uid) throws Exception {
        var machine = this.machineService.start(uid);
        return MachineMapper.instance.machineToMachineDto(machine);
    }

    @PostMapping("/{uid}/stop")
    @ResponseStatus(HttpStatus.OK)
    public MachineDto stop(@PathVariable String uid) throws Exception {
        var machine = this.machineService.stop(uid);
        return MachineMapper.instance.machineToMachineDto(machine);
    }

    @PostMapping("/{uid}/restart")
    @ResponseStatus(HttpStatus.OK)
    public MachineDto restart(@PathVariable String uid) throws Exception {
        var machine = this.machineService.restart(uid);
        return MachineMapper.instance.machineToMachineDto(machine);
    }

}

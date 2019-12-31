package rs.raf.cloud.domain.machine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.raf.cloud.domain.machine.request.MachineCreateRequest;
import rs.raf.cloud.domain.machine.mapper.MachineMapper;
import rs.raf.cloud.domain.machine.request.MachineSearchRequest;
import rs.raf.cloud.domain.machine.servicce.MachineService;
import rs.raf.cloud.domain.machine.dto.MachineDto;
import rs.raf.cloud.domain.user.facade.auth.IAuthFacade;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/machines")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @Autowired
    private IAuthFacade authenticationFacade;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MachineDto> search(@Valid MachineSearchRequest machineSearchRequest) {
        var machineList = this.machineService.search(this.authenticationFacade.getUser(), machineSearchRequest);
        return MachineMapper.instance.machineListToMachineDtoList(machineList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MachineDto create(@RequestBody @Valid MachineCreateRequest machineCreateRequest) {
        var machine = machineService.createMachine(this.authenticationFacade.getUser(), machineCreateRequest);
        return MachineMapper.instance.machineToMachineDto(machine);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable long id) throws Exception {
        this.machineService.destroyMachine(id);
    }

    @PostMapping("/{id}/start")
    @ResponseStatus(HttpStatus.OK)
    public MachineDto start(@PathVariable long id) throws Exception {
        var machine = this.machineService.start(id);
        return MachineMapper.instance.machineToMachineDto(machine);
    }

    @PostMapping("/{id}/stop")
    @ResponseStatus(HttpStatus.OK)
    public MachineDto stop(@PathVariable long id) throws Exception {
        var machine = this.machineService.stop(id);
        return MachineMapper.instance.machineToMachineDto(machine);
    }

    @PostMapping("/{id}/restart")
    @ResponseStatus(HttpStatus.OK)
    public MachineDto restart(@PathVariable long id) throws Exception {
        var machine = this.machineService.restart(id);
        return MachineMapper.instance.machineToMachineDto(machine);
    }

}

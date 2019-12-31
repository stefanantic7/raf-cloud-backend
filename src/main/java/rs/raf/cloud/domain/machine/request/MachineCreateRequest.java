package rs.raf.cloud.domain.machine.request;

import javax.validation.constraints.NotBlank;

public class MachineCreateRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

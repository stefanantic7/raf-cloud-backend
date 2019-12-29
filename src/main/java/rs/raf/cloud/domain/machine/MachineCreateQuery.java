package rs.raf.cloud.domain.machine;

import javax.validation.constraints.NotBlank;

public class MachineCreateQuery {

    @NotBlank(message = "Name is mandatory")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

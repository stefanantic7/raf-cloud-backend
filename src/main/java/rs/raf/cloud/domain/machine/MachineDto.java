package rs.raf.cloud.domain.machine;

import java.sql.Timestamp;

public class MachineDto {

    private String name;
    private String uid;
    private MachineStatusEnum status;
    private Timestamp createdAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public MachineStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MachineStatusEnum status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

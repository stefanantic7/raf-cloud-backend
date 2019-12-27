package rs.raf.cloud.domain.machine;

public class MachineDto {

    private String uid;
    private MachineStatusEnum status;

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
}

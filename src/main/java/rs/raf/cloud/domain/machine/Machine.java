package rs.raf.cloud.domain.machine;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Machine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "uid", nullable = false, unique = true)
    private String uid;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MachineStatusEnum status;

    @Column(name = "active", nullable = false)
    private boolean active;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

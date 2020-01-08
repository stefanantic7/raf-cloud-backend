package rs.raf.cloud.domain.machine.entity;

import rs.raf.cloud.domain.machine.enums.MachineStatusEnum;
import rs.raf.cloud.domain.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Machine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "uid", nullable = false, unique = true)
    private String uid;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MachineStatusEnum status;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "createdAt", nullable = true)
    private Timestamp createdAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

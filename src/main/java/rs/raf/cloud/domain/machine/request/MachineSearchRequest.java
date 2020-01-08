package rs.raf.cloud.domain.machine.request;

import rs.raf.cloud.domain.machine.enums.MachineStatusEnum;

import java.sql.Date;
import java.util.List;

public class MachineSearchRequest {

    private String name;
    private List<MachineStatusEnum> status;
    private Date dateFrom;
    private Date dateTo;

    public List<MachineStatusEnum> getStatus() {
        return status;
    }

    public void setStatus(List<MachineStatusEnum> status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}

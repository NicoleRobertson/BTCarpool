package demo.BTCarpool;

public class Booking {
    private long id;
    private long employeeId;
    private long carrideId;
    private Boolean active;

    public Booking(long id, long employeeId, long carrideId, Boolean active) {
        this.id = id;
        this.employeeId = employeeId;
        this.carrideId = carrideId;
        this.active = active;
    }

    public Booking() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getCarrideId() {
        return carrideId;
    }

    public void setCarrideId(long carrideId) {
        this.carrideId = carrideId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

package demo.BTCarpool;

import javax.validation.constraints.Size;
import java.util.Date;

public class CarRide {
    private long id;
    @Size(min=1, max=100)
    private long vehicleId;
    @Size(min=1, max=100)
    private Date date;
    @Size(min=1, max=100)
    private long employeeId;
    @Size(min=1, max=100)
    private long officeId;
    @Size(min=1, max=100)
    private int availableSeats;
    @Size(min=1, max=100)

    public CarRide(long id, @Size(min = 1, max = 100) long vehicleId, @Size(min = 1, max = 100) Date date, @Size(min = 1, max = 100) long employeeId, @Size(min = 1, max = 100) long officeId, @Size(min = 1, max = 100) int availableSeats) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.date = date;
        this.employeeId = employeeId;
        this.officeId = officeId;
        this.availableSeats = availableSeats;
    }

    public CarRide() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(long officeId) {
        this.officeId = officeId;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}

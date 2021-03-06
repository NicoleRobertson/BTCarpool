package demo.BTCarpool;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//@Setter
//@Getter
//@NoArgsConstructor

public class CarRide {
    private long id;
    //@Size(min=1, max=100)
    private long vehicleId;
    //@Size(min=1, max=100)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    //@Size(min=1, max=100)
    private long employeeId;
    //@Size(min=1, max=100)
    private long officeId;
    //@Size(min=1, max=100)
    private Integer availableSeats;
    //@Size(min=1, max=100)
    /*private boolean alreadyBooked = false;*/



    public CarRide(long id, long vehicleId, Date date, long employeeId, long officeId, int availableSeats) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.date = date;
        this.employeeId = employeeId;
        this.officeId = officeId;
        this.availableSeats = availableSeats;
    }

    public CarRide() {
    }


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

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    /*public boolean isAlreadyBooked() {
        return alreadyBooked;
    }

    public boolean getAlreadyBooked() {
        return alreadyBooked;


    public void setAlreadyBooked(boolean alreadyBooked) {
        this.alreadyBooked = alreadyBooked;
    }*/
}

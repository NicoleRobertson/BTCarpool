package demo.BTCarpool;

import javax.validation.constraints.Size;
import java.util.Date;

public class StartpageCarRides {

    private long id;
    @Size(min=1, max=100)
    private Date date;
    @Size(min=1, max=100)
    private String officeName;
    @Size(min=1, max=100)
    private int zipCode;
    @Size(min=1, max=100)
    private String employeeName;
    @Size(min=1, max=100)
    private int availableSeats;
    @Size(min=1, max=100)
    private boolean alreadyBooked = false;

    public StartpageCarRides(long id, @Size(min = 1, max = 100) Date date, @Size(min = 1, max = 100) String officeName, @Size(min = 1, max = 100) int zipCode, @Size(min = 1, max = 100) String employeeName, @Size(min = 1, max = 100) int availableSeats) {
        this.id = id;
        this.date = date;
        this.officeName = officeName;
        this.zipCode = zipCode;
        this.employeeName = employeeName;
        this.availableSeats = availableSeats;
    }


    public StartpageCarRides() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public boolean isAlreadyBooked() {
        return alreadyBooked;
    }

    public void setAlreadyBooked(boolean alreadyBooked) {
        this.alreadyBooked = alreadyBooked;
    }
}

package demo.BTCarpool;

import javax.validation.constraints.Size;

public class Vehicle {
    private long id;
    @Size(min=1, max=100)
    private long employeeId;
    @Size(min=1, max=100)
    private int seats;
    @Size(min=1, max=100)
    private double costPerMile;
    @Size(min=1, max=100)
    private String licensePlate;
    @Size(min=1, max=100)
    private String model;
    @Size(min=1, max=100)


    public Vehicle(long id, @Size(min = 1, max = 100) long employeeId, @Size(min = 1, max = 100) int seats, @Size(min = 1, max = 100) double costPerMile, @Size(min = 1, max = 100) String licensePlate, @Size(min = 1, max = 100) String model) {
        this.id = id;
        this.employeeId = employeeId;
        this.seats = seats;
        this.costPerMile = costPerMile;
        this.licensePlate = licensePlate;
        this.model = model;
    }

    public Vehicle() {}

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

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public double getCostPerMile() {
        return costPerMile;
    }

    public void setCostPerMile(double costPerMile) {
        this.costPerMile = costPerMile;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}

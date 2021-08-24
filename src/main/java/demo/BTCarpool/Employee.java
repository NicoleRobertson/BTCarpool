package demo.BTCarpool;

import javax.validation.constraints.Size;

public class Employee {
    private long id;
    @Size(min=1, max=100)
    private String firstName;
    @Size(min=1, max=100)
    private String lastName;
    @Size(min=1, max=100)
    private Boolean hasCar = false;
    @Size(min=1, max=100)
    private int addressId;
    @Size(min=1, max=100)
    private String userName;
    @Size(min=1, max=100)

    public Employee(long id, @Size(min = 1, max = 100) String firstName, @Size(min = 1, max = 100) String lastName, @Size(min = 1, max = 100) Boolean hasCar, @Size(min = 1, max = 100) int addressId, @Size(min = 1, max = 100) String userName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hasCar = hasCar;
        this.addressId = addressId;
        this.userName = userName;
    }

    public Employee() {};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getHasCar() {
        return hasCar;
    }

    public void setHasCar(Boolean hasCar) {
        this.hasCar = hasCar;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

package demo.BTCarpool;

import javax.validation.constraints.Size;

public class Office {
    private long id;
    @Size(min=1, max=100)
    private String name;
    @Size(min=1, max=100)
    private long addressId;
    @Size(min=1, max=100)


    public Office(long id, @Size(min = 1, max = 100) String name, @Size(min = 1, max = 100) long addressId) {
        this.id = id;
        this.name = name;
        this.addressId = addressId;
    }

    public Office() {}

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

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }
}

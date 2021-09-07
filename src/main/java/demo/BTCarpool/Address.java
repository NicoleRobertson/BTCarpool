package demo.BTCarpool;

public class Address {

    private long id;
    //@Size(min=1, max=100)
    private String streetName;
    //@Size(min=1, max=100)
    private Integer streetNumber;
    //@Size(min=1, max=100)
    private Integer zipCode;
    //@Size(min=1, max=5)
    private String city;
    //@Size(min=1, max=100)


    public Address(long id, String streetName, int streetNumber, int zipCode, String city) {
        this.id = id;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.city = city;
    }

    public Address() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

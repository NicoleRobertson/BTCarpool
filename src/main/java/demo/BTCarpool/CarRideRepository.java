package demo.BTCarpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRideRepository {

    @Autowired
    private DataSource dataSource; //Question: what does this do, and why only for JDBC?

    public List<CarRide> getCarRides() {
        List<CarRide> carRides = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM CARRIDE")) {

            while (rs.next()) {
                carRides.add(rsCarRide(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carRides;
    }

    public List<StartpageCarRides> publishedCarRides() {
        List<StartpageCarRides> publishedCarRides = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT CARRIDE.ID, CARRIDE.RIDEDATE, OFFICE.NAME AS OFFICE, ADDRESS.ZIPCODE AS PICKUPZONE, EMPLOYEE.FIRSTNAME AS EMPLOYEE, CARRIDE.AVAILABLESEATS FROM CARRIDE JOIN OFFICE ON CARRIDE.OFFICE_ID = OFFICE.ID JOIN ADDRESS ON EMPLOYEE.ADDRESS_ID = ADDRESS.ID JOIN EMPLOYEE ON CARRIDE.EMPLOYEE_ID = EMPLOYEE.ID")) {


            while (rs.next()) {
                publishedCarRides.add(rsStartpageCarRides(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publishedCarRides;
    }

    public Vehicle getVehicle(long id) {
        Vehicle vehicle = null;
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT *from Vehicle where vehicle.id = " + id)) {

            if (rs.next()) {
                vehicle = rsVehicle(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    /*SELECT Vehicle.id Vehicle.model, Vehicle.licenseplate, Vehicle.costpermile, Vehicle.numofseats, Employee.firstName, Employee.lastName
    from Vehicle
    INNER JOIN Employee ON Vehicle.Employee_id=Employee.id where vehicle.id = 1*/


    public Employee getEmployee(long id) {
        Employee employee = null;
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from Employee join on Vehicle where vehicle.id = " + id)) {

            if (rs.next()) {
                employee = rsEmployee(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public Address getAddress() {
        Address address = null;
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from Address")) {

            if (rs.next()) {
                address = rsAddress(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    private StartpageCarRides rsStartpageCarRides(ResultSet rs) throws SQLException {
        return new StartpageCarRides(rs.getLong("id"),
                rs.getDate("RIDEDATE"),
                rs.getString("OFFICE"),
                rs.getInt("PICKUPZONE"),
                rs.getString("EMPLOYEE"),
                rs.getInt("AVAILABLESEATS"));
    }

    private CarRide rsCarRide(ResultSet rs) throws SQLException {
        return new CarRide(rs.getLong("id"),
                rs.getLong("vehicle_id"),
                rs.getDate("ridedate"),
                rs.getInt("employee_id"),
                rs.getInt("office_id"),
                rs.getInt("availableseats"));

    }

    private Vehicle rsVehicle(ResultSet rs) throws SQLException {
        return new Vehicle(rs.getLong("id"),
                rs.getLong("employee_id"),
                rs.getInt("numofseats"),
                rs.getDouble("costPerMile"),
                rs.getString("licenseplate"),
                rs.getString("model"));
    }
    private Employee rsEmployee(ResultSet rs) throws SQLException {
        return new Employee(rs.getLong("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getBoolean("hascar"),
                rs.getInt("address_id"),
                rs.getString("username"));
}
    private Address rsAddress(ResultSet rs) throws SQLException {
        return new Address(rs.getLong("id"),
                rs.getString("STREETNAME"),
                rs.getInt("STREETNUMBER"),
                rs.getInt("ZIPCODE"),
                rs.getString("CITY"));
    }

    public int saveEmployeeCreateRide(Employee employee, long addressId) {
        int id = 0;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO EMPLOYEE(FIRSTNAME, LASTNAME,ADDRESS_ID) VALUES " +
                     " (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setLong(3, addressId);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys(); //hämta id som skapas automatiskt av databasen
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
}
    public int saveAddress(Address address) {
        int id = 0;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO ADDRESS(STREETNAME, STREETNUMBER,ZIPCODE,CITY) VALUES " +
                     " (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, address.getStreetName());
            ps.setInt(2, address.getStreetNumber());
            ps.setInt(3, address.getZipCode());
            ps.setString(4, address.getCity());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys(); //hämta id som skapas automatiskt av databasen
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    public int saveVehicle (Vehicle vehicle, long addressId) {
        int id = 0;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Vehicle(EMPLOYEE_ID, NUMOFSEATS, COSTPERMILE, LICENSEPLATE, MODEL) VALUES " +
                     " (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, addressId);
            ps.setInt(2, vehicle.getSeats());
            ps.setDouble(3, vehicle.getCostPerMile());
            ps.setString(4, vehicle.getLicensePlate());
            ps.setString(5, vehicle.getModel());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys(); //hämta id som skapas automatiskt av databasen
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    public void saveRide(CarRide carride, long vehicleId, long employeeId) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO CARRIDE(VEHICLE_ID, RIDEDATE, EMPLOYEE_ID, AVAILABLESEATS) VALUES " +
                     " (?, ?, ?, ?)")) {
            ps.setLong(1, vehicleId);
            System.out.println(carride.getDate());
            if (carride.getDate() != null){
                ps.setDate(2,new java.sql.Date(carride.getDate().getTime()));}
            else {ps.setDate (2, null);}
            ps.setLong(3, employeeId);
            ps.setInt(4, carride.getAvailableSeats());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int saveBooking (long carRideId, long employeeId) {
        int id = 0;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Booking(EMPLOYEE_ID, CARRIDE_ID, ACTIVE) VALUES " +
                     " (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, employeeId);
            ps.setLong(2, carRideId);
            ps.setBoolean(3, true);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys(); //hämta id som skapas automatiskt av databasen
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    //Find Employee where UserName = currentUserName (login)
    public Employee getEmployee(String currentUserName) {
        Employee employee = null;
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from Employee where Employee.USERNAME = " + "'" + currentUserName + "'")) {

            if (rs.next()) {
                employee = rsEmployee(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public CarRide getCarRide(long id) {
        CarRide carRide = null;
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ) {

             String sql = "UPDATE CARRIDE SET CARRIDE.AVAILABLESEATS = (CARRIDE.AVAILABLESEATS - 1) WHERE CARRIDE.ID = ";

             stmt.executeUpdate(sql + id);
             ResultSet rs = stmt.executeQuery("SELECT * FROM CARRIDE WHERE CARRIDE.ID = " + id);

            if (rs.next()) {
                carRide = rsCarRide(rs);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carRide;
    }

    public List<Booking> getBookings() {
        List<Booking> bookings = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Booking")) {

            while (rs.next()) {
                bookings.add(rsBooking(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    private Booking rsBooking(ResultSet rs) throws SQLException {
        return new Booking (rs.getLong("id"),
                rs.getLong("EMPLOYEE_ID"),
                rs.getLong("CARRIDE_ID"),
                rs.getBoolean("ACTIVE"));
    }
    public Booking cancelBooking(long id, long employeeId) {
        Booking booking = null;
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
        ) {

            String sql = "UPDATE BOOKING SET BOOKING.ACTIVE = FALSE WHERE BOOKING.CARRIDE_ID = ";
            String sql2 = " AND BOOKING.EMPLOYEE_ID = ";

            stmt.executeUpdate(sql + id + sql2 + employeeId);
            ResultSet rs = stmt.executeQuery("SELECT * FROM BOOKING WHERE BOOKING.CARRIDE_ID = " + id + " AND BOOKING.EMPLOYEE_ID =" + employeeId);

            if (rs.next()) {
                booking =rsBooking(rs);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

}
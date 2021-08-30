package demo.BTCarpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
             ResultSet rs = stmt.executeQuery("SELECT * FROM vehicle where vehicle.id = " + id)) {

            if (rs.next()) {
                vehicle = rsVehicle(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicle;
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
                rs.getString("carmodel"));
    }
    private Employee rsemployee(ResultSet rs) throws SQLException {
        return new Employee(rs.getLong("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getBoolean("hascar"),
                rs.getInt("address_id"),
                rs.getString("username"));
}}
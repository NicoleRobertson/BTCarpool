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

    public List<CarRide> getAvailableSeats() {
        List<CarRide> carRides = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT CARRIDE.AVAILABLESEATS")) {

            while (rs.next()){
                carRides.add(rsCarRide(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carRides;
    }

    private CarRide rsCarRide(ResultSet rs) throws SQLException {
        return new CarRide(rs.getLong("id"),
                rs.getInt("vehicle_id"),
                rs.getDate("ridedate"),
                rs.getInt("employee_id"),
                rs.getInt("office_id"),
                rs.getInt("availableseats"));

}
}

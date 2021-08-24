package demo.BTCarpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CarRideRepository {

    @Autowired
    private DataSource dataSource; //Question: what does this do, and why only for JDBC?


}

package jdbc;

import access_DB.Access_Data_DB;
import org.flywaydb.core.Flyway;

import java.io.IOException;

public class InitDB {

    public void startMigration() throws IOException {
        Flyway flyway = Flyway.configure().dataSource(
                new Access_Data_DB().getString("jdbcUrl"),
                new Access_Data_DB().getString("jdbcUser"),
                new Access_Data_DB().getString("jdbcPassword"))
                .load();

        flyway.migrate();
    }
}

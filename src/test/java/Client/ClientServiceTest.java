package Client;

import jdbc.Database;
import jdbc.InitDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ClientServiceTest {
    private Connection connection;
    private ClientService clientService;

    @BeforeEach
    public void beforeEach () throws SQLException, IOException {
        InitDB initDB = new InitDB();
        initDB.startMigration();
        connection = Database.getInstance().getConnection();

        clientService = new ClientService(connection);
    }

    @AfterEach
    public void afterEach () throws SQLException {
        connection.close();
    }

    @Test
    void thatClientCreatedCorrectly() throws SQLException {
        Client client1 = new Client();
//        client1.setId(1);
        client1.setName("name");

        long l = clientService.create("'name1'");
        clientService.getById(l);

        Assertions.assertEquals(client1.getId(), l);
    }

    @Test
    void getById() throws SQLException {
        Client client = new Client();
        client.setName("'asfd'");
        long l = clientService.create(client.getName());

        Assertions.assertEquals(client.getId(), l);
    }

    @Test
    void setName() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void listAll() {
    }
}
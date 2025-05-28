package Client;

import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ClientService {
    private PreparedStatement create;
    private PreparedStatement getById;
    private PreparedStatement setName;
    private PreparedStatement deleteById;
    private PreparedStatement listAll;

    private PreparedStatement maxId;

     public ClientService(Connection connection) throws SQLException {
        create = connection.prepareStatement("INSERT INTO CLIENT (id, NAME) VALUES (?, ?)");
        getById = connection.prepareStatement("SELECT name FROM client WHERE ID = ?");
        setName = connection.prepareStatement("update CLIENT set name = (?) WHERE ID = ?");
        deleteById = connection.prepareStatement("delete from client where id = ?");
        listAll = connection.prepareStatement("select * from client");

        maxId = connection.prepareStatement("SELECT MAX(id) AS max_id FROM client");
     }

    public long create(String name) throws SQLException {
        if (name.length()<2||name.length()>20){
            throw new RuntimeException("Incorrect name");
        }
        long id = 0;
        ResultSet l = maxId.executeQuery();

        if (l.next()) {
            id = l.getLong("max_id") + 1;
            System.out.println("id: " + id + " created");
        }
        create.setLong(1, id);
        create.setString(2, name);

        return create.executeUpdate();
    }

    public String getById(long id) throws SQLException {
         if (id>getMaxId()){
             throw new RuntimeException("Incorrect Id");
         }

         getById.setLong(1, id);
         try (ResultSet rs = getById.executeQuery()) {
             if (!rs.next()) {
                 return null;
             }
             System.out.println("Get by id: " + id + " name: " + rs.getString("name"));
             return rs.getString("name");
         }
    }


    public void setName(long id, String name) throws SQLException {
        if (name.length()<2||name.length()>20){
            throw new RuntimeException("Incorrect name");
        } else if (id>getMaxId()){
            throw new RuntimeException("Incorrect Id");
        }

        setName.setString(1, name);
        setName.setLong(2, id);
        System.out.println("In id " + id + " setted new name: " + name);

        setName.executeUpdate();
    }

    public void deleteById(long id) throws SQLException {
        if (id>getMaxId()){
            throw new RuntimeException("Incorrect Id");
        }

         deleteById.setLong(1, id);

         deleteById.executeUpdate();
    }

    public List<Client> listAll() {
        List <Client> allClients = new ArrayList<>();

        try (ResultSet rs = listAll.executeQuery()) {

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));
                allClients.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allClients;
    }

    public long getMaxId () throws SQLException {
        long id = 0;
        ResultSet l = maxId.executeQuery();

        if (l.next()) {
            id = l.getLong("max_id");
        }
        return id;
    }
}
package Client;


import lombok.Getter;
import lombok.Setter;


@Getter

public class Client {
    private long Id;
    private String Name;

    public void setId(long id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                '}';
    }
}

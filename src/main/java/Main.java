import Client.Client;
import Client.ClientService;
import jdbc.Database;
import jdbc.DatabaseQueryService;
import jdbc.InitDB;
import model.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        InitDB initDB = new InitDB();
        initDB.startMigration();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        ClientService cs = new ClientService(Database.getInstance().getConnection());

        for (Client c: cs.listAll()) {
            System.out.println(c);
        }

        cs.create("goggi");

        cs.getById(8);

        cs.setName(8,"bmnzxcvbnm");
        cs.setName(5,"etrsbtfsdc");


        cs.getById(8);

        cs.deleteById(8);
        cs.getById(5);

        cs.create("8888");

        for (Client c: cs.listAll()) {
            System.out.println(c);
        }


//        List<ProjectPrices> projectPrices = new DatabaseQueryService().printProjectPrices();
//
//        for (ProjectPrices project : projectPrices) {
//            System.out.println(project.toString());
//        }
//
//        System.out.println();
//        List <YoungestEldestWorker> list_yew = new DatabaseQueryService().findYoungestEldestWorkers();
//        for (YoungestEldestWorker ye:list_yew) {
//            System.out.println(ye.toString());
//        }
//        System.out.println();
//
//        List <MaxSalaryWorker> list_msw = new DatabaseQueryService().findMaxSalaryWorker();
//        for (MaxSalaryWorker ms: list_msw) {
//            System.out.println(ms.toString());
//        }
//        System.out.println();
//
//        List <MaxProjectsClient> list_mpc = new DatabaseQueryService().findMaxProjectsClient();
//        for (MaxProjectsClient mp: list_mpc) {
//            System.out.println(mp.toString());
//        }
//        System.out.println();
//
//        List <LongestProject> list_lp = new DatabaseQueryService().findLongestProject();
//        for (LongestProject lp: list_lp) {
//            System.out.println(lp.toString());
//        }
    }
}
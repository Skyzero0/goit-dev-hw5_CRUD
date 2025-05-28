package jdbc;

import model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    private String sql;
    private final Connection connection;

    {
        try {
            connection = Database.getInstance().getConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement select;
    private ResultSet rs;

//    public static void main(String[] args) throws IOException {
//        DatabaseQueryService databaseQueryService = new DatabaseQueryService();
////
////        List <ProjectPrices> list_pr = databaseQueryService.printProjectPrices();
////        for (ProjectPrices p:list_pr) {
////            System.out.println(p.toString());
////        }
////        System.out.println();
////
////        List <YoungestEldestWorker> list_yew = databaseQueryService.findYoungestEldestWorkers();
////        for (YoungestEldestWorker ye:list_yew) {
////            System.out.println(ye.toString());
////        }
////        System.out.println();
////
////        List <MaxSalaryWorker> list_msw = databaseQueryService.findMaxSalaryWorker();
////        for (MaxSalaryWorker ms: list_msw) {
////            System.out.println(ms.toString());
////        }
////        System.out.println();
////
////        List <MaxProjectsClient> list_mpc = databaseQueryService.findMaxProjectsClient();
////        for (MaxProjectsClient mp: list_mpc) {
////            System.out.println(mp.toString());
////        }
////        System.out.println();
////
////        List <LongestProject> list_lp = databaseQueryService.findLongestProject();
////        for (LongestProject lp: list_lp) {
////            System.out.println(lp.toString());
////        }
//        System.out.println(databaseQueryService.findYoungestEldestWorkers());
//    }

    public List <ProjectPrices> printProjectPrices() throws IOException {
        sql = Files.readString(Path.of("D:\\go\\goit-dev-hw5_Advanced_JDBC-main\\goit-dev-hw5_Advanced_JDBC-main\\sql\\print_project_prices.sql"));

        List <ProjectPrices> list_pr = new ArrayList<>();

        try {
            select = connection.prepareStatement(sql);
            rs = select.executeQuery();
            while (rs.next()){
                ProjectPrices pr = new ProjectPrices(rs.getString("name"), rs.getInt("price"));
                list_pr.add(pr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_pr;
    }

    public List <YoungestEldestWorker> findYoungestEldestWorkers() throws IOException {
        sql = Files.readString(Path.of("D:\\go\\goit-dev-hw5_Advanced_JDBC-main\\goit-dev-hw5_Advanced_JDBC-main\\sql\\find_youngest_eldest_workers.sql"));

        List <YoungestEldestWorker> list_yew = new ArrayList<>();

        try {
            select = connection.prepareStatement(sql);
            rs = select.executeQuery();
            while (rs.next()){
                YoungestEldestWorker pr = new YoungestEldestWorker(
                        rs.getString("TYPE"),
                        rs.getString("name"),
                        rs.getDate("birthday"));
                list_yew.add(pr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_yew;
    }

    public List <MaxSalaryWorker> findMaxSalaryWorker () throws IOException {
        sql = Files.readString(Path.of("D:\\go\\goit-dev-hw5_Advanced_JDBC-main\\goit-dev-hw5_Advanced_JDBC-main\\sql\\find_max_salary_worker.sql"));

        List <MaxSalaryWorker> maxSal = new ArrayList<>();

        try {
            select = connection.prepareStatement(sql);
            rs = select.executeQuery();

            while (rs.next()){
                MaxSalaryWorker fmsw = new MaxSalaryWorker(rs.getString("name"), rs.getInt("salary"));
                maxSal.add(fmsw);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return maxSal;
    }

    public List <MaxProjectsClient> findMaxProjectsClient() throws IOException {
        sql = Files.readString(Path.of("D:\\go\\goit-dev-hw5_Advanced_JDBC-main\\goit-dev-hw5_Advanced_JDBC-main\\sql\\find_max_projects_client.sql"));

        List <MaxProjectsClient> list_mpc = new ArrayList<>();

        try {
            select = connection.prepareStatement(sql);
            rs = select.executeQuery();

            while (rs.next()){
                MaxProjectsClient maxProjectsClient = new MaxProjectsClient(
                        rs.getString("name"),
                        rs.getInt("PROJECT_COUNT"));
                list_mpc.add(maxProjectsClient);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list_mpc;
    }

    public List <LongestProject> findLongestProject() throws IOException {
        sql = Files.readString(Path.of("D:\\go\\goit-dev-hw5_Advanced_JDBC-main\\goit-dev-hw5_Advanced_JDBC-main\\sql\\find_longest_project.sql"));

        List <LongestProject> list_lp = new ArrayList<>();

        try {
            select = connection.prepareStatement(sql);
            rs = select.executeQuery();

            while (rs.next()){
                LongestProject longestProject = new LongestProject(
                        rs.getString("name"),
                        rs.getInt("MONTH_COUNT"));
                list_lp.add(longestProject);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list_lp;
    }
}

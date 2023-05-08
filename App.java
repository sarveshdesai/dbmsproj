import java.sql.*;

public class App {

    public static void main(String[] args) throws SQLException {

        try {

            Class.forName("com.mysql.jdbc.Driver"); // jdbc

        } catch (ClassNotFoundException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/v_project", "root", "1234"); // connection
                                                                                                                // object

        conn.setAutoCommit(false); // for atomicty

        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); // isolation

        Statement stmt1 = null;

        try {

            stmt1 = conn.createStatement();

            stmt1.executeUpdate("DELETE FROM `v_project`.`depot` WHERE (`dep_id` = 'd1')"); // 2

            // stmt1.executeUpdate("UPDATE `v_project`.`depot` SET dep_id = 'dd1' WHERE
            // (`dep_id` = 'd1')"); //4

            // stmt1.executeUpdate("INSERT INTO `v_project`.`depot` VALUES ('d100',
            // 'Chicago', 100)"); // 6
            // stmt1.executeUpdate("INSERT INTO `v_project`.`stock` VALUES ('p1', 'd100',
            // 100)"); // 6

            // stmt1.executeUpdate("DELETE FROM `v_project`.`depot` WHERE (`dep_id` =
            // 'd100')"); // reverse
            // stmt1.executeUpdate("DELETE FROM `v_project`.`depot` WHERE (`dep_id` =
            // 'dd1')"); //reverse
            // stmt1.executeUpdate("DELETE FROM `v_project`.`stock` WHERE (`dep_id` =
            // 'd100')"); //reverse

        } catch (SQLException e) {

            System.out.println("An exception was thrown");

            e.printStackTrace();

            conn.rollback();

            stmt1.close();

            conn.close();

            return;

        }

        conn.commit();

        stmt1.close();

        conn.close();

        System.out.println("Statement executed!");
    }
}
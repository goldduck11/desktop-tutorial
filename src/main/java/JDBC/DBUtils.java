package JDBC;

import User.Quastion;
import User.UserAccount;

import java.io.StringReader;
import java.net.Inet4Address;
import java.sql.*;


public class DBUtils {

    public static UserAccount findUser(Connection conn, //
                                       String userName, String password) throws SQLException {

        String sql = "Select a.User_Name, a.Password from new_schema.user_account a " //
                + " where a.User_Name = ? and a.password= ? ";


        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            return user;
        }
        return null;
    }

    public static UserAccount findUser(Connection conn, String userName) throws SQLException {

        String sql = "Select a.User_Name, a.primary_id" +
                " from new_schema.user_account a "//
                + " where a.User_Name = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String password = rs.getString("Password");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            return user;
        }
        return null;
    }

    public static boolean CreateUserOnBD(Connection connection, //
                                         String userName, String password,
                                         String Quastion, String selection) throws SQLException {
        String sql;
        PreparedStatement pstm;
        try {
            sql = "INSERT INTO new_schema.user_account(user_name, password)" +
                    "values (?,?)";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, userName);
            pstm.setString(2, password);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Пользователь существует");
        }

        if (selection.equalsIgnoreCase("Date")) {

            try {
                sql = "insert into new_schema.quastion(date,primary_id) " +
                        "values (?, LAST_INSERT_ID())";
                pstm = connection.prepareStatement(sql);
                pstm.setString(1, Quastion);
                pstm.executeUpdate();
                return true;
            } catch (SQLException ex) {
                System.out.println("Заполнено");
            }
        }
        if (selection.equalsIgnoreCase("Dog name")) {
            try {
                sql = "insert into new_schema.quastion(Dog_Name,primary_id) " +
                        "values (?, LAST_INSERT_ID())";
                pstm = connection.prepareStatement(sql);
                pstm.setString(1, Quastion);
                pstm.executeUpdate();
                return true;
            } catch (SQLException ex) {
                System.out.println("Заполнено");
            }
        }
        if (selection.equalsIgnoreCase("Pin-Code")) {
            try {
                sql = "insert into new_schema.quastion(Pin_Code,primary_id) " +
                        "values (?, LAST_INSERT_ID())";
                pstm = connection.prepareStatement(sql);
                pstm.setString(1, Quastion);
                pstm.executeUpdate();
                return true;
            } catch (SQLException ex) {
                System.out.println("Заполнено");
            }
        }
        if (selection.equalsIgnoreCase("Non-Target Name")) {

            try {
                sql = "insert into new_schema.quastion(Non_Target_Name,primary_id) " +
                        "values (?, LAST_INSERT_ID())";
                pstm = connection.prepareStatement(sql);
                pstm.setString(1, Quastion);
                pstm.executeUpdate();
                return true;
            } catch (SQLException ex) {
                System.out.println("Заполнено");
            }
        }

        return false;
    }

    public static UserAccount checkQuastion(Connection connection, //
                                            String userName, String Quastion) throws SQLException {

        String sql = "SELECT * FROM new_schema.quastion " +
                "JOIN new_schema.user_account " +
                "ON USER_NAME = '" + userName + "'" + " and new_schema.user_account.primary_id = new_schema.quastion.primary_id " +
                "WHERE quastion.date IS NOT NULL or quastion.Dog_Name IS NOT NULL" +
                " or quastion.Non_Target_Name IS NOT null or quastion.Pin_Code IS NOT NULL";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            Date date = resultSet.getDate("date");
            System.out.println(date);
            String dog_name = resultSet.getString("Dog_Name");
            System.out.println(dog_name);
            Integer pin_code = resultSet.getInt("Pin_Code");
            System.out.println(pin_code);
            String Non_Target_Name = resultSet.getString("Non_Target_Name");
            System.out.println(Non_Target_Name);
            if (Quastion.equalsIgnoreCase(date.toString())) {
                UserAccount user = new UserAccount();
                user.setUserName(userName);
                user.setQuastion(Quastion);
                return user;
            }
            if (Quastion.equalsIgnoreCase(dog_name)) {
                UserAccount user = new UserAccount();
                user.setUserName(userName);
                user.setQuastion(Quastion);
                return user;
            }
            if (Quastion.equalsIgnoreCase(pin_code.toString())) {
                UserAccount user = new UserAccount();
                user.setUserName(userName);
                user.setQuastion(Quastion);
                return user;
            }
            if (Quastion.equalsIgnoreCase(Non_Target_Name)) {
                UserAccount user = new UserAccount();
                user.setUserName(userName);
                user.setQuastion(Quastion);
                return user;
            }
        }
        return null;

    }

}
import javax.swing.*;
import java.sql.*;


public class InsertApp {
    int lastID = 0;
    String currentCustomerID = null;

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:src/pizzaDB.sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public void insert(String name, String phone, String address, String city, String state, String zip) {
        String sql = "INSERT INTO Customer(Name, phone, Address, City, State, Zip) VALUES(?,?,?,?,?,?)";

        try  {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, address);
            pstmt.setString(4, city);
            pstmt.setString(5, state);
            pstmt.setString(6, zip);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public String search(String phoneNumber) throws SQLException{

        PreparedStatement preparedStatement = null;

        String sql = "SELECT phone, Name, Address, City, State, Zip FROM Customer WHERE phone = ?";

        try {
            Connection conn = connect();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,phoneNumber);

            // execute select SQL statement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                String name = rs.getString("Name");
                String phone = rs.getString("phone");
                String Address = rs.getString("Address");
                String City = rs.getString("City");
                String State = rs.getString("State");
                String Zip = rs.getString("Zip");

                System.out.println(name + ", " + phone + ", " + Address + ", " + City + ", " + State + ", " + Zip);
                currentCustomerID = phone;
                return (phone + ", " + name + ", " + Address + ", " + City + ", " + State + ", " + Zip);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connect() != null) {
                connect().close();
            }

        }

        return "no results";
    }
    public int getOrderID(){
        PreparedStatement pstmt = null;
        String sql = "SELECT MAX(orderID) as lastID FROM Receipt";
        try {
            Connection conn = connect();
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                lastID = rs.getInt("orderID");
                System.out.println("last Id: " + lastID);
                return lastID;
            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
        return lastID;
    }

    public static void main(String[] args) {

    }

    public String getCurrentCustomerID() {
        return currentCustomerID;
    }

    public void setCurrentCustomerID(String currentCustomerID) {
        this.currentCustomerID = currentCustomerID;
    }
}
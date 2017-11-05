import javax.swing.*;
import java.sql.*;


public class InsertApp {
    int lastID = 0;
    String currentCustomerID = null;

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:src/pizzaDB.sqlite";
        Connection conn = null;
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
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
    public void addCart(String customerID, int orderId, String size, int toppings, boolean drink, double cost) {
        String sql = "INSERT INTO Receipt(customerID, orderId, size, toppings, drink, cost) VALUES(?,?,?,?,?,?)";

        try  {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, currentCustomerID);
            pstmt.setInt(2, lastID);
            pstmt.setString(3, size);
            pstmt.setInt(4, toppings);
            pstmt.setBoolean(5, drink);
            pstmt.setDouble(6, cost);

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
        Statement stmt = null;
        String sql = "SELECT MAX(orderId) as lastID FROM Receipt";
        try {
            Connection conn = connect();
            //pstmt = conn.prepareStatement(sql);
            stmt = conn.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                //lastID = rs.getInt("size");
                lastID = rs.getInt(1);
                System.out.println("last Id: " + lastID);
                lastID++;
                return lastID;
            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
        return lastID;
    }

    public static void main(String[] args) {

    }

    public int getLastID() {
        return lastID;
    }

    public void setLastID(int lastID) {
        this.lastID = lastID;
    }

    public String getCurrentCustomerID() {
        return currentCustomerID;
    }

    public void setCurrentCustomerID(String currentCustomerID) {
        this.currentCustomerID = currentCustomerID;
    }
}
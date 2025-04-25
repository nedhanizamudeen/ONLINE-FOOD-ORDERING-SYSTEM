import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.ofos.util.DBConnection;

public class TestDB {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Connected successfully!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MenuItem");

            while (rs.next()) {
                System.out.println("MenuID: " + rs.getInt("MenuID") + 
                                   ", Restaurant_ID: " + rs.getInt("Restaurant_ID") + 
                                   ", FoodID: " + rs.getInt("FoodID"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

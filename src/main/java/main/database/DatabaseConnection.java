package main.database;
import java.io.IOException;
import java.security.cert.Extension;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DatabaseConnection {
    private String URL = "jdbc:mysql://localhost:3306/edict";
    private String USER = "root";
    private String PASSWORD = "123456";
    private Connection connection;
    private ArrayList<String> searchHint = new ArrayList<>();
    public DatabaseConnection(String URL, String USER, String PASSWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getURL() {
        return URL;
    }

    public String getUSERNAME() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void connectToDatabase() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> SearchHint() {
            return searchHint;
    }

    public void HintSearch(){
        for (String e : searchHint) {
            System.out.println(e + '\n');
        }
    }

    public void disconnectToDatabase() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String querySearch(String text) {
        String resultOfQuery = "";
        try (PreparedStatement detailStatement = connection.prepareStatement("SELECT detail FROM tbl_edict WHERE word = ?")) {
            detailStatement.setString(1, text);
            ResultSet detailResultSet = detailStatement.executeQuery();

            if (detailResultSet.next()) {
                resultOfQuery = detailResultSet.getString("detail");
            }

            // close resultSet
            detailResultSet.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi ở đây, ví dụ in thông báo lỗi
        }

        try (PreparedStatement hintStatement = connection.prepareStatement("SELECT word FROM tbl_edict WHERE word LIKE ? LIMIT 100")) {
            hintStatement.setString(1, text + "%");
            ResultSet hintResultSet = hintStatement.executeQuery();

            while (hintResultSet.next()) {
                searchHint.add(hintResultSet.getString("word"));
            }

            // close resultSet
            hintResultSet.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi ở đây, ví dụ in thông báo lỗi
        }

        /*try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT detail FROM tbl_edict WHERE word = '" + text + "'");
            // check next record
            if (resultSet.next()) {
                resultOfQuery = resultSet.getString("detail");
            }
            resultSet.close();
            ResultSet Hint = statement.executeQuery("SELECT word FROM tbl_edict WHERE word LIKE '" + text + "%'");
            while (Hint.next()) {
                searchHint.add(Hint.getString("word"));
                if(searchHint.size()>100) break;
            }
            // close resultSet
            Hint.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return resultOfQuery;
    }

    public static void main(String[] args) {
        try{
            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/edict","root","123456");

            DatabaseConnection databaseConnection = new DatabaseConnection("jdbc:mysql://localhost:3306/edict", "root", "123456");
            databaseConnection.connectToDatabase(); // Kết nối đến cơ sở dữ liệu
            String searchQuery = databaseConnection.querySearch("an");

            if (!searchQuery.isEmpty()) {
                //databaseConnection.HintSearch();
            }

            // Sau khi hoàn thành công việc, đảm bảo ngắt kết nối
            databaseConnection.disconnectToDatabase();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}

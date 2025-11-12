import com.google.gson.JsonArray;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLStarDataRetriever implements StarDataRetriever {

    private static final String SELECT_ALL_STARS = "SELECT * FROM stars";

    private final DataSource dataSource;

    MySQLStarDataRetriever() {
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/moviedbexample");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonArray RetrieveStarData() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_STARS)) {
            JsonArray jsonArray = new JsonArray();
            while (resultSet.next()) {
                String starId = resultSet.getString("id");
                String starName = resultSet.getString("name");
                String starBirthYear = resultSet.getString("birthYear");
                StarPojo starPojo = StarPojo.builder()
                        .setStarBirthYear(starBirthYear)
                        .setStarName(starName)
                        .setStarId(starId);
                jsonArray.add(starPojo.toJson());
            }
            return jsonArray;
        }
    }
}

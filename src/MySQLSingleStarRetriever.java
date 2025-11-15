import com.google.gson.JsonArray;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLSingleStarRetriever implements SingleStarRetriever {

    String SELECT_MOVIE_BY_ID =
              "SELECT * " +
              "FROM " +
              "  stars, " +
              "  stars_in_movies, " +
              "  movies " +
              "WHERE " +
              "  movies.id = stars_in_movies.movieId " +
              "  AND stars_in_movies.starId = stars.id " +
              "  AND stars.id = ?";

    private DataSource dataSource;

    MySQLSingleStarRetriever() {
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/moviedbexample");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JsonArray getSingleStar(String starId) throws SQLException {
        JsonArray jsonArray = new JsonArray();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_MOVIE_BY_ID);) {
            statement.setString(1, starId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String starName = rs.getString("name");
                    String starDob = rs.getString("birthYear");
                    String movieId = rs.getString("movieId");
                    String movieTitle = rs.getString("title");
                    String movieYear = rs.getString("year");
                    String movieDirector = rs.getString("director");
                    SingleStarPojo singleStarPojo =
                            SingleStarPojo.builder()
                                    .setStarName(starName)
                                    .setStarBirthYear(starDob)
                                    .setMovieId(movieId)
                                    .setMovieTitle(movieTitle)
                                    .setMovieYear(movieYear)
                                    .setMovieDirector(movieDirector)
                                    .setStarId(starId);
                    jsonArray.add(singleStarPojo.toJson());
                }
            }
        }
        return jsonArray;
    }
}

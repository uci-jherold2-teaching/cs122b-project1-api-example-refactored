import com.google.gson.JsonArray;

import java.sql.SQLException;

public interface SingleStarRetriever {
    JsonArray getSingleStar(String starId) throws SQLException;
}

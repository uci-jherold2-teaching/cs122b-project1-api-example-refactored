import com.google.gson.JsonArray;

import java.sql.SQLException;

public interface StarDataRetriever {
    JsonArray RetrieveStarData() throws SQLException;
}

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "StarsServlet", urlPatterns = "/api/stars")
public class StarsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    StarDataRetriever starsRetriever;

    public void init(ServletConfig config) {
        this.starsRetriever = new MySQLStarDataRetriever();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        JsonArray jsonArray = getJson(response);
        int numResults = jsonArray == null ? 0 : jsonArray.size();
        request.getServletContext().log("getting " + numResults + " results");
        try(PrintWriter out = response.getWriter()) {
            String jsonString = jsonArray == null ? "" : jsonArray.toString();
            out.write(jsonString);
        }
        response.setStatus(200);
    }

    private JsonArray getJson(HttpServletResponse response) throws IOException {
        JsonArray jsonArray = null;
        try {
            jsonArray = starsRetriever.RetrieveStarData();
        } catch (SQLException e) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("errorMessage", e.getMessage());
            try(PrintWriter out = response.getWriter()) {
                out.write(jsonObject.toString());
            }
            response.setStatus(500);
        }
        return jsonArray;
    }
}

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

@WebServlet(name = "SingleStarServlet", urlPatterns = "/api/single-star")
public class SingleStarServlet extends HttpServlet {
    private SingleStarRetriever  singleStarRetriever;

    public void init(ServletConfig config) {
        this.singleStarRetriever = new MySQLSingleStarRetriever();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json"); // Response mime type
        String id = request.getParameter("id");
        request.getServletContext().log("getting id: " + id);
        try {
            JsonArray jsonArray = singleStarRetriever.getSingleStar(id);
            try (PrintWriter out = response.getWriter()) {
                out.write(jsonArray.toString());
            }
            response.setStatus(200);
        }  catch (SQLException e) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("errorMessage", e.getMessage());
            try (PrintWriter out = response.getWriter()) {
                out.write(jsonObject.toString());
            }
            request.getServletContext().log("Error:", e);
            response.setStatus(500);
        }
    }
}

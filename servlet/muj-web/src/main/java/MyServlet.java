
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.servlet.ServletException;

@WebServlet("/timezone")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=utf-8");
        PrintWriter out = res.getWriter();
        String input = req.getParameter("zone");
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        boolean isValid = false;
        for (String str : zoneIds) {
            if (str != null && str.equals(input)) {
                isValid = true;
                break;
            }
        }
        String info = "Wrong time zone";
        if (isValid) {
            ZoneId zone = ZoneId.of(input);
            ZonedDateTime now = ZonedDateTime.now(zone);
            info = "Time: " + now;
        }
        out.println("<html><body>");
        out.println(info);
        out.println("</body></html>");
    }
}

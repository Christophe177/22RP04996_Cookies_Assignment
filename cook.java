package cook;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cook")
public class cook extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usernameOrEmail = "";

        // Retrieve the cookie value if it exists
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("usernameOrEmail".equals(cookie.getName())) {
                    usernameOrEmail = cookie.getValue();
                    break;
                }
            }
        }

        // Generate the HTML response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'><title>Username/Email Form</title></head>");
        out.println("<body>");
        out.println("<h1>Enter your Username or Email</h1>");
        out.println("<form action='StoreValueServlet' method='POST'>");
        out.println("<label for='userInput'>Username/Email:</label>");
        out.println("<input type='text' id='userInput' name='userInput' value='" + usernameOrEmail + "'>");
        out.println("<button type='submit'>Submit</button>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userInput = request.getParameter("userInput");

        // Store the input in a cookie
        Cookie cookie = new Cookie("usernameOrEmail", userInput);
        cookie.setMaxAge(60 * 60 * 24); // 1 day
        response.addCookie(cookie);

        // Redirect back to the form page (GET request)
        response.sendRedirect("cook.jsp");
    }
}


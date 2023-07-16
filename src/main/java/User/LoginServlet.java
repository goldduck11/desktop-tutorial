package User;

import JDBC.DBUtils;
import Server.MyUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    // Показать страницу Login.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/Login.html");

        dispatcher.forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("login");
        String password = request.getParameter("password");

        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;

        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Найти user в DB.
                user = DBUtils.findUser(conn, userName, password);

                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        if (hasError) {
            user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);

            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/Login.html");

            dispatcher.forward(request, response);
        }
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
            MyUtils.storeUserCookie(response, user);
            response.sendRedirect(request.getContextPath() + "/question");
        }
    }

}

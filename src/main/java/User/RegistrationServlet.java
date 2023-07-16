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

@WebServlet(urlPatterns = { "/Registration"})
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegistrationServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/Registration.html");

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("login");
        String password = request.getParameter("password");
        String select = request.getParameter("selection");
        String quastion = request.getParameter("Question");

        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;

        if (userName == null && password == null && userName.length() == 0 && password.length() == 0 &&
         quastion == null && quastion.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Найти user в DB.
                boolean flag = DBUtils.CreateUserOnBD(conn, userName, password, quastion,select);
                System.out.println(flag);
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // В случае, если есть ошибка,
        // forward (перенаправить) к /WEB-INF/views/login.jsp
        if (hasError) {
            user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);

            // Сохранить информацию в request attribute перед forward.
            /*request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);*/

            // Forward (перенаправить) к странице /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/Registration.html");

            dispatcher.forward(request, response);
        }
        // В случае, если нет ошибки.
        // Сохранить информацию пользователя в Session.
        // И перенаправить к странице userInfo.
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
            // Если пользователь выбирает функцию "Remember me".

            // Redirect (Перенаправить) на страницу /userInfo.
            response.sendRedirect(request.getContextPath() + "/home");
        }

    }

}


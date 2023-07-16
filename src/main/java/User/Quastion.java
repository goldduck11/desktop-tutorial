package User;

import JDBC.DBUtils;
import Server.MyUtils;

import java.io.*;
import java.net.CookieManager;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.CookieParam;

import static java.nio.file.Paths.get;


@WebServlet(urlPatterns = {"/question"})
public class Quastion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Quastion() {
        super();
    }

    // Показать страницу Login.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Forward (перенаправить) к странице /WEB-INF/views/loginView.jsp
        // (Пользователь не может прямо получить доступ
        // к страницам JSP расположенные в папке WEB-INF).
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/question.html");

        dispatcher.forward(request, response);

    }

    // Когда пользователь вводит userName & password, и нажимает Submit.
    // Этот метод будет выполнен.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String question = request.getParameter("question");
        /*String userName = Files.readString(get("login.txt"));*/
        String userName = MyUtils.getUserNameInCookie(request);
        System.out.println(userName);
        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;


        if (question == null || question.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Найти user в DB.
                user = DBUtils.checkQuastion(conn, String.valueOf(userName), question);

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
        // В случае, если есть ошибка,
        // forward (перенаправить) к /WEB-INF/views/login.jsp
        if (hasError) {

            // Сохранить информацию в request attribute перед forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);


            // Forward (перенаправить) к странице /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/question.html");

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
            response.sendRedirect(request.getContextPath() + "/add.jsp");
        }
    }

}

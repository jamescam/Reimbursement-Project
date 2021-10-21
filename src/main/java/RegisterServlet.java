import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

//Servlet that handles registration requests.
public class RegisterServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(RegisterServlet.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        EmployeeDAOImpl EmployeeDAO = null;

        try {
            EmployeeDAO = new EmployeeDAOImpl();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }

        String emailParam = request.getParameter("email");
        String passwordParam = request.getParameter("password");

        //grabs the domain portion of a user's email.
        String split = emailParam.substring(emailParam.indexOf("@") + 0);

        /*Compares the email's domain portion with the domain portion of a manager's email.
         * If true, then the user is a manager.
         * If false, then the user is an employee.
         */
        if (split.equals("@manager.com")) {

            UserRole userType = UserRole.MANAGER;

            try {
                EmployeeDAO.update(emailParam, passwordParam, userType);
            } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
                logger.error(e.getMessage(), e);
            }

        } else {
            UserRole userType = UserRole.EMPLOYEE;
            try {
                EmployeeDAO.update(emailParam, passwordParam, userType);
            } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}

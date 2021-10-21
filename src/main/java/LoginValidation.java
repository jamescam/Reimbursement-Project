import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;

//Class used to validate login password
public class LoginValidation {

    //Retrieve the hashed password from the database based on user's email
    private String hashedPasswordRetrieval(String email) throws SQLException, ClassNotFoundException {

        DataBaseConnection db = new DataBaseConnection();
        Connection conn = db.establishConnection();
        EmployeeDAOImpl EmployeeDAO = new EmployeeDAOImpl();

        String hashedPassword = EmployeeDAO.findHashedPassword(email);
        conn.close();

        return hashedPassword;

    }

    //Retrieve the salt  from the database based on user's email
    private String saltRetrieval(String email) throws SQLException, ClassNotFoundException {

        DataBaseConnection db = new DataBaseConnection();

        Connection conn = db.establishConnection();
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        String salt = employeeDAO.findSalt(email);

        conn.close();

        return salt;
    }

    //The method that does the password validation. Returns a Boolean.
    public boolean isPassword(String email, String plainTextPass, HttpServletResponse response) throws SQLException, NoSuchAlgorithmException, ClassNotFoundException {

        LoginValidation loginvalidation = new LoginValidation();

        String salt = loginvalidation.saltRetrieval(email);

        /*If an email does not exist, the salt will equal null,
         *and the method will set the response status code to 401
         * to restrict access of a user without registration credentials.
         */
        if(salt == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }else {

            byte[] decodedSalt = Base64.getDecoder().decode(salt);

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(decodedSalt);

            byte[] hashedPassword = md.digest(plainTextPass.getBytes(StandardCharsets.UTF_8));

            String stringEncodedHashedPassword = Base64.getEncoder().encodeToString(hashedPassword);
            String passwordRetrievedFromDB = loginvalidation.hashedPasswordRetrieval(email);

            boolean isEqual = stringEncodedHashedPassword.equals(passwordRetrievedFromDB);

            return isEqual;
        }
    }

}
import org.junit.jupiter.api.Test;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginValidationTest {

    @Test
    public void isPasswordTest() throws SQLException, NoSuchAlgorithmException, ClassNotFoundException {
        LoginValidation loginvalidation = new LoginValidation();
        HttpServletResponse response = null;
        Boolean expected = true;
        Boolean actual = loginvalidation.isPassword("example11@manager.com", "example", response);
        assertEquals(expected, actual);
    }
}
import com.reimbursement.data.EmployeeDAOImpl;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeDAOTest {

    @Test
    public void findAllTest() throws SQLException, ClassNotFoundException {
        EmployeeDAOImpl EmployeeDAO = new EmployeeDAOImpl();

        List actual = EmployeeDAO.findAll();

        assertNotNull(actual);

    }

    @Test
    public void findHashedPasswordTest() throws SQLException, ClassNotFoundException {
        EmployeeDAOImpl EmployeeDAO = new EmployeeDAOImpl();

        String actual = EmployeeDAO.findHashedPassword("example11@manager.com");
        String expected = "LYoxUdLZNkRtNASU/vy127kkvINwR12Bb8afOr72aAOKtk0sLjKXzzneQoXeUxA2V+LuHb6MS+DX2C+JV6jkyQ==";

        assertNotNull(actual, expected);

    }

    @Test
    public void findUserIDTest() throws SQLException, ClassNotFoundException {
        EmployeeDAOImpl EmployeeDAO = new EmployeeDAOImpl();

        Long actual = EmployeeDAO.findUserID("example11@manager.com");
        Long expected = 44L;

        assertEquals(actual, expected);

    }

    @Test
    public void findSaltTest() throws SQLException, ClassNotFoundException {
        EmployeeDAOImpl EmployeeDAO = new EmployeeDAOImpl();

        String actual = EmployeeDAO.findSalt("example11@manager.com");
        String expected = "LzuGVnXy7qJyJC2r1Fy2yQ==";

        assertEquals(actual, expected);

    }

}
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {
    @Test
    public void employeeListTest(){

        Employee employee = new Employee();

        List list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        employee.setEmployeeList(list);

        List expected = list;
        List actual = employee.getEmployeeList();
        assertEquals(expected, actual);
    }


    @Test
    public void userIDTest(){
        Employee employee = new Employee();
        employee.setUserID(34L);
        Long expected = 34L;
        Long actual = employee.getUserID();
        assertEquals(expected, actual);


    }
}

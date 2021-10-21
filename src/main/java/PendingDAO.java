import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Data Access Object used to do CRUD operations on the Pending table.
public interface PendingDAO {
    void update(String userID, String purchaseDate, String description, String total, String status) throws SQLException, ClassNotFoundException;
    void delete(String userID) throws SQLException, ClassNotFoundException;
    ArrayList<String> findID(String pending_id) throws SQLException, ClassNotFoundException;
    List<Map<Pending, Pending>> findAllPendingReimbursements() throws SQLException, ClassNotFoundException;
    List<Map<Pending, Pending>> findPendingReimbursements(Object userID) throws SQLException, ClassNotFoundException;
}

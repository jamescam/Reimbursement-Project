import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ReimbursementDAOImpl implements ReimbursementDAO{

    DataBaseConnection dbc = new DataBaseConnection();
    Reimbursement reimbursement = new Reimbursement();
    EmployeeDAOImpl EmployeeDAO = new EmployeeDAOImpl();

    public ReimbursementDAOImpl() throws SQLException, ClassNotFoundException {
    }

    //Method used to insert data into the database table with approved reimbursements.
    public void updateApprovedReimbursements(String reimbursementID, String purchaseDate, String description, String total, String status, String email) throws SQLException, ClassNotFoundException {

        Connection conn = dbc.establishConnection();

        PreparedStatement ps = conn.prepareStatement("insert into approved_reimbursements(reimbursement_id, purchase_date, description, total_amount, status, manager_email) values (?, ?, ?, ?, ?, ?)");
        ps.setInt(1, Integer.parseInt(reimbursementID));
        ps.setString(2, purchaseDate);
        ps.setString(3, description);
        ps.setString(4, total);
        ps.setString(5, status);
        ps.setString(6, email);
        ps.executeUpdate();
        conn.close();


    }

    //Method used to insert data into the database table with rejected reimbursements.
    public void updateRejectedReimbursements(String reimbursementID, String purchaseDate, String description, String total, String status, String email) throws SQLException, ClassNotFoundException {

        Connection conn = dbc.establishConnection();

        PreparedStatement ps = conn.prepareStatement("insert into rejected_reimbursements(reimbursement_id, purchase_date, description, total_amount, status, manager_email) values (?, ?, ?, ?, ?, ?)");
        ps.setInt(1, Integer.parseInt(reimbursementID));
        ps.setString(2, purchaseDate);
        ps.setString(3, description);
        ps.setString(4, total);
        ps.setString(5, status);
        ps.setString(6, email);
        ps.executeUpdate();
        conn.close();


    }

    //method used to retrieve all reimbursements based on a foreign key.
    public List<Map<Reimbursement, Reimbursement>> findReimbursement(String email) throws SQLException, ClassNotFoundException {

        Connection conn = dbc.establishConnection();
        Long UserID =  EmployeeDAO.findUserID(email);

        PreparedStatement ps = conn.prepareStatement("select * from reimbursement where reimbursement_id = ?");
        ps.setLong(1, UserID);
        ResultSet rs = ps.executeQuery();
        List<Map<Reimbursement, Reimbursement>> list = new ArrayList<Map<Reimbursement, Reimbursement>>();

        ResultSetMetaData meta = rs.getMetaData();

        while (rs.next()) {
            Map map = new TreeMap();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                String key = meta.getColumnName(i);
                String value = rs.getString(key);
                map.put(key, value);
            }
            list.add(map);
            reimbursement.setList(list);
            conn.close();

        }
        return list;
    }

    //method used to retrieve a single employee's rejected reimbursements.
    public List<Map<Reimbursement, Reimbursement>> findRejected(String email) throws SQLException, ClassNotFoundException {
        Connection conn = dbc.establishConnection();
        Long UserID = EmployeeDAO.findUserID(email);

        PreparedStatement ps = conn.prepareStatement("select * from reimbursement where reimbursement_id = ?");
        ps.setLong(1, UserID);
        ResultSet rs = ps.executeQuery();
        List<Map<Reimbursement, Reimbursement>> list = new ArrayList<Map<Reimbursement, Reimbursement>>();

        ResultSetMetaData meta = rs.getMetaData();

        while (rs.next()) {
            Map map = new TreeMap();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                String key = meta.getColumnName(i);
                String value = rs.getString(key);
                map.put(key, value);
            }
            list.add(map);
            reimbursement.setList(list);
            conn.close();

        }
        return list;
    }

    //Retrieve all reimbursements
    public List<Map<Reimbursement, Reimbursement>> findAllReimbursement() throws SQLException, ClassNotFoundException {
        DataBaseConnection db = new DataBaseConnection();
        Connection conn = db.establishConnection();
        PreparedStatement ps = conn.prepareStatement("(select * from reimbursement)");
        ResultSet rs = ps.executeQuery();

        ResultSetMetaData meta = rs.getMetaData();
        List<Map<Reimbursement, Reimbursement>> list = new ArrayList<Map<Reimbursement, Reimbursement>>();

        while (rs.next()) {
            Map map = new TreeMap();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                String key = meta.getColumnName(i);
                String value = rs.getString(key);
                map.put(key, value);
            }
            list.add(map);
        }
        conn.close();
        return list;

    }

    //Retrieve all reimbursements that have been approved.
    public List<Map<Reimbursement, Reimbursement>> findAllApprovedReimbursements() throws SQLException, ClassNotFoundException {

        DataBaseConnection db = new DataBaseConnection();
        Connection conn = db.establishConnection();

        Status state = Status.APPROVED;
        PreparedStatement ps = conn.prepareStatement("(select * from reimbursement where status = ?)");
        ps.setString(1, state.toString());
        ResultSet rs = ps.executeQuery();

        ResultSetMetaData meta = rs.getMetaData();
        List<Map<Reimbursement, Reimbursement>> list = new ArrayList<Map<Reimbursement, Reimbursement>>();

        while (rs.next()) {
            Map map = new TreeMap();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                String key = meta.getColumnName(i);
                String value = rs.getString(key);
                map.put(key, value);
            }
            list.add(map);
        }
        conn.close();
        return list;
    }

    //Retrieve all reimbursements that have been rejected.
    public List<Map<Reimbursement, Reimbursement>> findAllRejectedReimbursements() throws SQLException, ClassNotFoundException {

        DataBaseConnection db = new DataBaseConnection();
        Connection conn = db.establishConnection();

        Status state = Status.REJECTED;
        PreparedStatement ps = conn.prepareStatement("(select * from reimbursement where status = ?)");
        ps.setString(1, state.toString());
        ResultSet rs = ps.executeQuery();

        ResultSetMetaData meta = rs.getMetaData();
        List<Map<Reimbursement, Reimbursement>> list = new ArrayList<Map<Reimbursement, Reimbursement>>();

        while (rs.next()) {
            Map map = new TreeMap();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                String key = meta.getColumnName(i);
                String value = rs.getString(key);
                map.put(key, value);
            }
            list.add(map);
        }
        conn.close();
        return list;
    }

}

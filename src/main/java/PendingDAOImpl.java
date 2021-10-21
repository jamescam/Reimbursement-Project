import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//Concrete class that implements the EmployeeDAO, so that we can do CRUD operations on our accounts table.
public class PendingDAOImpl implements PendingDAO {

    @Override
    public void update(String userID, String purchaseDate, String description, String total, String status) throws SQLException, ClassNotFoundException {
        DataBaseConnection dbc = new DataBaseConnection();
        Connection conn = dbc.establishConnection();

        PreparedStatement ps = conn.prepareStatement("insert into pending(pending_id, purchase_date, description, total_amount, status) values (?, ?, ?, ?, ?)");
        ps.setInt(1, Integer.parseInt(userID));
        ps.setString(2, purchaseDate);
        ps.setString(3, description);
        ps.setString(4, total);
        ps.setString(5, status);
        ps.executeUpdate();
        conn.close();

    }

    @Override
    public ArrayList<String> findID(String pending_id) throws SQLException, ClassNotFoundException {
        DataBaseConnection db = new DataBaseConnection();
        Connection conn = db.establishConnection();
        PreparedStatement ps = conn.prepareStatement("select id from pending where pending_id = ?");
        ps.setInt(1, Integer.parseInt(pending_id));
        ResultSet rs = ps.executeQuery();

        ResultSetMetaData meta = rs.getMetaData();
        ArrayList<String> arrList = new ArrayList<String>();

        while (rs.next()) {

            for (int i = 1; i <= meta.getColumnCount(); i++) {
                String key = meta.getColumnName(i);
                String value = rs.getString(key);
                arrList.add(value);
            }
        }
        conn.close();
        return arrList;
    }

    @Override
    public void delete(String userID) throws SQLException, ClassNotFoundException {
        DataBaseConnection dbc = new DataBaseConnection();
        Connection conn = dbc.establishConnection();

        PreparedStatement ps = conn.prepareStatement("delete from pending where id = ?");
        ps.setLong(1, Long.parseLong(userID));
        ps.executeUpdate();
        conn.close();

    }

    @Override
    public List<Map<Pending, Pending>> findAllPendingReimbursements() throws SQLException, ClassNotFoundException {
        DataBaseConnection db = new DataBaseConnection();
        Connection conn = db.establishConnection();
        PreparedStatement ps = conn.prepareStatement("select pending_id, purchase_date, description, total_amount, status from pending");
        ResultSet rs = ps.executeQuery();

        ResultSetMetaData meta = rs.getMetaData();
        List<Map<Pending, Pending>> list = new ArrayList<Map<Pending, Pending>>();

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

    @Override
    public List<Map<Pending, Pending>> findPendingReimbursements(Object userID) throws SQLException, ClassNotFoundException {
        DataBaseConnection db = new DataBaseConnection();
        Connection conn = db.establishConnection();

        PreparedStatement ps = conn.prepareStatement("select * from pending where pending_id = ?");
        ps.setObject(1, userID);
        ResultSet rs = ps.executeQuery();

        ResultSetMetaData meta = rs.getMetaData();
        List<Map<Pending, Pending>> list = new ArrayList<Map<Pending, Pending>>();

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

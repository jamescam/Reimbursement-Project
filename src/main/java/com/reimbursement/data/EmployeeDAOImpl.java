package com.reimbursement.data;

import com.reimbursement.UserRole;
import com.reimbursement.model.Employee;
import com.reimbursement.service.DataBaseConnection;
import com.reimbursement.service.HashPassword;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//Concrete class that implements the com.reimbursement.data.EmployeeDAO, so that we can do CRUD operations on our accounts table.
public class EmployeeDAOImpl implements EmployeeDAO {
    DataBaseConnection dbc = new DataBaseConnection();
    Employee employee = new Employee();

    public EmployeeDAOImpl() throws SQLException, ClassNotFoundException {
    }

    //Method that updates the SQL database with our user data.
    @Override
    public void update(String email, String plainTextPass, UserRole userType) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {

        Connection connectionEstablished = dbc.establishConnection();

        //creates a new instance of com.reimbursement.service.HashPassword class
        HashPassword hashpassword = new HashPassword();

        //calls the salt method of the com.reimbursement.service.HashPassword class which returns a 16 byte array.
        byte[] salt2 = hashpassword.salt();

        //calls the hashAlgo method which take the plain text password of a user and returns a SHA-512 hashed string.
        String hashedPassword = hashpassword.hashAlgo(plainTextPass, salt2);
        String encodedSalt = hashpassword.encodeSalt(salt2);

        PreparedStatement ps = connectionEstablished.prepareStatement("insert into accounts(email, password, salt, user_role) values (?, ?, ?, ?)");
        ps.setString(1, email);
        ps.setString(2, hashedPassword);
        ps.setString(3, encodedSalt);
        ps.setString(4, userType.toString());
        ps.executeUpdate();
        connectionEstablished.close();

    }


    //Retrieves hashed password from the accounts table
    @Override
    public String findHashedPassword(String email) throws SQLException, ClassNotFoundException {
        DataBaseConnection db = new DataBaseConnection();
        Connection conn = db.establishConnection();

        PreparedStatement ps = conn.prepareStatement("select password from accounts where email = ?");
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            employee.setPassword(rs.getString("password"));
        }
        conn.close();
        return employee.getPassword();
    }

    //Retrieves salt from the accounts table.
    @Override
    public String findSalt(String email) throws SQLException, ClassNotFoundException {
        DataBaseConnection db = new DataBaseConnection();
        Connection conn = db.establishConnection();

        PreparedStatement ps = conn.prepareStatement("select salt from accounts where email = ?");
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            employee.setSalt(rs.getString("salt"));

        }

        conn.close();
        return employee.getSalt();
    }

    //Retrieves user id from the accounts table
    @Override
    public Long findUserID(String email) throws SQLException, ClassNotFoundException {
        DataBaseConnection db = new DataBaseConnection();
        Connection conn = db.establishConnection();

        PreparedStatement ps = conn.prepareStatement("select user_id from accounts where email = ?");
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            employee.setUserID(rs.getLong("user_id"));

        }
        conn.close();
        return employee.getUserID();
    }

    //Retrieves user's email from the accounts table.
    @Override
    public String findEmail(Long user_id) throws SQLException, ClassNotFoundException {
        DataBaseConnection db = new DataBaseConnection();
        Connection conn = db.establishConnection();

        PreparedStatement ps = conn.prepareStatement("select email from accounts where user_id = ?");
        ps.setLong(1, user_id);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            employee.setEmail(rs.getString("email"));

        }

        conn.close();
        return employee.getEmail();
    }

    //Retrieves user role(employee or manager) from the accounts table.
    @Override
    public String findUserRole(Long user_id) throws SQLException, ClassNotFoundException {
        DataBaseConnection db = new DataBaseConnection();
        Connection conn = db.establishConnection();

        PreparedStatement ps = conn.prepareStatement("select user_role from accounts where user_id = ?");
        ps.setLong(1, user_id);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            employee.setUserRole(rs.getString("user_role"));

        }

        conn.close();
        return employee.getUserRole();
    }

    //Retrieves all columns from the accounts table.
    @Override
    public List<Map<Employee, Employee>> findAll() throws SQLException, ClassNotFoundException {
        DataBaseConnection db = new DataBaseConnection();
        Connection conn = db.establishConnection();
        PreparedStatement ps = conn.prepareStatement("select * from accounts");
        ResultSet rs = ps.executeQuery();

        ResultSetMetaData meta = rs.getMetaData();
        List<Map<Employee, Employee>> list = new ArrayList<Map<Employee, Employee>>();

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

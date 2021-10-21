package com.reimbursement.data;

import com.reimbursement.UserRole;
import com.reimbursement.model.Employee;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

//Data Access Object used to do CRUD operations on the Employee table.
public interface EmployeeDAO {

    void update(String email, String plainTextPass, UserRole userRole) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException;
    String findHashedPassword(String email) throws SQLException, ClassNotFoundException;
    String findSalt(String salt) throws SQLException, ClassNotFoundException;
    Long findUserID(String email) throws SQLException, ClassNotFoundException;
    String findEmail(Long user_id) throws SQLException, ClassNotFoundException;
    String findUserRole(Long user_id) throws SQLException, ClassNotFoundException;
    List<Map<Employee, Employee>> findAll() throws SQLException, ClassNotFoundException;


}

package com.reimbursement.data;

import com.reimbursement.model.Pending;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

//Data Access Object used to do CRUD operations on the com.reimbursement.model.Pending table.
public interface PendingDAO {
    void update(String userID, String purchaseDate, String description, String total, String status) throws SQLException, ClassNotFoundException;
    void delete(String userID) throws SQLException, ClassNotFoundException;
    List<Map<Pending, Pending>> findAllPendingReimbursements() throws SQLException, ClassNotFoundException;
    List<Map<Pending, Pending>> findPendingReimbursements(Object userID) throws SQLException, ClassNotFoundException;
}

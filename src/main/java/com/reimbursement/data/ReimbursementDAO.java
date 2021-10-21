package com.reimbursement.data;

import com.reimbursement.model.Reimbursement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ReimbursementDAO {

    void updateApprovedReimbursements(String reimbursementID, String purchaseDate, String description, String total, String status, String email) throws SQLException, ClassNotFoundException;
    void updateRejectedReimbursements(String reimbursementID, String purchaseDate, String description, String total, String status, String email) throws SQLException, ClassNotFoundException;
    List<Map<Reimbursement, Reimbursement>> findReimbursement(String email) throws SQLException, ClassNotFoundException;
    List<Map<Reimbursement, Reimbursement>> findRejected(String email) throws SQLException, ClassNotFoundException;
    List<Map<Reimbursement, Reimbursement>> findAllApprovedReimbursements() throws SQLException, ClassNotFoundException;
    List<Map<Reimbursement, Reimbursement>> findAllRejectedReimbursements() throws SQLException, ClassNotFoundException;

}

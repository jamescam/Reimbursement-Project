package com.reimbursement.model;

public class PendingReimbursement {
    private String id;
    private String purchase_date;
    private String description;
    private String total_amount;
    private String status;
    private String pending_id;

    public String getID() {
        return id;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public String getDescription() {
        return description;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public String getStatus() {
        return status;
    }

    public String getPending_id() {
        return pending_id;
    }


}

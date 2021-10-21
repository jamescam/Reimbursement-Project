package com.reimbursement.model;

import java.util.List;

    /*Domain class used as a container for our persistence data.
     *Used by the com.reimbursement.data.EmployeeDAOImpl class to get necessary user data.
     */

public class Employee {
    private Long userID;
    private String email;
    private String hashedPassword;
    private String salt;
    private String userRole;
    private List employeeList;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.hashedPassword = password;
    }

    public String getPassword() {
        return hashedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public List getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List employeeList) {
        this.employeeList = employeeList;
    }

    public String toString() {
        return "Employee{" +
                "userID=" + userID +
                ", email='" + email + '\'' +
                ", hashedPassword=" + hashedPassword + '\'' +
                ", salt=" + salt +
                ", userRole=" + userRole +
                '}';
    }

}

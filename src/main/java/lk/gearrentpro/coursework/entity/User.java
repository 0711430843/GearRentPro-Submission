package lk.gearrentpro.coursework.entity;

public class User {
    private int userId;
    private String username;
    private String role; // Admin, Branch Manager, Staff [cite: 82, 83, 86, 88]
    private Integer assignedBranchId; // Null for Admins [cite: 85, 87]

    public User(int userId, String username, String role, Integer assignedBranchId) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.assignedBranchId = assignedBranchId;
    }

    // Getters
    public String getRole() { return role; }
    public Integer getAssignedBranchId() { return assignedBranchId; }
    public String getUsername() { return username; }
}
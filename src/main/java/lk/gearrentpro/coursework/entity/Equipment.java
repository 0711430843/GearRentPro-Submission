package lk.gearrentpro.coursework.entity;

/**
 * Entity class representing an equipment item as per business rules[cite: 10].
 */
public class Equipment {
    private int equipmentId;
    private int categoryId;
    private int branchId;
    private String brand;
    private String model;
    private int purchaseYear;
    private double baseDailyPrice;
    private double securityDeposit;
    private String status; // Available, Reserved, Rented, Under Maintenance

    // Default Constructor
    public Equipment() {}

    // Getters and Setters
    public int getEquipmentId() { return equipmentId; }
    public void setEquipmentId(int equipmentId) { this.equipmentId = equipmentId; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public int getBranchId() { return branchId; }
    public void setBranchId(int branchId) { this.branchId = branchId; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getPurchaseYear() { return purchaseYear; }
    public void setPurchaseYear(int purchaseYear) { this.purchaseYear = purchaseYear; }

    public double getBaseDailyPrice() { return baseDailyPrice; }
    public void setBaseDailyPrice(double baseDailyPrice) { this.baseDailyPrice = baseDailyPrice; }

    public double getSecurityDeposit() { return securityDeposit; }
    public void setSecurityDeposit(double securityDeposit) { this.securityDeposit = securityDeposit; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
package lk.ijse.semisterfinal.dto;

public class ItemDTO {
    private String ItemCode;
    private String SupplierId;
    private String ItemPrice;
    private String WarrantyPeriod;
    private String itemDetails;


    public ItemDTO(String itemCode, String supplierId, String itemPrice, String warrantyPeriod, String itemDetails) {
        this.ItemCode = itemCode;
        this.SupplierId = supplierId;
        this.ItemPrice = itemPrice;
        this.WarrantyPeriod = warrantyPeriod;
        this.itemDetails = itemDetails;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(String supplierId) {
        SupplierId = supplierId;
    }

    public String getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getWarrantyPeriod() {
        return WarrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        WarrantyPeriod = warrantyPeriod;
    }

    public String getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(String itemDetails) {
        this.itemDetails = itemDetails;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "ItemCode='" + ItemCode + '\'' +
                ", SupplierId='" + SupplierId + '\'' +
                ", ItemPrice='" + ItemPrice + '\'' +
                ", WarrantyPeriod='" + WarrantyPeriod + '\'' +
                ", itemDetails='" + itemDetails + '\'' +
                '}';
    }
}

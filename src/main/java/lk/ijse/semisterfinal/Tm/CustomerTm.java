package lk.ijse.semisterfinal.Tm;

/*
    @author DanujaV
    @created 10/23/23 - 12:01 PM   
*/

public class CustomerTm {
    private String id;
    private String name;
    private String address;
    private String tel;

    public CustomerTm() {
    }

    public CustomerTm(String id, String name, String address, String tel) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tel = tel;
    }

    public CustomerTm(String txtCustId, String txtCustName, String txtCustAddress, String txtCustMobile, String txtCustPayment, String txtCustitemId) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "CustomerTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}

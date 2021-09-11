package com.CFC.fynbee_app;

public class DB {

    private String icon;
    private String CategoryName;
    private String Description;
    private String ResturanLocation;
    private String ResturanName;
    private String BusniessNumber;
    private String OwnerEmail;
    private String OwnerName;
    private String Address;
    private String ResturantPhone;
    private String ResturantPic1;
    private String ResturantPic2;
    private String ResturantPic3;
    private String ResturantPic4;

    @Override
    public String toString() {
        return "DB{" +
                "icon='" + icon + '\'' +
                ", CategoryName='" + CategoryName + '\'' +
                ", Description='" + Description + '\'' +
                ", ResturanLocation='" + ResturanLocation + '\'' +
                ", ResturanName='" + ResturanName + '\'' +
                ", BusniessNumber='" + BusniessNumber + '\'' +
                ", OwnerEmail='" + OwnerEmail + '\'' +
                ", OwnerName='" + OwnerName + '\'' +
                ", Address='" + Address + '\'' +
                ", ResturantPhone='" + ResturantPhone + '\'' +
                ", ResturantPic1='" + ResturantPic1 + '\'' +
                ", ResturantPic2='" + ResturantPic2 + '\'' +
                ", ResturantPic3='" + ResturantPic3 + '\'' +
                ", ResturantPic4='" + ResturantPic4 + '\'' +
                '}';
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBusniessNumber() {
        return BusniessNumber;
    }

    public void setBusniessNumber(String busniessNumber) {
        BusniessNumber = busniessNumber;
    }

    public String getOwnerEmail() {
        return OwnerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        OwnerEmail = ownerEmail;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getResturanLocation() {
        return ResturanLocation;
    }

    public void setResturanLocation(String resturanLocation) {
        ResturanLocation = resturanLocation;
    }

    public String getResturanName() {
        return ResturanName;
    }

    public void setResturanName(String resturanName) {
        ResturanName = resturanName;
    }

    public String getResturantPhone() {
        return ResturantPhone;
    }

    public void setResturantPhone(String resturantPhone) {
        ResturantPhone = resturantPhone;
    }

    public String getResturantPic1() {
        return ResturantPic1;
    }

    public void setResturantPic1(String resturantPic1) {
        ResturantPic1 = resturantPic1;
    }

    public String getResturantPic2() {
        return ResturantPic2;
    }

    public void setResturantPic2(String resturantPic2) {
        ResturantPic2 = resturantPic2;
    }

    public String getResturantPic3() {
        return ResturantPic3;
    }

    public void setResturantPic3(String resturantPic3) {
        ResturantPic3 = resturantPic3;
    }

    public String getResturantPic4() {
        return ResturantPic4;
    }

    public void setResturantPic4(String resturantPic4) {
        ResturantPic4 = resturantPic4;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

package orders;

public class Orders {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDatev;
    private String comment;
    private String [] color;

    public Orders(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDatev, String comment, String [] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDatev = deliveryDatev;
        this.comment = comment;
        this.color = color;
    }
}
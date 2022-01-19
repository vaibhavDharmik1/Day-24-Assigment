public class Contacts {
    private String firstName;
    private String lastName;
    private String address;
    private  String city;
    private  String state;
    private String email;
    private long  phoneNum;
    private int zipCode;


    Contacts(String firstName, String lastName, String address, String city, String state, int zip, long phoneNum, String email){
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.city=city;
        this.state=state;
        this.zipCode=zipCode;
        this.phoneNum=phoneNum;
        this.email=email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public int getZip() {
        return zipCode;
    }
    public void setZip(int zip) {
        this.zipCode = zipCode;
    }
    public long getPhoneNumber() {
        return phoneNum;
    }
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNum = phoneNum;
    }
    @Override
    public String toString() {
        return "Contact [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", state="
                + state + ", email=" + email + ", city=" + city + ", zip=" + zipCode + ", phoneNumber=" + phoneNum
                + "]";
    }
}

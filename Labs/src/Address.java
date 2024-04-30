import java.io.Serializable;

public class Address implements Cloneable ,Comparable<Address> , Serializable{
    private int streetNum;
    private String street;
    private String suburb;
    private String city;

    public Address(int streetNum, String street, String suburb, String city) {
        this.streetNum = streetNum;
        this.street = street;
        this.suburb = suburb;
        this.city = city;
    }

    public Address(Address address) {
        streetNum = address.streetNum;
        street = address.street;
        suburb = address.suburb;
        city = address.city;
    }
    public Address clone() throws CloneNotSupportedException
    {
        return (Address) super.clone();
    }  


    public void setStreetNum(int streetNum) {
        this.streetNum = streetNum;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getcity() {
        return city;
    }
    public int getStreetNum() {
        return streetNum;
    }
    public String getStreet() {
        return street;
    }
    public String getSuburb() {
        return street;
    }

    public void printAddress() {
        System.out.println(" street number : " + streetNum + " street name : " + street + " suburb : " + suburb
                + " city : " + city);
    }

    public String toString() {
        return ", streetNum: " + streetNum + ", street: " + street + ", suburb: " + suburb + ", city: " + city;
    }
    // lab 4
    public int compareTo(Address otherAddress) {
        return city.compareTo(otherAddress.city);
    }
    // lab 06
    public String toDelimitedString() {
        return streetNum + "," + street + "," + suburb + "," + city;
    }
}

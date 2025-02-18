package hibernate.hibernate_martinmd;

import java.util.Arrays;
import java.util.Date;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
 
@Entity
@Table(name="address")
public class Address {
    // @id make address_id as a primary key and @GeneratedValue
    // auto increment
    @Id
    @GeneratedValue
    @Column(name="address_id")
    private int addid;
   
    // This will override and make column length 50 in
    // place of street
    @Column(length=50)
    private String street;
   
      // This will override and make column name City in
    // place of City
      @Column(name="city")
    private String city;
   
    private boolean isOpen;
   
       // This will not create column name x in database
       @Transient
    private double x;
        
      // This will override and make column name date with specific Date format
    @Temporal(TemporalType.DATE)
    private Date date;
     
       //Lob to tell hibernate that image’s a large object and is not a simple object
    @Lob
    private byte[] images;
     
    public Address(int addid, String street, String city, boolean isOpen, double x, Date date, byte[] images) {
        super();
        this.addid = addid;
        this.street = street;
        this.city = city;
        this.isOpen = isOpen;
        this.x = x;
        this.date = date;
        this.images = images;
    }
    public Address() {
        super();
        // TODO Auto-generated constructor stub
    }
     
    public int getAddid() {
        return addid;
    }
    public void setAddid(int addid) {
        this.addid = addid;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public boolean isOpen() {
        return isOpen;
    }
    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public byte[] getImages() {
        return images;
    }
    public void setImages(byte[] images) {
        this.images = images;
    }
    @Override
    public String toString() {
        return "Address [addid=" + addid + ", street=" + street + ", city=" + city + ", isOpen=" + isOpen + ", x=" + x
                + ", date=" + date + ", images=" + Arrays.toString(images) + "]";
    }
 
}

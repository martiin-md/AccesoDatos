package hibernate.hibernate_martinmd;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
// Entity is declare to make this class an object for the
// database
@Entity
 
// By default hibernate will name the table Student as class
// name but @Table annotation override it to student
@Table(name="student")
public class Student {
     
    @Id
    private int id;
    private String firstName;
    private String city;
     
    public Student() {
        super();
         
    }
 
    public Student(int id, String firstName, String city) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.city = city;
    }
 
       //   Basic getters and setters to set and get values
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
     
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
 
    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", city=" + city + "]";
    }
 
     
}
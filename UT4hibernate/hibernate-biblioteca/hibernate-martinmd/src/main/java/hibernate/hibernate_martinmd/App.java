package hibernate.hibernate_martinmd;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    System.out.println( "Project Started" );
        
        // We use sessionfactory to build a session for
        // database and hibernate
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
          
        //creating object of student
        Student student= new Student(102,"xyz","pune");
        System.out.println(student);
          
         
        //creating object of class
         
        Address address= new  Address();
        address.setStreet("JBRoad");
        address.setCity("Pune");
        address.setDate(new Date());
        address.setX(34.8);
        address.setOpen(true);
         
         
       // opening a session
       Session session = factory.openSession();
       // Transaction is a java object used to give the
       // instructions to database
       Transaction tx=session.beginTransaction();
         // we use save to provide the object to push in
       // database table
       session.save(student);
       session.save(address);
       // commit is a transaction function used to push
       // some changes to database with reference to hql
       // query
       tx.commit();
       session.close();
    }
}
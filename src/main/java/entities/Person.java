package entities;

import org.eclipse.persistence.platform.database.events.DatabaseEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;


@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;

@ManyToOne(cascade = CascadeType.PERSIST)
   private Address address;

    public Address getAddress() {
        return address;
    }

    public void addAddress(Address address) {
        this.address = address;
        if (address !=null)
        {
            this.address.addPerson(this);
        }
    }

    @Temporal(TemporalType.DATE)
    private Date created;
    @Temporal(TemporalType.DATE)
    private Date lastEdited;

    public Person(String firas, String korouni, String lastName, Address address) {
    }

    @PrePersist
      protected void onCreate()
      {
          lastEdited=created = new Date();
      }
    @PreUpdate
    protected void onUpdate()
        {
            lastEdited = new Date();
        }

    public Person() {
    }



    public Person(Long id, String firstName, String lastName, String phone, Date created, Date lastEdited) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.created = created;
        this.lastEdited = lastEdited;
    }

    public Person(Long id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Person(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;


    }

    public Person(String firstName, String lastName, String phone, Date created, Date lastEdited) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.created = created;
        this.lastEdited = lastEdited;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    // TODO, delete this class, or rename to an Entity class that makes sense for what you are about to do
    // Delete EVERYTHING below if you decide to use this class, it's dummy data used for the initial demo

}

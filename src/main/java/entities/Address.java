package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "adrress")
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String city;
    private String street;
    private int zip;
@OneToMany(mappedBy = "address")
    List<Person> personList;


    public List<Person> getPersonList() {
        return personList;
    }

    public void addPerson(Person person) {
        if (person!=null)
        {
        this.personList.add(person);
            person.addAddress(this);
        }
    }
    public Address(String city, String street, int zip) {
        this.city = city;
        this.street = street;
        this.zip = zip;
        this.personList= new ArrayList<>();
        }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }


}
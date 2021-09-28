/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Address;
import entities.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tha
 */
public class PersonDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private Date created;
    private Date lastEdited;
    private String city;
    private String street;
    private int zip;

    public static List<PersonDTO> getDtos(List<Person> rms){
        List<PersonDTO> rmdtos = new ArrayList();
        rms.forEach(rm->rmdtos.add(new PersonDTO(rm)));
        return rmdtos;
    }


    public PersonDTO(Person rm) {
        if(rm.getId() != null)
            this.id = rm.getId();
        this.firstName = rm.getFirstName();
        this.lastName = rm.getLastName();
        this.phone = rm.getPhone();
        this.created=rm.getCreated();
        this.lastEdited= rm.getLastEdited();
        if (rm.getAddress()!=null)
        {
            this.street=rm.getAddress().getStreet();
            this.city = rm.getAddress().getCity();
            this.zip = rm.getAddress().getZip();
        }


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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}

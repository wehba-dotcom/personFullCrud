/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import entities.Address;
import entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate() throws Exception {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        FacadePerson fe = FacadePerson.getFacadePerson(emf);
        Person p1 = new Person("sddf","ertretrt","456456");
        Address a2 = new Address("Syria","Alsweeda",5500);
        Address a3 = new Address("Kobenhagen","Shahba",1100);

        //p.setAddress(a1);
       // fe.create(new PersonDTO(a1));

       // fe.create(new PersonDTO(p));
       // fe.create(new PersonDTO(new Person("Wehba", "kor","345345")));

    }

    public static void main(String[] args) throws Exception {
        Person p = new Person("wwwallledd","java","3453454");
        Person p1 = new Person("maher","korouni","5555555");

        Address a1 = new Address("Dubai","Toftevej",3400);



        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
       try {
           EntityManager em = emf.createEntityManager();
           em.getTransaction().begin();

           em.persist(p);
            em.persist(p1);
           em.getTransaction().commit();
       }catch (Exception e)
       {
           System.out.println("pass på"+e.getMessage());
       }finally {
           emf.close();
       }
       //populate();
    }
}

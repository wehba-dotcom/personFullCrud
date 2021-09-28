package facades;

import dtos.PersonDTO;
import entities.Person;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import errorhandling.personNotFoundExeption;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadePerson {

    private static FacadePerson instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadePerson() throws personNotFoundExeption{}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadePerson getFacadePerson(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            try {
                instance = new FacadePerson();
            } catch (personNotFoundExeption e) {
                e.getMessage();

            }
        }
        return instance;
    }

    private EntityManager getEntityManager()throws personNotFoundExeption {
        return emf.createEntityManager();
    }
    
    public PersonDTO create(PersonDTO personDTO)throws personNotFoundExeption{
        Person person = new Person(personDTO.getFirstName(), personDTO.getLastName(),personDTO.getPhone(),personDTO.getCreated(),personDTO.getLastEdited());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }
    public PersonDTO getPersonById(long id) throws personNotFoundExeption{
        EntityManager em = emf.createEntityManager();
        return new PersonDTO(em.find(Person.class, id));
    }
    
    //TODO Remove/Change this before use
    public long getPersonesCount()throws personNotFoundExeption{
        EntityManager em = emf.createEntityManager();
        try{
            long personesCount = (long)em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            return personesCount;
        }finally{  
            em.close();
        }
    }
    public PersonDTO update(PersonDTO personDTO)throws personNotFoundExeption
        {
                Person person = new Person(personDTO.getId(),personDTO.getFirstName(), personDTO.getLastName(),personDTO.getPhone(),personDTO.getCreated(),personDTO.getLastEdited());
                EntityManager em = emf.createEntityManager();
                try {
                em.getTransaction().begin();
               person= em.merge(person);
                em.getTransaction().commit();
                } finally {
                    em.close();
                }
                return new PersonDTO(person);
    }
    public PersonDTO delete(long id) throws personNotFoundExeption {

                EntityManager em = getEntityManager();
                Person p = em.find(Person.class, id);
                if(p == null)

                try {
                em.getTransaction().begin();
                em.remove(p);
                em.getTransaction().commit();
                } finally {
                    em.close();
                }
                return new PersonDTO(p);
        }
    
    public List<PersonDTO> getAllPersones()throws personNotFoundExeption{
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
         List<Person> rms = query.getResultList();
        return PersonDTO.getDtos(rms);
    }
    
    public static void main(String[] args)throws personNotFoundExeption {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadePerson fe = getFacadePerson(emf);
        fe.getAllPersones().forEach(dto->System.out.println(dto));
    }

}

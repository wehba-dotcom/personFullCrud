package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import errorhandling.personNotFoundExeption;
import utils.EMF_Creator;
import facades.FacadePerson;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResources{

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final FacadePerson FACADE =  FacadePerson.getFacadePerson(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
        public String demo(){
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() throws personNotFoundExeption {

        long count = FACADE.getPersonesCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAll() throws personNotFoundExeption{
        List<PersonDTO> reslt = FACADE.getAllPersones();
        return Response.ok().entity(GSON.toJson(reslt)).build();
    }
    @Path("add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String a) throws personNotFoundExeption {
        PersonDTO personDTO = GSON.fromJson(a, PersonDTO.class);
        PersonDTO reslt = FACADE.create(personDTO);
        return Response.ok().entity(GSON.toJson(reslt)).build();
    }
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id")long id,String a) throws personNotFoundExeption {
        PersonDTO personDTO = GSON.fromJson(a, PersonDTO.class);
        personDTO.setId(id);
        PersonDTO reslt = FACADE.update(personDTO);
        return Response.ok().entity(GSON.toJson(reslt)).build();
    }
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response delete(@PathParam("id")long id) throws personNotFoundExeption {
            PersonDTO personDTO = null;
            personDTO = FACADE.delete(id);
            return Response.ok().entity(GSON.toJson(personDTO)).build();
    }
}

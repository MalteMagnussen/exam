package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.IngredientDTO;
import dto.ItemDTO;
import dto.RecipeDTO;
import dto.StorageDTO;
import dto.WeekDTO;
import utils.EMF_Creator;
import facades.RestaurantFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@Path("restaurant")
public class RestaurantResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final RestaurantFacade FACADE = RestaurantFacade.getRestaurantFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @POST
    @Path("item/add")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
    public ItemDTO adminAddHobby(ItemDTO item) {
        System.out.println("item/add: "+ item);
        return FACADE.addItem(item);
    }
    
    @GET
    @Path("items")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
    public List<ItemDTO> getItems() {
        return FACADE.getItems();
    }
    
    @GET
    @Path("storage")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
    public List<StorageDTO> getStorage() {
        return FACADE.getStorage();
    }
    
    @GET
    @Path("storage/{id}/{amount}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
    public StorageDTO adminAddStorage(@PathParam("id") int id, @PathParam("amount") int amount) {
        return FACADE.updateStorage(amount, id);
    }
    
    @GET
    @Path("populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populateRecipes() {
        FACADE.populateWithRecipes();
        return "{\"Message\":\"Database populated with recipes\"}";
    }
    
    @GET
    @Path("recipe")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin", "user"})
    public List<RecipeDTO> getAllRecipes() {
        return FACADE.getRecipes();
    }
    
    @POST
    @Path("week/add")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin", "user"})
    public WeekDTO addWeek(WeekDTO menu) {
        return FACADE.addWeek(menu);
    }
    
    @GET
    @Path("week")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin", "user"})
    public List<WeekDTO> getWeek() {
        return FACADE.getWeeks();
    }
    
    @POST
    @Path("recipe/check")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin", "user"})
    public String checkStorage(List<IngredientDTO> ingredients) {
        return "{\"message\":\""+FACADE.checkStorageHelper(ingredients)+"\"}";
    }
    

//    @GET
//    @Path("id/{id}")
//    @Produces({MediaType.APPLICATION_JSON})
//    @RolesAllowed({"admin", "user"})
//    public PersonDTO getPersonByID(@PathParam("id") int id) {
//        return FACADE.getPersonByID(id);
//    }
//
//    @GET
//    @Path("email/{email}")
//    @Produces({MediaType.APPLICATION_JSON})
//    @RolesAllowed({"admin", "user"})
//    public PersonDTO getPersonByEmail(@PathParam("email") String email) {
//        return FACADE.getPersonByEmail(email);
//    }
//
//    @GET
//    @Path("phone/{phone}")
//    @Produces({MediaType.APPLICATION_JSON})
//    @RolesAllowed({"admin", "user"})
//    public PersonDTO getPersonByPhone(@PathParam("phone") String phone) {
//        return FACADE.getPersonByPhone(phone);
//    }
//
//    @GET
//    @Path("hobby/{name}")
//    @Produces({MediaType.APPLICATION_JSON})
//    @RolesAllowed({"admin", "user"})
//    public List<PersonDTO> getPersonsByHobby(@PathParam("name") String name) {
//        return FACADE.getPersonsByHobby(name);
//    }
//
//    @GET
//    @Path("hobby/all")
//    @Produces({MediaType.APPLICATION_JSON})
//    @RolesAllowed({"admin", "user"})
//    public List<HobbyDTO> getAllHobbies() {
//        return FACADE.getAllHobbies();
//    }
//
//    @POST
//    @Path("admin/hobby/add")
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    @RolesAllowed({"admin"})
//    public HobbyDTO adminAddHobby(HobbyDTO hobby) {
//        return FACADE.adminAddHobby(hobby);
//    }
//
//    @PUT
//    @Path("admin/hobby/edit")
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    @RolesAllowed({"admin"})
//    public HobbyDTO adminEditHobby(HobbyDTO hobby) {
//        return FACADE.adminEditHobby(hobby);
//    }
//
//    @DELETE
//    @Produces({MediaType.APPLICATION_JSON})
//    @Path("admin/hobby/delete/{id}")
//    @RolesAllowed({"admin"})
//    public HobbyDTO adminDeleteHobby(@PathParam("id") int id) {
//        return FACADE.adminDeleteHobby(id);
//    }
//
//    @POST
//    @Path("admin/person/add")
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    @RolesAllowed({"admin"})
//    public PersonDTO adminAddPerson(PersonDTO person) {
//        return FACADE.adminAddPerson(person);
//    }
//
//    @PUT
//    @Path("admin/person/edit")
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    @RolesAllowed({"admin"})
//    public PersonDTO adminEditPerson(PersonDTO person) {
//        return FACADE.adminEditPerson(person);
//    }
//
//    @DELETE
//    @Path("admin/person/delete/{id}")
//    @Produces({MediaType.APPLICATION_JSON})
//    @RolesAllowed({"admin"})
//    public PersonDTO adminDeletePerson(@PathParam("id") int id) {
//        return FACADE.adminDeletePerson(id);
//    }
//
//    @GET
//    @Path("testdata")
//    @Produces({MediaType.APPLICATION_JSON})
//    public String populateDatabase() {
//
//        boolean success = FACADE.populate();
//
//        if (success) {
//            return "{\"message\":\"Database populated with dummy data\"}";
//        } else {
//            return "{\"message\":\"Failed to populate database\"}";
//        }
//    }

}

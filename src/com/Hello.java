package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;

// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/")
public class Hello {
	

  // This method is called if TEXT_PLAIN is request
	 @GET
	  @Path("delete/{id}")
	  @Produces(MediaType.TEXT_HTML)
	  public Response deleteUser(@PathParam("id") String id) {
		 int key=Integer.parseInt(id);
		 Users_new usr=new Users_new();
		 usr.setId(key);
		 new TestUsers().deleteUser(usr);
		  return Response.status(200).entity("deletion Sucessful deleted  id : " + id).build();
		  
	  }

  // This method is called if DELETE is request
	 @DELETE
	  @Path("id/{id}")
	  @Produces(MediaType.TEXT_HTML)
	  public Response deleteUser1(@PathParam("id") String id) {
		 int key=Integer.parseInt(id);
		 Users_new usr=new Users_new();
		 usr.setId(key);
		 new TestUsers().deleteUser(usr);
		  return Response.status(200).entity("deletion Sucessful deleted  id : " + id).build();
		  
	  }


  // This method is called if HTML is request
  @GET
  @Path("{id}")
  @Produces(MediaType.TEXT_HTML)
  public Response sayHtmlHello(@PathParam("id") String id) {
	  return Response.status(200).entity("getUserById is called, id : " + id).build();
	  
  }
  
  @POST
  @Path("update/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_HTML)
  public Response updateUser(@PathParam("id") String id, String metadata) {
		System.out.print("put request arrived");
		// new TestUsers().updateUser(usr);
	  	int uid=Integer.parseInt(id);
		 Session session=SsnFctry.getFactory().openSession();
		   Transaction t=session.beginTransaction();  
		   Users_new user= (Users_new) session.get(Users_new.class,uid);
		   user.setMetadata(metadata);
		   session.update(user); 
		   t.commit();  
		   session.close();  
		 
		 
		 
		// Product product = (Product) session.get(Product.class, productId);
		 //product.setProductDetail("new detail");
		 
		 
		 
		  return Response.status(200).entity("updation Sucessful updated user_id  id : " + id).build();
		  
	  }
  /*
  @POST
  @Produces(MediaType.TEXT_HTML)
  public Response getpostdata
  (		  @FormParam("uid") String uid,
		  @FormParam("fname") String fname,
		  @FormParam("lname") String lname,
		  @FormParam("email") String email,
		  @FormParam("dob") String dob,
		  @FormParam("role") String role,
		  @FormParam("metadata") String metadata
	)
  {
	 
	  String res= "<h3>Recieved a post request with Fname="+fname+"\n Last name="+lname+"email="+email+"<p></h3> and media type was TEXT_HTML";
     Users_new user=new Users_new();
     System.out.print(""+uid);
     user.setId(Integer.parseInt(uid));
     user.setFname(fname);
     user.setLname(lname);
     user.setEmail(email);
     user.setDob(dob);
     user.setMetadata(metadata);
    new TestUsers().saveUser(user);
	  return Response.status(200).entity(res).build();
  }
*/
  @Path("add/")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response getJsonData (	String jsondata	 )
  {
	 System.out.println(jsondata);
	  JSONObject obj;
	try {
		obj = new JSONObject(jsondata);
     Users_new user=new Users_new();
   
     user.setId(obj.getInt("uid"));
     user.setFname(obj.getString("fname"));
     user.setLname(obj.getString("lname"));
     user.setEmail(obj.getString("email"));
     user.setDob(obj.getString("dob"));
     //user.setMetadata(obj.getString("metadata"));
    new TestUsers().saveUser(user);
	  return Response.status(200).entity("saved in db").build();
	} catch (JSONException e) {
		
				e.printStackTrace();
		return Response.status(418).entity("json exception occured").build();
	}
	
  }

} 
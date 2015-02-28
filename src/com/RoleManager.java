/**
 * 
 */
package com;

/**
 * @author amit
 *
 */

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Path("/roles")
public class RoleManager {
	@DELETE
	  @Path("{id}")
	  @Produces(MediaType.TEXT_HTML)
	  public Response deleteUser(@PathParam("id") String id) {
		 
		 Roles role=new Roles();
		 role.setRole(id);
			Session session=SsnFctry.getFactory().openSession();
		    Transaction t=session.beginTransaction();  
		    session.delete(role);
		    t.commit();
		    session.close();
		  return Response.status(200).entity("role Sucessful deleted  id : " + id).build();
		  
	  }

// This method is called if XML is request


// This method is called if HTML is request
@GET
@Produces(MediaType.TEXT_HTML)
public Response getRules(@PathParam("id") String id) {
	
	Session session=SsnFctry.getFactory().openSession();
    
	List<?> allRoles;
    Query queryResult = (Query) session.createQuery("from Roles");
    allRoles = queryResult.list();
    StringBuilder str=new StringBuilder();
    str.append("[");
    for (int i = 0; i < allRoles.size(); i++) {
     Roles role = (Roles) allRoles.get(i);
     str.append(" '"+role.toString()+"'");
    }
     str.append(" ]");
	
	return Response.status(200).entity(""+ str.toString()).build();
	  
}

@PUT
@Path("{id}")
@Produces(MediaType.TEXT_HTML)
public  Response updateUser(@PathParam("id") String arg)
{
	Session session=SsnFctry.getFactory().openSession();
	   Transaction t=session.beginTransaction(); 
	   Roles role=new Roles();
	   role.setRole(arg);
	   session.save(role);  
	   t.commit();  
	   session.close();  
	   System.out.println("successfully saved");  
	   return Response.status(200).entity("Role saved").build();
	  }

@POST
@Path("{id}")
@Produces(MediaType.TEXT_HTML)
public Response getpostdata(@FormParam("newrole") String newRole, @PathParam("id") String roleid)
{
	Roles role=new Roles();
	role.setRole(roleid);
	Session session=SsnFctry.getFactory().openSession();
	Transaction t=session.beginTransaction();
	session.delete(role);
	role.setRole(newRole);
	session.save(role);
	t.commit();
	  return Response.status(200).entity("role updated").build();
}


}

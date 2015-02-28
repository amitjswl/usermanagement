package com;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gson.Gson;

@Path("/all")
public class mainPage {

	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response viewUsersHtml(){
		
		Session session=SsnFctry.getFactory().openSession();
		List<?> allUsers;
	    Query queryResult = (Query) session.createQuery("from Users_new");
	    allUsers = queryResult.list();
	    String json = new Gson().toJson(allUsers );
	   // return Response.status(200).entity(""+ json).build();
	    
	    
	    StringBuilder str=new StringBuilder();
	    str.append("<html><body><table style='width:80%'>");
	    str.append(Users_new.toTableHeader());
	    for (int i = 0; i < allUsers.size(); i++) {
	     Users_new usr = (Users_new) allUsers.get(i);
	    
	     str.append(usr.toTableRow());
	    }
	     str.append(" </body></html>");
		
		return Response.status(200).entity(""+ str.toString()).build();
		
	    
	    
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewUsersJson(){
		
		Session session=SsnFctry.getFactory().openSession();
		List<?> allUsers;
	    Query queryResult = (Query) session.createQuery("from Users_new");
	    allUsers = queryResult.list();
	    String json = new Gson().toJson(allUsers );
	    return Response.status(200).entity(""+ json).build();
	    /*
	    
	    StringBuilder str=new StringBuilder();
	    str.append("<html><body><table style='width:80%'>");
	    str.append(Users_new.toTableHeader());
	    for (int i = 0; i < allUsers.size(); i++) {
	     Users_new usr = (Users_new) allUsers.get(i);
	    
	     str.append(usr.toTableRow());
	    }
	     str.append(" </body></html>");
		
		return Response.status(200).entity(""+ str.toString()).build();
		*/
	    
	    
	}
}

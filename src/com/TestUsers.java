package com;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class TestUsers {

	public  void saveUser(Users_new user) {
		System.out.print("now in saaving paartttt");
		 System.out.println("uid of the user is "+user.getId());
		Session session=SsnFctry.getFactory().openSession();
   Transaction t=session.beginTransaction();  
   session.save(user);  
   t.commit();  
   session.close();  
   System.out.println("successfully saved");  
}  

	@SuppressWarnings("unchecked")
	public List<Users_new> retrieveUser(Users_new user){
		Session session=SsnFctry.getFactory().getCurrentSession();
		Query query = session.createQuery("from users");
		return query.list();
	}
	


public void updateUser(Users_new user){
	Session session=SsnFctry.getFactory().openSession();
    Transaction t=session.beginTransaction();  
    session.update(user);
    t.commit();
    session.close();
    
	
}
public void deleteUser(Users_new usr)
{
	Session session=SsnFctry.getFactory().openSession();
    Transaction t=session.beginTransaction();  
    session.delete(usr);
    t.commit();
    session.close();
    
}

}

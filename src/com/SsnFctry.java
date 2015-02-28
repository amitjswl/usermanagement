package com;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public  class SsnFctry {

	private static SessionFactory Factory;

	/**
	 * @return the assignmentFactory
	 */
	public  SsnFctry(){
	
		Factory=new AnnotationConfiguration()  
	        .configure().buildSessionFactory();
		
	}
	
	public static SessionFactory getFactory() {
		if(Factory==null){

			Factory=new AnnotationConfiguration()  
		        .configure().buildSessionFactory();
		}
		return Factory;
	}

	
}

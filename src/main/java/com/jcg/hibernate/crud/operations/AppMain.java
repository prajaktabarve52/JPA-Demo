package com.jcg.hibernate.crud.operations;

import java.util.List;

import org.apache.log4j.Logger;

public class AppMain {

	public final static Logger logger = Logger.getLogger(AppMain.class);

	public static void main(String[] args) {
		

		logger.info("\n=======CREATE RECORDS=======\n");
		
                //create department
                   DbOperations.createDepartment("IT","Mumbai");
                   DbOperations.createDepartment("Finance","Pune");
                   
                 //create employee record
                   DbOperations.createEmployeewithDept("Finance");
                   DbOperations.createEmployeewithDept("IT");
                   
                   //non db operations
                   NonDbOperations.findbyage();
                   NonDbOperations.findbyage();
		logger.info("\n=======READ RECORDS=======\n");

//
//		logger.info("\n=======DELETE ALL RECORDS=======\n");
//		DbOperations.deleteAllRecords();
//		logger.info("\n=======READ RECORDS AFTER ALL RECORDS DELETION=======");
	
		System.exit(0);
	} 
}
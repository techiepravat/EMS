package org.comp.util;

public class EmployeeConstant {

	public static final String INSERT = "INSERT INTO EMPDETAILS(EMPNAME,EMPADDRESS,EMPSTATUS) VALUES(?,?,?)";
	public static final String UPDATE = "UPDATE EMPDETAILS SET EMPADDRESS=?,EMPSTATUS=? WHERE EMPID=?";
	public static final String DELETE = "DELETE FROM EMPDETAILS  WHERE EMPID=?";
	public static final String SELECT = "SELECT * FROM EMPDETAILS";
	public static final String SEARCH = "SELECT EMPID,EMPNAME,EMPADDRESS,EMPSTATUS FROM EMPDETAILS WHERE EMPID =?";
}

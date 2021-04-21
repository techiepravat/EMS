
package org.comp.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.comp.util.EmployeeConstant;
import org.comp.pojo.Employee;

public class EmployeeManagement {

	// INSERT EMPLOYEE
	public Map<Integer, String> addEmployee(List<Employee> employee) throws ClassNotFoundException, SQLException {

		Connection con = ConnectionManager.getConnection();
		String sqlInsert = "INSERT INTO EMPDETAILS(EMPNAME,EMPADDRESS,EMPSTATUS) VALUES(?,?,?)";

		PreparedStatement insertStmt = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
		int rowsInsert = 0;

		Map<Integer, String> map = new HashMap<Integer, String>();
		int generatedKey = 0;
		for (Employee employee2 : employee) {

			insertStmt.setString(1, employee2.getEmpName());
			insertStmt.setString(2, employee2.getEmpAddress());
			insertStmt.setString(3, employee2.getEmpStatus());
			rowsInsert = insertStmt.executeUpdate();

			ResultSet rs = insertStmt.getGeneratedKeys();
			while (rs.next()) {
				generatedKey = rs.getInt(1);
				// String empName = rs.getString(2);//doubt
				String empName = employee2.getEmpName();
				map.put(generatedKey, empName);
			}
		}
		System.out.println(map);
		if (rowsInsert > 0) {
			System.out.println("Employee was inserted successfully!" + generatedKey);
		}
		return map;
	}

	// UPDATE EMPLOYEE
	public Employee upadteEmployee(Employee emp) throws ClassNotFoundException, SQLException {

		Connection con = ConnectionManager.getConnection();
		// For Dynamic Query Generation
		StringBuilder sqlUpdate = new StringBuilder();
		sqlUpdate.append("UPDATE EMPDETAILS SET ");

		List<String> list = new ArrayList<>();
		int rowsUpdate = 0;
		Map<Integer, String> map = new HashMap<Integer, String>();

		if (emp.getEmpName() == null && emp.getEmpAddress() == null) {
			System.out.println("Please Give Either Employee Name or Employee Address:");
		}
		if (emp.getEmpName() != null) {
			sqlUpdate.append(" EMPNAME=?");
			map.put(1, emp.getEmpName());

		}
		if (emp.getEmpAddress() != null) {
			if (map.size() == 1) {
				sqlUpdate.append(",");
				sqlUpdate.append(" EMPADDRESS=?");
			} else {
				sqlUpdate.append(" EMPADDRESS=?");
			}
			// sqlUpdate.append(" EMPADDRESS=?");
			if (map.size() < 1) {
				map.put(1, emp.getEmpAddress());
			} else if (map.size() == 1) {
				map.put(2, emp.getEmpAddress());
			}
		}
		sqlUpdate.append(" WHERE EMPID=?");
		PreparedStatement updateStmt = con.prepareStatement(sqlUpdate.toString());
		if (map.size() == 1) {
			updateStmt.setString(1, map.get(1));
			updateStmt.setInt(2, emp.getEmpId());
		} else if (map.size() == 2) {
			updateStmt.setString(1, map.get(1));
			updateStmt.setString(2, map.get(2));
			updateStmt.setInt(3, emp.getEmpId());
		}
		rowsUpdate = updateStmt.executeUpdate();

		if (rowsUpdate > 0) {
			System.out.println("Employee was updated successfully!");
		}
		return emp;
	}

	// Search Employees
	public void searchEmployeeByName(Employee emp) throws ClassNotFoundException, SQLException {
		Connection con = ConnectionManager.getConnection();

		StringBuilder sqlUpdate = new StringBuilder();
		sqlUpdate.append("SELECT * FROM EMPDETAILS WHERE EMPNAME LIKE '");
		sqlUpdate.append(emp.getEmpName()).append('%').append("'");

		PreparedStatement searchStmt = con.prepareStatement(sqlUpdate.toString());
		System.out.println(searchStmt.toString());

		ResultSet rs = searchStmt.executeQuery();

		while (rs.next()) {
			int empId = rs.getInt(1);
			String empName = rs.getString(2);
			String empAddress = rs.getString(3);
			String empStatus = rs.getString(4);
			System.out
					.println("EMPLOYEE DETAILS IS:" + empId + " , " + empName + " , " + empAddress + " , " + empStatus);
		}
	}

	// Search Employees
	public void searchEmployeeById(Employee emp) throws ClassNotFoundException, SQLException {
		Connection con = ConnectionManager.getConnection();
		String sqlSearch = "SELECT EMPID,EMPNAME,EMPADDRESS,EMPSTATUS FROM EMPDETAILS WHERE EMPID =?";

		PreparedStatement searchStmt = con.prepareStatement(sqlSearch);
		searchStmt.setInt(1, emp.getEmpId());
		// System.out.println(searchStmt.toString());

		ResultSet rs = searchStmt.executeQuery();

		while (rs.next()) {
			int empId = rs.getInt(1);
			String empName = rs.getString(2);
			String empAddress = rs.getString(3);
			String empStatus = rs.getString(4);
			System.out
					.println("EMPLOYEE DETAILS IS:" + empId + " , " + empName + " , " + empAddress + " , " + empStatus);
		}
	}

	// Deactive Employees
	public void deActivatedEmployee(Employee emp) throws ClassNotFoundException, SQLException {
		Connection con = ConnectionManager.getConnection();
		String sqlDelete = "UPDATE EMPDETAILS SET EMPSTATUS=? WHERE EMPID=?";

		PreparedStatement deactivedStmt = con.prepareStatement(sqlDelete);
		deactivedStmt.setString(1, emp.getEmpStatus());
		deactivedStmt.setInt(2, emp.getEmpId());

		int rowsDelete = deactivedStmt.executeUpdate();
		if (rowsDelete > 0) {
			System.out.println("Employee was Deactivated Successfully!");
		}
	}

	// Retrieve all Employees
	public void retrieveEmployee(Employee emp) throws ClassNotFoundException, SQLException {
		Connection con = ConnectionManager.getConnection();
		String sqlRetrieve = "SELECT * FROM EMPDETAILS";

		PreparedStatement retrieveStmt = con.prepareStatement(sqlRetrieve);
		ResultSet result = retrieveStmt.executeQuery(sqlRetrieve);

		while (result.next()) {
			int empId = result.getInt(1);
			String empName = result.getString(2);
			String empAddress = result.getString(3);
			String empStatus = result.getString(4);

			System.out
					.println("EMPLOYEE DETAILS IS:" + empId + " , " + empName + " , " + empAddress + " , " + empStatus);
		}
	}
}

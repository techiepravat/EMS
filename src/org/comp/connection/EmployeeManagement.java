
package org.comp.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.comp.util.EmployeeConstant;
import org.comp.pojo.Employee;

public class EmployeeManagement {

	// INSERT EMPLOYEE
	public Map<Integer, String> addEmployee(List<Employee> employee) throws ClassNotFoundException, SQLException {

		Connection con = ConnectionManager.getConnection();
		String sqlInsert = "INSERT INTO EMPDETAILS(FIRST_NAME,MIDDLE_NAME,LAST_NAME,PERSONAL_MAILID,EMPADDRESS,EMPBIRTH_DATE,EMPSTATUS) VALUES(?,?,?,?,?,?,?)";

		PreparedStatement insertStmt = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
		int rowsInsert = 0;

		Map<Integer, String> map = new HashMap<Integer, String>();
		int generatedKey = 0;
		for (Employee employee2 : employee) {

			insertStmt.setString(1, employee2.getFirstName());
			insertStmt.setString(2, employee2.getMiddleName());
			insertStmt.setString(3, employee2.getLastName());
			insertStmt.setString(4, employee2.getMailId());
			insertStmt.setString(5, employee2.getEmpAddress());

			java.sql.Date sqlDate = new java.sql.Date(employee2.getEmpbirthDate().getTime());
			insertStmt.setDate(6, sqlDate);
			insertStmt.setString(7, employee2.getEmpStatus());
			rowsInsert = insertStmt.executeUpdate();

			ResultSet rs = insertStmt.getGeneratedKeys();
			while (rs.next()) {
				generatedKey = rs.getInt(1);
				String empName = employee2.getFirstName() + employee2.getMiddleName() + employee2.getLastName();
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

		Connection con = ConnectionManager.getConnection(); // For Dynamic Query Generation
		StringBuilder sqlUpdate = new StringBuilder();
		sqlUpdate.append("UPDATE EMPDETAILS SET ");

		List<String> list = new ArrayList<>();
		int rowsUpdate = 0;
		Map<Integer, Object> map = new HashMap<Integer, Object>();

		String name = emp.getFirstName() + emp.getMiddleName() + emp.getLastName();
		// emp.getFirstName() == null && emp.getMiddleName() == null &&
		// emp.getLastName() == null

		if (name == null && emp.getEmpAddress() == null) {
			System.out.println("Please Give Either Employee Name or Employee Address:");
		}
		if (emp.getFirstName() != null) {
			sqlUpdate.append(" FIRST_NAME=?");
			map.put(1, emp.getFirstName());
		}
		if (emp.getMiddleName() != null) {
			if (map.size() == 1) {
				sqlUpdate.append(",");
				sqlUpdate.append(" MIDDLE_NAME=?");
			} else {
				sqlUpdate.append(" MIDDLE_NAME=?");
			}
			if (map.size() < 1) {
				map.put(1, emp.getMiddleName());
			} else if (map.size() == 1) {
				map.put(2, emp.getMiddleName());
			}
		}
		if (emp.getLastName() != null) {
			if (map.size() >= 1) {
				sqlUpdate.append(",");
				sqlUpdate.append(" LAST_NAME=?");
			} else {
				// sqlUpdate.append(",");
				sqlUpdate.append(" LAST_NAME=?");
			}
			if (map.size() < 1) {
				map.put(1, emp.getLastName());
			} else if (map.size() == 1) {
				map.put(2, emp.getLastName());
			} else if (map.size() == 2)
				map.put(3, emp.getLastName());
		}
		if (emp.getEmpAddress() != null) {
			if (map.size() >= 1) {
				sqlUpdate.append(",");
				sqlUpdate.append(" EMPADDRESS=?");
			} else {
				// sqlUpdate.append(",");
				sqlUpdate.append(" EMPADDRESS=?");
			}
			if (map.size() < 1) {
				map.put(1, emp.getEmpAddress());
			} else if (map.size() == 1) {
				map.put(2, emp.getEmpAddress());
			} else if (map.size() == 2) {
				map.put(3, emp.getEmpAddress());
			} else if (map.size() == 3) {
				map.put(4, emp.getEmpAddress());
			} else if (map.size() == 4) {
				map.put(5, emp.getEmpAddress());
			}
		}
		if (emp.getEmpbirthDate() != null) {
			if (map.size() >= 1) {
				sqlUpdate.append(",");
				sqlUpdate.append(" EMPBIRTH_DATE=?");
			} else {
				sqlUpdate.append(" EMPBIRTH_DATE=?");
			}
			/*
			 * DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); String strDate =
			 * dateFormat.format(emp.getEmpbirthDate().getTime());
			 */
			java.sql.Date sqlDate = new java.sql.Date(emp.getEmpbirthDate().getTime());
			if (map.size() < 1) {
				map.put(1, sqlDate);
			} else if (map.size() == 1) {
				map.put(2, sqlDate);
			} else if (map.size() == 2) {
				map.put(3, sqlDate);
			} else if (map.size() == 3) {
				map.put(4, sqlDate);
			} else if (map.size() == 4) {
				map.put(5, sqlDate);
			} else if (map.size() == 5) {
				map.put(6, sqlDate);
			}
		}
		sqlUpdate.append(" WHERE EMPID=?");
		PreparedStatement updateStmt = con.prepareStatement(sqlUpdate.toString());
		if (map.size() == 1) {
			updateStmt.setString(1, map.get(1).toString());
			updateStmt.setInt(2, emp.getEmpId());
		} else if (map.size() == 2) {
			updateStmt.setString(1, map.get(1).toString());
			updateStmt.setString(2, map.get(2).toString());
			updateStmt.setInt(3, emp.getEmpId());
		} else if (map.size() == 3) {
			updateStmt.setString(1, map.get(1).toString());
			updateStmt.setString(2, map.get(2).toString());
			updateStmt.setString(3, map.get(3).toString());
			updateStmt.setInt(4, emp.getEmpId());
		} else if (map.size() == 4) {
			updateStmt.setString(1, map.get(1).toString());
			updateStmt.setString(2, map.get(2).toString());
			updateStmt.setString(3, map.get(3).toString());
			updateStmt.setString(4, map.get(4).toString());
			updateStmt.setInt(5, emp.getEmpId());
		}
		rowsUpdate = updateStmt.executeUpdate();

		if (rowsUpdate > 0) {
			System.out.println("Employee was updated successfully!");
		}
		return emp;
	}

	// Search By Name Employees
	public void searchEmployeeByName(Employee emp) throws ClassNotFoundException, SQLException {
		Connection con = ConnectionManager.getConnection();

		StringBuilder sqlUpdate = new StringBuilder();
		/*
		 * sqlUpdate.append("SELECT * FROM EMPDETAILS WHERE FIRST_NAME LIKE '");
		 * sqlUpdate.append(emp.getFirstName()).append('%').append("'");
		 */

		sqlUpdate.append("SELECT * FROM EMPDETAILS WHERE ");
		if (emp.getFirstName() != null) {
			sqlUpdate.append(" FIRST_NAME LIKE '");
			sqlUpdate.append(emp.getFirstName()).append('%').append("'");
		}else if(emp.getLastName() != null) {
			sqlUpdate.append(" LAST_NAME LIKE '");
			sqlUpdate.append(emp.getLastName()).append('%').append("'");
		}
		
		PreparedStatement searchStmt = con.prepareStatement(sqlUpdate.toString());
		System.out.println(searchStmt.toString());

		ResultSet rs = searchStmt.executeQuery();

		while (rs.next()) {
			int empId = rs.getInt(1);
			String empFirst = rs.getString(2);
			String empMiddle = rs.getString(3);
			String empLast = rs.getString(4);
			String mailId = rs.getString(5);
			String empAddress = rs.getString(6);
			String birth = rs.getString(7);
			String empStatus = rs.getString(8);

			System.out.println("EMPLOYEE DETAILS IS:" + empId + " , " + empFirst + " , " + empMiddle + " , " + empLast
					+ " , " + mailId + " , " + empAddress + " , " + birth + " , " + empStatus);
		}
	}

	// Search By ID Employees
	public void searchEmployeeById(Employee emp) throws ClassNotFoundException, SQLException {
		Connection con = ConnectionManager.getConnection();
		String sqlSearch = "SELECT * FROM EMPDETAILS WHERE EMPID =?";

		PreparedStatement searchStmt = con.prepareStatement(sqlSearch);
		searchStmt.setInt(1, emp.getEmpId());
		// System.out.println(searchStmt.toString());

		ResultSet rs = searchStmt.executeQuery();

		while (rs.next()) {
			int empId = rs.getInt(1);
			String empFirst = rs.getString(2);
			String empMiddle = rs.getString(3);
			String empLast = rs.getString(4);
			String mailId = rs.getString(5);
			String empAddress = rs.getString(6);
			String birth = rs.getString(7);
			String empStatus = rs.getString(8);

			System.out.println("EMPLOYEE DETAILS IS:" + empId + " , " + empFirst + " , " + empMiddle + " , " + empLast
					+ " , " + mailId + " , " + empAddress + " , " + birth + " , " + empStatus);
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
			String empFirst = result.getString(2);
			String empMiddle = result.getString(3);
			String empLast = result.getString(4);
			String mailId = result.getString(5);
			String empAddress = result.getString(6);
			String birth = result.getString(7);
			String empStatus = result.getString(8);

			System.out.println("EMPLOYEE DETAILS IS:" + empId + " , " + empFirst + " , " + empMiddle + " , " + empLast
					+ " , " + mailId + " , " + empAddress + " , " + birth + " , " + empStatus);
		}
	}
}

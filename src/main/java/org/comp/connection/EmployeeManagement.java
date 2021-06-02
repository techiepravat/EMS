
package org.comp.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.comp.pojo.Employee;

public class EmployeeManagement {

	// INSERT EMPLOYEE
	public Map<Integer, String> addEmployee(List<Employee> employee) throws ClassNotFoundException, SQLException {

		Connection con = ConnectionManager.getConnection();
		String sqlInsert = "INSERT INTO EMPDETAILS(FIRST_NAME,MIDDLE_NAME,LAST_NAME,PERSONAL_MAILID,MOBILE_NUMBER,EMPBIRTH_DATE,EMP_ADDRESS,EMP_STATUS,JOINING_DATE,GENDER,SALARY,COUNTRY) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement insertStmt = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
		int rowsInsert = 0;

		Map<Integer, String> map = new HashMap<Integer, String>();
		int generatedKey = 0;
		for (Employee employee2 : employee) {

			insertStmt.setString(1, employee2.getFirstName());
			insertStmt.setString(2, employee2.getMiddleName());
			insertStmt.setString(3, employee2.getLastName());
			insertStmt.setString(4, employee2.getMailId());
			insertStmt.setLong(5, employee2.getMobileNo());

			java.sql.Date birthDate = new java.sql.Date(employee2.getEmpbirthDate().getTime());
			insertStmt.setDate(6, birthDate);
			insertStmt.setString(7, employee2.getEmpAddress());
			insertStmt.setString(8, employee2.getEmpStatus());

			java.sql.Date joinDate = new java.sql.Date(employee2.getJoinDate().getTime());
			insertStmt.setDate(9, joinDate);
			insertStmt.setString(10, employee2.getGender());
			insertStmt.setDouble(11, employee2.getSalary());
			insertStmt.setString(12, employee2.getCountry());
			rowsInsert = insertStmt.executeUpdate();

			ResultSet rs = insertStmt.getGeneratedKeys();
			while (rs.next()) {
				generatedKey = rs.getInt(1);
				String empName = employee2.getFirstName() + employee2.getMiddleName() + employee2.getLastName();
				map.put(generatedKey, empName);
			}
		}
		// System.out.println(map);
		if (rowsInsert > 0) {
			System.out.println("Emp Id:" + generatedKey + " was inserted successfully!");
		}
		return map;
	}

	// UPDATE EMPLOYEE
	public Employee updateEmployee(Employee emp) throws ClassNotFoundException, SQLException {

		Connection con = ConnectionManager.getConnection();
		// For Dynamic Query Generation
		StringBuilder sqlUpdate = new StringBuilder();
		sqlUpdate.append("UPDATE EMPDETAILS SET ");

		// List<String> list = new ArrayList<String>();
		int rowsUpdate = 0;
		Map<Integer, Object> map = new HashMap<Integer, Object>();

		String name = emp.getFirstName() + emp.getMiddleName() + emp.getLastName();

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

		if (emp.getMailId() != null) {
			if (map.size() >= 1) {
				sqlUpdate.append(",");
				sqlUpdate.append(" PERSONAL_MAILID=?");
			} else {
				sqlUpdate.append(" PERSONAL_MAILID=?");
			}
			if (map.size() < 1) {
				map.put(1, emp.getMailId());
			}
		}

		if (emp.getMobileNo() >= 1) {
			if (map.size() >= 1) {
				sqlUpdate.append(",");
				sqlUpdate.append(" MOBILE_NUMBER=?");
			} else {
				sqlUpdate.append(" MOBILE_NUMBER=?");
			}
			if (map.size() < 1) {
				map.put(1, emp.getMobileNo());
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

		sqlUpdate.append(" WHERE EMPID=?");
		PreparedStatement updateStmt = con.prepareStatement(sqlUpdate.toString());
		if (map.size() == 1) {
			updateStmt.setObject(1, map.get(1));
			updateStmt.setInt(2, emp.getEmpId());
		} else if (map.size() == 2) {
			updateStmt.setObject(1, map.get(1));
			updateStmt.setObject(2, map.get(2));
			updateStmt.setInt(3, emp.getEmpId());
		} else if (map.size() == 3) {
			updateStmt.setObject(1, map.get(1));
			updateStmt.setObject(2, map.get(2));
			updateStmt.setObject(3, map.get(3));
			updateStmt.setInt(4, emp.getEmpId());
		} else if (map.size() == 4) {
			updateStmt.setObject(1, map.get(1));
			updateStmt.setObject(2, map.get(2));
			updateStmt.setObject(3, map.get(3));
			updateStmt.setObject(4, map.get(4));
			updateStmt.setInt(5, emp.getEmpId());
		} else if (map.size() == 5) {
			updateStmt.setObject(1, map.get(1));
			updateStmt.setObject(2, map.get(2));
			updateStmt.setObject(3, map.get(3));
			updateStmt.setObject(4, map.get(4));
			updateStmt.setObject(5, map.get(5));
			updateStmt.setInt(6, emp.getEmpId());
		}
		rowsUpdate = updateStmt.executeUpdate();

		if (rowsUpdate > 0) {
			System.out.println("Employee was updated successfully!");
		}
		return emp;
	}

	// Search By Name Employees
	public Employee searchEmployeeByName(Employee emp) throws ClassNotFoundException {

		try {
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
			} else if (emp.getLastName() != null) {
				sqlUpdate.append(" LAST_NAME LIKE '");
				sqlUpdate.append(emp.getLastName()).append('%').append("'");
			}
			PreparedStatement searchStmt = con.prepareStatement(sqlUpdate.toString());
			// System.out.println(searchStmt.toString());

			ResultSet rs = searchStmt.executeQuery();

			while (rs.next()) {
				int empId = rs.getInt(1);
				String fullName = (rs.getString(2) + rs.getString(3) + rs.getString(4));
				String mailId = rs.getString(5);
				long mobileNo = rs.getLong(6);
				String birthDate = rs.getString(7);
				String empAddress = rs.getString(8);
				String empStatus = rs.getString(9);
				Date joinDate = rs.getDate(10);
				String gender = rs.getString(11);
				long sal = rs.getLong(12);
				String country = rs.getString(13);

				System.out.println("EMPLOYEE DETAILS BY NAME IS:" + empId + " , " + fullName + " ," + mailId + " , "
						+ mobileNo + " , " + birthDate + " , " + empAddress + " , " + empStatus + " , " + joinDate
						+ " , " + gender + " , " + sal + " , " + country);
			}
		} catch (Exception e) {
			System.out.println("Unable to try for search by name");
		}
		return emp;
	}

	// Search By ID Employees
	public Employee searchEmployeeById(Employee emp) throws ClassNotFoundException, SQLException {
		Connection con = ConnectionManager.getConnection();
		String sqlSearch = "SELECT * FROM EMPDETAILS WHERE EMPID =?";

		try {

			PreparedStatement searchStmt = con.prepareStatement(sqlSearch);
			searchStmt.setInt(1, emp.getEmpId());
			// System.out.println(searchStmt.toString());

			ResultSet rs = searchStmt.executeQuery();
			while (rs.next()) {
				int empId = rs.getInt(1);
				String fullName = (rs.getString(2) + rs.getString(3) + rs.getString(4));
				String mailId = rs.getString(5);
				long mobileNo = rs.getLong(6);
				String empAddress = rs.getString(7);
				String birthDate = rs.getString(8);
				String empStatus = rs.getString(9);
				Date joinDate1 = rs.getDate(10);
				String gender = rs.getString(11);
				long sal = rs.getLong(12);
				String country = rs.getString(13);

				System.out.println("EMPLOYEE DETAILS IS BY ID:" + empId + " , " + fullName + " ," + mailId + " , "
						+ mobileNo + " , " + birthDate + " , " + empAddress + " , " + empStatus + " , " + joinDate1
						+ " , " + gender + " , " + sal + " , " + country);
			}
		} catch (Exception e) {
			System.out.println("Unable to Search the Employee id");
		}
		return emp;

	}

	// Deactive Employees
	public boolean deActivatedEmployee(Employee emp) throws ClassNotFoundException, SQLException {

		Connection con = ConnectionManager.getConnection();
		String sqlDelete = "UPDATE EMPDETAILS SET EMPSTATUS=? WHERE EMPID=?";
		boolean isEmployeeDeactivated = false;

		try {

			PreparedStatement deactivedStmt = con.prepareStatement(sqlDelete);
			deactivedStmt.setString(1, emp.getEmpStatus());
			deactivedStmt.setInt(2, emp.getEmpId());

			int rowsDelete = deactivedStmt.executeUpdate();
			if (rowsDelete > 0) {
				System.out.println("Employee was Deactivated Successfully!");
			}
			isEmployeeDeactivated = true;
		} catch (Exception e) {
			System.out.println("unable to try");
		}
		return isEmployeeDeactivated;

	}

	// Retrieve all Employees
	public List<Employee> retrieveEmployee() throws ClassNotFoundException, SQLException {

		List<Employee> list = new ArrayList<Employee>();

		Connection con = ConnectionManager.getConnection();
		String sqlRetrieve = "SELECT * FROM EMPDETAILS";

		try {
			PreparedStatement retrieveStmt = con.prepareStatement(sqlRetrieve);
			ResultSet result = retrieveStmt.executeQuery(sqlRetrieve);

			// Retrieving the ResultSetMetadata object
			ResultSetMetaData rsMetaData = result.getMetaData();

			// Retrieving the list of column names
			int count = rsMetaData.getColumnCount();

			// Process the statements
			for (int i = 1; i <= count; i++) {
				System.out.print(rsMetaData.getColumnName(i) + "\t");
			}

			while (result.next()) {

				Employee emp = new Employee();

				int empId = result.getInt(1);
				String firstName = result.getString(2);
				String middleName = result.getString(3);
				String lastName = result.getString(4);
				String mailId = result.getString(5);
				long mobileNo = result.getLong(6);
				String empAddress = result.getString(7);
				String birthDate = result.getString(8);
				String empStatus = result.getString(9);
				Date joinDate2 = result.getDate(10);
				String gender = result.getString(11);
				long sal = result.getLong(12);
				String country = result.getString(13);

				emp.setEmpId(empId);
				emp.setFirstName(firstName);
				emp.setMiddleName(middleName);
				emp.setLastName(lastName);
				emp.setMailId(mailId);
				emp.setMobileNo(mobileNo);
				emp.setEmpAddress(empAddress);
				emp.setEmpbirthDate(result.getDate(8));
				emp.setEmpStatus(empStatus);
				emp.setJoinDate(joinDate2);
				emp.setGender(gender);
				emp.setSalary(sal);
				emp.setCountry(country);

				list.add(emp);

				System.out.println("\n");
				System.out.println("EMPLOYEE DETAILS IS BY ID:" + empId + " ," + firstName + " ," + middleName + " ,"
						+ lastName + " ," + mailId + " , " + mobileNo + " , " + birthDate + " , " + empAddress + " , "
						+ empStatus + " , " + joinDate2 + " , " + gender + " , " + sal + " , " + country);

			}
		} catch (Exception e) {
			System.out.println("Unable to Retrieve Employee Details");
		}
		return list;
	}
}

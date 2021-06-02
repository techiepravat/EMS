package org.comp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.comp.connection.EmployeeManagement;
import org.comp.pojo.Employee;
import org.comp.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import org.comp.connection.ConnectionManager;

public class EmployeeManagementJTest {
	private EmployeeManagement employeeManagement;

	@Before
	public void setUp() {
		employeeManagement = new EmployeeManagement();

	}

	@Test
	public void addEmployee_shouldReturnSuccess() throws ClassNotFoundException, SQLException, ParseException {

		List<Employee> employees = new ArrayList<Employee>();

		Employee emp = new Employee();
		emp.setFirstName("First Name");
		emp.setMiddleName("Middle Name");
		emp.setLastName("Last Name");
		emp.setMailId("a@gmail.com");
		emp.setMobileNo(9900193568L);
		Date birthDate = DateUtil.formatStringToDate("13-07-1991", "yyyy-MM-dd");
		emp.setEmpbirthDate(birthDate);
		emp.setEmpAddress("xyz");
		emp.setEmpStatus("A");
		emp.setGender("M");
		emp.setSalary(1000000);
		emp.setCountry("India");
		Date joiningDate = DateUtil.formatStringToDate("15-06-1991", "yyyy-MM-dd");
		emp.setJoinDate(joiningDate);

		// Populate employee object;
		employees.add(emp);

		try (MockedStatic<ConnectionManager> mockObject = Mockito.mockStatic(ConnectionManager.class)) {

			ResultSet mockedResultSet = Mockito.mock(ResultSet.class);
			Mockito.when(mockedResultSet.next()).thenReturn(true).thenReturn(false);
			Mockito.when(mockedResultSet.getInt(Mockito.anyInt())).thenReturn(101);

			// Mock of Preparedstatement
			PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);
			Mockito.when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockedResultSet);
			Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(1);

			// Mock of connection class
			Connection mockConnection = Mockito.mock(Connection.class);
			Mockito.when(mockConnection.prepareStatement(Mockito.anyString(), Mockito.anyInt()))
					.thenReturn(mockPreparedStatement);

			// lambda exp
			mockObject.when(() -> ConnectionManager.getConnection()).thenReturn(mockConnection);

			Map<Integer, String> actual = employeeManagement.addEmployee(employees);
			assertNotNull(actual);
			assertEquals(1, actual.size());

		}
	}

	@Test
	public void addEmployee_shouldReturnFailure() throws ClassNotFoundException, SQLException, ParseException {

		List<Employee> employees = new ArrayList<Employee>();

		Employee emp = new Employee();
		emp.setFirstName("First Name");
		emp.setMiddleName("Middle Name");
		emp.setLastName("Last Name");
		emp.setMailId("a@gmail.com");
		emp.setMobileNo(9900193568L);
		Date birthDate = DateUtil.formatStringToDate("13-07-1991", "yyyy-MM-dd");
		emp.setEmpbirthDate(birthDate);
		emp.setEmpAddress("xyz");
		emp.setEmpStatus("A");
		emp.setGender("M");
		emp.setSalary(1000000);
		emp.setCountry("India");
		Date joiningDate = DateUtil.formatStringToDate("15-06-1991", "yyyy-MM-dd");
		emp.setJoinDate(joiningDate);

		// Populate employee object;
		employees.add(emp);

		try (MockedStatic<ConnectionManager> mockObject = Mockito.mockStatic(ConnectionManager.class)) {

			ResultSet mockedResultSet = Mockito.mock(ResultSet.class);
			Mockito.when(mockedResultSet.next()).thenReturn(true).thenReturn(false);
			Mockito.when(mockedResultSet.getInt(Mockito.anyInt())).thenReturn(101);

			// Mock of Preparedstatement
			PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);
			Mockito.when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockedResultSet);
			Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(SQLException.class);

			// Mock of connection class
			Connection mockConnection = Mockito.mock(Connection.class);
			Mockito.when(mockConnection.prepareStatement(Mockito.anyString(), Mockito.anyInt()))
					.thenReturn(mockPreparedStatement);

			// lambda exp
			mockObject.when(() -> ConnectionManager.getConnection()).thenReturn(mockConnection);

			Map<Integer, String> actual = employeeManagement.addEmployee(employees);
			assertNotNull(actual);
			assertEquals(0, actual.size());

		}
	}
}

package org.comp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.comp.connection.ConnectionManager;
import org.comp.connection.EmployeeManagement;
import org.comp.pojo.Employee;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class SearchEmployeeTest {

	private EmployeeManagement employeeManagement;

	@Before
	public void setUp() {
		employeeManagement = new EmployeeManagement();

	}

	@Test
	public void searchEmployeeByFirstName_shouldReturnSuccess() throws ClassNotFoundException, SQLException {

		Employee emp = new Employee();
		emp.setFirstName("First Name");

		try (MockedStatic<ConnectionManager> mockObject = Mockito.mockStatic(ConnectionManager.class)) {

			Calendar cal = Calendar.getInstance();
			// cal.setTime(currentDate);
			// add 1 days to current day
			cal.add(Calendar.DAY_OF_MONTH, 2);

			Date joinDate = new Date(cal.getTimeInMillis());
			// System.out.println("Adding 1 days to current date: " + joinDate);

			ResultSet mockedResultSet = Mockito.mock(ResultSet.class);
			Mockito.when(mockedResultSet.next()).thenReturn(true).thenReturn(false);

			Mockito.when(mockedResultSet.getInt(1)).thenReturn(111);
			Mockito.when(mockedResultSet.getString(2)).thenReturn("Arya");
			Mockito.when(mockedResultSet.getString(3)).thenReturn("Sushree");
			Mockito.when(mockedResultSet.getString(4)).thenReturn("Priyadarshini");
			Mockito.when(mockedResultSet.getString(5)).thenReturn("arya@gmailcom");
			Mockito.when(mockedResultSet.getLong(6)).thenReturn(1234567897l);
			Mockito.when(mockedResultSet.getString(7)).thenReturn("1991-11-12");
			Mockito.when(mockedResultSet.getString(8)).thenReturn("Cuttack");
			Mockito.when(mockedResultSet.getString(9)).thenReturn("Inactive");
			Mockito.when(mockedResultSet.getDate(10)).thenReturn(joinDate);
			Mockito.when(mockedResultSet.getString(11)).thenReturn("Female");
			Mockito.when(mockedResultSet.getLong(12)).thenReturn(123458l);
			Mockito.when(mockedResultSet.getString(13)).thenReturn("India");

			// Mock of Preparedstatement
			PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);
			Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockedResultSet);

			// Mock of connection class
			Connection mockConnection = Mockito.mock(Connection.class);
			Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);

			mockObject.when(() -> ConnectionManager.getConnection()).thenReturn(mockConnection);
			Employee actual = employeeManagement.searchEmployeeByName(emp);
			assertNotNull(actual);
			assertEquals(emp, actual);
		}
	}

	@Test
	public void searchEmployeeByLastName_shouldReturnSuccess() throws ClassNotFoundException, SQLException {

		Employee emp = new Employee();
		emp.setLastName("last Name");

		try (MockedStatic<ConnectionManager> mockObject = Mockito.mockStatic(ConnectionManager.class)) {

			Calendar cal = Calendar.getInstance();
			// add 1 days to current day
			cal.add(Calendar.DAY_OF_MONTH, 2);

			Date joinDate = new Date(cal.getTimeInMillis());
			// System.out.println("Adding 1 days to current date: " + joinDate);

			ResultSet mockedResultSet = Mockito.mock(ResultSet.class);
			Mockito.when(mockedResultSet.next()).thenReturn(true).thenReturn(false);

			Mockito.when(mockedResultSet.getInt(1)).thenReturn(111);
			Mockito.when(mockedResultSet.getString(2)).thenReturn("Arya");
			Mockito.when(mockedResultSet.getString(3)).thenReturn("Sushree");
			Mockito.when(mockedResultSet.getString(4)).thenReturn("Priyadarshini");
			Mockito.when(mockedResultSet.getString(5)).thenReturn("arya@gmailcom");
			Mockito.when(mockedResultSet.getLong(6)).thenReturn(1234567897l);
			Mockito.when(mockedResultSet.getString(7)).thenReturn("1991-11-12");
			Mockito.when(mockedResultSet.getString(8)).thenReturn("Cuttack");
			Mockito.when(mockedResultSet.getString(9)).thenReturn("Inactive");
			Mockito.when(mockedResultSet.getDate(10)).thenReturn(joinDate);
			Mockito.when(mockedResultSet.getString(11)).thenReturn("Female");
			Mockito.when(mockedResultSet.getLong(12)).thenReturn(123458l);
			Mockito.when(mockedResultSet.getString(13)).thenReturn("India");

			// Mock of Preparedstatement
			PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);
			Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockedResultSet);

			// Mock of connection class
			Connection mockConnection = Mockito.mock(Connection.class);
			Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);

			mockObject.when(() -> ConnectionManager.getConnection()).thenReturn(mockConnection);
			Employee actual = employeeManagement.searchEmployeeByName(emp);
			assertNotNull(actual);
			assertEquals(emp, actual);
		}
	}

	@Test
	public void searchEmployeeByName_shouldReturnFailure() throws ClassNotFoundException, SQLException {

		Employee emp = new Employee();
		emp.setFirstName("First Name");

		try (MockedStatic<ConnectionManager> mockObject = Mockito.mockStatic(ConnectionManager.class)) {

			Calendar cal = Calendar.getInstance();
			// add 1 days to current day
			cal.add(Calendar.DAY_OF_MONTH, 2);

			Date joinDate = new Date(cal.getTimeInMillis());
			// System.out.println("Adding 1 days to current date: " + joinDate);

			ResultSet mockedResultSet = Mockito.mock(ResultSet.class);
			Mockito.when(mockedResultSet.next()).thenReturn(true).thenReturn(false);

			Mockito.when(mockedResultSet.getInt(1)).thenReturn(111);
			Mockito.when(mockedResultSet.getString(2)).thenReturn("Arya");
			Mockito.when(mockedResultSet.getString(3)).thenReturn("Sushree");
			Mockito.when(mockedResultSet.getString(4)).thenReturn("Priyadarshini");
			Mockito.when(mockedResultSet.getString(5)).thenReturn("arya@gmailcom");
			Mockito.when(mockedResultSet.getLong(6)).thenReturn(1234567897l);
			Mockito.when(mockedResultSet.getString(7)).thenReturn("1991-11-12");
			Mockito.when(mockedResultSet.getString(8)).thenReturn("Cuttack");
			Mockito.when(mockedResultSet.getString(9)).thenReturn("Inactive");
			Mockito.when(mockedResultSet.getDate(10)).thenReturn(joinDate);
			Mockito.when(mockedResultSet.getString(11)).thenReturn("Female");
			Mockito.when(mockedResultSet.getLong(12)).thenReturn(123458l);
			Mockito.when(mockedResultSet.getString(13)).thenReturn("India");

			// Mock of Preparedstatement
			PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);
			Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(SQLException.class);

			// Mock of connection class
			Connection mockConnection = Mockito.mock(Connection.class);
			Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);

			mockObject.when(() -> ConnectionManager.getConnection()).thenReturn(mockConnection);
			Employee actual = employeeManagement.searchEmployeeByName(emp);
			assertNotNull(actual);
			assertEquals(emp, actual);
		}
	}

	@Test
	public void searchEmployeeById_shouldReturnSuccess() throws ClassNotFoundException, SQLException {

		Employee emp = new Employee();
		emp.setEmpId(101);

		try (MockedStatic<ConnectionManager> mockObject = Mockito.mockStatic(ConnectionManager.class)) {

			Calendar cal = Calendar.getInstance();
			// add 1 days to current day
			cal.add(Calendar.DAY_OF_MONTH, 2);

			Date joinDate = new Date(cal.getTimeInMillis());
			// System.out.println("Adding 1 days to current date: " + joinDate);

			ResultSet mockedResultSet = Mockito.mock(ResultSet.class);
			Mockito.when(mockedResultSet.next()).thenReturn(true).thenReturn(false);

			Mockito.when(mockedResultSet.getInt(1)).thenReturn(101);
			Mockito.when(mockedResultSet.getString(2)).thenReturn("Pravat");
			Mockito.when(mockedResultSet.getString(3)).thenReturn("Kumar");
			Mockito.when(mockedResultSet.getString(4)).thenReturn("Patra");
			Mockito.when(mockedResultSet.getString(5)).thenReturn("pkp@gmailcom");
			Mockito.when(mockedResultSet.getLong(6)).thenReturn(1234567897l);
			Mockito.when(mockedResultSet.getString(7)).thenReturn("1991-10-25");
			Mockito.when(mockedResultSet.getString(8)).thenReturn("Kendrapada");
			Mockito.when(mockedResultSet.getString(9)).thenReturn("Active");
			Mockito.when(mockedResultSet.getDate(10)).thenReturn(joinDate);
			Mockito.when(mockedResultSet.getString(11)).thenReturn("Male");
			Mockito.when(mockedResultSet.getLong(12)).thenReturn(123458l);
			Mockito.when(mockedResultSet.getString(13)).thenReturn("India");

			// Mock of Preparedstatement
			PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);
			Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockedResultSet);

			// Mock of connection class
			Connection mockConnection = Mockito.mock(Connection.class);
			Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);

			mockObject.when(() -> ConnectionManager.getConnection()).thenReturn(mockConnection);
			Employee actual = employeeManagement.searchEmployeeById(emp);
			assertNotNull(actual);
			assertEquals(emp, actual);
		}
	}

	@Test
	public void searchEmployeeById_shouldReturnFailure() throws ClassNotFoundException, SQLException {

		Employee emp = new Employee();
		emp.setEmpId(101);

		try (MockedStatic<ConnectionManager> mockObject = Mockito.mockStatic(ConnectionManager.class)) {

			Calendar cal = Calendar.getInstance();
			// add 1 days to current day
			cal.add(Calendar.DAY_OF_MONTH, 2);

			Date joinDate = new Date(cal.getTimeInMillis());
			// System.out.println("Adding 1 days to current date: " + joinDate);
			ResultSet mockedResultSet = Mockito.mock(ResultSet.class);
			Mockito.when(mockedResultSet.next()).thenReturn(true).thenReturn(false);

			Mockito.when(mockedResultSet.getInt(1)).thenReturn(101);
			Mockito.when(mockedResultSet.getString(2)).thenReturn("Pravat");
			Mockito.when(mockedResultSet.getString(3)).thenReturn("Kumar");
			Mockito.when(mockedResultSet.getString(4)).thenReturn("Patra");
			Mockito.when(mockedResultSet.getString(5)).thenReturn("pkp@gmailcom");
			Mockito.when(mockedResultSet.getLong(6)).thenReturn(1234567897l);
			Mockito.when(mockedResultSet.getString(7)).thenReturn("1991-10-25");
			Mockito.when(mockedResultSet.getString(8)).thenReturn("Kendrapada");
			Mockito.when(mockedResultSet.getString(9)).thenReturn("Active");
			Mockito.when(mockedResultSet.getDate(10)).thenReturn(joinDate);
			Mockito.when(mockedResultSet.getString(11)).thenReturn("Male");
			Mockito.when(mockedResultSet.getLong(12)).thenReturn(123458l);
			Mockito.when(mockedResultSet.getString(13)).thenReturn("India");

			// Mock of Preparedstatement
			PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);
			Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(SQLException.class);

			// Mock of connection class
			Connection mockConnection = Mockito.mock(Connection.class);
			Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);

			mockObject.when(() -> ConnectionManager.getConnection()).thenReturn(mockConnection);
			Employee actual = employeeManagement.searchEmployeeById(emp);
			assertNotNull(actual);
			assertEquals(emp, actual);
		}
	}
}

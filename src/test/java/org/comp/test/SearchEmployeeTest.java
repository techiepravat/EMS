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

			ResultSet mockedResultSet = Mockito.mock(ResultSet.class);
			// TODO: current date using sql date
			////Date currentDate = new Date(0);
			//System.out.println("Current date: " + currentDate);

			Calendar cal = Calendar.getInstance();
			//cal.setTime(currentDate);

			// add 1 days to current day
			cal.add(Calendar.DAY_OF_MONTH, 2);

			Date datePlus1 = (Date) cal.getTime();
			System.out.println("Adding 1 days to current date: " + datePlus1);

			Mockito.when(mockedResultSet.next()).thenReturn(true).thenReturn(false);

			Mockito.when(mockedResultSet.getInt(1)).thenReturn(101);
			Mockito.when(mockedResultSet.getString(2)).thenReturn("fname");
			Mockito.when(mockedResultSet.getString(3)).thenReturn("mname");
			Mockito.when(mockedResultSet.getString(4)).thenReturn("lastname");
			Mockito.when(mockedResultSet.getString(5)).thenReturn("s@gmailcom");
			Mockito.when(mockedResultSet.getLong(6)).thenReturn(1234567897l);
			Mockito.when(mockedResultSet.getString(7)).thenReturn("1998-08-11");
			Mockito.when(mockedResultSet.getString(8)).thenReturn("address");
			Mockito.when(mockedResultSet.getString(9)).thenReturn("status");
			Mockito.when(mockedResultSet.getDate(10)).thenReturn(datePlus1);

			Mockito.when(mockedResultSet.getString(11)).thenReturn("gender");
			Mockito.when(mockedResultSet.getLong(12)).thenReturn(123458l);
			Mockito.when(mockedResultSet.getString(13)).thenReturn("country");

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
}
/*
 * @Test public void searchEmployeeByLastName_shouldReturnSuccess() throws
 * ClassNotFoundException, SQLException {
 * 
 * Employee emp = new Employee(); emp.setLastName("last Name");
 * 
 * try (MockedStatic<ConnectionManager> mockObject =
 * Mockito.mockStatic(ConnectionManager.class)) {
 * 
 * ResultSet mockedResultSet = Mockito.mock(ResultSet.class); // TODO: current
 * date using sql date Date date = new Date(new Date(0).getTime());
 * Mockito.when(mockedResultSet.next()).thenReturn(true).thenReturn(false);
 * 
 * Mockito.when(mockedResultSet.getInt(1)).thenReturn(101);
 * Mockito.when(mockedResultSet.getString(2)).thenReturn("fname");
 * Mockito.when(mockedResultSet.getString(3)).thenReturn("mname");
 * Mockito.when(mockedResultSet.getString(4)).thenReturn("lastname");
 * Mockito.when(mockedResultSet.getString(5)).thenReturn("s@gmailcom");
 * Mockito.when(mockedResultSet.getLong(6)).thenReturn(1234567897l);
 * Mockito.when(mockedResultSet.getString(7)).thenReturn("1998-08-11");
 * Mockito.when(mockedResultSet.getString(8)).thenReturn("address");
 * Mockito.when(mockedResultSet.getString(9)).thenReturn("status");
 * Mockito.when(mockedResultSet.getDate(10)).thenReturn(date);
 * 
 * Mockito.when(mockedResultSet.getString(11)).thenReturn("gender");
 * Mockito.when(mockedResultSet.getLong(12)).thenReturn(123458l);
 * Mockito.when(mockedResultSet.getString(13)).thenReturn("country");
 * 
 * // Mock of Preparedstatement PreparedStatement mockPreparedStatement =
 * Mockito.mock(PreparedStatement.class);
 * Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockedResultSet
 * );
 * 
 * // Mock of connection class Connection mockConnection =
 * Mockito.mock(Connection.class);
 * Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn
 * (mockPreparedStatement);
 * 
 * mockObject.when(() ->
 * ConnectionManager.getConnection()).thenReturn(mockConnection); Employee
 * actual = employeeManagement.searchEmployeeByName(emp); assertNotNull(actual);
 * assertEquals(emp, actual); } }
 * 
 * @Test public void searchEmployeeById_shouldReturnSuccess() throws
 * ClassNotFoundException, SQLException {
 * 
 * Employee emp = new Employee(); emp.setEmpId(101);
 * 
 * try (MockedStatic<ConnectionManager> mockObject =
 * Mockito.mockStatic(ConnectionManager.class)) {
 * 
 * ResultSet mockedResultSet = Mockito.mock(ResultSet.class);
 * Mockito.when(mockedResultSet.next()).thenReturn(true).thenReturn(false);
 * 
 * Mockito.when(mockedResultSet.getInt(1)).thenReturn(101);
 * Mockito.when(mockedResultSet.getString(2)).thenReturn("fname");
 * Mockito.when(mockedResultSet.getString(3)).thenReturn("mname");
 * Mockito.when(mockedResultSet.getString(4)).thenReturn("lastname");
 * Mockito.when(mockedResultSet.getString(5)).thenReturn("s@gmailcom");
 * Mockito.when(mockedResultSet.getLong(6)).thenReturn(1234567897l);
 * Mockito.when(mockedResultSet.getString(7)).thenReturn("1991-11-12");
 * Mockito.when(mockedResultSet.getString(8)).thenReturn("address");
 * Mockito.when(mockedResultSet.getString(9)).thenReturn("status");
 * Mockito.when(mockedResultSet.getObject(10)).thenReturn(1999 - 11 - 12);
 * Mockito.when(mockedResultSet.getString(11)).thenReturn("gender");
 * Mockito.when(mockedResultSet.getLong(12)).thenReturn(123458l);
 * Mockito.when(mockedResultSet.getString(13)).thenReturn("country");
 * 
 * // Mock of Preparedstatement PreparedStatement mockPreparedStatement =
 * Mockito.mock(PreparedStatement.class);
 * Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockedResultSet
 * );
 * 
 * // Mock of connection class Connection mockConnection =
 * Mockito.mock(Connection.class);
 * Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn
 * (mockPreparedStatement);
 * 
 * mockObject.when(() ->
 * ConnectionManager.getConnection()).thenReturn(mockConnection); Employee
 * actual = employeeManagement.searchEmployeeById(emp); assertNotNull(actual);
 * assertEquals(emp, actual); } }
 * 
 * @Test public void searchEmployeeById_shouldReturnFailure() throws
 * ClassNotFoundException, SQLException {
 * 
 * Employee emp = new Employee(); emp.setEmpId(101);
 * 
 * try (MockedStatic<ConnectionManager> mockObject =
 * Mockito.mockStatic(ConnectionManager.class)) {
 * 
 * ResultSet mockedResultSet = Mockito.mock(ResultSet.class);
 * Mockito.when(mockedResultSet.next()).thenReturn(true).thenReturn(false);
 * 
 * Mockito.when(mockedResultSet.getInt(1)).thenReturn(101);
 * Mockito.when(mockedResultSet.getString(2)).thenReturn("fname");
 * Mockito.when(mockedResultSet.getString(3)).thenReturn("mname");
 * Mockito.when(mockedResultSet.getString(4)).thenReturn("lastname");
 * Mockito.when(mockedResultSet.getString(5)).thenReturn("s@gmailcom");
 * Mockito.when(mockedResultSet.getLong(6)).thenReturn(1234567897l);
 * Mockito.when(mockedResultSet.getString(7)).thenReturn("1991-11-12");
 * Mockito.when(mockedResultSet.getString(8)).thenReturn("address");
 * Mockito.when(mockedResultSet.getString(9)).thenReturn("status");
 * Mockito.when(mockedResultSet.getObject(10)).thenReturn(1999 - 11 - 12);
 * Mockito.when(mockedResultSet.getString(11)).thenReturn("gender");
 * Mockito.when(mockedResultSet.getLong(12)).thenReturn(123458l);
 * Mockito.when(mockedResultSet.getString(13)).thenReturn("country");
 * 
 * // Mock of Preparedstatement PreparedStatement mockPreparedStatement =
 * Mockito.mock(PreparedStatement.class);
 * Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(SQLException.
 * class);
 * 
 * // Mock of connection class Connection mockConnection =
 * Mockito.mock(Connection.class);
 * Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn
 * (mockPreparedStatement);
 * 
 * mockObject.when(() ->
 * ConnectionManager.getConnection()).thenReturn(mockConnection); Employee
 * actual = employeeManagement.searchEmployeeById(emp); assertNotNull(actual);
 * assertEquals(emp, actual); } } }
 */
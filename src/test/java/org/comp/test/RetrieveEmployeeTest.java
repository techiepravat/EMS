package org.comp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.comp.connection.ConnectionManager;
import org.comp.connection.EmployeeManagement;
import org.comp.pojo.Employee;
import org.comp.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class RetrieveEmployeeTest {

	private EmployeeManagement employeeManagement;

	@Before
	public void setUp() {
		employeeManagement = new EmployeeManagement();

	}

	@Test
	public void retrieveEmployee_shouldReturnSuccess() throws ClassNotFoundException, SQLException, ParseException {

		Employee emp = new Employee();

		try (MockedStatic<ConnectionManager> mockObject = Mockito.mockStatic(ConnectionManager.class)) {

			ResultSetMetaData rsMetaData = Mockito.mock(ResultSetMetaData.class);
			Mockito.when(rsMetaData.getColumnName(1)).thenReturn("EmpId");
			Mockito.when(rsMetaData.getColumnName(2)).thenReturn("First Name");
			Mockito.when(rsMetaData.getColumnName(3)).thenReturn("Middle Name");
			Mockito.when(rsMetaData.getColumnName(4)).thenReturn("Last Name");
			Mockito.when(rsMetaData.getColumnName(5)).thenReturn("Gmail");
			Mockito.when(rsMetaData.getColumnName(6)).thenReturn("MobileNo");
			Mockito.when(rsMetaData.getColumnName(7)).thenReturn("BirthDate");
			Mockito.when(rsMetaData.getColumnName(8)).thenReturn("Address");
			Mockito.when(rsMetaData.getColumnName(9)).thenReturn("Status");
			Mockito.when(rsMetaData.getColumnName(10)).thenReturn("Joining Date");
			Mockito.when(rsMetaData.getColumnName(11)).thenReturn("Gender");
			Mockito.when(rsMetaData.getColumnName(12)).thenReturn("Salary");
			Mockito.when(rsMetaData.getColumnName(13)).thenReturn("Country");

			ResultSet mockedResultSet = Mockito.mock(ResultSet.class);
			Mockito.when(mockedResultSet.next()).thenReturn(true).thenReturn(false);

			Mockito.when(mockedResultSet.getInt(1)).thenReturn(101);
			Mockito.when(mockedResultSet.getString(2)).thenReturn("Arya");
			Mockito.when(mockedResultSet.getString(3)).thenReturn("sushree");
			Mockito.when(mockedResultSet.getString(4)).thenReturn("Priyadarshini");
			Mockito.when(mockedResultSet.getString(5)).thenReturn("s@gmail.com");
			Mockito.when(mockedResultSet.getLong(6)).thenReturn(1234567897l);
			Mockito.when(mockedResultSet.getString(7)).thenReturn("1998-08-11");
			Mockito.when(mockedResultSet.getString(8)).thenReturn("Cuttack");
			Mockito.when(mockedResultSet.getString(9)).thenReturn("Active");
			Mockito.when(mockedResultSet.getObject(10)).thenReturn("1999-11-12");
			Mockito.when(mockedResultSet.getString(11)).thenReturn("Female");
			Mockito.when(mockedResultSet.getLong(12)).thenReturn(123458l);
			Mockito.when(mockedResultSet.getString(13)).thenReturn("country");

			// Mock of Preparedstatement
			PreparedStatement mockRetrieveStmt = Mockito.mock(PreparedStatement.class);
			Mockito.when(mockRetrieveStmt.executeQuery()).thenReturn(mockedResultSet);

			// Mock of Connection class
			Connection mockConnection = Mockito.mock(Connection.class);

			mockObject.when(() -> ConnectionManager.getConnection()).thenReturn(mockConnection);
			List<Employee> actual = employeeManagement.retrieveEmployee();
			assertNotNull(actual);
			assertEquals(1, actual.size());
		}
	}

	@Test
	public void retrieveEmployee_shouldReturnFailure() throws ClassNotFoundException, SQLException, ParseException {

		Employee emp = new Employee();

		try (MockedStatic<ConnectionManager> mockObject = Mockito.mockStatic(ConnectionManager.class)) {

			ResultSetMetaData rsMetaData = Mockito.mock(ResultSetMetaData.class);
			Mockito.when(rsMetaData.getColumnName(1)).thenReturn("EmpId");
			Mockito.when(rsMetaData.getColumnName(2)).thenReturn("First Name");
			Mockito.when(rsMetaData.getColumnName(3)).thenReturn("Middle Name");
			Mockito.when(rsMetaData.getColumnName(4)).thenReturn("Last Name");
			Mockito.when(rsMetaData.getColumnName(5)).thenReturn("Gmail");
			Mockito.when(rsMetaData.getColumnName(6)).thenReturn("MobileNo");
			Mockito.when(rsMetaData.getColumnName(7)).thenReturn("BirthDate");
			Mockito.when(rsMetaData.getColumnName(8)).thenReturn("Address");
			Mockito.when(rsMetaData.getColumnName(9)).thenReturn("Status");
			Mockito.when(rsMetaData.getColumnName(10)).thenReturn("Joining Date");
			Mockito.when(rsMetaData.getColumnName(11)).thenReturn("Gender");
			Mockito.when(rsMetaData.getColumnName(12)).thenReturn("Salary");
			Mockito.when(rsMetaData.getColumnName(13)).thenReturn("Country");

			ResultSet mockedResultSet = Mockito.mock(ResultSet.class);
			Mockito.when(mockedResultSet.next()).thenReturn(true).thenReturn(false);

			Mockito.when(mockedResultSet.getInt(1)).thenReturn(101);
			Mockito.when(mockedResultSet.getString(2)).thenReturn("Arya");
			Mockito.when(mockedResultSet.getString(3)).thenReturn("sushree");
			Mockito.when(mockedResultSet.getString(4)).thenReturn("Priyadarshini");
			Mockito.when(mockedResultSet.getString(5)).thenReturn("s@gmail.com");
			Mockito.when(mockedResultSet.getLong(6)).thenReturn(1234567897l);
			Mockito.when(mockedResultSet.getString(7)).thenReturn("1998-08-11");
			Mockito.when(mockedResultSet.getString(8)).thenReturn("Cuttack");
			Mockito.when(mockedResultSet.getString(9)).thenReturn("Active");
			Mockito.when(mockedResultSet.getObject(10)).thenReturn("1999-11-12");
			Mockito.when(mockedResultSet.getString(11)).thenReturn("Female");
			Mockito.when(mockedResultSet.getLong(12)).thenReturn(123458l);
			Mockito.when(mockedResultSet.getString(13)).thenReturn("country");

			// Mock of Preparedstatement
			PreparedStatement mockRetrieveStmt = Mockito.mock(PreparedStatement.class);
			Mockito.when(mockRetrieveStmt.executeQuery()).thenThrow(SQLException.class);

			// Mock of connection class
			Connection mockConnection = Mockito.mock(Connection.class);

			mockObject.when(() -> ConnectionManager.getConnection()).thenReturn(mockConnection);
			List<Employee> actual = employeeManagement.retrieveEmployee();
			assertNull(actual);
			assertEquals(0, actual.size());
		}
	}
}

package org.comp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.comp.connection.ConnectionManager;
import org.comp.connection.EmployeeManagement;
import org.comp.pojo.Employee;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class DeactivatedEmployeeTest {
	private EmployeeManagement employeeManagement;

	@Before
	public void setUp() {
		employeeManagement = new EmployeeManagement();

	}

	@Test
	public void deActivatedEmployee_shouldReturnTrue() throws ClassNotFoundException, SQLException {

		Employee emp = new Employee();
		emp.setEmpStatus("active");
		emp.setEmpId(111);
		try (MockedStatic<ConnectionManager> mockObject = Mockito.mockStatic(ConnectionManager.class)) {

			// Mock of Preparedstatement
			PreparedStatement mockDeactiveStmt = Mockito.mock(PreparedStatement.class);
			Mockito.when(mockDeactiveStmt.executeUpdate()).thenReturn(101);

			// Mock of connection class
			Connection mockConnection = Mockito.mock(Connection.class);
			Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockDeactiveStmt);

			mockObject.when(() -> ConnectionManager.getConnection()).thenReturn(mockConnection);
			boolean actual = employeeManagement.deActivatedEmployee(emp);
			assertNotNull(actual);
			assertEquals(true, actual);
		}
	}
	@Test
	public void deActivatedEmployee_shouldReturnFalse() throws ClassNotFoundException, SQLException {

		Employee emp = new Employee();
		emp.setEmpStatus("active");
		emp.setEmpId(111);
		try (MockedStatic<ConnectionManager> mockObject = Mockito.mockStatic(ConnectionManager.class)) {

			// Mock of Preparedstatement
			PreparedStatement mockDeactiveStmt = Mockito.mock(PreparedStatement.class);
			Mockito.when(mockDeactiveStmt.executeUpdate()).thenThrow(SQLException.class);

			// Mock of connection class
			Connection mockConnection = Mockito.mock(Connection.class);
			Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockDeactiveStmt);

			mockObject.when(() -> ConnectionManager.getConnection()).thenReturn(mockConnection);
			boolean actual = employeeManagement.deActivatedEmployee(emp);
			assertNotNull(actual);
			assertEquals(false, actual);
		}
	}
	
}
package org.comp.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.comp.connection.ConnectionManager;
import org.comp.connection.EmployeeManagement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class UpdateEmployeeTest {
	private EmployeeManagement employeeManagement;

	@Before
	public void setUp() {
		employeeManagement = new EmployeeManagement();

	}

	@Test
	public void updatedEmployee_shouldReturnTrue() throws ClassNotFoundException, SQLException {
		
		try (MockedStatic<ConnectionManager> mockObject = Mockito.mockStatic(ConnectionManager.class)) {

		Connection mockConnection = Mockito.mock(Connection.class);
		}
	}
}

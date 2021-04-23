package org.comp.connection;

import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.comp.pojo.Employee;

public class EmployeeMnagementTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {

		int selection;
		Scanner sc = new Scanner(System.in);

		Employee emp = new Employee();
		List<Employee> employee = new ArrayList<Employee>();
		EmployeeManagement method = new EmployeeManagement();

		System.out.println("Employee Management System \n" + "Please choose an option");
		System.out.println("-------------------------\n");
		System.out.println("1 - Add Employee");
		System.out.println("2 - Edit/Update Employee");
		System.out.println("3 - Search Employee");
		System.out.println("4 - Delete Employee");
		System.out.println("5 - Retrieve Employee");
		System.out.println();

		System.out.println("Choose an option:");
		selection = sc.nextInt();
		switch (selection) {
		case 1:
			System.out.println("1 - Add Employee");
			System.out.println("------------------------");
			System.out.println("Please Enter Employee  First Name:");
			String fName = sc.next();
			System.out.println("Please Enter Employee Middle Name:");
			String mName = sc.next();
			System.out.println("Please Enter Employee Last Name:");
			String lName = sc.next();
			// String name = fName + mName + lName + sc.nextLine();
			System.out.println("Please Enter Employee Pesonal Mailid:");
			String mailId = sc.next();
			System.out.println("Please Enter Employee Address: ");
			String address = sc.next();
			System.out.println("Please Enter Employee Date of Birth: (yyyy-mm-dd)");
			String dob = sc.next();

			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dob);

			emp.setFirstName(fName);
			emp.setMiddleName(mName);
			emp.setLastName(lName);
			emp.setMailId(mailId);
			emp.setEmpAddress(address);
			emp.setEmpbirthDate(date1);
			emp.setEmpStatus("Active");

			employee.add(emp);
			method.addEmployee(employee);

			break;
		case 2:
			System.out.println("2 - Edit/Update Employee");
			System.out.println("------------------------");
			System.out.println("Enter your Employee id:");
			int id = sc.nextInt();

			System.out.println("What you want to update");
			System.out.println("1:- NAME");
			System.out.println("2:- ADDRESS");
			System.out.println("3:- NAME AND ADDRESS");

			System.out.println("Choose an option:");
			int selectionEdit = sc.nextInt();
			switch (selectionEdit) {
			case 1:
				System.out.println("Enter your Employee Name:");
				String name1 = sc.next();
				// emp.setEmpName(name1);
				emp.setEmpId(id);
				// method.upadteEmployee(emp);
				break;
			case 2:
				System.out.println("Enter your Employee Address:");

				String address1 = sc.next();
				emp.setEmpAddress(address1);
				emp.setEmpId(id);
				// employee.add(emp);
				// method.upadteEmployee(emp);
				break;
			case 3:
				System.out.println("Enter your Employee Name:");
				String name2 = sc.next();
				System.out.println("Enter your Employee Address:");
				String address2 = sc.next();
				// emp.setEmpName(name2);
				emp.setEmpAddress(address2);
				emp.setEmpId(id);
				// method.upadteEmployee(emp);
				break;

			default:
				System.out.println("Please select a valid option for Edit Employee");
				break;
			}
		case 3:
			System.out.println("3 - Search Employee");
			System.out.println("------------------------");

			System.out.println("What you want to Search");
			System.out.println("1:- BYID");
			System.out.println("2:- BYNAME");

			System.out.println("Choose an option:");
			int selectionSearch = sc.nextInt();
			switch (selectionSearch) {
			case 1:
				System.out.println("Enter your Employee Id:");
				int id1 = sc.nextInt();
				emp.setEmpId(id1);
				emp.setEmpStatus("Active");
				method.searchEmployeeById(emp);
				break;
			case 2:
				System.out.println("Enter your Employee Name:");
				String nameSerach = sc.next();
				// emp.setEmpName(nameSerach);
				emp.setEmpStatus("Active");
				method.searchEmployeeByName(emp);
				break;

			default:
				System.out.println("Please select a valid option for Edit Employee");
				break;
			}
		case 4:
			System.out.println("4 - Delete Employee");
			System.out.println("------------------------");
			System.out.println("Enter your Employee id:");
			int idDeactive = sc.nextInt();

			emp.setEmpId(idDeactive);
			emp.setEmpStatus("InActive");
			// employee.add(emp);
			method.deActivatedEmployee(emp);
			break;

		case 5:
			System.out.println("5 - Retrieve Employee");
			System.out.println("------------------------");

			method.retrieveEmployee(emp);
			break;
		}

		/*
		 * Employee emp = new Employee(); Employee emp1 = new Employee(); Employee emp2
		 * = new Employee();
		 * 
		 * emp.setEmpName("Aryasushree Priyadarshini"); emp.setEmpAddress("CUTTACK");
		 * emp.setStatus("Active"); employee.add(emp1); employee.add(emp2);
		 */

	}
}

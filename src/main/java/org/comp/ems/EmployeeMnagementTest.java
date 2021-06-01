package org.comp.ems;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.comp.connection.EmployeeManagement;
import org.comp.pojo.Employee;
import org.comp.util.DateUtil;
import org.comp.util.ValidateUtil;

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

			// MailId Validation
			System.out.println("Please Enter Employee Personal Mailid:");
			String mailId = sc.next();
			boolean isValid = ValidateUtil.validateMail(mailId);

			while (!isValid) {
				System.out.println("Please Enter Your Valid Email ID:");
				mailId = sc.next();
				isValid = ValidateUtil.validateMail(mailId);
			}
			// Mobile Number Validation
			System.out.println("Please Enter Employee Mobile Number: ");
			long mobileNo = sc.nextLong();
			boolean isValidNo = ValidateUtil.validateMobileNumber(mobileNo);

			while (!isValidNo) {
				System.out.println("Please Enter Your valid Mobile Number:");
				mobileNo = sc.nextLong();
				isValidNo = ValidateUtil.validateMobileNumber(mobileNo);
			}

			System.out.println("Please Enter Employee Date of Birth: (yyyy-mm-dd)");
			String dob = sc.next();
			System.out.println("Please Enter Employee Address: ");
			String address = sc.next();
			System.out.println("Please Enter Employee's Joining Date : (yyyy-mm-dd)");
			String joiningDate = sc.next();

			// Gender Validation
			System.out.println("Please Enter The Employee Gender :");
			System.out.println("1.MALE");
			System.out.println("2.FEMALE");
			System.out.println("3.TRANSGENDER");
			System.out.println("Choose an option:");
			int selectionGender = sc.nextInt();
			String gender = null;
			switch (selectionGender) {
			case 1:
				gender = "MALE";
				break;
			case 2:
				gender = "FEMALE";
				break;
			case 3:
				gender = "TRANSGENDER";
				break;
			default:
				System.out.println("Please give valid ");
				break;
			}

			System.out.println("Please Enter Employee's Salary:");
			Double sal = sc.nextDouble();

			// country validation
			String[] countries = { "India", "Australia", "Japan", "USA", "UAE", "Canada", "Brazil" };
			System.out.println("Please Enter Employee's Country:");
			String country = sc.next();
			boolean isCountryValid = ValidateUtil.validateCountry(countries, country);

			while (!isCountryValid) {
				System.out.println("Please Enter your valid Country:");
				country = sc.next();
				isCountryValid = ValidateUtil.validateCountry(countries, country);
			}
			// DOB Validation
			Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dob);

			// Employee JoinDate validation
			boolean isValidDate = DateUtil.isAfterCurrentDate(joiningDate);

			if (isValidDate) {
				Date joiningDate1 = DateUtil.formatStringToDate(joiningDate, "yyyy-MM-dd");
				emp.setJoinDate(joiningDate1);
			} else {
				System.out.println("invalid joining date");
			}

			emp.setFirstName(fName);
			emp.setMiddleName(mName);
			emp.setLastName(lName);
			emp.setMailId(mailId);
			emp.setMobileNo(mobileNo);
			emp.setEmpbirthDate(dateOfBirth);
			emp.setEmpAddress(address);
			emp.setGender(gender);
			emp.setSalary(sal);
			emp.setCountry(country.toUpperCase());

			emp.setEmpStatus("Active");

			employee.add(emp);
			method.addEmployee(employee);

			break;
		case 2:
			System.out.println("2 - Edit/Update Employee");
			System.out.println("------------------------");
			System.out.println("Enter your Employee id:");
			int id = sc.nextInt();

			System.out.println("What do you want to update ?");
			System.out.println("1:- NAME");
			System.out.println("2:- ADDRESS");
			System.out.println("3:- DATE OF BIRTH");
			System.out.println("4:- MAIL ID");
			System.out.println("5:- MOBILE NUMBER");
			System.out.println("6:- NAME AND ADDRESS");
			System.out.println("7:- NAME AND DATE OF BIRTH");

			System.out.println("Choose an option:");
			int selectionEdit = sc.nextInt();
			switch (selectionEdit) {
			case 1:

				System.out.println("What do you want to update ?");
				System.out.println("1:- UPDATE FIRST NAME");
				System.out.println("2:- UPDATE MIDDLE NAME");
				System.out.println("3:- UPDATE LAST NAME");

				System.out.println("Choose an option:");
				int selectionByName = sc.nextInt();
				switch (selectionByName) {

				case 1:
					System.out.println("Enter your Employee First Name For Update:");
					String firstName = sc.next();

					emp.setFirstName(firstName);
					emp.setEmpId(id);
					emp.setEmpStatus("Active");
					method.updateEmployee(emp);
					break;
				case 2:
					System.out.println("Enter Your Employee Middle Name For Update:");
					String middleName = sc.next();

					emp.setMiddleName(middleName);
					emp.setEmpId(id);
					emp.setEmpStatus("Active");
					method.updateEmployee(emp);
					break;
				case 3:
					System.out.println("Enter Your Employee Last Name For Update:");
					String lastName = sc.next();

					emp.setLastName(lastName);
					emp.setEmpId(id);
					emp.setEmpStatus("Active");
					method.updateEmployee(emp);
					break;
				}
				break;
			case 2:
				System.out.println("Enter Your Employee Address For Update:");
				String address1 = sc.next();

				emp.setEmpAddress(address1);
				emp.setEmpId(id);
				employee.add(emp);
				method.updateEmployee(emp);
				break;
			case 3:
				System.out.println("Enter Your Employee Date Of Birth (yyyy-MM-dd) :");
				String birthDate = sc.next();

				Date dateOf = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
				emp.setEmpbirthDate(dateOf);
				emp.setEmpId(id);
				method.updateEmployee(emp);
				break;
			case 4:
				System.out.println("Enter Your Personal Email ID:");
				String mail = sc.next();
				boolean isValidMail = ValidateUtil.validateMail(mail);

				while (!isValidMail) {
					System.out.println("Please Enter Your Valid Email ID:");
					mail = sc.next();
					isValidMail = ValidateUtil.validateMail(mail);
				}
				/*
				 * if (isValid) { emp.setMailId(mail); } else { // emp.setMailId(mail);
				 * System.out.println("EmailId :" + mail + " is " + isValid); }
				 */
				emp.setMailId(mail);
				emp.setEmpId(id);
				method.updateEmployee(emp);
				break;
			case 5:
				System.out.println("Enter Your Mobile Number:");
				long mobileNo1 = sc.nextLong();
				boolean isValidNo1 = ValidateUtil.validateMobileNumber(mobileNo1);

				while (!isValidNo1) {
					System.out.println("Please Enter Your valid Mobile Number:");
					mobileNo1 = sc.nextLong();
					isValidNo1 = ValidateUtil.validateMobileNumber(mobileNo1);
				}
				emp.setMobileNo(mobileNo1);
				emp.setEmpId(id);
				method.updateEmployee(emp);
				break;
			case 6:
				System.out.println("Enter Your Employee First Name For Update:");
				String uName = sc.next();
				System.out.println("Enter Your Employee Middle Name For Update:");
				String upName = sc.next();
				System.out.println("Enter Your Employee Last Name For Update:");
				String dName = sc.next();
				System.out.println("Enter Your Employee Address For Update:");
				String address2 = sc.next();

				emp.setFirstName(uName);
				emp.setMiddleName(upName);
				emp.setLastName(dName);

				emp.setEmpAddress(address2);
				emp.setEmpId(id);
				method.updateEmployee(emp);
				break;

			case 7:
				System.out.println("Enter Your Employee First Name For Update:");
				String name1 = sc.next();
				System.out.println("Enter Your Employee Middle Name For Update:");
				String name2 = sc.next();
				System.out.println("Enter Your Employee Last Name For Update:");
				String name3 = sc.next();
				System.out.println("Enter Your Employee Date Of Birth(yyyy-MM-dd) :");
				String dateBirth = sc.next();

				Date birth = new SimpleDateFormat("yyyy-MM-dd").parse(dateBirth);

				emp.setFirstName(name1);
				emp.setMiddleName(name2);
				emp.setLastName(name3);
				emp.setEmpbirthDate(birth);
				emp.setEmpId(id);
				method.updateEmployee(emp);
				break;

			default:
				System.out.println("Please select a valid option for Edit Employee");
				break;
			}
			break;

		case 3:
			System.out.println("3 - Search Employee");
			System.out.println("------------------------");

			System.out.println("What do you want to Search ?");
			System.out.println("1:- BY ID");
			System.out.println("2:- BY NAME");

			System.out.println("Choose an option:");
			int selectionSearch = sc.nextInt();
			switch (selectionSearch) {
			case 1:

				System.out.println("Enter Your Employee Id:");
				int id1 = sc.nextInt();
				emp.setEmpId(id1);
				emp.setEmpStatus("Active");
				method.searchEmployeeById(emp);
				break;
			case 2:

				System.out.println("What do you want to Search ?");
				System.out.println("1:- SEARCH BY FIRST NAME");
				System.out.println("2:- SEARCH BY LAST NAME");

				System.out.println("Choose an option:");
				int selectionSearch1 = sc.nextInt();
				switch (selectionSearch1) {
				case 1:
					System.out.println("Enter Your Employee First Name For Search:");
					String nameSerach = sc.next();
					emp.setFirstName(nameSerach);
					emp.setEmpStatus("Active");
					method.searchEmployeeByName(emp);
					break;
				case 2:
					System.out.println("Enter Your Employee Last Name For Search:");
					String nameSerach2 = sc.next();
					emp.setLastName(nameSerach2);
					emp.setEmpStatus("Active");
					method.searchEmployeeByName(emp);
					break;
				}
			default:
				System.out.println("Please select a valid option for search Employee");
				break;
			}
			break;
		case 4:

			System.out.println("4 - Delete Employee");
			System.out.println("------------------------");
			System.out.println("Enter Your Employee id:");
			int idDeactive = sc.nextInt();

			emp.setEmpId(idDeactive);
			emp.setEmpStatus("InActive");
			employee.add(emp);
			method.deActivatedEmployee(emp);
			break;

		case 5:
			System.out.println("5 - Retrieve Employee");
			System.out.println("------------------------");

			method.retrieveEmployee();
			break;
		}

		/*
		 * Employee emp = new Employee(); Employee emp1 = new Employee(); Employee emp2
		 * = new Employee(); emp.setEmpName("Aryasushree Priyadarshini");
		 * emp.setEmpAddress("CUTTACK"); emp.setStatus("Active"); employee.add(emp1);
		 * employee.add(emp2);
		 */
		// break problem
		// tabular form
		// Exception
		// how to add list of employees from console

	}
}

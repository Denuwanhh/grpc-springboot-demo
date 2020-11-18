package demo.employee.service;

import java.util.ArrayList;
import java.util.List;

import demo.interfaces.grpc.Employee;

public class EmployeeResourceProvider {
	
	/**
	 * In order to simplify the solution, use this static list as source of
	 * Employees
	 * 
	 * @return Employee List
	 */
	public static List<Employee> getEmployeeListfromEmployeeSource() {
		return new ArrayList<Employee>() {
			{
				add(Employee.newBuilder().setEmployeeID(1l).setEmployeeFirstName("Sam")
						.setEmployeeLastName("Newman").setEmployeeDateOfBirth(709324200000l)
						.setEmployeeWorkingYears(30).build());
				add(Employee.newBuilder().setEmployeeID(2l).setEmployeeFirstName("Martin")
						.setEmployeeLastName("Fowler").setEmployeeDateOfBirth(677701800000l)
						.setEmployeeWorkingYears(40).build());
				add(Employee.newBuilder().setEmployeeID(3l).setEmployeeFirstName("Kevlin")
						.setEmployeeLastName("Henney").setEmployeeDateOfBirth(828124200000l)
						.setEmployeeWorkingYears(35).build());
			}
		};
	}
}

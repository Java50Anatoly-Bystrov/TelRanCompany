package TelranPeople;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class Company {
    private Employee[] employees;

    public Company(Employee[] employees) {
        this.employees = employees;
    }

    public Employee[] getAllEmployees() {
        Employee[] allEmployees = Arrays.copyOf(employees, employees.length);
       
        return allEmployees;
    }
    public Employee[] getEmployeesByAge(int yearFrom, int yearTo) {
        Employee[] filteredEmployees = Arrays.stream(employees)
                .filter(emp -> emp.getBirthYear() >= yearFrom && emp.getBirthYear() <= yearTo)
                .toArray(Employee[]::new);
        Arrays.sort(filteredEmployees, Comparator.comparingInt(Employee::getBirthYear));
        return filteredEmployees;
    }

    public Employee[] getEmployeesBySalary(int salaryFrom, int salaryTo) {
        return Arrays.stream(employees)
                .filter(employee -> employee != null && employee.getSalary() >= salaryFrom && employee.getSalary() <= salaryTo)
                .sorted(Comparator.comparingInt(Employee::getSalary))
                .toArray(Employee[]::new);
    }

    

    public Employee[] getEmployeesByDepartment(String department) {
        Employee[] filteredEmployees = Arrays.stream(employees)
                .filter(emp -> emp.getDepartment().equals(department))
                .toArray(Employee[]::new);
        Arrays.sort(filteredEmployees, Comparator.comparingInt(Employee::getId));
        return filteredEmployees;
    }

    public boolean addEmployee(Employee employee) {
        int id = employee.getId();
        for (Employee emp : employees) {
            if (emp != null && emp.getId() == id) {
                return false; // Employee with the same ID already exists
            }
        }

        Employee[] newEmployees = new Employee[employees.length + 1];

       
        // Copy existing employees to the new array
        System.arraycopy(employees, 0, newEmployees, 0, employees.length);

        // Find the first available slot in the new array to add the new employee
        for (int i = 0; i < newEmployees.length; i++) {
            if (newEmployees[i] == null) {
                newEmployees[i] = employee;
                employees = newEmployees; // Update the employees reference to point to the new array
                return true; // Employee added successfully
            }
        }
		return false;
    }
    
    public boolean removeEmployeesIf(Predicate<Employee> predicate) {
        boolean removed = false;
        for (int i = 0; i < employees.length; i++) {
            if (predicate.test(employees[i])) {
                employees[i] = null;
                removed = true;
            }
        }
        if (removed) {
            employees = Arrays.stream(employees)
                    .filter(emp -> emp != null)
                    .toArray(Employee[]::new);
        }
        return removed;
    }

    //By what?
    public Employee getEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null; 
    }
}
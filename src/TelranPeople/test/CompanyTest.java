package TelranPeople.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TelranPeople.Company;
import TelranPeople.Employee;

class CompanyTest {
    private Company company;
    private Employee[] employees;

    @BeforeEach
    void setUp() {
        employees = new Employee[] {
                new Employee(1, "Alyosha", 1985, "Sales", 5000),
                new Employee(2, "Stav", 1990, "HR", 4500),
                new Employee(5, "Eden", 1988, "Sales", 5500),
                new Employee(4, "Maks", 1987, "IT", 6000),
                new Employee(3, "Yuri", 1992, "IT", 5600)
        };
        company = new Company(employees);
    }

    @Test
    void testGetAllEmployees() {
        Employee[] expected = new Employee[] {
                new Employee(1, "Alyosha", 1985, "Sales", 5000),
                new Employee(2, "Stav", 1990, "HR", 4500),
                new Employee(5, "Eden", 1988, "Sales", 5500),
                new Employee(4, "Maks", 1987, "IT", 6000),
                new Employee(3, "Yuri", 1992, "IT", 5600)
        };
        
        assertArrayEquals(expected, company.getAllEmployees());
    }

    @Test
    void testGetEmployeesByAge() {
        Employee[] expected = new Employee[]{employees[0], employees[3], employees[2]};
        assertArrayEquals(expected, company.getEmployeesByAge(1985, 1988));
    }

    @Test
    void testGetEmployeesBySalary() {
        Employee[] expected = new Employee[]{
            employees[1],
            employees[0],
            employees[2],
            employees[4],
            employees[3]
        };
        Employee[] actual = company.getEmployeesBySalary(4500, 6000);

        assertArrayEquals(expected, actual);
    }

    @Test
    void testGetEmployeesByDepartment() {
        Employee[] expected = new Employee[]{employees[0], employees[2]};
        assertArrayEquals(expected, company.getEmployeesByDepartment("Sales"));
    }

    @Test
    void testAddEmployee() {
        Employee newEmployee = new Employee(6, "Laura", 1995, "Marketing", 4000);
        assertTrue(company.addEmployee(newEmployee));
        assertEquals(newEmployee, company.getEmployee(6));
    }

    
    @Test
    void testAddEmployee_DuplicateId() {
        Employee duplicateEmployee = new Employee(2, "Chris", 1989, "Finance", 5000);
        assertFalse(company.addEmployee(duplicateEmployee));
    }

    void testRemoveEmployeesIf() {
        Predicate<Employee> predicate = employee -> employee.getSalary() < 5500;

        // Remove employees with a salary less than 5500
        boolean removed = company.removeEmployeesIf(predicate);

        // Verify the expected employees are removed
        assertTrue(removed);
        assertEquals(3, company.getAllEmployees().length);

        // Get the names of remaining employees
        String[] remainingNames = Arrays.stream(company.getAllEmployees())
                .map(Employee::getName)
                .toArray(String[]::new);

        // Verify the employees with a salary of 5500 or greater are still present
        Employee[] remainingEmployees = company.getEmployeesBySalary(5500, Integer.MAX_VALUE);
        assertEquals(2, remainingEmployees.length);
        assertTrue(Arrays.asList(remainingNames).contains(remainingEmployees[0].getName()));
        assertTrue(Arrays.asList(remainingNames).contains(remainingEmployees[1].getName()));
    }
}
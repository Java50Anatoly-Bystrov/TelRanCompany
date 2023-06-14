package TelranPeople;

import java.util.Objects;

public class Employee implements Comparable<Employee> {
    private int id;
    private String name;
    private int birthYear;
    private String department;
    private int salary;

    public Employee(int id, String name, int birthYear, String department, int salary) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }
    public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}


	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    Employee employee = (Employee) o;
	    return id == employee.id &&
	            name.equals(employee.name) &&
	            department.equals(employee.department) &&
	            salary == employee.salary;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(id, name, department, salary);
	}
    @Override
    public String toString() {
        return "Employee{name=" + name + ", department=" + department + ", salary=" + salary + "}";
    }
    @Override
    public int compareTo(Employee other) {
        return Double.compare(this.salary, other.salary);
    }
}
	
import java.util.*;
import java.util.stream.Collectors;
import java.util.Comparator;

class Employee {

    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return String.format("Employee{name='%s', salary=%.2f}", name, salary);
    }
}

class Department {

    private String name;
    private List<Employee> employeeList;

    public Department(String name, List<Employee> employeeList) {
        this.name = name;
        this.employeeList = employeeList;
    }

    public String getName() { return name; }
    public List<Employee> getEmployeeList() { return employeeList; }

    @Override
    public String toString() {
        return String.format("Department{name='%s', employees=%s}", name, employeeList);
    }
}

public class Stream3Department {

    public static void main(String[] args) {

        List<Department> departmentList = Arrays.asList(
                new Department("HR", Arrays.asList(
                        new Employee("Alice", 5000),
                        new Employee("Bob", 4500)
                )),
                new Department("IT", Arrays.asList(
                        new Employee("Charlie", 7000),
                        new Employee("Diana", 6500)
                ))
        );

        // Get all employees across all departments using flatMap
        List<Employee> allEmployeeList = departmentList.stream()
                .flatMap(department -> department.getEmployeeList().stream())
                .toList();

        System.out.println("All Employees: " + allEmployeeList);

        /*
        All Employees:
        [Employee{name='Alice', salary=5000.00},
         Employee{name='Bob', salary=4500.00},
         Employee{name='Charlie', salary=7000.00},
         Employee{name='Diana', salary=6500.00}]
        */


        // Extract only employee names from all departments
        List<String> employeeNameList = departmentList.stream()
                .flatMap(department -> department.getEmployeeList().stream())
                .map(Employee::getName)
                .toList();
        System.out.println("Employee Names: " + employeeNameList);
        // Employee Names: [Alice, Bob, Charlie, Diana]
        

        // Collect all salaries into a single list
        List<Double> salaryList = departmentList.stream()
                .flatMap(department -> department.getEmployeeList().stream())
                .map(Employee::getSalary)
                .toList();
        System.out.println("All Salaries: " + salaryList);
        // All Salaries: [5000.0, 4500.0, 7000.0, 6500.0]
        

        // Filter employees with salary greater than 5000
        List<Employee> highSalaryEmployeeList = departmentList.stream()
                .flatMap(department -> department.getEmployeeList().stream())
                .filter(employee -> employee.getSalary() > 5000)
                .toList();
        System.out.println("High Salary Employees: " + highSalaryEmployeeList);
        // High Salary Employees:[Employee{name='Charlie', salary=7000.00}, Employee{name='Diana', salary=6500.00}]
        

        // Calculate total salary using reduce
        double totalSalary = departmentList.stream()
                .flatMap(department -> department.getEmployeeList().stream())
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);
        System.out.println("Total Salary (reduce): $" + totalSalary);
        // Total Salary (reduce): $23000.0

        // Calculate total salary using mapToDouble and sum
        double totalSalaryAlt = departmentList.stream()
                .flatMap(department -> department.getEmployeeList().stream())
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("Total Salary: $" + totalSalaryAlt);
        // Total Salary: $23000.0


        // Extract only maximum salary value
        double maxSalaryValue = departmentList.stream()
                .flatMap(department -> department.getEmployeeList().stream())
                .map(Employee::getSalary)
                .reduce(0.0, Double::max);        
        System.out.println("Max Salary Value: $" + maxSalaryValue);
        // Max Salary Alternative Value: $7000.0

        double  maxSalaryAlternative = departmentList.stream()
                .flatMap(department -> department.getEmployeeList().stream())
                .mapToDouble(Employee::getSalary)
                .max()
                .orElse(0.0);
        System.out.println("Max Salary Alternative Value: $" + maxSalaryAlternative);
        // Max Salary Value: $7000.0
        

        // Extract only minimum salary value
        Optional<Double> minSalaryValue = departmentList.stream()
                .flatMap(department -> department.getEmployeeList().stream())
                .map(Employee::getSalary)
                .reduce(Double::min);
        System.out.println("Min Salary Value: $" + minSalaryValue.orElse(0.0));
        // Min Salary Value: $4500.0

        double minSalaryValueAlternative = departmentList.stream()
                .flatMap(department -> department.getEmployeeList().stream())
                .mapToDouble(Employee::getSalary)
                .min()
                .orElse(0.0);
        System.out.println("Min Salary Alternative Value: $" + minSalaryValueAlternative);
        // Min Salary Value: $4500.0
        

        // Find employee with maximum salary
        Optional<Employee> maxSalaryEmployee = departmentList.stream()
                .flatMap(department -> department.getEmployeeList().stream())
                .max(Comparator.comparing(Employee::getSalary));
        System.out.println("Max Salary Employee: " + maxSalaryEmployee.orElse(null));
        // Max Salary Employee: Employee{name='Charlie', salary=7000.00}
        

        // Find employee with minimum salary
        Optional<Employee> minSalaryEmployee = departmentList.stream()
                .flatMap(department -> department.getEmployeeList().stream())
                .min(Comparator.comparing(Employee::getSalary));
        System.out.println("Min Salary Employee: " + minSalaryEmployee.orElse(null));
        // Min Salary Employee: Employee{name='Bob', salary=4500.00}
        

        // Group employees by department name
        Map<String, List<Employee>> employeesGroupedByDepartment =
                departmentList.stream()
                        .collect(Collectors.toMap(
                            Department::getName,
                            Department::getEmployeeList
                        )
                    );

        employeesGroupedByDepartment.forEach((departmentName, employeeList) ->
                System.out.println(departmentName + " -> " + employeeList));

        /*
        HR -> [Employee{name='Alice', salary=5000.00},
               Employee{name='Bob', salary=4500.00}]
        IT -> [Employee{name='Charlie', salary=7000.00},
               Employee{name='Diana', salary=6500.00}]
        */
    }
}

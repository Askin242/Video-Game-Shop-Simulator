package fr.meow.simulator.core.entity.employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager extends Employee {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeManager(String color, String name, String type) {
        super(color, name, type);
    }

    public void addEmployee(Employee employee) {
        if (employee == null) return;
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}

package fr.meow.simulator.core.entity.employee;

import fr.meow.simulator.core.SimulatorObject;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager extends SimulatorObject {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeManager(String name) {
        super(name);
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

    @Override
    public void newGameDay() {
        for (Employee employee : employees) {
            employee.DoTask();
        }
    }
    
    public void update() {
        for (Employee employee : employees) {
            employee.DoTask();
        }
    }
}

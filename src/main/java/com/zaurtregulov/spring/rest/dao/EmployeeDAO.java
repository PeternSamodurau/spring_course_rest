package com.zaurtregulov.spring.rest.dao;

import com.zaurtregulov.spring.rest.entity.Employee;
import java.util.List;

//DAO (Data Access Object): DAO - это интерфейс, который предоставляет методы для доступа к данным.
//DAO - это способ доступа к данным, которые хранятся в базе данных.
//DAO - абстрагирует доступ к базе данных,
// что позволяет бизнес-логике приложения не зависеть от конкретной базы данных.
public interface EmployeeDAO {
    public List<Employee> getAllEmployees();
    public void saveEmployee(Employee employee);
    public Employee getEmployee(int id);
    public Employee getEmployee(String name);
    public void deleteEmployee(int id);
}

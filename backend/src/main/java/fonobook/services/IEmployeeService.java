package fonobook.services;

import fonobook.models.Employee;
import fonobook.models.EmployeeDTO;

import java.util.List;

public interface IEmployeeService {

    List<EmployeeDTO> getAllEmployees();
    Employee getEmployeeById(final Long id);
    void createEmployee(final EmployeeDTO employee);
}

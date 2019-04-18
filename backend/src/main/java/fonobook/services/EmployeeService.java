package fonobook.services;

import fonobook.models.Employee;
import fonobook.models.EmployeeDTO;
import fonobook.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class EmployeeService implements IEmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.getOne(id);
    }

    @Override
    public void createEmployee(EmployeeDTO employeeDTO) {
        employeeRepository.save(modelMapper.map(employeeDTO, Employee.class));
    }
}

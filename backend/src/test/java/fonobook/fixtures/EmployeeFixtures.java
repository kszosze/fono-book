package fonobook.fixtures;

import fonobook.models.Employee;
import fonobook.models.EmployeeDTO;

public class EmployeeFixtures {

    public static final EmployeeDTO EMPLOYEE_DTO = EmployeeDTO.builder().employeeId("11111").depto("test").name("John").surname("Smith").role("tester").build();

    public static final Employee EMPLOYEE = Employee.builder().employeeId("11111").depto("test").name("John").surname("Smith").role("tester").id(1L).build();

}

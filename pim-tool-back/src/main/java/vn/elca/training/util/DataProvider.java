package vn.elca.training.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import vn.elca.training.ApplicationWebConfig;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Project;
import vn.elca.training.repository.EmployeeRepository;
import vn.elca.training.repository.ProjectRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataProvider {
    static ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationWebConfig.class);
    static ProjectRepository projectRepository = context.getBean(ProjectRepository.class);
    static EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);

    public static void init() {
        Project prjEFV = projectRepository.findByName("EFV");
        Employee jhd = employeeRepository.findEmployeeByVisa("JHD");
        Employee hoa = employeeRepository.findEmployeeByVisa("HOA");

        Set<Employee> employees = new HashSet<>();
        employees.add(jhd);
        employees.add(hoa);

        prjEFV.setEmployees(employees);
        projectRepository.save(prjEFV);
    }
}

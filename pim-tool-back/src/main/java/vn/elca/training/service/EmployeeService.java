package vn.elca.training.service;

import vn.elca.training.model.entity.Employee;

/**
 * @author gtn
 */
public interface EmployeeService {
    Employee findOne(Long id);

    Employee findOne(String visa);

    Employee update(Employee user);
}

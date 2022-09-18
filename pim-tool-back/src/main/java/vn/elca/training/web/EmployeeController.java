package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.service.EmployeeService;

/**
 * @author gtn
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController extends AbstractApplicationController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/id/{id}")
    public Employee findOne(@PathVariable Long id) {
        return employeeService.findOne(id);
    }

    @PostMapping({"/update"})
    public Employee update(@RequestBody Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Invalid request! Employee not found");
        }

        return employeeService.update(employee);
    }
}

package org.up.roque.project.employee;

import org.up.roque.project.Project;
import org.up.roque.project.util.RelatedTo;
import org.up.roque.project.util.Service;

public interface EmployeeService extends Service<Employee, Integer>, RelatedTo<Employee, Project> {
}

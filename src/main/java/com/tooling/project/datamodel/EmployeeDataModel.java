package com.tooling.project.datamodel;  
  
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.tooling.project.model.Employee;
  
public class EmployeeDataModel extends ListDataModel<Employee> implements SelectableDataModel<Employee> {    
  
    public EmployeeDataModel() {  
    }  
  
    public EmployeeDataModel(List<Employee> data) {  
        super(data);  
    }  
      
    @Override  
    @SuppressWarnings("unchecked")
    public Employee getRowData(String rowKey) {  
          
        List<Employee> employees = (List<Employee>) getWrappedData();  
          
        for(Employee employee : employees) {  
            if(employee.getId().toString().equals(rowKey))
                return employee;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Employee e) {  
        return e;  
    }  
} 
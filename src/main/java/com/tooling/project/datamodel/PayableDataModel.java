package com.tooling.project.datamodel;  
  
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.tooling.project.model.Payable;
  
public class PayableDataModel extends ListDataModel<Payable> implements SelectableDataModel<Payable> {    
  
    public PayableDataModel() {  
    }  
  
    public PayableDataModel(List<Payable> data) {  
        super(data);  
    }  
      
    @Override  
    @SuppressWarnings("unchecked")
    public Payable getRowData(String rowKey) {  
          
        List<Payable> employees = (List<Payable>) getWrappedData();  
          
        for(Payable employee : employees) {  
            if(employee.getId().toString().equals(rowKey))
                return employee;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Payable e) {  
        return e;  
    }  
} 
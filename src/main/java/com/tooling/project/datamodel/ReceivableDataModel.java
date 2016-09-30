package com.tooling.project.datamodel;  
  
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.tooling.project.model.Receivable;
  
public class ReceivableDataModel extends ListDataModel<Receivable> implements SelectableDataModel<Receivable> {    
  
    public ReceivableDataModel() {  
    }  
  
    public ReceivableDataModel(List<Receivable> data) {  
        super(data);  
    }  
      
    @Override  
    @SuppressWarnings("unchecked")
    public Receivable getRowData(String rowKey) {  
          
        List<Receivable> employees = (List<Receivable>) getWrappedData();  
          
        for(Receivable employee : employees) {  
            if(employee.getId().toString().equals(rowKey))
                return employee;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Receivable e) {  
        return e;  
    }  
} 
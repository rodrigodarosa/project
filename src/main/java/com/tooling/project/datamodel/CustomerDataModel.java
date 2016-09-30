package com.tooling.project.datamodel;  
  
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.tooling.project.model.Customer;
  
public class CustomerDataModel extends ListDataModel<Customer> implements SelectableDataModel<Customer> {    
  
    public CustomerDataModel() {  
    }  
  
    public CustomerDataModel(List<Customer> data) {  
        super(data);  
    }  
      
    @Override  
    @SuppressWarnings("unchecked")
    public Customer getRowData(String rowKey) {  
          
        List<Customer> customers = (List<Customer>) getWrappedData();  
          
        for(Customer customer : customers) {  
            if(customer.getId().toString().equals(rowKey))
                return customer;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Customer e) {  
        return e;  
    }  
} 
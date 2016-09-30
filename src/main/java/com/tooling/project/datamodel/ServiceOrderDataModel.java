package com.tooling.project.datamodel;  
  
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.tooling.project.model.ServiceOrder;
  
public class ServiceOrderDataModel extends ListDataModel<ServiceOrder> implements SelectableDataModel<ServiceOrder> {    
  
    public ServiceOrderDataModel() {  
    }  
  
    public ServiceOrderDataModel(List<ServiceOrder> data) {  
        super(data);  
    }  
      
    @Override  
    @SuppressWarnings("unchecked")
    public ServiceOrder getRowData(String rowKey) {  
          
        List<ServiceOrder> serviceOrders = (List<ServiceOrder>) getWrappedData();  
          
        for(ServiceOrder serviceOrder : serviceOrders) {  
            if(serviceOrder.getId().toString().equals(rowKey))
                return serviceOrder;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(ServiceOrder e) {  
        return e;  
    }  
} 
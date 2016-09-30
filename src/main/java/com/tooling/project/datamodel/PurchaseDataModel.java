package com.tooling.project.datamodel;  
  
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.tooling.project.model.Purchase;
  
public class PurchaseDataModel extends ListDataModel<Purchase> implements SelectableDataModel<Purchase> {    
  
    public PurchaseDataModel() {  
    }  
  
    public PurchaseDataModel(List<Purchase> data) {  
        super(data);  
    }  
      
    @Override  
    @SuppressWarnings("unchecked")
    public Purchase getRowData(String rowKey) {  
          
        List<Purchase> purchases = (List<Purchase>) getWrappedData();  
          
        for(Purchase purchase : purchases) {  
            if(purchase.getId().toString().equals(rowKey))
                return purchase;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Purchase e) {  
        return e;  
    }  
} 
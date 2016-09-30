package com.tooling.project.datamodel;  
  
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.tooling.project.model.Sale;
  
public class SaleDataModel extends ListDataModel<Sale> implements SelectableDataModel<Sale> {    
  
    public SaleDataModel() {  
    }  
  
    public SaleDataModel(List<Sale> data) {  
        super(data);  
    }  
      
    @Override  
    @SuppressWarnings("unchecked")
    public Sale getRowData(String rowKey) {  
          
        List<Sale> sales = (List<Sale>) getWrappedData();  
          
        for(Sale sale : sales) {  
            if(sale.getId().toString().equals(rowKey))
                return sale;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Sale e) {  
        return e;  
    }  
} 
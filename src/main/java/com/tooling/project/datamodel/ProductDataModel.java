package com.tooling.project.datamodel;  
  
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.tooling.project.model.Product;
  
public class ProductDataModel extends ListDataModel<Product> implements SelectableDataModel<Product> {    
  
    public ProductDataModel() {  
    }  
  
    public ProductDataModel(List<Product> data) {  
        super(data);  
    }  
      
    @Override  
    @SuppressWarnings("unchecked")
    public Product getRowData(String rowKey) {  
          
        List<Product> products = (List<Product>) getWrappedData();  
          
        for(Product product : products) {  
            if(product.getId().toString().equals(rowKey))
                return product;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Product e) {  
        return e;  
    }  
} 
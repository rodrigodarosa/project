package com.tooling.project.datamodel;  
  
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.tooling.project.model.ManufacturedProduct;
  
public class ManufacturedProductDataModel extends ListDataModel<ManufacturedProduct> implements SelectableDataModel<ManufacturedProduct> {    
  
    public ManufacturedProductDataModel() {  
    }  
  
    public ManufacturedProductDataModel(List<ManufacturedProduct> data) {  
        super(data);  
    }  
      
    @Override  
    @SuppressWarnings("unchecked")
    public ManufacturedProduct getRowData(String rowKey) {  
          
        List<ManufacturedProduct> manufacturedProducts = (List<ManufacturedProduct>) getWrappedData();  
          
        for(ManufacturedProduct manufacturedProduct : manufacturedProducts) {  
            if(manufacturedProduct.getId().toString().equals(rowKey))
                return manufacturedProduct;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(ManufacturedProduct e) {  
        return e;  
    }  
} 
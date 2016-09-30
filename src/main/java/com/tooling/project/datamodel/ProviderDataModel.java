package com.tooling.project.datamodel;  
  
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.tooling.project.model.Provider;
  
public class ProviderDataModel extends ListDataModel<Provider> implements SelectableDataModel<Provider> {    
  
    public ProviderDataModel() {  
    }  
  
    public ProviderDataModel(List<Provider> data) {  
        super(data);  
    }  
      
    @Override  
    @SuppressWarnings("unchecked")
    public Provider getRowData(String rowKey) {  
          
        List<Provider> providers = (List<Provider>) getWrappedData();  
          
        for(Provider provider : providers) {  
            if(provider.getId().toString().equals(rowKey))
                return provider;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Provider e) {  
        return e;  
    }  
} 
package com.tooling.project.datamodel;  
  
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.tooling.project.model.Notation;
  
public class NotationDataModel extends ListDataModel<Notation> implements SelectableDataModel<Notation> {    
  
    public NotationDataModel() {  
    }  
  
    public NotationDataModel(List<Notation> data) {  
        super(data);  
    }  
      
    @Override  
    @SuppressWarnings("unchecked")
    public Notation getRowData(String rowKey) {  
          
        List<Notation> notations = (List<Notation>) getWrappedData();  
          
        for(Notation notation : notations) {  
            if(notation.getId().toString().equals(rowKey))
                return notation;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Notation e) {  
        return e;  
    }  
} 
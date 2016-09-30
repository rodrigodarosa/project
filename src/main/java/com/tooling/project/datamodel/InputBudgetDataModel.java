package com.tooling.project.datamodel;  
  
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.tooling.project.model.InputBudget;
  
public class InputBudgetDataModel extends ListDataModel<InputBudget> implements SelectableDataModel<InputBudget> {    
  
    public InputBudgetDataModel() {  
    }  
  
    public InputBudgetDataModel(List<InputBudget> data) {  
        super(data);  
    }  
      
    @Override  
    @SuppressWarnings("unchecked")
    public InputBudget getRowData(String rowKey) {  
          
        List<InputBudget> inputBudgets = (List<InputBudget>) getWrappedData();  
          
        for(InputBudget inputBudget : inputBudgets) {  
            if(inputBudget.getId().toString().equals(rowKey))
                return inputBudget;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(InputBudget e) {  
        return e;  
    }  
} 
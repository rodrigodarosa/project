package com.tooling.project.datamodel;  
  
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.tooling.project.model.OutputBudget;
  
public class OutputBudgetDataModel extends ListDataModel<OutputBudget> implements SelectableDataModel<OutputBudget> {    
  
    public OutputBudgetDataModel() {  
    }  
  
    public OutputBudgetDataModel(List<OutputBudget> data) {  
        super(data);  
    }  
      
    @Override  
    @SuppressWarnings("unchecked")
    public OutputBudget getRowData(String rowKey) {  
          
        List<OutputBudget> outputBudgets = (List<OutputBudget>) getWrappedData();  
          
        for(OutputBudget outputBudget : outputBudgets) {  
            if(outputBudget.getId().toString().equals(rowKey))
                return outputBudget;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(OutputBudget e) {  
        return e;  
    }  
} 
package com.tooling.project.datamodel;  
  
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.tooling.project.model.Machine;
  
public class MachineDataModel extends ListDataModel<Machine> implements SelectableDataModel<Machine> {    
  
    public MachineDataModel() {  
    }  
  
    public MachineDataModel(List<Machine> data) {  
        super(data);  
    }  
      
    @Override  
    @SuppressWarnings("unchecked")
    public Machine getRowData(String rowKey) {  
          
        List<Machine> machines = (List<Machine>) getWrappedData();  
          
        for(Machine machine : machines) {  
            if(machine.getId().toString().equals(rowKey))
                return machine;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Machine e) {  
        return e;  
    }  
} 
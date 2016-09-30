package com.tooling.project.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.chart.PieChartModel;

import com.tooling.project.datamodel.MachineDataModel;
import com.tooling.project.enums.MachineSituationEnum;
import com.tooling.project.facade.MachineFacade;
import com.tooling.project.model.Machine;
import com.tooling.project.util.Util;
import com.tooling.project.validators.MachineValidator;

@ManagedBean
@ViewScoped
public class MachineBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Machine entity;
	private MachineFacade machineFacade;
	private Machine entitySelected;
	private MachineDataModel machineDataModel;
	private SelectItem[] machinesSituationOptions;
	private final static String[] situationsEnum;
	private final static String[] situations;
	private List<Machine> filteredMachines;
	private PieChartModel pieModel;

	public PieChartModel getPieModel() {
		return pieModel;
	}
	
	public List<Machine> getFilteredMachines() {
		return filteredMachines;
	}

	public void setFilteredMachines(List<Machine> filteredMachines) {
		this.filteredMachines = filteredMachines;
	}

	static {

		situationsEnum = new String[2];
		situationsEnum[0] = "AVAILABLE";
		situationsEnum[1] = "UNAVAILABLE";

		situations = new String[2];
		situations[0] = "Dispon\u00EDvel";
		situations[1] = "Indispon\u00EDvel";
	}

	public Machine getEntity() {
		if (entity == null) {
			entity = new Machine();
		}
		return entity;
	}

	public void setEntity(Machine entity) {
		this.entity = entity;
	}

	public MachineFacade getMachineFacade() {
		if (machineFacade == null) {
			machineFacade = new MachineFacade();
		}
		return machineFacade;
	}

	public void setMachineFacade(MachineFacade machineFacade) {
		this.machineFacade = machineFacade;
	}

	public Machine getEntitySelected() {
		if(entitySelected == null){
			entitySelected = new Machine();
		}
		return entitySelected;
	}

	public void setEntitySelected(Machine entitySelected) {
		this.entitySelected = entitySelected;
	}

	public MachineDataModel getMachineDataModel() {
		return machineDataModel;
	}

	public void setMachineDataModel(MachineDataModel machineDataModel) {
		this.machineDataModel = machineDataModel;
	}

	public void onRowSelect(SelectEvent event) {
	}

	public void onRowUnselect(UnselectEvent event) {
	}

	public MachineBean() {
		createPieModel();
		machinesSituationOptions = createFilterOptions(situationsEnum, situations);
		machineDataModel = new MachineDataModel(getAll());
	}

	public String save() {
		if (MachineValidator.validate(entity)) {
			getMachineFacade().save(entity);
			entity = new Machine();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "M\u00E1quina", "Salvo com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return "machines";

	}

	public String remove() {
		getMachineFacade().remove(entitySelected);
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "M\u00E1quina", "Removido com Sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		return "machines";

	}

	public Machine retrieve(Long id) {
		return getMachineFacade().retrieve(id);
	}

	public void update() {
		if (MachineValidator.validate(entitySelected)) {
			getMachineFacade().update(entitySelected);
			entitySelected = new Machine();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "M\u00E1quina", "Atualizado com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public List<Machine> getAll() {
		return getMachineFacade().getAll();
	}

	public SelectItem[] getMachinesSituationOptions() {
		return machinesSituationOptions;
	}

	public void setMachinesSituationOptions(SelectItem[] machinesSituationOptions) {
		this.machinesSituationOptions = machinesSituationOptions;
	}

	private SelectItem[] createFilterOptions(String[] situationsEnum, String[] situations) {
		SelectItem[] options = new SelectItem[situationsEnum.length + 1];

		options[0] = new SelectItem("", "Selecione");
		for (int i = 0; i < situationsEnum.length; i++) {
			options[i + 1] = new SelectItem(situationsEnum[i], situations[i]);
		}

		return options;
	}
	
	public List<Machine> complete(String complete) {
		List<Machine> results = getMachineFacade().retrieveByName(complete);
		return results;

	}
	
	public String extractReport() throws IOException {
		Util.extractReport("RelatorioMaquinas-", getFilteredMachines() == null ? getAll() : getFilteredMachines(), "machineReport.jrxml");
		return "";
	}
	
	private void createPieModel() {
		pieModel = new PieChartModel();

		pieModel.set("Dispon\u00EDvel", countBySituation(MachineSituationEnum.AVAILABLE));
		pieModel.set("Indispon\u00EDvel", countBySituation(MachineSituationEnum.UNAVAILABLE));
	}

	public int countBySituation(MachineSituationEnum situation){
		return getMachineFacade().countBySituation(situation);
	}
	
	public String extractInformation() throws IOException {
		List<Machine> machines = new ArrayList<Machine>();
		machines.add(getEntitySelected());
		Util.extractReport("Maquina-"+machines.get(0).getId()+"-", machines, "machineInformation.jrxml");
		return "";
	}
	
}

package com.tooling.project.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import com.tooling.project.enums.DischargeSituationEnum;
import com.tooling.project.facade.PayableFacade;
import com.tooling.project.facade.ReceivableFacade;

@ManagedBean
@ViewScoped
public class ChartBean {
	private PieChartModel pieModelPayable;
	private PieChartModel pieModelReceivable;
	private PayableFacade payableFacade;
	private ReceivableFacade receivableFacade;
	private List<PieChartModel> charts;
	
	public List<PieChartModel> getCharts() {
		if(charts == null){
			charts= new ArrayList<PieChartModel>();
		}
		return charts;
	}

	public void setCharts(List<PieChartModel> charts) {
		this.charts = charts;
	}

	public PieChartModel getPieModelPayable() {
		return pieModelPayable;
	}

	public void setPieModelPayable(PieChartModel pieModelPayable) {
		this.pieModelPayable = pieModelPayable;
	}

	public PieChartModel getPieModelReceivable() {
		return pieModelReceivable;
	}

	public void setPieModelReceivable(PieChartModel pieModelReceivable) {
		this.pieModelReceivable = pieModelReceivable;
	}

	public PayableFacade getPayableFacade() {
		if(payableFacade == null){
			payableFacade = new PayableFacade();
		}
		return payableFacade;
	}

	public void setPayableFacade(PayableFacade payableFacade) {
		this.payableFacade = payableFacade;
	}
	
	public ReceivableFacade getReceivableFacade() {
		if(receivableFacade== null){
			receivableFacade = new ReceivableFacade();
		}
		return receivableFacade;
	}

	public void setReceivableFacade(ReceivableFacade receivableFacade) {
		this.receivableFacade = receivableFacade;
	}

	private void createPieModelPayable() {
		pieModelPayable = new PieChartModel();
		pieModelPayable.set("Completo", getPayableFacade().getCountBySituation(DischargeSituationEnum.COMPLETED));
		pieModelPayable.set("Pendente", getPayableFacade().getCountBySituation(DischargeSituationEnum.PENDING));
	}
	private void createPieModelReceivable() {
		pieModelReceivable = new PieChartModel();
		pieModelReceivable.set("Completo", getReceivableFacade().getCountBySituation(DischargeSituationEnum.COMPLETED));
		pieModelReceivable.set("Pendente", getReceivableFacade().getCountBySituation(DischargeSituationEnum.PENDING));
	}
	
	public ChartBean() {
	createPieModelPayable();
	createPieModelReceivable();
	getCharts().add(pieModelPayable);
	getCharts().add(pieModelReceivable);
	}
	

}

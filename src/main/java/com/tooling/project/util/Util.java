package com.tooling.project.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Util {

	public static JasperDesign jasperDesign;
	public static JasperPrint jasperPrint;
	public static JasperReport jasperReport;

	public static void redirect(String page) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void extractReport(String fileName, List dataList, String report) throws IOException {
		ServletOutputStream servletOutputStream = null;
		try {
			Map param = new HashMap();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm");
			fileName = fileName + sdf.format(date);

			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String path = servletContext.getRealPath("/images/tools-clip-art-1.png");
			param.put("logo", path);

			InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(report);
			jasperDesign = JRXmlLoader.load(resourceAsStream);
			jasperReport = JasperCompileManager.compileReport(jasperDesign);
			jasperPrint = JasperFillManager.fillReport(jasperReport, param, new JRBeanCollectionDataSource(dataList));
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fileName + ".pdf");
			servletOutputStream = httpServletResponse.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (servletOutputStream != null) {
				servletOutputStream.flush();
				servletOutputStream.close();
			}

		}
	}

	public static String calculateDiferenceBetweenTwoDates(String dateStart, String dateStop) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date d1 = null;
		Date d2 = null;

		d1 = format.parse(dateStart);
		d2 = format.parse(dateStop);

		// in milliseconds
		long diff = d2.getTime() - d1.getTime();

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		// long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffHours = diff / (60 * 60 * 1000);

		return diffHours + " horas - " + diffMinutes + " minutos - " + diffSeconds + " segundos ";
	}

	public static String sumDates(String date1, String date2) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date dateStart = df.parse(date1);
		Date dateStop = df.parse(date2);
		Date newDate = new Date(dateStart.getTime() + dateStop.getTime());

		return df.format(newDate);

	}
}

package com.tooling.project.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.tooling.project.facade.EmployeeFacade;
import com.tooling.project.facade.ThemeFacade;
import com.tooling.project.model.Employee;
import com.tooling.project.model.Theme;

@ManagedBean
@SessionScoped
public class ThemeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Theme entity;
	private ThemeFacade themeFacade;
	private EmployeeFacade employeeFacade;
	private Map<String, String> themes;

	public ThemeBean() {
		themes = new TreeMap<String, String>();
		
		themes.put("Afterdark", "afterdark");
        themes.put("Afternoon", "afternoon");
        themes.put("Afterwork", "afterwork");
        themes.put("Aristo", "aristo");
        themes.put("Black-Tie", "black-tie");
        themes.put("Blitzer", "blitzer");
        themes.put("Bluesky", "bluesky");
        themes.put("Bootstrap", "bootstrap");
        themes.put("Casablanca", "casablanca");
        themes.put("Cupertino", "cupertino");
        themes.put( "Cruze", "cruze");
        themes.put( "Dark-Hive", "dark-hive");
        themes.put( "Delta", "delta");
        themes.put( "Dot-Luv", "dot-luv");
        themes.put( "Eggplant", "eggplant");
        themes.put( "Excite-Bike", "excite-bike");
        themes.put( "Flick", "flick");
        themes.put( "Glass-X", "glass-x");
        themes.put( "Home", "home");
        themes.put( "Hot-Sneaks", "hot-sneaks");
        themes.put( "Humanity", "humanity");
        themes.put( "Le-Frog", "le-frog");
        themes.put( "Midnight", "midnight");
        themes.put( "Mint-Choc", "mint-choc");
        themes.put( "Overcast", "overcast");
        themes.put( "Pepper-Grinder", "pepper-grinder");
        themes.put( "Redmond", "redmond");
        themes.put( "Rocket", "rocket");
        themes.put( "Sam", "sam");
        themes.put( "Smoothness", "smoothness");
        themes.put( "South-Street", "south-street");
        themes.put( "Start", "start");
        themes.put( "Sunny", "sunny");
        themes.put( "Swanky-Purse", "swanky-purse");
        themes.put( "Trontastic", "trontastic");
        themes.put( "UI-Darkness", "ui-darkness");
        themes.put( "UI-Lightness", "ui-lightness");
        themes.put( "Vader", "vader");
        
	}

	public Theme getEntity() {
			entity = getThemeFacade().retrieveByEmployee(getEmployeeFacade().getCurrentLogged());
			if (entity == null) {
				entity = new Theme();
				entity.setTheme("aristo");
		}
		return entity;
	}

	public void setEntity(Theme entity) {
		this.entity = entity;
	}

	public ThemeFacade getThemeFacade() {
		if (themeFacade == null) {
			themeFacade = new ThemeFacade();
		}
		return themeFacade;
	}

	public void setThemeFacade(ThemeFacade themeFacade) {
		this.themeFacade = themeFacade;
	}

	public EmployeeFacade getEmployeeFacade() {
		if (employeeFacade == null) {
			employeeFacade = new EmployeeFacade();
		}
		return employeeFacade;
	}

	public void setEmployeeFacade(EmployeeFacade employeeFacade) {
		this.employeeFacade = employeeFacade;
	}

	public String update() {
		String string = entity.getTheme();
		Employee employee = getEmployeeFacade().getCurrentLogged();
		entity = getThemeFacade().retrieveByEmployee(getEmployeeFacade().getCurrentLogged());
		if(entity==null){
			entity = new Theme();
			entity.setEmployee(employee);
			entity.setTheme(string);
			getThemeFacade().save(entity);
		}else{
			entity.setTheme(string);
			getThemeFacade().update(entity);
		}
		return "";
	}

	public Theme retrieve(Long id) {
		return getThemeFacade().retrieve(id);
	}

	public String cancel() {
		entity = new Theme();
		return "";
	}

	public List<Theme> getAll() {
		return getThemeFacade().getAll();
	}

	public Map<String, String> getThemes() {
		return themes;
	}

	public void setThemes(Map<String, String> themes) {
		this.themes = themes;
	}

}

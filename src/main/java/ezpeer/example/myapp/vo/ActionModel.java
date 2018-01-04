package ezpeer.example.myapp.vo;

import java.util.Map;

public class ActionModel {

	private String name = "audioPlayGenieSource";
	
	private Map<String,String> properties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
}

package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	static Properties properties;
	
	public PropertyReader() {
		loadAllProperties();
	}
	
	public void loadAllProperties() {
		properties = new Properties();
		String filePath = "./src/test/resources/config.properties";
		try {
			properties.load(new FileInputStream(filePath));
		} catch (IOException e) {
			throw new RuntimeException("File not found.");
		}
	}
	
	public String readProperty(String propertyName) {
		return properties.getProperty(propertyName);
	}
	
}

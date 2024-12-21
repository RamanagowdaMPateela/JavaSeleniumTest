package javaSeleniumAcademy.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public static void main(String[] args) throws IOException {
		getJsonDataToMap();
	}
	
	public static List<HashMap<String, String>> getJsonDataToMap() throws IOException {
			
          //read json to string		
	     String jsonContent = FileUtils.readFileToString( new File(System.getProperty("user.dir") + "\\src\\test\\java\\javaSeleniumAcademy\\data\\PurchaseOrder.json"), "UTF-8" );
	     
	     System.out.println(jsonContent);

	     //String to HashMap using Jackson Databind 
		  ObjectMapper mapper = new ObjectMapper();
		 // HashMap<String, Object> data = mapper.readValue(jsonContent, HashMap.class); 
		  List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});

		 // Example: Printing the HashMap
		  System.out.println(data); 
		
		return data;
     }
}
	

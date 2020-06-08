package ua.com.foxminded;

import java.util.Map;

public class OutputFormatter {
	
	String format(String input, Map<String, Integer> counterData) {
		StringBuilder outputBuilder = new StringBuilder();
		
		outputBuilder.append(input + "\n");
		
		for(Map.Entry<String, Integer> entry : counterData.entrySet()) {
			outputBuilder.append("\"" + entry.getKey() + "\" - " + entry.getValue() + "\n");
		}
		
		return outputBuilder.toString();
	}

}

package ua.com.foxminded;

import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		String input = "hello world!";
		UniqueCharCounter charCounter = new UniqueCharCounter();
		OutputFormatter formatter = new OutputFormatter();
		
		Map<String, Integer> counterOutput = charCounter.count(input);
		String result = formatter.format(input, counterOutput);
		System.out.println(result);
	}
}

package ua.com.foxminded;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class OutputFormatterTest {
	
	OutputFormatter formatter;
	Map <String, Integer> inputData;
	
	@Nested
	class formatTest {
		
		@BeforeEach
		void setUp() throws Exception {
			formatter = new OutputFormatter();
			inputData = new LinkedHashMap<>();
			inputData.put("a", 1);
			inputData.put("@", 2);
			inputData.put(" ", 3);
			inputData.put("4", 4);
		}

		@Test
		@DisplayName("mixed characters")
		void testMixedChars() {
			String expected = 
					"a @@ 44 44\n\"a\" - 1\n\"@\" - 2\n\" \" - 3\n\"4\" - 4\n";
			String actual = formatter.format("a @@ 44 44", inputData);
			
			assertEquals(expected, actual, "passed " + inputData.toString());	
		}
		
		@Test
		@DisplayName("empty input")
		void testEmptyInput() {
			String expected = "\n";
			String actual = 
					formatter.format("", new LinkedHashMap<String, Integer>());
			
			assertEquals(expected, actual, "passed empty input");
		}
	}


}

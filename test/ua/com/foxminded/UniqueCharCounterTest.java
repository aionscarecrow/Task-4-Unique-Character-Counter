package ua.com.foxminded;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class UniqueCharCounterTest {
	
	private UniqueCharCounter charCounter;

	@Nested
	@DisplayName("Character counting")
	class CountTest {
		
		@BeforeEach
		void setUp() throws Exception {
			charCounter = new UniqueCharCounter();
		}
		
		@Test
		@DisplayName("count letters")
		void testCountLetters() {
			String expected = "{e=1, r=3, o=1}";
			String actual = charCounter.count("error").toString();
			
			assertEquals(expected, actual, "passed \"error\"");
		}
		
		@Test
		@DisplayName("count spaces")
		void testSpacesHandling() {
			String expected = "{ =3}";
			String actual = charCounter.count("   ").toString();
			
			assertEquals(expected, actual, "passed \"   \"");
		}
		
		@Test
		@DisplayName("empty String handling")
		void testEmptyString() {
			String expected = "{}";
			String actual = charCounter.count("").toString();
			
			assertEquals(expected, actual, "passed \"\"");
		}
		
		@Test
		@DisplayName("count digits")
		void testCountDigits() {
			String expected = "{1=2, 2=1}";
			String actual = charCounter.count("112").toString();
			
			assertEquals(expected, actual, "passed \"112\"");
		}
		
		@Test
		@DisplayName("count mixed chars")
		void testCountMixed() {
			String expected = "{1=2, 2=1,  =2, e=1, r=3, o=1, @=1, #=1, %=1}";
			String actual = charCounter.count("112 error @#%").toString();
			
			assertEquals(expected, actual, "passed \"112 error @#%\"");
		}
		
		@Test
		@DisplayName("null handling")
		void testNullHandling() {
			assertThrows(
					IllegalArgumentException.class, 
					() -> charCounter.count(null),
					"passed null"
				);
		}
		
	}
	
	
	@Nested
	@DisplayName("Caching")
	class CacheTest {
		
		String input;
		
		@BeforeEach
		void setUp() throws Exception {
			input = "This is a string used to check caching efficiency";
			charCounter = new UniqueCharCounter();
			
			for (int i = 0; i < 1000; i += 100) {
				charCounter.count(Integer.toString(i));
			}
		}
		
		@Test
		@DisplayName("cache hit performance")
		void testCacheHit() {
			long beginTime;
			long doneTime;
			
			beginTime = Math.abs(System.nanoTime()); 
			charCounter.count(input);
			doneTime = Math.abs(System.nanoTime());
			long cacheMissTime = doneTime - beginTime;
			
			beginTime = Math.abs(System.nanoTime());
			charCounter.count(input);
			doneTime = Math.abs(System.nanoTime());
			long cacheHitTime = Math.abs(doneTime - beginTime);
			
			assertTrue((cacheHitTime - cacheMissTime) < 0, "cache hit should be faster");
		}
		
	}
	
}

package ua.com.foxminded;

import java.util.Map;
import java.util.Collections;
import java.util.LinkedHashMap;

public class UniqueCharCounter {
	
	private Cache cache = new Cache(10);
	
	public Map<String, Integer> count(String input) throws IllegalArgumentException {
		
		validateInput(input);
		Map <String, Integer> output = cache.requestCached(input);
		
		if (!output.isEmpty()) {
			return output;
		}
		
		for (int i = 0; i < input.length(); i++) {
			Integer focusCharCount = 0;
			String focusChar = input.substring(i, i + 1);
			
			if(output.containsKey(focusChar)) {
				focusCharCount = output.get(focusChar);
			}
			output.put(focusChar, ++focusCharCount);
		}
		
		cache.storeToCache(input, output);
		return output;
	}
	
	
	private void validateInput(String input) throws IllegalArgumentException {
		if (input == null) {
			throw new IllegalArgumentException("Input must not be null");
		}
	}
	
	
	private class Cache {
		
		private Map<String, Map<String, Integer>> cachedData;
		private int maxEntries;
		
		Cache (int capacity) {
			this.cachedData = new LinkedHashMap<>(capacity, 0.75f, true);
			this.maxEntries = capacity;
		}
		
		Map <String, Integer> requestCached(String input) {
			if(cachedData.containsKey(input)) {
				return Collections.unmodifiableMap(cachedData.get(input));
			}
			return new LinkedHashMap <String, Integer>(); 
		}
		
		void storeToCache(String key, Map <String, Integer> value) {
			cachedData.put(key, value);
			
			if(cachedData.size() > maxEntries) {
				removeEldestEntry();
			}
		}
		
		private void removeEldestEntry() {
			cachedData.remove(getEldestKey());
		}
		
		private String getEldestKey() {
			return cachedData.keySet().iterator().next();
		}
		
	}
	
}

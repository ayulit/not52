package ru.not.litvinov.lec14.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Streamzz {

	public static class PlantBox {
		String plant;
		
		public PlantBox(String plant) {
			this.plant = plant;
		}

		@Override
		public String toString() {
			return "???? ? " + plant;
		}
	}

	public static void main(String[] args) {
		List<String> strings = Arrays.asList("oranges", "apples", "onions", "carrots", "cabbage");
		
		strings.stream().count();
		
		strings.stream().filter(new Predicate<String>() {
			public boolean test(String s) {
				return s.startsWith("o");
			}
		});
		
		strings.stream()
		 .filter(s -> s.startsWith("o"));
		
		strings.stream()
		 .filter(plant -> plant.startsWith("o"))
		 .map(plant -> { return new PlantBox(plant); })
		 .forEach(plantBox -> System.out.println(plantBox.toString()));
		
		for (String string : strings) {
			if (string.startsWith("o")) {
				System.out.println(new PlantBox(string));
			}
		}
		
		strings.stream().allMatch( str -> str.contains("s"));
		
		boolean loopBool = false;
		for (String string : strings) {
			if (string.contains("s")) {
				loopBool = true;
				break;
			}
		}
		strings.stream().anyMatch( str -> str.contains("oranges"));
		strings.stream().noneMatch( str -> str.contains("bananas "));
		
		String reduced = strings.stream().reduce("??????", new BinaryOperator<String>()  {
			public String apply(String s, String s2) {
				return s + s2 + ",";
			}
		});
		
		List<PlantBox> boxes = strings.stream().map(plant -> new PlantBox(plant)).collect(Collectors.toList());
		Map<String, PlantBox> collect = strings.stream().map(plant -> new PlantBox(plant)).collect(Collectors.toMap(box -> box.plant, box -> box));
		
		Map<Character, List<String>> collect2 = strings.stream().collect(Collectors.groupingBy(fruit -> fruit.toCharArray()[0]));
	}
}

package ru.not.litvinov.lec14.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lambdas {
	public static void main(String[] args) {
		List<String> strings = Arrays.asList("a", "b", "c", "a", "e");
		Collections.sort(strings, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2) * -1;
			}
		});
		System.out.println(strings);
	}
	
	public static void main2(String[] args) {
		List<String> strings = Arrays.asList("a", "b", "c", "a", "e");
		Collections.sort(strings, (o1, o2) -> o1.compareTo(o2) * -1);
		System.out.println(strings);
		
		Comparator<String> stringComparator = (o1, o2) -> compareTo1(o1, o2);
		
		Lambdas object = new Lambdas();
		Comparator<String> stringComparator2 = object::compareTo2;
		Collections.sort(strings, stringComparator2);
	}
	
	public static int compareTo1(String one, String another) {
		return another.compareTo(one);
	}
	public int compareTo2(String one, String another) {
		return another.compareTo(one);
	}
}
package otherpractices;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {

		Integer[] intInput1 = new Integer[] { 3, 6, 2, 8, 9, 1 };
		SelectionSort.sort(intInput1, true);
		Arrays.stream(intInput1).forEach(System.out::println);
		
		Integer[] intInput2 = new Integer[] { 3, 6, 2, 8, 9, 1 };
		InsertionSort.recursiveSort(intInput2, intInput2.length);
		Arrays.stream(intInput2).forEach(System.out::println);
		
		Integer[] intInput3 = new Integer[] { 3, 6, 2, 8, 9, 1 };
		ShellSort.sort(intInput3);
		Arrays.stream(intInput3).forEach(System.out::println);

	}
}

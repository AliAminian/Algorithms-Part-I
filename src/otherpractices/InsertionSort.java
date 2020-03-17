package otherpractices;

import java.util.Arrays;

public class InsertionSort {

	/**
	 * features:
	 * 1. time: O(n^2)
	 * 2. memory: Inplace (< c*lgN)
	 * 3. Type: online
	 * 4. Generic (Object)
	 * 
	 */
	public static <T extends Comparable<T>> void sort(T[] array) {
		// public static void sort(Comparable[] array) {
		int n = array.length;
		for (int i = 1; i < n; i++) {
			T smallest = array[i];
			// Comparable<?> smaller = array[i];
			int j = i-1;
			while (j >= 0 && array[j].compareTo(smallest) > 0) {
				array[j+1] = array[j];
				j--;
			}
			array[j+1] = smallest;
		}
	}


	public static void main(String[] args) {

		Integer[] intInput = new Integer[] { 3, 6, 2, 8, 9, 1 };
		InsertionSort.sort(intInput);
		Arrays.stream(intInput).forEach(System.out::println);

		Character[] charInput = new Character[] { 'a', 'c', 'e', 'z', 'g', 'b' };
		InsertionSort.sort(charInput);
		Arrays.stream(charInput).forEach(System.out::println);
	}
}

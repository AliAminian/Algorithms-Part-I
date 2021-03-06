package others.sort;

import java.util.Arrays;

public class SelectionSort {

	/**
	 * features: 
	 * 	1. time: O(n^2) 
	 * 	2. memory: Inplace (< c*lgN) 
	 * 	3. Type: online 
	 * 	4. Generic (Object) 
	 * 	5. Default is not stable : two objects with equal or same keys don't
	 * 	   appear in the same order in sorted output as they appear in the input array
	 *     to be sorted
	 */
	public static <T extends Comparable<T>> void sort(T[] array, boolean... isStable) {
		// public static void sort(Comparable[] array) {
		int n = array.length;

		for (int i = 0; i < n - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (array[minIndex].compareTo(array[j]) > 0) {
					minIndex = j;
				}
			}

			if (isStable.length > 0 && isStable[0]) {
				//Keeping order to be stable
				T key = array[minIndex]; 
	            while (minIndex > i)  
	            { 
	                array[minIndex] = array[minIndex - 1]; 
	                minIndex--; 
	            } 
	            array[i] = key;
			} else {
				//Swapping not consider the order
				T tmp = array[i];
				// Comparable<?> tmp = array[i];
				array[i] = array[minIndex];
				array[minIndex] = tmp;
			}
		}
	}

	public static void main(String[] args) {

		Integer[] intInput = new Integer[] { 3, 6, 2, 8, 9, 1 };
		SelectionSort.sort(intInput, true);
		Arrays.stream(intInput).forEach(System.out::println);

		Character[] charInput = new Character[] { 'a', 'c', 'e', 'z', 'g', 'b' };
		SelectionSort.sort(charInput);
		Arrays.stream(charInput).forEach(System.out::println);
	}
}

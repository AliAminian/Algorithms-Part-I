package others.sort;

public class InsertionSort {

	/**
	 * features:
	 * 1. time: O(n^2)
	 * 2. memory: Inplace (< c*lgN)
	 * 3. Type: online -> An online algorithm is one that can process its input 
	 * 			piece-by-piece in a serial fashion, i.e., in the order that the
	 * 			input is fed to the algorithm, without having the entire input available
	 * 			from the start
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
	
	
	public static <T extends Comparable<T>> void recursiveSort(T[] array, int numberOfElements) {
		if (numberOfElements < 2) {
			return;
		}
		
		InsertionSort.recursiveSort(array, numberOfElements-1);
		
		T smallest = array[numberOfElements-1]; //because array index starts from 0
		int j = numberOfElements-2;
		while (j >= 0 && array[j].compareTo(smallest) > 0) {
			array[j+1] = array[j];
			j--;
		}
		array[j+1] = smallest;
		
	}
}

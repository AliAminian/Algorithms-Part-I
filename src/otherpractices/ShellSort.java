package otherpractices;

public class ShellSort {

	/**
	 * features:  very efficient with medium-sized lists
	 * 	1. time: between O(N) and O(N^2)
	 * 	2. memory: Inplace (< c*lgN) 
	 * 	3. Type: online 
	 * 	4. Generic (Object) 
	 * 	5. not stable
	 * 
	 */
	
	//Insertion sort for different subArrays of the main input array as gaps
	public static <T extends Comparable<T>> void sort(T[] array) {
		int n = array.length;
		
		for (int gap = n/2; gap > 0; gap/=2) {
			for (int i = gap; i < n; i++) {
				T smallest = array[i];
				int j = i-1;
				while (j >= gap && array[j].compareTo(smallest) > 0) {
					array[j+1] = array[j];
					j--;
				}
				array[j+1] = smallest;
			}
		}
	}
	
}

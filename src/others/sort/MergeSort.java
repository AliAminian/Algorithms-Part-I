package others.sort;

public class MergeSort {
	private MergeSort() {
	}

	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (aux[j].compareTo(aux[i]) < 0)
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}

	}

	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		//Modification: Here we can make a threshold in case hi <= lo + CUTOFF (~ 7) - 1
		//				then use "Insertion.sort(a, lo, hi); return;"
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}

	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}
}

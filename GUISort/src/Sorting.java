import java.util.Arrays;

public class Sorting {

	public static void main(String[] args) {
		int[] ints = { 2, 6, 7, 1, 9, 0, 6, 5 };

		// insertionSort(ints);
		// selectionSort(ints);
		bubbleSort(ints);
		for (int i : ints) {
			System.out.println(i);
		}

	}

	public static void insertionSort(int[] ar) {
		for (int i = 1; i < ar.length; i++) {
			int index = ar[i];
			int j = i;
			while (j > 0 && ar[j - 1] > index) {
				ar[j] = ar[j - 1];
				j--;
			}
			ar[j] = index;
		}
	}

	static void selectionSort(int[] ar) {
		for (int i = 0; i < ar.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < ar.length; j++)
				if (ar[j] < ar[min])
					min = j;
			int temp = ar[i];
			ar[i] = ar[min];
			ar[min] = temp;
		}
	}

	static void bubbleSort(int ar[]) {
		for (int i = (ar.length - 1); i >= 0; i--) {
			for (int j = 1; j <= i; j++) {
				if (ar[j - 1] > ar[j]) {
					int temp = ar[j - 1];
					ar[j - 1] = ar[j];
					ar[j] = temp;
				}
			}
		}
	}
}
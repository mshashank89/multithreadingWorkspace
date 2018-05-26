package deloitte.countingLeaves;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Stack {
	int a[];
	int top;

	Stack(int size) {
		a = new int[size];
		top = -1;
	}

	public void add(int n) {
		a[++top] = n;
	}

	public boolean contains(int n) {
		for (int i = 0; i <= top; i++) {
			if (a[i] == n)
				return true;
		}

		return false;
	}

	public int pop() {
		return a[top--];
	}
}
public class CountDigits {


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int k = 0; k < t; k++) {
			int n = sc.nextInt();
			int a[] = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = sc.nextInt();
			}
			int ks = sc.nextInt();

			Stack s = new Stack(ks);
			for (int j = 1; j <= ks; j++) {
				int smallest = Integer.MAX_VALUE;
				for (int i = 0; i < n; i++) {
					if (a[i] < smallest && !s.contains(a[i])) {
						smallest = a[i];
					}
				}
				s.add(smallest);
			}

			System.out.println(s.pop());

		}
	}

	public static void main2(String[] args) {

		// int r=3;
		int[][] cargoList = { { 1, 130, 500 }, { 2, 280, 1800 },
				{ 3, 120, 1500 } };
		int n = cargoList.length;
		int maxWeight = 300;
		List<Integer> outputList = new ArrayList<Integer>();

		int maxProfit = 0;
		int cargos[] = new int[n];
		for (int i = 0; i < n; i++) {
			cargos[i] = i;
		}

		List<Integer> max = new ArrayList<Integer>();
		for (int r = 1; r <= n; r++) {
			int p = getProfit(cargos, n, r, maxWeight, cargoList, outputList);

			if (p > maxProfit) {
				maxProfit = p;
				max = outputList;
			}
		}

		int output[] = new int[max.size()];

		for (int i = 0; i < max.size(); i++) {
			output[i] = max.get(i);
		}

		System.out.println(maxProfit);

	}

	public static int getProfit(int[] cargos, int n, int r, int maxWeight,
			int[][] cargoList, List<Integer> outputList) {

		int data[] = new int[r];

		cargoCombination(cargos, data, 0, n, 0, r);

		int profit = findProfit(data, cargoList, maxWeight, outputList);
		return profit;
	}

	private static int findProfit(int[] data, int[][] cargoList, int maxWeight,
			List<Integer> outputList) {

		int totalWeight = 0;
		int totalProfit = 0;
		for (int j = 0; j < data.length; j++) {
			int id = cargoList[j][0];
			if (maxWeight >= totalWeight + cargoList[j][1]) {
				outputList.add(id);
				totalWeight += cargoList[j][1];
				totalProfit += cargoList[j][2];

			}

		}
		outputList.add(totalProfit);
		return totalProfit;
	}

	public static void cargoCombination(int arr[], int data[], int start,
			int end, int index, int r) {
		for (int i = start; i < end && end - i + 1 >= r - index; i++) {
			data[index] = arr[i];
			cargoCombination(arr, data, i + 1, end, index + 1, r);
		}
	}

	public static int comb(int n, int r) {

		return (fact(n) / fact(n - r)) / fact(r);
	}

	static int fact(int n) {

		if (n < 0) {
			n = -1 * n;
		}

		int f = 1;
		while (n > 0) {
			f = f * n;
			n--;
		}

		return f;
	}

	public static void main1(String[] args) {

		int n = 10;
		int m = 0;
		int tn = n;

		boolean flag = false;

		int temp = 0;
		if (n == 0) {
			temp = m;
			flag = true;
		} else if (m == 0) {
			temp = n;
			flag = true;
		}

		if (flag) {
			int tm = temp;
			while (tm > 0) {
				int j = tm % 10;

				if (0 == j) {
					System.out.println(n + " contains " + j + " in " + m);
					return;
				}
				tm = tm / 10;
			}
		}

		while (tn > 0) {
			int i = tn % 10;
			int tm = m;
			while (tm > 0) {
				int j = tm % 10;

				if (i == j) {
					System.out.println(n + " contains " + j + " in " + m);
					return;
				}
				tm = tm / 10;
			}
			tn = tn / 10;
		}
	}

}

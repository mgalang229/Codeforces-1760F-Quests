import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.StringTokenizer;

/*

- we cannot do the same quest for k days (possible on k+1-th day) 
- position of a[i] doesn't matter
- we can do "nothing" on a particular day
- zero (0) is a possible answer (meaning = there is no interval for each quest)
- a[i] is not distinct

goals:
- find max value of k
- gain at least c coins over d days (basically, within d days)

"earn c coins over d days"

------------------------

1
2 5 4
1 2

earn 5 coins over 4 days

maybe binary search? <-

k = 2:
2 1 0 2

k = 1:
2 1 2 1

ans: 2

------------------------

1
2 20 10
100 10

100 ? ? ? ? ? ? ? ? ?

occurs if max(a[1], a[2], ..., a[n]) > c

ans: Infinity

------------------------

1
3 100 3
7 2 6

7 7 7

even if k = 0, it's impossible to reach 100
occurs if max(a[1], a[2], ..., a[n]) * d < c

ans: Impossible

------------------------

1
4 20 3
4 5 6 7

7 6 7

ans: 1

------------------------

1
2 20 4
5 1

5 5 5 5

ans: 0

------------------------

1
4 40 10
4 5 6 7

7 6 5 4

k = 0:
7 7 7 7 7 7 7 7 7 7

k = 1:
7 6 7 6 7 6 7 6 7 6

k = 2:
7 6 5 7 6 5 7 6 5 7

k = 3:
7 6 5 4 7 6 5 4 7 6

k = 4:
7 6 5 4 0 7 6 5 4 0

k = 5:
7 6 5 4 0 0 7 6 5 4

k = 6:
7 6 5 4 0 0 0 7 6 5

let k = mid + 1,
temp = ceil(d / k)
=> d -= temp
=> k, k - 1, k - 2, ..., 1

ans: 6

------------------------

 */

public class Main {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt();
			long c = fs.nextLong();
			int d = fs.nextInt();
			Long[] a = new Long[n];
			for (int i = 0; i < n; i++) {
				a[i] = fs.nextLong();
			}
			Arrays.sort(a, new Comparator<Long>() {
				@Override
				public int compare(Long o1, Long o2) {
					return -Long.compare(o1, o2);
				}
			});
//			if (a[0] > c) {
//				System.out.println("Infinity");
//				continue;
//			}
//			if (a[0] * d < c) {
//				System.out.println("Impossible");
//				continue;
//			}
			int low = 0, high = d, k = -1;
			while (low <= high) {
				int mid = low + (high - low) / 2;
				if (possible(a, n, c, d, mid)) {
					k = Math.max(k, mid);
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
			if (k == -1) {
				System.out.println("Impossible");
				continue;
			}
			if (k == d) {
				System.out.println("Infinity");
				continue;
			}
			System.out.println(k);
		}
		out.close();
	}

	static boolean possible(Long[] a, int n, long c, int d, int mid) {
		int k = mid + 1;
		long res = 0;
		for (int i = 0; i < n && k > 0; i++) {
			int temp = (d + k - 1) / k;
			res += a[i] * temp;
			d -= temp;
			k--;
		}
		return res >= c;
	}
	
	static final Random rnd = new Random();
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newInd = rnd.nextInt(n);
			int temp = a[newInd]; //change this
			a[newInd] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		double[] readDoubleArray(int n) {
			double[] a = new double[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextDouble();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}

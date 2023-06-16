import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.StringTokenizer;

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

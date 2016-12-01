import java.util.*;

class Hungarian {

  // a[1..n][1..m] >= 0, n <= m
  public static int solveAssignmentProblem(int[][] a) {
    int n = a.length - 1;
    int m = a[0].length - 1;
    int[] u = new int[n + 1];
    int[] v = new int[m + 1];
    int[] p = new int[m + 1];
    int[] way = new int[m + 1];
    for (int i = 1; i <= n; ++i) {
      p[0] = i;
      int j0 = 0;
      int[] minv = new int[m + 1];
      Arrays.fill(minv, Integer.MAX_VALUE);
      boolean[] used = new boolean[m + 1];
      do {
        used[j0] = true;
        int i0 = p[j0];
        int delta = Integer.MAX_VALUE;
        int j1 = 0;
        for (int j = 1; j <= m; ++j)
          if (!used[j]) {
            int cur = a[i0][j] - u[i0] - v[j];
            if (cur < minv[j]) {
              minv[j] = cur;
              way[j] = j0;
            }
            if (minv[j] < delta) {
              delta = minv[j];
              j1 = j;
            }
          }
        for (int j = 0; j <= m; ++j)
          if (used[j]) {
            u[p[j]] += delta;
            v[j] -= delta;
          } else
            minv[j] -= delta;
        j0 = j1;
      } while (p[j0] != 0);
      do {
        int j1 = way[j0];
        p[j0] = p[j1];
        j0 = j1;
      } while (j0 != 0);
    }
    return -v[0];
  }

  // random test
  public static void main(String[] args) {
    Random rnd = new Random(1);
    for (int step = 0; step < 1000; step++) {
      int n = rnd.nextInt(8) + 1;
      int m = n + rnd.nextInt(9 - n);
      int[][] a = new int[n + 1][m + 1];
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
          a[i][j] = rnd.nextInt(100000);
        }
      }
      int res1 = solveAssignmentProblem(a);
      System.out.println(res1);
    }
  }
}
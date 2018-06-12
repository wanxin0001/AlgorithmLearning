/*
* 1) Talk about time complexity.
* 2) Review Tries
* 3) Review visited
*/

public class June_9th_Q1_find_a_given_word_exists_in_the_grid {

	public boolean hasExists(char[][] grid, String target) {

		if (grid == null || grid.length == 0) {
			return target == null || target.length() == 0;
		}

		if (target == null || target.length() == 0) {
			return true;
		}

		int m = grid.length;
		int n = grid[0].length;

		boolean[][] visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] != target.charAt(0)) {
					continue;
				}
				visited[i][j] = true;
				if (dfs(grid, visited, target, 0, i, j)) {
					return true;
				}
				visited[i][j] = false;
			}
		}

		return false;
	}

	private boolean dfs(char[][] grid, boolean[][] visited, String target, int pos, int x, int y) {
		if (target.length() == pos) {
			return true;
		}
		if (grid[x][y] != target.charAt(pos)) {
			return false
		}

		int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		for (int[] dir : dirs) {
			int neiX = x + dir[0];
			int neiY = y + dir[1];

			if (neiX < 0 || neiX >= grid.length || neiY < 0 || neiY >= grid[0].length || visited[neiX][neiY]) {
				continue;
			}

			visited[neiX][neiY] = true;
			dfs(grid, visited, target, pos + 1, neiX, neiY);
			visited[neiX][neiY] = false;
		}

		return false;
	}
}
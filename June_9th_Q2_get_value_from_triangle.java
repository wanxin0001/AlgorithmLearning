public class June_9th_Q2_get_value_from_triangle {
	public int[] getLevel(int level) {
		if (level <= 0) {
			return new int[0];
		} else if (level == 0) {
			return {1, 1};
		}

		int[] result = new int[level + 1];
		result[0] = 1;
		result[1] = 1;

		for (int i = 2; i < level + 1; i++) {
			int pos = i;
			while (pos >= 0) {
				if (pos == i || pos == 1) {
					result[pos] = 1;
				} else {
					result[pos] = (result[pos] + result[pos - 1]) % 7;
				}

				pos++;
			}
		}

		return result;
	}
}
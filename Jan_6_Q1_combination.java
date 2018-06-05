import java.util.*;

public class Jan_6_Q1_combination {
	public static void main(String[] args) {
		Jan_6_Q1_combination obj = new Jan_6_Q1_combination();

		int[] array = {2, 3, 5};
		System.out.println("input: " + Arrays.toString(array));
		System.out.println("Output: " + obj.getProducts(array));

		int[] arrayWithDeps = {2, 2, 11};
		System.out.println("input: " + Arrays.toString(arrayWithDeps));
		System.out.println("Output: " + obj.getProductsWithDeps(arrayWithDeps));

	}
	public List<Integer> getProducts(int[] primes) {
		List<Integer> result = new ArrayList<Integer>();

		if (primes == null || primes.length == 0) {
			return result;
		}

		helper(result, primes, 0, 1);
		return result;
	}

	private void helper(List<Integer> result, int[] primes, int pos, int cur) {
		if (pos == primes.length) {
			return;
		}

		result.add(cur * primes[pos]);
		helper(result, primes, pos + 1, cur * primes[pos]);
		helper(result, primes, pos + 1, cur);
	}

	public List<Integer> getProductsWithDeps(int[] primes) {
		List<Integer> result = new ArrayList<Integer>();

		Map<Integer, Integer> map = new HashMap<>();
		for (int num : primes) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		int[] nums = new int[map.size()];
		int[] counts = new int[map.size()];
		int i = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			nums[i] = entry.getKey();
			counts[i] = entry.getValue();
			i++;
		}

		helper(result, nums, counts, 0, 1);

		return result;
	}

	private void helper(List<Integer> result, int[] nums, int[] counts, int pos, int cur) {
		if (pos == nums.length) {
			return;
		}

		int count = counts[pos];
		int val = nums[pos];

		int tmp = 1;
		for (int i = 0; i < count; i++) {
			tmp *= val;
			result.add(tmp * cur);
			helper(result, nums, counts, pos + 1, tmp * cur);
		}
		helper (result, nums, counts, pos + 1, cur);
	}
}
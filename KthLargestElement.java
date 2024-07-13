import java.util.Arrays;

public class KthLargestElement {
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums, 0, nums.length);
        return nums[nums.length - k];
    }
}
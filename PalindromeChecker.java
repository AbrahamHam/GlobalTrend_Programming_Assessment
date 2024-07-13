public class PalindromeChecker {
    public String isPalindrome(String str) {
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return "not a Palindrome";
            }
            left++;
            right--;
        }

        return "Palindrome";
    }
}
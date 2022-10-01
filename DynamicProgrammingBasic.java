package co.edu.uniandes;

import java.util.HashMap;

// Sebastian Ospino
// 201913643

public class DynamicProgrammingBasic {
    public static void main(String[] args) {
        // Note -> for this code I used java JDK 17
        int[] array = {1, -10, 2, -9, 3, -8, -7, 4, 5};
        System.out.println(maximumSumIncreasingSubsequence(array));

        String s1 = "abcaaba";
        System.out.println(longestPalindromicSubsequence(s1));

        String s2 = "abcdef";
        String t2 = "defghiabcd";
        System.out.println(longestCommonSubstring(s2, t2));
    }

    /**
     * Find a subsequence of a given sequence such that the subsequence sum is as
     *    high as possible and the subsequence’s elements are sorted in ascending order
     *     Arguments:
     *         array: array of Int numbers
     *     Returns:
     *         The sum of the maximum increasing subsequence of array (Int)
     */
    private static int maximumSumIncreasingSubsequence(int[] array){
        int max = 0;
        int tempMax;
        int[] mem = new int[array.length];
        // Starts from the end
        for (int i = (array.length - 1); i > -1 ; i--) {
            tempMax = 0;
            for (int j = (i + 1); j < array.length; j++){
                if ((array[i] < array[j]) && (mem[j] > tempMax)) tempMax = mem[j];
            }
            mem[i] = tempMax + 1;
            if (mem[i] > max) max = mem[i];
        }
        return max;
    }

    /**
     *     Find the length of the longest palindromic subsequence in the string s
     *     Arguments:
     *         s: a String
     *     Returns:
     *         The length of the longest palindromic subsequence in s (Int)
     */
    private static int longestPalindromicSubsequence(String s) {
        HashMap<Pair<Integer, Integer>, Integer> mem = new HashMap<>(100);
        return LPSR(s.toCharArray(), 0, s.length() - 1, mem);
    }

    private static int LPSR(char[] s, int l, int r, HashMap<Pair<Integer, Integer>, Integer> mem) {
        int max = 0;
        if (l == r) {
            max = 1;
        } else if (l < r) {
            Pair<Integer, Integer> pair = new Pair<>(l, r);
            if (mem.containsKey(pair)) {
                max = mem.get(pair);
            } else {
                int bMax = -1;
                if (s[l] == s[r]) {
                    bMax = LPSR(s, l + 1, r - 1, mem) + 2;
                }
                max = Math.max(Math.max(LPSR(s, l + 1, r, mem), LPSR(s, l, r - 1, mem)), bMax);
                mem.put(pair, max);
            }
        }
        return max;
    }

    // Java record was included in JDK 14
    private record Pair<S, T>(S x, T y) {}

    /**
     * Function to find the length of the longest common substring of strings: s,t
     *     Arguments:
     *         s: a String
     *         t: a String
     *     Returns:
     *         The length of the longest common substring of s and t (String)
     */
    private static int longestCommonSubstring(String s, String t){
        int max = 0;
        int temp;
        int[][] mem = new int[t.length() + 1][s.length() + 1];
        for (int i = 1; i < mem.length; i++) {
            for (int j = 1; j < mem[i].length; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    temp = mem[i -1][j - 1] + 1;
                    mem[i][j] = temp;
                    if (temp > max) max = temp;
                }
            }
        }
        return max;
    }
}
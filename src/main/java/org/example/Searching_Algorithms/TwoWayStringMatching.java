package org.example.Searching_Algorithms;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Returns the index of needle in haystack, or -1 if needle is not in haystack.
 * This method uses the <a href="https://en.wikipedia.org/wiki/Two-way_string-matching_algorithm">Two-Way
 * string matching algorithm</a>, which yields O(1) space complexity and excellent performance.
 */
public class TwoWayStringMatching {

    public static int indexOf(String needle, String haystack, int startIndex) {
        if (haystack == null || needle == null) {
            return -1;
        }

        int n = haystack.length();
        int m = needle.length();
        if (m == 0) {
            return 0;
        }

        if (m > n -  startIndex) {
            return -1;
        }

        int i;
        int j = startIndex;
        long suffixes = maxSuf(needle, m, true);
        long prefixes = maxSuf(needle, m, false);
        int ell = Math.max((int) (suffixes >> 32), (int) (prefixes >> 32));
        int per = Math.max((int) suffixes, (int) prefixes);
        int memory;
        int length = Math.min(m - per, ell + 1);

        if (equals(needle, 0, needle, per, length)) {
            memory = -1;
            while (j <= n - m) {
                i = Math.max(ell, memory) + 1;
                while (i < m && needle.charAt(i) == haystack.charAt(i + j)) {
                    ++i;
                }
                if (i >= m) {
                    i = ell;
                    while (i > memory && needle.charAt(i) == haystack.charAt(i + j)) {
                        --i;
                    }
                    if (i <= memory) {
                        return j;
                    }
                    j += per;
                    memory = m - per - 1;
                } else {
                    j += i - ell;
                    memory = -1;
                }
            }
        } else {
            per = Math.max(ell + 1, m - ell - 1) + 1;
            while (j <= n - m) {
                i = ell + 1;
                while (i < m && needle.charAt(i) == haystack.charAt(i + j)) {
                    ++i;
                }
                if (i >= m) {
                    i = ell;
                    while (i >= 0 && needle.charAt(i) == haystack.charAt(i + j)) {
                        --i;
                    }
                    if (i < 0) {
                        return j;
                    }
                    j += per;
                } else {
                    j += i - ell;
                }
            }
        }
        return -1;
    }

    private static long maxSuf(String x, int m, boolean isSuffix) {
        int p = 1;
        int ms = -1;
        int j = 0;
        int k = 1;

        while (j + k < m) {
            char a = x.charAt(j + k);
            char b = x.charAt(ms + k);
            boolean suffix = isSuffix ? a < b : a > b;

            if (suffix) {
                j += k;
                k = 1;
                p = j - ms;
            } else if (a == b) {
                if (k != p) {
                    ++k;
                } else {
                    j += p;
                    k = 1;
                }
            } else {
                ms = j;
                j = ms + 1;
                k = p = 1;
            }
        }
        return ((long) ms << 32) + p;
    }

    private static boolean equals(String s1, int start1, String s2, int start2, int length) {
        for (int i = 0; i < length; i++) {
            if (s1.charAt(start1 + i) != s2.charAt(start2 + i)) {
                return false;
            }
        }
        return true;
    }

    public static void findAllOccurrences(String needle, String haystack, BufferedWriter writer) throws IOException {
        int startIndex = 0;
        while (startIndex < haystack.length() - needle.length()) {
            int index = indexOf(needle, haystack, startIndex);
            if (index == -1) {
                break; // No more occurrences
            }
            writer.write("Found at index: " + index+"\n");
            //System.out.println("Found at index: " + index);
            startIndex = index + 1; // Move to the next character
        }

    }
}

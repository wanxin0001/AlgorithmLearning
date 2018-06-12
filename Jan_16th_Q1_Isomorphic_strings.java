/*
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
*/

/*
Solution:
https://leetcode.com/problems/isomorphic-strings/description/

*/


/**
* What if your input has 3 words, or n words? Given n words, return isomorphic groups
* Set<Set<String>> getIsoGroups(List<String> words)
* Solution: map each words to a base word and but a map <base word, set<words>>
*/

class Solution {
    /*
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) {
            return s == t;
        } else if (s.length() != t.length()) {
            return false;
        }
        
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            char first = s.charAt(i);
            char second = t.charAt(i);
            
            if (!map1.containsKey(first)) {
                if (!map2.containsKey(second)) {
                    map1.put(first, second);
                    map2.put(second, first);
                } else {
                    return false;
                }
            } else {
                if (map2.containsKey(second) && map2.get(second) == first) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        
        return true;
    }
    */
    
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) {
            return s == t;
        } else if (s.length() != t.length()) {
            return false;
        }
        
        Map<Character, Character> map1 = new HashMap<Character, Character>();
        Map<Character, Character> map2 = new HashMap<Character, Character>();
        
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char first = s.charAt(i);
            char second = t.charAt(i);
            if (!map1.containsKey(first)) {
                sb.append('a' + map1.size());
                map1.put(first, (char) ('a' + map1.size()));
            } else {
                sb.append(map1.get(first));
            }
            
            if (!map2.containsKey(second)) {
                sb2.append('a' + map2.size());
                map2.put(second, (char) ('a' + map2.size()));
            } else {
                sb2.append(map2.get(second));
            }
        }
        
        
        
        return sb.toString().equals(sb2.toString());
    }
}
class Solution {
    /*
    ** This solution uses a constant window of size of the pattern.
    ** the number of character of window is matched with number of characters in pattern.
    ** If the number of characters in both are matching, then permutation is found, if not 
    ** then move the window to right and keep repeating it until match is found or end of string is hit.
    */
    public boolean checkInclusion22(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] hash1 = new int[26];
        int[] hash2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            hash1[s1.charAt(i) - 'a']++;
            hash2[s2.charAt(i) - 'a']++;
        }
        
        if (Arrays.equals(hash1, hash2))
            return true;
        /*
        ** Keep the window size constant to the size of pattern.
        ** Slide the window to the right in s2 and update the hash2.
        ** after every slide to the right, check if hash1 and hash2 are matching.
        */
        int i = 0, j = s1.length();
        while (j < s2.length()) {
            hash2[s2.charAt(i++) - 'a']--;
            hash2[s2.charAt(j++) - 'a']++;
            if (Arrays.equals(hash1, hash2))
                return true;
        }
        return false;
    }
    
    /* This solution uses a sliding window which dynamically changing.
    ** it maintains two maps: 
    ** (1) for desires number characters and their occurances in pattern
    ** (2) for actual number of missing characters in window and remaining occerances
    ** it also maintains the missing characters in sliding window.
    ** end side of the window is moves forward and checks whether char pointed be end is in pattern or not
    ** (a) char is not in pattern, end moves forward and start is set to end.
    ** (b) char is in pattern and there is a missing occurance then "missing" and actualMap is updated accordingly
    ** (c) if char is not actual map but is in desired map, which means that there are more 
    ** an required occrances of this char. so the start pointer forward upto the point that when 
    ** there is no excess occurance of any char.
    ** while iterating when "missing" count becomes 0 then pattern is found.
    ** BUT THIS DOES NOT SEEM TO BE AN EFFICIENT SOLUTION. COMMENTS ARE WELCOME!!!
    */
    public boolean checkInclusion(String s1, String s2) {
        if(s1 == null || s2 == null || s2.length() == 0 || s1.length() == 0)
            return false;
        
        Map<Character, Integer> dMap = new HashMap<>();
        for(char ch: s1.toCharArray())
            dMap.put(ch, dMap.getOrDefault(ch,0)+1);

        Map<Character, Integer> aMap = new HashMap();
        aMap.putAll(dMap);
        int start=0, end=0, missing=s1.length();
        while(end<s2.length()){
            char ch = s2.charAt(end++);
            if(dMap.keySet().contains(ch)){
                if(!aMap.keySet().contains(ch)){
                    while(true){
                        char removedCh = s2.charAt(start++);
                        missing++;
                        aMap.put(removedCh, aMap.getOrDefault(removedCh,0)+1);
                        if(removedCh == ch)
                            break;
                    }
                }
                int count = aMap.get(ch);
                missing--;
                if(missing == 0)
                    return true;
                
                if(count == 1)
                    aMap.remove(ch);
                else
                    aMap.put(ch,count-1);
            
            }
            else if(missing != s1.length()){
                missing = s1.length();
                aMap.clear();
                aMap.putAll(dMap);
                start = end;
            }
            else
                start = end;
        }
        return false;
    }
}

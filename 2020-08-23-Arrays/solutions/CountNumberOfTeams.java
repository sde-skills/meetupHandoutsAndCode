class Solution {
    public int numTeams(int[] rating) {
        int countTeams = 0;

        for (int i = 0; i < rating.length; i++) {
            int lowerValues = 0;

            for (int j = 0; j < i; j++) {
                if (rating[i] > rating[j]) {
                    lowerValues++;
                }
            }

            int higherValues = 0;
            for (int k = i + 1; k < rating.length; k++) {
                if (rating[i] < rating[k]) {
                    higherValues++;
                }
            }

            countTeams += lowerValues * higherValues;
            countTeams += (i - lowerValues) * ((rating.length - (i + 1)) - higherValues);
        }

        return countTeams;
    }
}
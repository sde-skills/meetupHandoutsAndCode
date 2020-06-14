import java.lang.Math;

class Solution {
    public int[][] merge(int[][] intervals) {

        List<int[]> intervalsList = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            intervalsList.add(intervals[i]);
        }
        Collections.sort(intervalsList,(int[] a, int[] b) -> a[0] - b[0]);

        return mergeSortedIntervals(intervalsList);

    }

    private int[][] mergeSortedIntervals(List<int[]> intervals) {

        if (intervals == null || intervals.size() == 0) {
            return new int[0][0];
        }

        for(int i = 1; i < intervals.size(); i++){
            if (intervals.get(i)[0] <= intervals.get(i-1)[1]) {
                intervals.get(i-1)[1] = Math.max(intervals.get(i)[1], intervals.get(i-1)[1]);
                intervals.remove(i);
                i--;
            }
        }

        return intervals.toArray(new int[0][0]);
    }
}
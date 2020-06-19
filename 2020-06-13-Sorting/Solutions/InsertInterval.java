class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> result = new ArrayList<>();
        int i = 0;
        // boolean isNewRowinserted = false;
        while(i < intervals.length) {
            if(intervals[i][0] > newInterval[0]) {
                result.add(newInterval);
                //       isNewRowinserted =  true;
                break;
            }
            result.add(intervals[i++]);
        }

        while(i < intervals.length) {
            result.add(intervals[i++]);
        }
        /*if (!isNewRowinserted) {
            result.add(newInterval);
        }*/

        if (intervals.length == result.size()) {
            result.add(newInterval);
        }

        return mergeSortedIntervals(result);
    }


    private int[][] mergeSortedIntervals(List<int[]> intervals) {

        if (intervals == null || intervals.size() == 0) {
            return new int[0][0];
        }

        for(int i = 1; i < intervals.size(); i++){
            if (intervals.get(i)[0] <= intervals.get(i-1)[1]) {
                intervals.get(i-1)[1] = Math.max(intervals.get(i)[1],                                                   intervals.get(i-1)[1]);
                intervals.remove(i);
                i--;
            }
        }

        return intervals.toArray(new int[0][0]);
    }
}
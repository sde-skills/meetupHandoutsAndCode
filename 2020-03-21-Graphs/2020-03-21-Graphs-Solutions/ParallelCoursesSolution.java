class ParallelCoursesSolution {
    public int minimumSemesters(int N, int[][] relations) {
        Map<Integer, List<Integer>> coursePath = new HashMap<>();
        int[] prerequisites = new int[N + 1]; // N + 1 because the courses start with 1
        Queue<Integer> fundamentals = new LinkedList();
        buildCoursePath(coursePath, prerequisites, relations);
        findFundamentalCourses(fundamentals, prerequisites);
        return calculateSemesters(coursePath, fundamentals, prerequisites, N);
    }
    
    private int calculateSemesters(Map<Integer, List<Integer>> coursePath, Queue<Integer> fundamentals, int[] prerequisites, int N) {
        int semesters = 0;
        while(!fundamentals.isEmpty()) {
            int numberOfCourses = fundamentals.size();
            while(numberOfCourses > 0) {
                int course = fundamentals.poll();
                N--;
                if(coursePath.containsKey(course)) {
                    List<Integer> nextCourses = coursePath.remove(course);
                    for(int nextCourse: nextCourses) {
                        prerequisites[nextCourse]--;
                        if(prerequisites[nextCourse] == 0) {
                            fundamentals.add(nextCourse);
                        }
                    }
                } 
                numberOfCourses--;
            }
            semesters++;
        }
        if(N > 0) return -1;
        return semesters;
    }
    
    private void findFundamentalCourses(Queue<Integer> fundamentals, int[] prerequisites) {
        for(int i = 1; i < prerequisites.length; i++) {
            if(prerequisites[i] == 0) {
                fundamentals.add(i);
            }
        }
    }
    
    private void buildCoursePath(Map<Integer, List<Integer>> coursePath, int[] prerequisites, int[][] relations) {
        for(int[] relation: relations) {
            List<Integer> nextCourses = new ArrayList<>();
            int course1 = relation[0];
            int course2 = relation[1];
            if(coursePath.containsKey(course1)) {
                nextCourses = coursePath.get(course1);
            }
            nextCourses.add(course2);
            coursePath.put(course1, nextCourses);
            prerequisites[course2]++; // updating the number of prerequisites course 2 has
        }
    }
}
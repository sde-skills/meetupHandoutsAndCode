class FindJudgeSolution {
    public int findJudge(int N, int[][] trust) {
        if(trust.length < N-1) return -1;
        int[] trustedBy = new int[N + 1];
        int[] trusts =  new int[N + 1];
        for(int i = 0; i < trust.length; i++) {
            int person1 = trust[i][0];
            int person2 = trust[i][1];
            trusts[person1]++;
            trustedBy[person2]++;
        }
        for(int i = 1; i <= N; i++) {
            if(trustedBy[i] == N-1 
               //trusted by everyone
               && 
               //trusts no one
               trusts[i] == 0) return i;
        }
        return -1;
    }
}
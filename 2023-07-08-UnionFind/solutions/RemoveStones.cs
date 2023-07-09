public class Solution {
    public int RemoveStones(int[][] stones) {
        var u =  new  UnionFind (stones);
        foreach (var stoneRow in stones)
        {
            u.union(stoneRow[0], stoneRow[1]);
        }
        return stones.Length - u.count;
    }
    
    class UnionFind
    {
        private Dictionary<string, string> parent = new Dictionary<string, string>();
        public int count;

        public UnionFind(int[][] stones)
        {
            foreach (var stoneRow in stones)
            {
                string row = $"R{stoneRow[0]}";
                string col = $"C{stoneRow[1]}";
                parent[row] = row;
                parent[col] = col;
            }
            count = parent.Count;
        }

        public string find(string x)
        {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int row, int col)
        {
            var parentRow = find($"R{row}");
            var parentCol = find($"C{col}");
            if (parentRow == parentCol) return;
            count--;
            parent[parentCol] = parent[parentRow];
        }
    }
}
public class Solution {
    List<(int row, int col)> directions = new List<(int row, int col)> { (0,1), (-1,0) };

    public int NumIslands(char[][] grid) {
        var unionFind = new UnionFind(grid.Length * grid[0].Length);
        int index = 0;
        for(int row = 0 ; row<grid.Length; row++)
        {
            for(int col = 0; col < grid[0].Length; col++)
            {
                 if (grid[row][col] == '0') { unionFind.Remove(index);}
                 else
                 {
                   foreach(var direction in directions)
                   {
                       var newRow = row + direction.row;
                       var newCol = col + direction.col;
                       if (newRow <0|| newCol<0) continue;
                       if (newRow == grid.Length || newCol == grid[0].Length) continue;
                       if (grid[newRow][newCol] == '0') continue;
                       int nextIndex = index;
                        nextIndex += (direction.row == 0) ? direction.col :   // right or up
                                direction.row > 0 ? grid[0].Length : -grid[0].Length;                       
                       unionFind.Union(index, nextIndex);
                   }
                 }
                 index++;   
            }
        }
        return unionFind.GetCount();
    }

    class UnionFind
    {
        private Dictionary<int, int> parents;
        private int count = 0;
        public int GetCount() 
        {
            return count; 
        }

        public UnionFind(int count)
        {
           parents = Enumerable.Range(0, count).ToDictionary(key=> key, value => value);
           this.count = count;
        }

        public void Remove(int n)
        {
            parents.Remove(n); count--;
        }

        public int Find(int n)
        {
            return (parents[n] != n) ? Find(parents[n]) : parents[n];
        }

        public void Union(int n, int m)
        {
            var parentN = Find(n);
            var parentM = Find(m);
            if (parentN == parentM) return;
            parents[parentN] = parentM;
            count--;
        }
    }
}
 
public class Solution {    
    public int[] FindRedundantConnection(int[][] edges) {
        var n = edges.Max(x=>Math.Max(x[0],x[1]));

        var unionFind = new UnionFind(n);

        for (int i = 0; i < edges.Length; i++)
        {
            if (!unionFind.Union(edges[i][0], edges[i][1]))
            {
                return new int[] {edges[i][0], edges[i][1]};
            }
        }
        throw new Exception();
    }

    class UnionFind
    {
        private Dictionary<int, int> parent = new Dictionary<int, int>();

        public UnionFind(int n)
        {
            parent =  Enumerable.Range(1, n).ToDictionary(key => key, value => value);
        }

        private int Find(int x)
        {
           return (parent[x] != x) ? Find(parent[x]) : parent[x];
        }

        public bool Union(int x, int y)
        {
            int parentX = Find(x);
            int parentY = Find(y);

            if (parentX == parentY)
            {
                return false;
            }
              parent[parentY] = parent[parentX];
           
            return true;
        }
    }
}
public class Solution {
    public IList<IList<string>> AccountsMerge(IList<IList<string>> accounts) {
        var unionFind = new UnionFind(accounts.Count);
        var emailMapping = new Dictionary<string, int>();
        for(int row = 0; row < accounts.Count; row++)
        {
            for(int col = 1; col < accounts[row].Count; col++)
            {   
                var email = accounts[row][col];
                if (emailMapping.ContainsKey(email))
                {
                    unionFind.Union(emailMapping[email], row);
                }
                else
                { 
                    emailMapping.Add(email, row); 
                }
            }
        }

        var mergedEmails = new Dictionary<int, IList<string>>();
        foreach(var email in emailMapping)
        {
            var parent = unionFind.Find(email.Value);
            mergedEmails[parent] = mergedEmails.GetValueOrDefault(parent, new List<string>());
            mergedEmails[parent].Add(email.Key);
        }

        var result = new List<IList<string>>();
        foreach (var emails in mergedEmails)
        {
            var mails = new List<string>() {accounts[emails.Key][0]};
            mails.AddRange(emails.Value.OrderBy(x => x, StringComparer.Ordinal).ToList());
            result.Add(mails);
        }

        return result;
    }
    
    class UnionFind
    {
        private Dictionary<int, int> parents;
        public UnionFind(int count)
        {
           parents = Enumerable.Range(0, count).ToDictionary(key=> key, value => value);
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
        }
    }
}
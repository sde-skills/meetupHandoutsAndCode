class EvaluateDivisionSolution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        
        Graph graph = new Graph(equations, values);
        double[] result = new double[queries.size()];
        for(int i = 0; i < queries.size(); i++) {
           result[i] = graph.getTotal(queries.get(i));
        }
        return result;
    }
}

class Graph {
    Map<String, GraphNode> graphNodes;
    
    public Graph(List<List<String>> equations, double[] values) {
        graphNodes = new HashMap();
        String dividend1, dividend2;
        for(int i = 0; i < equations.size(); i++) {
            dividend1 = equations.get(i).get(0);
            dividend2 = equations.get(i).get(1);
            GraphNode dividend1Node = graphNodes.getOrDefault(dividend1, new GraphNode()); 
            GraphNode dividend2Node = graphNodes.getOrDefault(dividend2, new GraphNode());
            dividend1Node.add(dividend2, values[i]);
            dividend2Node.add(dividend1, 1 / values[i]);
            if(!graphNodes.containsKey(dividend1))
                graphNodes.put(dividend1, dividend1Node);
            if(!graphNodes.containsKey(dividend2))
                graphNodes.put(dividend2, dividend2Node);
        }
    }
    
    public double getTotal(List<String> query) {
        String from = query.get(0);
        String to = query.get(1);
        Set<String> visited = new HashSet();
        return dfs(from,to,visited);
    }
    
    private double dfs(String start, String end, Set<String> visited) {
        if(!graphNodes.containsKey(start)) return -1.0;
        Map<String, Double> startDivisorMap = graphNodes.get(start).divisorResultMap;
        if(startDivisorMap.containsKey(end)) return startDivisorMap.get(end);
        visited.add(start);
        for(Map.Entry<String, Double> divisorResult: startDivisorMap.entrySet()) {
            if(!visited.contains(divisorResult.getKey())) {
                double result = dfs(divisorResult.getKey(), end, visited);
                if(result != -1) {
                    return divisorResult.getValue() * result;
                }
            }
        }
        return -1.0;
    }
}

class GraphNode {
    Map<String, Double> divisorResultMap;
    
    public GraphNode() {
        divisorResultMap = new HashMap();
    }
    
    public void add(String divisor, double result) {
        divisorResultMap.put(divisor, result);
    }
}
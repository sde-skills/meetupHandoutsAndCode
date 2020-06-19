class Solution {
    
    class BuildingPoint{
        int x, y;
        boolean isStart;
        BuildingPoint(int _x, int _y, boolean _isSt){
            x=_x;
            y=_y;
            isStart=_isSt;
        }
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<BuildingPoint> bpList=new ArrayList<>();
        for(int[] b: buildings){
            bpList.add(new BuildingPoint(b[0], b[2], true));
            bpList.add(new BuildingPoint(b[1], b[2], false));
        }
        
        Collections.sort(bpList, (b1, b2) -> {
            if(b1.x!=b2.x)
                return b1.x-b2.x; //lowest x should be picked first
            else if(b1.isStart && b2.isStart){
                return b2.y-b1.y; //highest one appear first
            }
            else if(!b1.isStart && !b2.isStart){ //both are end
                return b1.y-b2.y; //lowest appear first
            }
            else{
                return b1.isStart ? -1 : 1; //start needs to go first
            }
        });
        
        
        List<List<Integer>> result=new ArrayList<>();
        TreeMap<Integer, Integer> pq=new TreeMap<>(); //Priority queue <height, count>
        pq.put(0, 1); //initialized with 0
        int prevMaxHeight=0;
        
        for(BuildingPoint b: bpList){
            if(b.isStart){
                pq.put(b.y, pq.getOrDefault(b.y, 0)+1);
            }
            else{
                pq.put(b.y, pq.getOrDefault(b.y, 0)-1);
                if(pq.get(b.y)==0)
                    pq.remove(b.y);
            }
            
            int curMaxHeight=pq.lastKey();
            
            if(prevMaxHeight!=curMaxHeight){
                result.add(Arrays.asList(b.x, curMaxHeight));
                prevMaxHeight=curMaxHeight;
            }
        }
        return result;
    }
}
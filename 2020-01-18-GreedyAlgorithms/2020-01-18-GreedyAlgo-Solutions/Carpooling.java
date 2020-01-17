/*
Carpooling
*/
public boolean carPooling(int[][] trips, int capacity) {
        //sort by trip start time
        Arrays.sort(trips, new java.util.Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        
        int noOfPassengers = 0;
        PriorityQueue<Trip> heap = new PriorityQueue<Trip>(Comparator.comparing( t -> t.end));
        for(int i=0; i < trips.length; i++){
            int[] temp = trips[i];
            Trip currTrip = new Trip(temp[0], temp[1], temp[2]);
            if(heap.isEmpty()){
                heap.offer(currTrip);
                noOfPassengers = noOfPassengers + currTrip.pass;
            }else {
                while(!heap.isEmpty() && currTrip.start >= heap.peek().end){
                    noOfPassengers = noOfPassengers - heap.peek().pass;
                    heap.poll();
                }
                heap.offer(currTrip);
                noOfPassengers = noOfPassengers + currTrip.pass;
            }
            if(noOfPassengers > capacity){
                return false;
            }
        }
        return true;
    }
    class Trip{
        int pass;
        int start;
        int end;
        public Trip(int pass, int s, int e){
            this.pass = pass;
            this.start = s;
            this.end = e;
        }
    }
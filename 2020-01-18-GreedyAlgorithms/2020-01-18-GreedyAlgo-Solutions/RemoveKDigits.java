/*
Remove K digits
*/
public String removeKdigits(String num, int k) {
        return helper();
}
public String helper(String num, int k){        
        if(num.length() == k) return "0";
        if(k == 0) return num;
        StringBuilder sb = new StringBuilder();
        int i =0;
        int j= i+1;
        List<Integer> delIndex = new ArrayList<Integer>();
        while(i < num.length() && j < num.length()){
            if(k <= 0){
                break;
            }
            if(Character.getNumericValue(num.charAt(i)) == Character.getNumericValue(num.charAt(j))){
                j++;
            }else if(Character.getNumericValue(num.charAt(i)) < Character.getNumericValue(num.charAt(j))){
                k--;
                delIndex.add(j);
                j++;
            }else if(Character.getNumericValue(num.charAt(i)) > Character.getNumericValue(num.charAt(j))){
                k--;
                delIndex.add(i);
                i++;
                j++;
            }else if(Character.getNumericValue(num.charAt(i)) == 0){
                i++;
                j++;
            }
        }
        if(delIndex.size() == 0){
            sb = new StringBuilder(num);
            sb.delete(0, k);
            return sb.toString();
        }
        for(int t=0; t < num.length(); t++){
          if(!delIndex.contains(t)){
                sb.append(num.charAt(t));
          }
        }
        System.out.println(sb.toString());
        if(Character.getNumericValue(sb.charAt(0)) == 0){
            if(sb.length() > 1){
                sb.deleteCharAt(0);
                return sb.toString();
            }else {
                return "0";
            }
        }
        return sb.toString();
    }
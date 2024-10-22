package participationProblems;

import java.util.*;

public class Problem3 {



    public static String[] getDestinations(String[][] trips){
        HashMap<String,Integer[]> destinationInfo = new HashMap<>();
        HashMap<String,String> tripMap = new HashMap<>();
        String[] finalList = new String[2];

        for (int i=0; i<trips.length;i++) {
            String start = trips[i][0];
            String end = trips[i][1];
            tripMap.put(start,end);
            if(destinationInfo.containsKey(start)){
                (destinationInfo.get(start)[0])++;
            }else{
                destinationInfo.put(start, new Integer[]{1,0});
            }
            if(destinationInfo.containsKey(end)){
                destinationInfo.get(end)[0]++;
            }else{
                destinationInfo.put(end, new Integer[]{1,1});
            }
        }
        for (Map.Entry<String, Integer[]> location : destinationInfo.entrySet()) {
            String name = location.getKey();
            Integer[] info  = location.getValue();
            if(info[0]==0){
                if(info[1]==0){
                    finalList[0] = name;
                }else{
                    finalList[1] = name;
                }
            }
            
        }
        return finalList;
    }

    public static void main(String[] args) {
        
    }
}

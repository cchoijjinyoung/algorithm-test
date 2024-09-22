import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        // 각각의 친구들에 대해 id를 부여
        Map<String, Integer> idMap = new HashMap<>();
        
        for (int i = 0; i < friends.length; i++) {
            idMap.put(friends[i], i);
        }
        
        // 각자 각 친구마다 몇 개의 선물을 줬는지 이차원 배열로 표현
        int[][] map = new int[friends.length][friends.length];
        
        // 각자 총 몇 개의 선물을 줬는지 표현
        int[] give = new int[friends.length];
        
        // 각자 총 몇 개의 선물을 받았는지 표현
        int[] take = new int[friends.length];
        
        for (int i = 0; i < gifts.length; i++) {
            String[] splited = gifts[i].split(" ");
            String sender = splited[0];
            String receiver = splited[1];
            
            int senderId = idMap.get(sender);
            int receiverId = idMap.get(receiver);
            
            map[senderId][receiverId]++;
            give[senderId]++;
            take[receiverId]++;
        }
        
        int[] expect = new int[friends.length];
        
        for (int i = 0; i < friends.length; i++) {
            for (int j = i; j < friends.length; j++) {
                int f1 = map[i][j];
                int f2 = map[j][i];
                
                if (f1 > f2) {
                    expect[i]++;
                } else if (f2 > f1) {
                    expect[j]++;
                } else { // 같은 갯수의 선물을 주고 받았다면,
                    int v1 = give[i] - take[i];
                    int v2 = give[j] - take[j];
                    
                    if (v1 > v2) {
                        expect[i]++;
                    } else if (v2 > v1) {
                        expect[j]++;
                    }
                }
            }
        }
        
        int answer = 0;
        for (int i = 0; i < expect.length; i++) {
            answer = Math.max(answer, expect[i]);
        }
        
        return answer;
    }
}
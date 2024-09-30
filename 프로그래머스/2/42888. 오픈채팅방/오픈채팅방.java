import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        List<String[]> list = new ArrayList<>();
        
        Map<String, String> users = new HashMap<>();
        
        for (String s : record) {
            String userId = s.split(" ")[1];
            if (s.startsWith("Enter")) {
                String nickname = s.split(" ")[2];
                list.add(new String[]{userId, "님이 들어왔습니다."});
                users.put(userId, nickname);
            } else if (s.startsWith("Leave")) {
                list.add(new String[]{userId, "님이 나갔습니다."});
            } else {
                String nickname = s.split(" ")[2];
                users.put(userId, nickname);
            }
        }
        
        String[] answer = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String[] row = list.get(i);
            String userId = row[0];
            String message = row[1];
            answer[i] = users.get(userId) + message;
        }
        return answer;
    }
}
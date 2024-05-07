import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        
        Map<String, Integer> hm = new HashMap<>();
        for (String s : id_list) {
            hm.put(s, 0);
        }
        
        Set<String> hs = new HashSet<>();
        for (String s : report) {
            hs.add(s);
        }
        
        Map<String, List<String>> map = new HashMap<>();
        for (String s : hs) {
            String[] tuple = s.split(" ");
            String reporter = tuple[0];
            String reportee = tuple[1];
            List<String> reportees = map.getOrDefault(reporter, new ArrayList<String>());
            reportees.add(reportee);
            map.put(reporter, reportees);
            
            hm.put(reportee, hm.get(reportee) + 1);
            
        }
        
        List<String> suspendeds = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : hm.entrySet()) {
            if (entry.getValue() >= k) {
                suspendeds.add(entry.getKey());
            }
        }
        
        // map 순회하면서, suspended에 있는 애가 있는 지.
        int[] answer = new int[id_list.length];
        if (suspendeds.size() > 0) {
            for (int i = 0; i < id_list.length; i++) {
                if (map.get(id_list[i]) == null) {
                    continue;
                }
                List<String> reportees = map.get(id_list[i]);
                int count = 0;

                for (String suspended : suspendeds) {
                    for (String reportee : reportees) {
                        if (suspended.equals(reportee)) {
                            count++;
                        }
                    }
                }
                answer[i] = count;
            }
        }
        
        return answer;
    }
}
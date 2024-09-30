import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        Map<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < files.length; i++) {
            hm.put(files[i], i);
        }
        
        Arrays.sort(files, (f1, f2) -> {
            String[] sp1 = convert(f1);
            String[] sp2 = convert(f2);
            
            String h1 = sp1[0].toUpperCase();
            String h2 = sp2[0].toUpperCase();
            
            System.out.println(sp1[1]);
            System.out.println(sp2[1]);
            
            int n1 = Integer.parseInt(sp1[1]);
            int n2 = Integer.parseInt(sp2[1]);
            
            if (h1.compareTo(h2) == 0) {
                if (n1 - n2 == 0) {
                    return hm.get(f1) - hm.get(f2);
                }
                return n1 - n2;
            }
            return h1.compareTo(h2);
        });
        
        String[] answer = files;

        return answer;
    }
    
    public String[] convert(String file) {
        String[] result = new String[2];
        int start = 0;
        int end = 0;
        
        for (int i = 0; i < file.length(); i++) {
            if (Character.isDigit(file.charAt(i))) {
                start = i;
                for (int j = start; j < file.length(); j++) {
                    if (Character.isDigit(file.charAt(j))) {
                        end = j + 1;
                        continue;
                    } else {
                        break;
                    }
                }
                break;
            }
        }
        
        String HEAD = file.substring(0, start);
        String NUMBER = file.substring(start, end);
        
        result[0] = HEAD;
        result[1] = NUMBER;
        
        return result;
    }
}
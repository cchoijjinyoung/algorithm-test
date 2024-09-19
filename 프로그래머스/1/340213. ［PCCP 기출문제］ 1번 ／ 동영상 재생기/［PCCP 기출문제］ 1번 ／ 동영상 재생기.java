class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int posi = convert(pos);
        int leni = convert(video_len);
        int starti = convert(op_start);
        int endi = convert(op_end);
        
        if (check(posi, starti, endi)) {
           posi = endi;
        }
        
        for (int i = 0; i < commands.length; i++) {
            if ("prev".equals(commands[i])) {
                posi = prev(posi);
            } else {
                posi = next(posi, leni);
            }
            
            if (check(posi, starti, endi)) {
               posi = endi;
            }
        }
        return convertStr(posi);
    }
    
    public String convertStr(int pos) {
        String answer = "";
        int m = pos / 60;
        int s = pos % 60;
        if (m < 10) {
            answer += "0";
        }
        answer += String.valueOf(m);
        answer += ":";
        
        if (s < 10) {
            answer += "0";
        }
        answer += String.valueOf(s);
        return answer;
    }
    
    public int convert(String str) {
        String[] sp = str.split(":");
        int m = Integer.parseInt(sp[0]);
        int s = Integer.parseInt(sp[1]);
        int answer = m * 60 + s;
        return answer;
    }
    
    public boolean check(int pos, int op_start, int op_end) {
        if (op_start <= pos && pos <= op_end) {
            return true;
        }
        return false;
    }
    
    public int prev(int pos) {
        pos -= 10;
        if (pos <= 0) {
            pos = 0;
        }
        return pos;
    }
    
    public int next(int pos, int len) {
        pos += 10;
        if (pos >= len) {
            pos = len;
        }
        return pos;
    }
}
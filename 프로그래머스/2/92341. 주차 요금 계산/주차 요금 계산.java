import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, List<String>> inMap = new HashMap<>();
        Map<String, List<String>> outMap = new HashMap<>();
        
        List<Car> cars = new ArrayList<>();
        
        for (int i = 0; i < records.length; i++) {
            String[] record = records[i].split(" ");
            String time = record[0];
            String number = record[1];
            String state = record[2];
            
            if ("IN".equals(state)) {
                List<String> list = inMap.getOrDefault(number, new ArrayList<String>());
                list.add(time);
                inMap.put(number, list);
                continue;
            }
            
            if ("OUT".equals(state)) {
                List<String> list = outMap.getOrDefault(number, new ArrayList<String>());
                list.add(time);
                outMap.put(number, list);
                continue;
            }
        }
        
        for (Map.Entry<String, List<String>> entry : inMap.entrySet()) {
            String number = entry.getKey();
            List<String> inTimes = entry.getValue();
            
            if (outMap.containsKey(number)) {
                cars.add(new Car(number, inTimes, outMap.get(number)));
            } else {
                List<String> outTimes = new ArrayList<>();
                outTimes.add("23:59");
                cars.add(new Car(number, inTimes, outTimes));
            }
        }
        
        cars.sort((c1, c2) -> Integer.parseInt(c1.number) - Integer.parseInt(c2.number));
        
        int[] answer = new int[cars.size()];
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            answer[i] = car.price(car.inTimes, car.outTimes, fees);
        }
        return answer;
    }
    
    class Car {
        String number;
        List<String> inTimes = new ArrayList<>();
        List<String> outTimes = new ArrayList<>();
        
        public Car(String number, List<String> inTimes, List<String> outTimes) {
            this.number = number;
            this.inTimes = inTimes;
            this.outTimes = outTimes;
        }
        
        public int price(List<String> inTimes, List<String> outTimes, int[] fees) {
            int result = 0;
            int time = calc(inTimes, outTimes);
            System.out.println(time);
            if (time > fees[0]) {
                // 기본요금 + time - fees[0] / fees[2] * fees[3];
                int temp = time - fees[0];
                if (temp % fees[2] > 0) {
                    result = fees[1] + (temp / fees[2] + 1) * fees[3];
                } else {
                    result = fees[1] + (temp / fees[2]) * fees[3];
                }
            } else {
                result = fees[1];
            }
            return result;
        }
        
        public int calc(List<String> inTimes, List<String> outTimes) {
            int time = 0;
        
            if (inTimes.size() != outTimes.size()) {
                outTimes.add("23:59");
            }
            for (int i = 0; i < inTimes.size(); i++) {
                String inTime = inTimes.get(i);
                String outTime = outTimes.get(i);
                
                int diff = transfer(outTime) - transfer(inTime);
                time += diff;
            }
            return time;
        }
        
        private int transfer(String strTime) {
            int result = 0;
            String[] s = strTime.split(":");
            String h = s[0];
            String m = s[1];
            
            result = Integer.parseInt(h) * 60 + Integer.parseInt(m);
            System.out.println(h + " " + m + " " + result);
            return result;
        }
    }
}
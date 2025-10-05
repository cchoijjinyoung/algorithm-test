class Solution {
    public String solution(int[] food) {
        StringBuilder front = new StringBuilder();
        StringBuilder back = new StringBuilder();
        
        for (int i = 1; i < food.length; i++) {
            int quantity = food[i];
            int repeat = quantity / 2;
            while (repeat --> 0) {
                front.append(i);
                back.append(i);
            }
        }
        front.append("0");
        back.reverse();
        front.append(back);
        return front.toString();
    }
}
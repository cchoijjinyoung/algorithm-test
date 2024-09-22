class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxHealth = health;
        int maxStack = bandage[0];
        int heal = bandage[1];
        int bonus = bandage[2];
        
        int second = 0;
        int stack = 0;
        int idx = 0;
        while (true) {
            if (idx == attacks.length) {
                break;
            }
            second++;
            if (attacks[idx][0] == second) {
                stack = 0;
                health -= attacks[idx][1];
                if (health <= 0) {
                    return -1;
                }
                idx++;
                continue;
            }
            stack++;
            
            if (stack == maxStack) {
                health += bonus;
                stack = 0;
            }
            
            health += heal;
            if (health > maxHealth) {
                health = maxHealth;
            }
        }
        return health;
    }
}
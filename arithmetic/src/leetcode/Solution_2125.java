package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_2125 {
    public int numberOfBeams(String[] bank) {
        int ans = 0;
        int lastNum = 0;
        for (String s : bank) {
            int num = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    num++;
                }
            }
            if (num != 0) {
                ans += num * lastNum;
                lastNum = num;
            }
        }
        return ans;
    }
}

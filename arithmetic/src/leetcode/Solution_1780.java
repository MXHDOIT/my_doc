package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: maxinhang.
 */
public class Solution_1780 {
    public static void main(String[] args) {
        new Solution_1780().checkPowersOfThree(21);
    }

    public boolean checkPowersOfThreeOpt(int n) {
        while (n > 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3;
        }
        return true;
    }

    public boolean checkPowersOfThree(int n) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        while (true) {
            int pow = (int) Math.pow(3, i);
            if (pow > n) {
                break;
            }
            list.add(pow);
            i++;
        }
        return check(n, 0, list, 0);
    }

    public boolean check(int n, int num, List<Integer> list, int index) {
        if (n == num) {
            return true;
        }
        if (num > n) {
            return false;
        }
        for (int i = index; i < list.size(); i++) {
            if (check(n, num + list.get(i), list, i + 1)) {
                return true;
            }
        }
        return false;
    }
}

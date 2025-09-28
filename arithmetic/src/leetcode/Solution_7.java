package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_7 {
    public int reverse(int x) {
        int res = 0;
        int last = 0;
        while(x!=0) {
            //每次取末尾数字
            int tmp = x%10;
            last = res;
            res = res*10 + tmp;
            //判断整数溢出
            if(last != res/10)
            {
                return 0;
            }
            x /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        new Solution_7().reverse(-123);
    }
}

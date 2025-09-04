package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_468 {

    public static void main(String[] args) {
        new Solution_468().validIPAddress("0.0.0.0");
    }
    public String validIPAddress(String queryIP) {
        if (isIPv4(queryIP)) {
            return "IPv4";
        }
        if (isIPv6(queryIP)) {
            return "IPv6";
        }

        return "Neither";
    }

    private boolean isIPv6(String queryIP) {
        if (queryIP.charAt(0) == ':' || queryIP.charAt(queryIP.length()-1) == ':') {
            return false;
        }
        String[] split = queryIP.split("\\:");
        if (split.length != 8) {
            return false;
        }
        for (String s : split) {
            if (s.length() > 4 || s.length() < 1) {
                return false;
            }
            char[] chars = s.toCharArray();
            for (char ch : chars) {
                if ((ch >= '0' && ch <= '9')
                        || (ch >= 'a' && ch <= 'z')
                        || (ch >= 'A' && ch <= 'Z')) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isIPv4(String queryIP) {
        if (queryIP.charAt(0) == '.' || queryIP.charAt(queryIP.length()-1) == '.') {
            return false;
        }
        String[] split = queryIP.split("\\.");
        if (split.length != 4) {
            return false;
        }
        for (String s : split) {
            if (s.length() > 3 || s.length() < 1) {
                return false;
            }
            char[] chars = s.toCharArray();
            int num = 0;
            for (int i = 0; i < chars.length; i++) {
                char ch = chars[i];
                if (ch < '0' || ch > '9') {
                    return false;
                }
                if (i == 0 && ch == '0' && chars.length != 1) {
                    return false;
                }
                num = num * 10 + (ch - '0');
            }
            if (num > 255) {
                return false;
            }
        }
        return true;
    }
}

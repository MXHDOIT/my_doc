package leetcode;

import java.util.Objects;
import java.util.Stack;

/**
 * @author: maxinhang.
 */
public class MinStack {
    Stack<Integer> masterStack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();
    public MinStack() {

    }

    public void push(int val) {
        masterStack.push(val);
        if (minStack.isEmpty() || minStack.peek() >= val) {
            minStack.push(val);
        }
    }

    public void pop() {
        Integer pop = masterStack.pop();
        if (Objects.equals(minStack.peek(), pop)) {
            minStack.pop();
        }
    }

    public int top() {
       return masterStack.peek();
    }

    public int getMin() {
       return minStack.peek();
    }
}

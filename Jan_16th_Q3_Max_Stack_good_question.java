/*
716. Max Stack

https://leetcode.com/problems/max-stack/description/

Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
*/

/* Solution 1: popMax() -> O(n)
class MaxStackV1 {
    Deque<Integer> stack1;
    Deque<Integer> stack2;
    Deque<Integer> tmp;
    
    
    public MaxStack() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
        tmp = new ArrayDeque<>();
    }
    
    public void push(int x) {
        stack1.offerFirst(x);
        
        if (!stack2.isEmpty() && stack2.peek() > x) {
            stack2.offerFirst(stack2.peek());
        } else {
            stack2.offerFirst(x);
        }
        
    }
    
    public int pop() {
        
        stack2.pollFirst();
        return stack1.pollFirst();
        
    }
    
    public int top() {
        return stack1.peek();
    }
    
    public int peekMax() {
        return stack2.peek();
    }
    
    public int popMax() {
        int max = stack2.peek();
        while (max != stack1.peek()) {
            tmp.offerFirst(stack1.peek());
            pop();
        }
        
        pop();
        while (!tmp.isEmpty()) {
            push(tmp.peek());
            tmp.pop();
        }
        
        return max;
    }
}
*/

/*
Solution 2: TreeMap + DoubleLinkedList -> popMax() -> O(lgn)
*/
class MaxStack {
    private class Node {
        int val;
        Node prev;
        Node next;
        
        public Node(int val) {
            this.val = val;
        }
    }
    Node head;
    Node tail;
    TreeMap<Integer, List<Node>> map;
    
    /** initialize your data structure here. */
    public MaxStack() {
        map = new TreeMap<Integer, List<Node>>();
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
    }
    
    public void push(int x) {
        Node cur = new Node(x);
        tail.prev.next = cur;
        cur.prev = tail.prev;
        cur.next = tail;
        tail.prev = cur;
        
        if (!map.containsKey(x)) {
            map.put(x, new ArrayList<Node>());
        }
        
        map.get(x).add(cur);
    }
    
    public int pop() {
        Node node = tail.prev;
        Node pre = node.prev;
        pre.next = tail;
        tail.prev = pre;
        
        map.get(node.val).remove(map.get(node.val).size() - 1);
        if (map.get(node.val).size() == 0) {
            map.remove(node.val);
        }
        return node.val;
    }
    
    public int top() {
        return tail.prev.val;
    }
    
    public int peekMax() {
        return map.lastKey();
    }
    
    public int popMax() {
        int max = map.lastKey();
        List<Node> nodes = map.get(max);
        Node removed = nodes.get(nodes.size() - 1);
        map.get(max).remove(nodes.size() - 1);
        
        if (map.get(max).size() == 0) {
            map.remove(max);
        }
        
        Node pre = removed.prev;
        Node next = removed.next;
        pre.next = next;
        next.prev = pre;
        
        return max;
    }
}


/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
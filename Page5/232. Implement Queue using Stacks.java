//My code:分为两个栈，一个用来出栈操作，一个用在入栈操作
class MyQueue {
    Stack<Integer> in = new Stack<>();//入栈需要在in中操作
    Stack<Integer> out = new Stack<>();//出栈需要在out中操作
    /** Initialize your data structure here. */
    public MyQueue() {
        
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) { //每次入栈都需要检查并将数据移动至in
        if (in.isEmpty()) move(out, in);
        in.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() { //每次出栈都需要检查并将数据移动至out
        if (out.isEmpty()) move(in, out);
        return out.pop();
    }
    
    /** Get the front element. */
    public int peek() { //每次出栈都需要检查并将数据移动至out
        if (out.isEmpty()) move(in, out);
        return out.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
    
    private void move(Stack<Integer> from, Stack<Integer> to) {
        while (!from.isEmpty()) {
            to.push(from.pop());
        }
    }
}

//Discuss：其实根本不需要每次入栈的检测以及不必要的移动
//当out栈为空时，移动in栈中的数据至out栈，这样可以让前部分的队列随意出队，后半部分的队列随意入队
class MyQueue {
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;
    /** Initialize your data structure here. */
    public MyQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) { //入栈直接入栈，不关乎后续出栈操作
        stackIn.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() { 
        if (stackOut.isEmpty()) { //出栈时需要检测out栈是否为空，为空则需要将in栈数据移动至out栈中
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if (stackOut.isEmpty()) { //出栈时需要检测out栈是否为空，为空则需要将in栈数据移动至out栈中
            while (!stackIn.isEmpty()) { 
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }
}

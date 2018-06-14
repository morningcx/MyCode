/**
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
*/

My code://105ms 82%
//看了discuss改进后的做法，主要是每个节点多存放一个记录入栈时最小值的属性
class MinStack {
    class Element{
        public int x;
        public Element next;
        public int min=Integer.MAX_VALUE;
        public Element(int x){this.x=x;}
    }
    
    private Element head=null;
    
    private int min=Integer.MAX_VALUE;
    
    public MinStack() {
        head=new Element(0);
    }
    
    public void push(int x) {
        Element input=new Element(x);
        min=Math.min(x,min);
        input.min=min;
        input.next=head.next;
        head.next=input;
    }
    
    public void pop() {
        if(head.next.min == min ){
            if(head.next.next!=null)
                min=head.next.next.min;
            else
                min=Integer.MAX_VALUE;
        }
            
        head.next=head.next.next;
    }
    
    public int top() {
        return head.next.x;
    }
    
    public int getMin() {
        return head.next.min;
    }
}

Discuss：
（1）//自定义栈，外加min属性，代码简朴，思路清晰，不用头结点，代码会少很多
class MinStack {
    private Node head;
    
    public void push(int x) {
        if(head == null) 
            head = new Node(x, x);//如果栈为空则next不用设置，min就为当前插入值
        else 
            head = new Node(x, Math.min(x, head.min), head);//栈不为空，判断当前最小值和插入值，取最小值设置进属性min
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
    
    private class Node {
        int val;
        int min;
        Node next;
        
        private Node(int val, int min) {
            this(val, min, null);
        }
        
        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}
（2）//有的方法用了两个栈，一个存储当前最小值，一个存储栈值
//这个方法每次入栈入两个数值，先放入栈值，再放入最小值
class MinStack {

Stack<Integer> stack=new Stack<>();
int min=Integer.MAX_VALUE;
public void push(int x) {
    if(x<=min) {stack.push(min); min=x;}//当插入的值小于当前最小值，则多插入一次上次最小值节点，为出栈作准备
    stack.push(x);
}
public void pop() {
    //出栈判断一下值是否为min，为min则直接赋值出栈的那个元素，所以这里出栈后，栈为空也没关系，会将MAX_VALUE赋值给min
    if(stack.peek()==min){ stack.pop(); min=stack.pop(); }
    else stack.pop();
}
public int top() {
    return stack.peek();
}
public int getMin() {
    return min;
}
}

/**
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
*/

My code://没看清题目，说道最大存储空间为树的深度h
//这里就是中序遍历了一下树，然后顺序输出。
public class BSTIterator {

    List<TreeNode> result=new ArrayList<>();
    int pos=0;
    public BSTIterator(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        while(root!=null||!stack.isEmpty()){
            if(root!=null){
                stack.add(root);
                root=root.left;
            }else{
                TreeNode temp=stack.pop();
                result.add(temp);
                root=temp.right;
            }
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return pos<result.size();
    }

    /** @return the next smallest number */
    public int next() {
        return result.get(pos++).val;
    }
}

Discuss：
（1）//相当于把整个结构当做遍历二叉树的栈，每次获取节点的时候再去执行遍历操作
public class BSTIterator {

Stack<TreeNode> stack;
public BSTIterator(TreeNode root) {
    stack = new Stack<>();
    fillStack(root);
}

/** @return whether we have a next smallest number */
public boolean hasNext() {
    return !stack.isEmpty();
}

/** @return the next smallest number */
public int next() {
    TreeNode curNode = stack.pop();
    fillStack(curNode.right);
    return curNode.val;
}

private void fillStack(TreeNode root){
    while(root != null){
        stack.push(root);
        root = root.left;
    }
}
（2）//本质上都一样，没有在构造函数里先设置遍历，和栈遍历二叉树的算法吻合度较高
public class BSTIterator {
    
        Stack<TreeNode> stack =  null ;            
        TreeNode current = null ;
    	
        public BSTIterator(TreeNode root) {
        	  current = root;	     
        	  stack = new Stack<> ();
    	}
    
    	/** @return whether we have a next smallest number */
    	public boolean hasNext() {		  
    	      return !stack.isEmpty() || current != null;  
    	}
    
    	    /** @return the next smallest number */
    	public int next() {
    		while (current != null) {
    			stack.push(current);
    			current = current.left ;
    		}		
    		TreeNode t = stack.pop() ;		
    		current = t.right ;		
    		return t.val ;
    	}
    }

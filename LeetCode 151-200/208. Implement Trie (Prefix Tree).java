/**
Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true
Note:

You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.
*/

My code://前缀树，每个字母对应一个节点既可
//节点结构中包括26个节点指针数组和当前节点是否为单词的boolean值
class Trie {

    /** Initialize your data structure here. */
    private Element root;
    public Trie() {
        root = new Element();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Element cur = root;
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < wordArray.length; ++i) {
            int index = wordArray[i] - 97;
            if (cur.node[index] == null) 
                cur.node[index] = new Element();
            cur = cur.node[index];
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Element cur = root;
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < wordArray.length; ++i) {
            Element node = cur.node[wordArray[i] - 97];
            if (node == null) return false;
            cur = node;
        }
        return cur.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Element cur = root;
        char[] prefixArray = prefix.toCharArray();
        for (char c : prefixArray) {
            Element node = cur.node[c - 97];
            if (node == null) return false;
            cur = node;
        }
        return true;
    }
    
    
    class Element {
        boolean isWord = false;//是否为一个单词
        Element[] node = new Element[26];//子节点
    }
}

Discuss://将search和startsWith的寻找节点操作定义成一个函数，返回找到的尾节点，找不到返回null
//search需要判断尾节点是否存在，并且判断尾节点的isWord值是否为true
//startsWith只需要判断节点是否存在既可
class TrieNode {
    public boolean isWord;
    public TrieNode[] children = new TrieNode[26];
    // Initialize your data structure here.
    public TrieNode() {
        
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode ws = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if(ws.children[ch - 'a'] == null){
                ws.children[ch - 'a'] = new TrieNode();
            }
            ws = ws.children[ch - 'a'];
        }
        ws.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode ws = searchHelper(word);
        return ws != null && ws.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
         return searchHelper(prefix) != null;
    }
    
    public TrieNode searchHelper(String key){
        TrieNode ws = root;
        for(int i = 0; i < key.length() && ws != null; i++){
            char ch = key.charAt(i);
            ws = ws.children[ch - 'a'];
        }
        return ws;
    }
}

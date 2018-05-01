/**
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

Corner Cases:

Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
*/

My Code://11ms 80%
class Solution {
    public String simplifyPath(String path) {
        if(path.length()==0) return path;
        int i=0;
        StringBuilder result=new StringBuilder();
        while(i<path.length()){
            String s="";
            if(path.charAt(i)=='/'){ //如果当前字符为'/',删除后面所有无效的'/'
                while(i<path.length()&&path.charAt(i)=='/')
                    i++;
            }
            else{
                while(i<path.length()&&path.charAt(i)!='/')//文件名可能是2个长度及以上的，循环添加至s
                    s+=path.charAt(i++);
                if(!s.equals("..")&&!s.equals(".")){//有效文件名则添加result
                    result.append("/"+s);
                }
                else if(s.equals("..")&&result.length()!=0){//result不为空并且转向上级文件
                    while(result.charAt(result.length()-1)!='/')//删除当前文件名
                        result.deleteCharAt(result.length()-1);
                    result.deleteCharAt(result.length()-1);//删除文件名前的'/'
                }
            }
        }
        return result.length()==0?result.append("/").toString():result.toString();//长度为0要添加'/',即表示当前文件
    }
}

Discuss：//spilt对String进行划分（一开始也想到了，感觉那样做太投机取巧了），创建栈进行进栈出栈操作
public String simplifyPath(String path) {
    Deque<String> stack = new LinkedList<>();
    Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));
    for (String dir : path.split("/")) {
        if (dir.equals("..") && !stack.isEmpty()) stack.pop();//上一级文件并且不为空，出栈
        else if (!skip.contains(dir)) stack.push(dir);//划分字符不为空，并且不为'.'，即有效字符，则进栈
                                                      //这里有可能栈为空遇到'..'，绕过第一个判断，所以set里要存在'..'的判断
    }
    String res = "";
    for (String dir : stack) res = "/" + dir + res;
    return res.isEmpty() ? "/" : res;
}

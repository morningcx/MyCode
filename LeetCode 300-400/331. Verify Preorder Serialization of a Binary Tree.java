// my code 9%
class Solution {
    public boolean isValidSerialization(String preorder) {
        return isValid(Arrays.asList(preorder.split(",")));
    }
    
    public boolean isValid(List<String> list) {
        if (list.size() == 1 && list.get(0).equals("#")) {
            return true;
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < list.size();) {
            if (i < list.size() - 2 && !"#".equals(list.get(i)) && "#".equals(list.get(i + 1)) && "#".equals(list.get(i + 2))) {
                result.add("#");
                i += 3;
            } else {
                result.add(list.get(i++));
            }
        }
        if (list.size() == result.size()) return false;
        return isValid(result);
    }
    
}

// discuss
public boolean isValidSerialization(String preorder) {
    String[] nodes = preorder.split(",");
    int diff = 1; // diff = out - in;
    for (String node: nodes) {
        if (--diff < 0) return false; // in 节点减一
        if (!node.equals("#")) diff += 2;// 不为#则out节点加2
    }
    return diff == 0;
}

// my code map记录路程的起始点，对路程终点进行字典默认排序，dfs搜索第一个可以遍历完所有路程的路线，不行就回溯
class Solution {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (String[] ticket : tickets) {
            List<String> list = map.get(ticket[0]);
            if (list == null) {
                list = PriorityQueue<>();
                map.put(ticket[0], list);
            }
            list.add(ticket[1]);
        }
        return dfs(map, "JFK", new ArrayList<>(), tickets.length);
    }
    
    private List<String> dfs(Map<String, PriorityQueue<String>> map, String start, List<String> result, int len) {
        result.add(start);
        if (len == 0) return result;
        PriorityQueue<String> nextList = map.get(start);
        if (nextList != null) {
            for (int i = 0; i < nextList.size(); ++i) {
                String next = nextList.get(i);
                nextList.remove(next);
                if (dfs(map, next, result, len - 1) != null) {
                    return result;
                }
                nextList.add(i, next);
            }
        }
        result.remove(result.size() - 1);
        return null;
    }
}

// discuss
// 涉及到欧拉回路的问题：每次访问无向图中的每一条边有且仅有一次，也就是一笔画图
// 在无向图中，所有顶点的度数均为偶，则存在 Eularian cycle；若有且仅有两个顶点的度数为奇，其余的都为偶，则存在 Eularian path；
// 在有向图中，所有顶点的入度数等于出度数，则存在 Eularian cycle；若有且仅有两个顶点：其中2一个入度数比出度数大1，
// 另一个入度数比出度数小 1，其余的顶点入度数等于出度数，则存在Eularian path.

// 此题为无向图范围，题目已经保证存在欧拉回路，并且要求从JFK节点出发
// 从JSK节点出发，每次都dfs访问已经排序好的优先队列的第一个机场，访问完成以后删除队列中的值
// 1、如果按照dfs寻找路线并且没有发生回到曾经访问过的点（最好的情况），则按倒序插入路程路线列表，结束
// 2、如果回到了曾经访问过的点（访问路线出现环路）
//   2.1、这个点还存在其他没有访问过的路线，则继续访问下一个未访问过的路线，继续1、2步骤
//   2.2、如果这个点在map中已经没有下一个可访问的节点，则说明此节点为该路程的结束节点，插入路线列表，并且进行访问出栈操作（不算是回溯）
//        回到上一个出发的节点，继续1,2步骤
// 原理：当某一个节点存在其他路径可以选择时，其实该节点就是其他路径（该节点之前的路径）完成之后的入口节点，无非就是该节点之后的路径已经早就插入至
// 路线列表之中，之前的节点路径只要插入在列表最前既可
public List<String> findItinerary(String[][] tickets) {
// map用优先队列进行存储
    for (String[] ticket : tickets)
        targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
    visit("JFK");
    return route;
}

Map<String, PriorityQueue<String>> targets = new HashMap<>();
List<String> route = new LinkedList();

void visit(String airport) {
    while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
        visit(targets.get(airport).poll()); // 每次访问完成以后删除节点
    // 先插入的是行程的终点元素，后续节点当没有额外路径时，插入到上一个节点之前
    route.add(0, airport); 
}

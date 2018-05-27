/**
Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

My code://先完成1，然后在每行后面增加一个1，对第二个至倒数第二个重新赋值
class Solution {
    public List<List<Integer>> generate(int numRows) {
        if(numRows==0) return new ArrayList<>();
        if(numRows==1){//当num=1时返回1
            List<List<Integer>> result=new ArrayList<>();
            List<Integer> list=new ArrayList<>();
            list.add(1);
            result.add(list);
            return result;
        }
        List<List<Integer>> result=generate(numRows-1);
        List<Integer> list=new ArrayList<>(result.get(result.size()-1));
        list.add(1);//在末尾加1
        int pre=1;//先前的数
        //这里其实倒序赋值将不用其他标识，倒序1-2-1-1  1-2-3-1  1-3-3-1
        for(int i=1;i<list.size()-1;i++){
            int before=list.get(i);//标识没有重复赋值之前的数
            list.set(i,pre+list.get(i));
            pre=before;//赋给下一次循环的先前数
        }
        result.add(list);
        return result;
    }
}

Discuss://思想一样
（1）//正序
public class Solution {
public List<List<Integer>> generate(int numRows)
{
	List<List<Integer>> allrows = new ArrayList<List<Integer>>();
	ArrayList<Integer> row = new ArrayList<Integer>();
	for(int i=0;i<numRows;i++)
	{
		row.add(0, 1);//在起始位置添加1
		for(int j=1;j<row.size()-1;j++)
			row.set(j, row.get(j)+row.get(j+1));//当前值加上后续值
		allrows.add(new ArrayList<Integer>(row));
	}
	return allrows;
	
}
（2）//倒序，这个算法可以的~
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> row = new ArrayList();
        for(int i = 0; i < numRows; i++) {
            for(int j = row.size() - 1; j >= 1 ; j--) {//从最后一个开始赋值
                row.set(j, row.get(j) + row.get(j - 1));//当前值加上前一个值
            }
            row.add(1);//最后添加1
            res.add(new ArrayList(row));
        }
        return res;
    }

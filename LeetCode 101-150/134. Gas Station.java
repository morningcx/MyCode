/**
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.

Note:

If there exists a solution, it is guaranteed to be unique.
Both input arrays are non-empty and have the same length.
Each element in the input arrays is a non-negative integer.
Example 1:

Input: 
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

Output: 3

Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
Example 2:

Input: 
gas  = [2,3,4]
cost = [3,4,3]

Output: -1

Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.
*/

My code://1ms 71%
//求每一站剩余汽油总和，总和小于0说明没有结果，大于0返回第一个可以后续有持续汽油的车站
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum=0;
        int poSum=0;
        int poIndex=-1;
        for(int i=0;i<gas.length;i++){
            int left=gas[i]-cost[i];//求剩余汽油
            if(poIndex==-1&&left>=0)//若存在正数，则越过，若不存在正数并且当前值为正数
                poIndex=i;
            if(poIndex!=-1)//存在正数，求正数后续之和
                poSum+=left;
            if(poSum<0){//后续之和为0则说明不能当第一个车站
               poIndex=-1;
               poSum=0; 
            }
            sum+=left;//求和
        }
        return sum<0?-1:poIndex;
    }
}

Discuss：//思想一致
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int gasSum = 0;
        int costSum = 0;
        int tank = 0;
        int start = 0;
        for(int i=0; i<gas.length; i++) {
            gasSum += gas[i];
            costSum += cost[i];
            tank += (gas[i] - cost[i]);//正数后续之和
            if(tank < 0){//当为负时不论当前值为负还是后续之和为负，都将重置
                tank = 0;
                start=i+1;//下一个数作为下一个正数，如果下一个为负，则又重置，为正则求和继续
            }
        }
        if(gasSum < costSum) {
            return -1;
        }
        return start;   
    }
}

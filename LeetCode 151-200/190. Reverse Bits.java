/**
Reverse bits of a given 32 bits unsigned integer.

Example:

Input: 43261596
Output: 964176192
Explanation: 43261596 represented in binary as 00000010100101000001111010011100, 
             return 964176192 represented in binary as 00111001011110000010100101000000.
Follow up:
If this function is called many times, how would you optimize it?
*/

My code://4ms 57%
//n >>>= 1 表示无符号右移
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        int temp = 1;
        for (int i = 0; i < 31; i++) {
            result ^= (n & temp);//将result亦或n的最右位，其实|也可以，而且速度好像快一点
            n >>= 1;//这里应该是n>>>=1无符号右移，但是oj好像通过了
            result <<= 1;//左移
        }
        result ^= (n & temp);//最后一次不用左移
        return result;
    }
}

Discuss：
（1）//一样的思路，代码简单清晰
 public int reverseBits(int n) {
  int result = 0;
  for (int i = 0; i < 32; ++i) {
    result = result << 1 | (n & 1);//将result左移的操作放在运算中，这样就可以排除对最后一次左移操作
    n >>>= 1;
  }
  return result;  
}
（2）
//for 8 bit binary number abcdefgh, the process is as follow:
//abcdefgh -> efghabcd -> ghefcdab -> hgfedcba
class Solution {
public:
    uint32_t reverseBits(uint32_t n) {
        n = (n >> 16) | (n << 16);
        n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
        return n;
    }
};

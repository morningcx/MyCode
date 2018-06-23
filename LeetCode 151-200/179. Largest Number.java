/**
Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a string instead of an integer.
*/

My code://22ms 99%
//自定义判断大小，冒泡排序再添加
class Solution {
    public String largestNumber(int[] nums) {
        for(int i = 0; i < nums.length - 1; i++) {
            for(int j = 0; j < nums.length - i - 1; j++){
                if(!bigger(nums[j], nums[j + 1])){
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        if(nums[0] == 0) return "0";
        StringBuilder result=new StringBuilder();
        for(int i: nums){
            result.append(i);
        }
        return result.toString();
    }
    public boolean bigger(int first, int second){
        int len1 = getLen(first);
        int len2 = getLen(second);
        if(len1 == len2) return first > second;
        int max = Math.max(len1, len2);
        for(int i = 0; i < max; i++) {
            Integer temp1 = getNumAt(first, len1, len1 - i);
            Integer temp2 = getNumAt(second, len2, len2 - i);
            if(temp1 != null && temp2 != null && temp1 != temp2){
                return temp1 > temp2;
            }
            if(temp1 == null||temp2 == null) {
                String s1=first+""+second;
                String s2=second+""+first;
                return s1.compareTo(s2) > 0;
            } 
        }
        return false;
    }
    
    public Integer getNumAt(int num, int len, int n){
        if(n < 1) return null;
        while(n-- > 1) num /= 10;
        num %= 10;
        return num;
    }
    
    public int getLen(int num){
        int result = 1;
        while(num > 9) {
            num /= 10;
            result++;
        }
        return result;
    }
}

Discuss：
（1）//重写comparator接口，将两个字符串互相相加判断两数大小
public class Solution {
     public String largestNumber(int[] num) {
		if(num == null || num.length == 0)
		    return "";
		
		// Convert int array to String array, so we can sort later on
		String[] s_num = new String[num.length];
		for(int i = 0; i < num.length; i++)
		    s_num[i] = String.valueOf(num[i]);
			
		// Comparator to decide which string should come first in concatenation
		Comparator<String> comp = new Comparator<String>(){
		    @Override
		    public int compare(String str1, String str2){
		        String s1 = str1 + str2;
			String s2 = str2 + str1;
			return s2.compareTo(s1); // reverse order here, so we can do append() later
		    }
	        };
		
		Arrays.sort(s_num, comp);
                // An extreme edge case by lc, say you have only a bunch of 0 in your int array
                if(s_num[0].charAt(0) == '0')
                    return "0";
            
		StringBuilder sb = new StringBuilder();
		for(String s: s_num)
	            sb.append(s);
		
		return sb.toString();
		
	}
}
（2）//取余判断两数大小，归并排序
class Solution {
     public String largestNumber(int[] nums) {
        // all permutations O(N!)
        
        // mergesort with a custom comparation O(N*logN)
         mergeSort(nums, 0, nums.length);
         
        StringBuilder largestBuilder = new StringBuilder();
         for (int i = 0; i < nums.length; i++) {
        	 largestBuilder.append(String.valueOf(nums[i]));
		}
        
        String largest = largestBuilder.toString();
        
         if (largest.indexOf("0") == 0) {
             return "0";
         }
         
         return largest;
        
    }
    
    
    public void mergeSort(int[] nums, int start, int end) {
        
        if (end - start == 1) {
            return;
        }
        
        int middle = start + (end - start) / 2;

        mergeSort(nums, start, middle);
        mergeSort(nums, middle, end);

        merge(nums, start, middle, end);
        
    }
               
               
                   
    public void merge(int[] nums, int start, int middle, int end) {
    
		int left[] = Arrays.copyOfRange(nums, start, middle);
		int right[] = Arrays.copyOfRange(nums, middle, end);

		int iLeft = 0, iRight = 0;

		for (int i = start; i < end; i++) {

			boolean noElementsInRightOrBiggerElementInLeft = iRight == right.length
					|| (iLeft < left.length && isBigger(left[iLeft], right[iRight]));

			if (noElementsInRightOrBiggerElementInLeft) {
				nums[i] = left[iLeft++];
			} else {
				nums[i] = right[iRight++];
			}

		}
        
    }    
    
    public boolean isBigger(int a, int b) {
        
        if (a == b || allSameChars(a, b)) {
            return true;
        }
        
        char[] aDigits = String.valueOf(a).toCharArray();
        char[] bDigits = String.valueOf(b).toCharArray();
        
        int aLength = aDigits.length;
        int bLength = bDigits.length;
        
        int i = 0;        
        while (true) {//i做指针，循环取余
            //遇到121,121121会出现无限循环
            //for(int i = 0; i < Math.max(aLength, bLength); i++) 这样写会更好
            if(aDigits[i % aLength] > bDigits[i % bLength]) {
                return true;
            } else if(aDigits[i % aLength] < bDigits[i % bLength]) {
                return false;
            }
            
            i++;
        } 
        
      
    }
    
    public boolean allSameChars(int a, int b) {
        
        char[] aDigits = String.valueOf(a).toCharArray();
        char[] bDigits = String.valueOf(b).toCharArray();
        
        char firstDigit = aDigits[0];
        
        for (int i = 1; i < aDigits.length; i++) {
            if(aDigits[i] != firstDigit) {
                return false;
            }
        }
        
        for (int i = 0; i < bDigits.length; i++) {
            if(bDigits[i] != firstDigit) {
                return false;
            }
        }
        
        return true;
        
    }
    
    
}

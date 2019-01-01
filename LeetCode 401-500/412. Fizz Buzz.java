// discuss1 
// 一般来说，对于CPU取余数的运算相对来说效率很低，下面解法避免了使用大量的求余数操作，提升了程序的性能。
    public List<String> fizzBuzz(int n) {
        
        List<String> result = new ArrayList<>();
        
        if(n < 1) return result;
        
        for(int i = 1, fizz = 3, buzz = 5; i <= n; i++) {
        
            String addVal = null;
            
            if(i == fizz && i == buzz) {
                addVal = "FizzBuzz"; 
                fizz += 3;
                buzz += 5;
            } else if(i == fizz) {
                addVal = "Fizz";
                fizz += 3;
            } else if(i == buzz) {
                addVal ="Buzz";
                buzz += 5;
            } else
                addVal = String.valueOf(i);
            
            result.add(addVal); 
        }
        
        return result;
    }
    
    // discuss 2
    // 可拓展的算法
        public static List<String> fizzBuzz(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        // 这里要用TreeMap的保持取余成功以后的添加字符串的顺序
        TreeMap<Integer, String> map = new TreeMap<>((a, b) -> a - b);
        map.put(3, "Fizz");
        map.put(5, "Buzz");
        map.put(7, "Yuzz"); // 7和9都是拓展的
        map.put(9, "Mozz"); // 其实9也可以被3整除
        // 这里拓展其实也要保证输入的正确性，如果再次添加15，则会在FizzBuzz后再添加一次FizzBuzz
        List<String> numsStrs = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            StringBuilder encoded = new StringBuilder();
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if (i % entry.getKey() == 0)
                    encoded.append(entry.getValue());
            }
            numsStrs.add((encoded.length() == 0) ? String.valueOf(i) : encoded.toString());
        }
        return numsStrs;
    }

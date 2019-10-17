package Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String s = new String();
        int max = 0;
        Character c = new Character('a');
        Map<Character,Integer> map = new HashMap();
        Scanner sc = new Scanner(System.in);
        s = sc.next();
        char[] chars = s.toCharArray();
        if(chars.length>0)
            for (char aChar : chars) {
                Integer integer = map.get(aChar);
                if(integer!=null){
                    map.put(aChar,map.get(aChar)+1);
                }else{
                    map.put(aChar,1);
                }
            }
        Set<Map.Entry<Character, Integer>> entries = map.entrySet();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < entries.size(); i++) {
            for (Map.Entry<Character, Integer> entry : entries) {
                if(entry.getValue()>max){
                    c = entry.getKey();
                    max = entry.getValue();
                }
            }
            for (int j = 0; j < max; j++) {
                sb.append(c);
            }
            map.put(c,-1);
            max = 0;
        }
        System.out.println(sb);
    }
}

package Util;

import Demo.AbstractSort;

import java.util.Arrays;
import java.util.Random;

public class DataChecker {
    /**
     * 随机生成存有10000数据的数组
     * @return
     */
    private static int[] generatorRandomArray(){
        Random r = new Random();
        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(10000);
        }
        return arr;
    }

    /**
     * 检查排序是否正确
     * @return
     */
    public static boolean check(AbstractSort mySort){
        int[] arr = generatorRandomArray();
        int[] arr2 = new int[10000];
        System.arraycopy(arr,0,arr2,0,arr.length);

        Arrays.sort(arr);//arr专业排序
        mySort.run(arr2);//arr2自我排序

        //校验：查看两个排序过后元素是否完全一致
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != arr2[i])
                return false;
        }
        return true;
    }
}

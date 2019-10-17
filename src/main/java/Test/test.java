package Test;

import Demo.RadixSort;
import Util.DataChecker;

/**
 * 测试排序算法是否正确
 */
public class test {
    public static void main(String[] args) {
        //DataChecker.check传入排序类，会测试run方法，来评测排序是否正确
        //System.out.println(DataChecker.check(new SelectionSort()));
        //System.out.println(DataChecker.check(new BubbleSort()));
        //System.out.println(DataChecker.check(new InsertionSort()));
        //System.out.println(DataChecker.check(new ShellSort()));
        //System.out.println(DataChecker.check(new MergeSort()));
        //System.out.println(DataChecker.check(new QuickSort()));
        //System.out.println(DataChecker.check(new CountingSort()));
        System.out.println(DataChecker.check(new RadixSort()));
    }
}

package Test;

import java.util.Arrays;

public class test5 {
    public static void main(String[] args) {
        //int[] arr = {5,1,74,8,6,4185,7,4,3,4,0,9,45,2};
        int[] arr = {5,7,1,9,3,4,0,2,6,8,14};
        //选择排序
        //selectSort(arr);
        //冒泡排序
        //bubbleSort(arr);
        //插入排序
        //insertSort(arr);
        //希尔排序
        //shellSort(arr);
        //归并排序
        mergesort(arr,0,arr.length-1);


        System.out.println(Arrays.toString(arr));
    }

    private static void shellSort(int[] arr) {
        int len = arr.length;
        int temp;
        int h = 1;
        while (h<len/3){
            h = 3*h + 1;
        }
        for (int gap = h; gap > 0; gap=(gap-1)/3) {
            for (int i = gap; i < len; i++) {
                int current = arr[i];
                int p = i-gap;
                while(p>=0 && current<arr[p]){
                    arr[p+gap] = arr[p];
                    p-=gap;
                }
                arr[p+gap] = current;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void insertSort(int[] arr) {
        int len = arr.length;
        int temp;
        for (int i = 1; i < len; i++) {
            int current = arr[i];
            int p = i-1;
            while(p>=0 && current<arr[p]){
                arr[p+1] = arr[p];
                p--;
            }
            arr[p+1] = current;
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void bubbleSort(int[] arr) {
        int len = arr.length;
        int temp;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if(arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void selectSort(int[] arr) {
        int len = arr.length;

        int temp;
        for (int i = 0; i < len - 1; i++) {
            int minPos = i;
            for (int j = i+1; j < len; j++) {
                minPos = arr[minPos] > arr[j] ? j : minPos;
            }
            temp = arr[i];
            arr[i] = arr[minPos];
            arr[minPos] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    //归并排序
    public static void mergesort(int[] arr,int start,int end) {
        if(start<end){
            int mid = (start+end)/2;
            //归并
            mergesort(arr,start,mid);//这里不能换成start mid-1 和 mid end 会出现StackOverflowError异常(死循环)，因为mid可能为0，那么就会传入-1，失败跳出后执行mid为0 ，又继续了之前的循环。
            mergesort(arr,mid+1,end);
            //排序
            merge(arr,start,mid,end);
        }
    }
/*
死循环情况
start mid end
0      2    4
0      0    1
0          -1 结束 然后执行下边的mid为0 作为参数start
0      0    1
0          -1 递归里的上边的结束，递归里的下边的
0      0    1
0          -1 递归里的下边的结束，开始递归里的递归里的上边的 mid为0 作为参数start
 */

    public static void merge(int[] arr,int left,int mid,int right) {
        int len = arr.length;
        int[] temp = new int[len];
        int k = left;
        int p1 = left,p2 = mid + 1;//不能为left mid-1 和 mid right 因为0 0 1 时 mid-1为-1 那么whil循环进不去，这两个数就无法排序
        while (p1<=mid && p2<=right){
            temp[k++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1<=mid) temp[k++] = arr[p1++];
        while (p2<=right) temp[k++] = arr[p2++];

        for (int i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }
}

package Demo;

import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort implements AbstractSort {
    public void run(int[] arr) {
        int digit = 0;
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = arr[i] > max ? arr[i] : max;
        }
        int[] buck = new int[max+1];
        int[] result = new int[arr.length];

        while(max!=0){
            max = max/10;
            digit++;//最大位数
        }
        for (int i = 0; i < digit; i++) {
            //将第i+1位数放桶里
            int division = (int) Math.pow(10,i);
            for (int j = 0; j < arr.length; j++) {
                int num = arr[j]/division%10;
                buck[num]++;
            }
            //累加桶
            for (int j = 1; j < buck.length; j++) {
                buck[j] = buck[j] + buck[j-1];
            }

            for (int k = arr.length-1; k >=0; k--) {
                int num = arr[k]/division%10;
                result[--buck[num]] = arr[k];
            }
            System.arraycopy(result,0,arr,0,arr.length);
            Arrays.fill(buck,0);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = result[i];
        }
    }
    /*public static void main(String[] args) {
        int[] arr = {5,4,1,56,8,7,10,3,45,546,40,5,4,50,45,15,524,798};
        int digit = 0;
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = arr[i] > max ? arr[i] : max;
        }
        int[] buck = new int[max+1];
        int[] result = new int[arr.length];

        while(max!=0){
            max = max/10;
            digit++;//最大位数
        }
        for (int i = 0; i < digit; i++) {
            //将第i+1位数放桶里
            int division = (int) Math.pow(10,i);
            for (int j = 0; j < arr.length; j++) {
                int num = arr[j]/division%10;
                buck[num]++;
            }
            //累加桶
            for (int j = 1; j < buck.length; j++) {
                buck[j] = buck[j] + buck[j-1];
            }

            for (int k = arr.length-1; k >=0; k--) {
                int num = arr[k]/division%10;
                result[--buck[num]] = arr[k];
            }
            System.arraycopy(result,0,arr,0,arr.length);
            Arrays.fill(buck,0);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = result[i];
        }
        System.out.println(Arrays.toString(arr));
    }*/
}

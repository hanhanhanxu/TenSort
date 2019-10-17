package Demo;

/**
 * 插入排序，比冒泡和选择都要快一倍
 *
 * 将前面的元素看作基本有序，后面新来的一个元素插入里面
 * 最好情况下时间复杂度O(n)，外层循环一边，内层循环只比较，不进去
 */
public class InsertionSort implements AbstractSort {
    public void run(int[] arr) {
        int len = arr.length;

        //外层循环控制插入元素，从index为1到最后   index选为1的原因是将index=0看作已排好序的数组
        for (int i = 1; i < len; i++) {
            int current = arr[i];//先把当前元素记录下来，因为当前位置会被其他元素占用
            int pre = i-1;
            while(pre>=0 && current<arr[pre]){//内层while循环负责找到当前元素current合适的插入位置p+1
                arr[pre+1] = arr[pre];//将前面的元素后移
                pre--;//指针前移
            }
            arr[pre+1] = current;
        }
    }

    /*public static void main(String[] args) {
        int[] arr = {5,7,1,3,2,4,6,9,8};
        int len = arr.length;

        for (int i = 1; i < len; i++) {
            int current = arr[i];//先把当前元素记录下来，因为当前位置会被其他元素占用
            int pre = i-1;
            while(pre>=0 && current<arr[pre]){
                arr[pre+1] = arr[pre];//将前面的元素后移
                pre--;//指针前移
            }
            arr[pre+1] = current;
        }

        for (int i = 0; i < len; i++) {
            System.out.print(arr[i]+" ");
        }
    }*/
}

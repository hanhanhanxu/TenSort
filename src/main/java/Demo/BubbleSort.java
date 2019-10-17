package Demo;

/**
 * 冒泡排序
 * 最好情况下时间复杂度为O(n)
 * 改进：加一个标识sign = false，只要发生交换就置为true，若一轮循环下来，sign仍为false，那么就说明已经有序，立即retuen
 */
public class BubbleSort implements AbstractSort {

    public void run(int[] arr){
        int len = arr.length;
        int temp;
        boolean sign;//加标记，使时间复杂度能够达到O(n)[数组本来就有序情况下]

        for (int i = 0; i < len - 1; i++) {//外层循环控制循环次数，循环n-1次就能排好序
            sign = false;
            for (int j = 0; j < len - 1 - i; j++) {//内层循环找到最大值，负责将最大值“冒”出来
                //两个中选择较大者，放后面
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;

                    sign = true;
                }
            }
            if(!sign)
                return;
        }
    }

    /*public static void main(String[] args) {
        int[] arr = {5,7,1,3,2,4,6,9,8};
        int len = arr.length;
        int temp;

        for (int i = 0; i < len - 1; i++) {
            //由于是每次选择两个数比较，基准值是从0开始，所以当j=len-1时，选择的数是第len-1 和 第len个比较，i是已排序的，不用比较
            for (int j = 0; j < len - 1 - i; j++) {
                //两个中选择较大者，放后面
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }
    }*/
}

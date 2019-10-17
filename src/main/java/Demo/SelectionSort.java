package Demo;

/**
 * 各个排序类实现run方法，对传入数组arr排序
 * 选择排序
 */
public class SelectionSort implements AbstractSort {

    public void run(int[] arr){
        int len = arr.length;
        int temp;
        int minPos;

        //可以看作是反过来的冒泡：冒泡每次冒出最大值放到最后，选择每次选出最小值放到最前 【当然这些都是可以控制到底是选最大还是最小值】

        //外层循环控制遍历几遍，由于核心代码中每执行一个循环才能找到一个最值，所以需要遍历n-1遍。且还能将i索引处的值排序
        for (int i = 0; i < len - 1; i++) {//遍历至倒数第二个数就行了
            minPos = i;//假设 最小值的位置 i
            for (int j = i + 1; j < len; j++) {//内层循环找出最小值的下标
                minPos = arr[j] < arr[minPos] ? j : minPos;//重新选择最小值的位置
            }

            //将真实最小值和假设最小值交换
            temp = arr[i];
            arr[i] = arr[minPos];
            arr[minPos] = temp;
        }
    }

    /*public static void main(String[] args) {
        int[] arr = {5,7,1,3,2,4,6,9,8};
        int len = arr.length;
        int temp;
        int minPos;

        for (int i = 0; i < len - 1; i++) {//遍历至倒数第二个数就行了
            minPos = i;//假设 最小值的位置 i
            for (int j = i + 1; j < len; j++) {
                minPos = arr[j] < arr[minPos] ? j : minPos;//重新选择最小值的位置
            }

            //将真实最小值和假设最小值交换
            temp = arr[i];
            arr[i] = arr[minPos];
            arr[minPos] = temp;
        }

        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }
    }*/
}

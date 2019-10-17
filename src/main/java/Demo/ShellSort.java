package Demo;

public class ShellSort implements AbstractSort {
    public void run(int[] arr) {
        int len = arr.length;
        //效率更高的gap挑选方法
        int h = 1;
        while(h < len/3){
            h = 3*h + 1;
        }

        //第一个for循环是控制gap，间隔从某个数，一直变到1
        for (int gap = h; gap > 0; gap = (gap-1)/3) {
            //第二个for循环就是插入排序，将插入排序的间隔 1  改为了gap
            for (int i = gap; i < len ; i++) {//这里是i++的原因是将分组执行变为了多个分组交替执行（大分组执行拆分成了小分组执行）
                int current = arr[i];
                int j = i-gap;
                while(j>=0 && current<arr[j]){
                    arr[j+gap] = arr[j];
                    j-=gap;
                }
                arr[j+gap] = current;
            }
        }
    }

    /*public static void main(String[] args) {
        int[] arr = {5,7,1,3,2,4,6,9,8};
        int len = arr.length;

        for (int gap = 4; gap > 0; gap/=2) {
            for (int i = gap; i < len ; i++) {
                int current = arr[i];
                int j = i-gap;
                while(j>=0 && current<arr[j]){
                    arr[j+gap] = arr[j];
                    j-=gap;
                }
                arr[j+gap] = current;
            }
        }

        for (int i : arr) {
            System.out.print(i+" ");
        }
    }*/
}

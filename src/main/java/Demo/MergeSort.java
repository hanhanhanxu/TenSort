package Demo;

/**
 * 归并排序
 */
public class MergeSort implements AbstractSort {
    public void run(int[] arr) {
        int len = arr.length;
        sort(arr,0,len-1);
    }

    public void sort(int[] arr,int start,int end) {
        if(start<end){
            int mid = (start+end)/2;
            //归并
            //这里不能换成start mid-1 和 mid end 会出现StackOverflowError异常(死循环)，因为mid可能为0，那么就会传入-1，失败跳出后执行mid为0 ，又继续了之前的循环。
            sort(arr,start,mid);
            sort(arr,mid+1,end);
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

    public void merge(int[] arr,int left,int right,int end) {
        int len = arr.length;
        int[] temp = new int[len];
        int k = left;
        //这里不能换成left mid-1 和 mid right 因为0 0 1 时 mid-1为-1 那么whil循环进不去，这两个数就无法排序
        int p1 = left;
        int p2 = right+1;
        while(p1<=right && p2<=end){
            temp[k++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        //只会执行一个while循环
        while(p1<=right) temp[k++] = arr[p1++];
        while (p2<=end) temp[k++] = arr[p2++];

        for (int i = left; i <= end; i++) {
            arr[i] = temp[i];
        }
    }
}

package Demo;

public class QuickSort implements AbstractSort {
    @Override
    public void run(int[] arr) {
        int len = arr.length;
        sort(arr,0,len-1);
    }
    /*public static void main(String[] args) {
        int[] arr = {5,7,1,3,2,4,6,9,8};
        int len = arr.length;
        sort(arr,0,len-1);
        System.out.println(Arrays.toString(arr));
    }*/
    //自研最新改良版本
    public static void sort(int[] arr,int left,int right){
        int i=left,j=right;
        int current = arr[left];
        boolean sign = true;
        while(left<right){
            if(true == sign){
                //指针移动的过程
                while (left<right && arr[right]>=current){
                    right--;
                }
                //数据交换的过程
                if(left<right){
                    sign = false;
                    arr[left] = arr[right];
                    continue;
                }
            }else{
                while (left<right && arr[left]<current){
                    left++;
                }
                if(left<right){
                    sign = true;
                    arr[right] = arr[left];
                }
            }
        }
        arr[left] = current;//将基准值回归到中间位置
        //此时left == right == 基准值  , 基准值已经不用再排序，所以只需要排 i到left-1  和 right+1到j
        if(left-1>i)//至少要有两个数才能排（left-1是“一个”索引值） 1 2 3 4 5 基准值是3 left-1是2的索引
            sort(arr,i,left-1);
        if(right+1<j)
            sort(arr,right+1,j);
    }

    private static void hqsort(int arr[],int start,int end){
        int i = start;
        int j = end;
        int markNum = arr[start];
        int p = arr[end];
        int sign = 0;

        while (i<j){
            if(sign%2==0){
                while(i<j && p>=markNum){
                    j--;
                    p = arr[j];
                }
                if(i<j){
                    //交换两数
                    arr[i]=p;//p赋值给arr[i]

                    //p移动过去 p已经“移动”过去了,p现在已经是那个“较小”的值了
                }
            }else{
                while(i<j && p<markNum){
                    i++;
                    p = arr[i];//★
                }
                if(i<j){
                    arr[j] = p;//★
                }
            }
            sign++;
        }
        //循环结束后，将基准值填入。
        arr[i] = markNum;//i = j

        if(i-1>start)
            hqsort(arr,start,i-1);
        if(j+1<end)
            hqsort(arr,j+1,end);

    }




}

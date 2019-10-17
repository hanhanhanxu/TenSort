package Demo;

/**
 * 计数排序
 * 适用于已知元素范围，且最大值不是特别大的情况
 * 如果包含小数的话，可以将小数*Math.pow(10,n)，变为整数，排序后再变为小数。
 */
public class CountingSort implements AbstractSort {
    @Override
    public void run(int[] arr) {
        //寻找最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = arr[i] > max ? arr[i] : max;
        }
        //定义桶长度
        int[] buck = new int[max+1];
        //遍历待排序数组，把元素出现的次数用桶记录下来，桶下标代表原数组元素，桶值代表出现次数。
        for (int i = 0; i < arr.length; i++) {
            buck[arr[i]] += 1;
        }

        //累加桶  这样桶中的元素-1就是该索引代表的数在原数组中的位置
        for (int i = 1; i < buck.length; i++) {
            buck[i] = buck[i] + buck[i-1];
        }

        //遍历桶，对元素排序
        int[] result = new int[arr.length];
        for (int i = arr.length-1; i >= 0; i--) {//倒序遍历是为了不打乱相同元素的相对位置【稳定性】，在后面的还是在后面。
            //通过待排序元素arr[i]作为索引值在桶中找到该元素实际的索引值--buck[arr[i]]，然后将该元素赋值上去。
            result[--buck[arr[i]]] = arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = result[i];
        }
    }

    /*public static void main(String[] args) {
        int[] arr = {5,8,1,4,6,9,0,2,4,5,6,7,2,8,78,54,62,74,96,65,35,41,9,6,78,3,4};
        //寻找最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = arr[i] > max ? arr[i] : max;
        }
        //定义桶长度
        int[] buck = new int[max+1];
        //遍历待排序数组
        for (int i = 0; i < arr.length; i++) {
            buck[arr[i]] += 1;
        }
        //遍历桶，对元素排序
        int j=0;
        for (int i = 0; i < buck.length; i++) {
            while(buck[i]>0){
                arr[j++] = i;
                buck[i]-=1;
            }
        }
        System.out.println(Arrays.toString(arr));
    }*/
}

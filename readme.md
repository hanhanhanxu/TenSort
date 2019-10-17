编写算法思路：由简单到复杂，先局部后整体，先粗糙后精细。



本文所有排序均有DataChecker验证。【验证的是普遍情况，多元素情况，不验证数组长度位0等少数情况】

验证思路：编写一个DataChecker类，编写check方法，接受一个AbstractSort类型的参数。check方法内部自动生成随机数组，并拷贝一份。调用AbstractSort的run方法将输入传入一个数组，然后再调用Arrays.sort方法传入另一个数组。最后一一比对两个数组是否完全一致，即可得知自定义排序算法是否正确排序。

AbstractSort为一个接口，规定void run(int[] arr);方法，所以自定义排序类需继承此接口并实现此方法。

最后调用`System.out.println(DataChecker.check(new RadixSort()));`，输出true，即证明你的算法正确。



### 1、选择排序

最**简单** 最**没用**的排序算法 （时间复杂度：O(n平方)，空间复杂度：1，**不稳定**）

思想：一遍一遍的找一个“最小值”，让他放在前面去。

优化：每次遍历除了找到最小值外，还再找一个最大值。最小值放前面， 最大值放后面。

最好情况也是O(n平方)，也要进行许多次循环

**基本不用，不稳。**（2 3 2 1，第一次循环，将第一个2和1交换位置，那么两个2在排序前后的相对位置变化了）

代码：

```java
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
```



## 

### 2、冒泡排序

时间复杂度O(n平方)，最好时间复杂度O(n)，空间复杂度1，稳定

优化：加一个标识sign=false，当交换时，置为true，若一轮循环下来，sign仍为false，则return

**基本不用，太慢。**（两两比较，两两交换）

代码：

```java
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
        boolean sign;

        for (int i = 0; i < len - 1; i++) {
            sign = false;
            //由于是每次选择两个数比较，基准值是从0开始，所以当j=len-1时，选择的数是第len-1 和 第len个比较，i是已排序的，不用比较
            for (int j = 0; j < len - 1 - i; j++) {
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
```



### 3、快速排序

思想：选择一个基准值，然后经过一轮循环将比此基准值大的值都放到基准值右边，将比此基准值小的值都放到左边（此时基准值已经在正确位置上了）。然后递归循环基准值左侧和右侧，直至不能循环（left==right），整个数组即为有序。

时间复杂度：O(nlogn)，空间复杂度：O(logn)，**不稳定**

不要求稳定性的情况，可以用快排。

代码：

```java
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
}
```



## 

> 插入排序稳定，希尔排序不稳定

### 4、插入排序

思想：将前面部分元素做为已排序元素，新来的元素插入到已排序元素中，从已排序元素中一个个比较，当找到合适位置时直接放入。由于内层循环没有全部遍历或比较，所以比冒泡和选择都要快一倍

时间复杂度：O(n平方)，最好时间复杂度：O(n)，空间复杂度1，**稳定**

这里是已经优化过的，没有优化的是交换而不是使用临时遍历current储存

适用于**样本小且基本有序**的数组。

代码：

```java
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

        for (int i = 1; i < len; i++) {
            int current = arr[i];//先把当前元素记录下来，因为当前位置会被其他元素占用
            int pre = i-1;
            while(pre>=0 && current<arr[pre]){
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
```



### 5、希尔排序

> 改进的插入排序，新增了间隔：gap。

优点：在间隔比较大的时候，挪动的次数比较小；在间隔比较小的时候，挪动的距离又比较短。

由于是跳着排，所以不稳定，所以不太重要。



思想：原先的插入排序可看作是将间隔为1的元素互相比较，希尔排序增加一个间隔gap，先排序第

0, 0+gap, 0+gap+gap ... 这些数，然后将间隔按规律缩小，继续排序，最后缩小gap至1，再进行排序。



时间复杂度第一个突破O(n平方)的排序算法，最好情况大致为O(n1.3次方)，空间复杂度1，**不稳定**（原因：有间隔，跳着排）。

代码：

```java
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
```



### 6、归并排序

思想：分治法，让已有序的子序列合并，得到完全有序的序列。【2路归并】，还有多路归并。

时间复杂度：O(nlogn)，空间复杂度O(n)，【申请了临时的数组】,稳定。

**归并排序和选择排序的性能都不受输入数据的影响，但是比选择排序快很多，始终都是O(nlogn)的时间复杂度，但是需要额外的储存空间。**

[![KkxZqJ.jpg](https://s2.ax1x.com/2019/10/17/KkxZqJ.jpg)](https://imgchr.com/i/KkxZqJ)

代码：

```java
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
        //这里不能换成left right-1 和 right end 因为0 0 1 时 mid-1为-1 那么whil循环进不去，0 1索引这两个数就无法排序
        int p1 = left;
        int p2 = right+1;
        while(p1<=right && p2<=end){
            temp[k++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        //只会执行一个while循环
        while(p1<=right) temp[k++] = arr[p1++];
        while (p2<=end) temp[k++] = arr[p2++];

        //将已排序元素赋值给arr
        for (int i = left; i <= end; i++) {
            arr[i] = temp[i];
        }
    }
}
```





### 7、计数排序

#### 第一代

使用情况：数组元素可预算最大值（或元素范围可知）且最大值不是很大（1000以下），全部整数。

思想：创建一个新数组，容量是待排序数组的最大值+1，并初始化为0。遍历待排序数组，将待排序中的元素作为索引值，将新数组中此索引值的元素+1；然后遍历新数组，当元素不为0时，循环将元素-1，并将此下标赋值给待排序数组（待排序数组从0开始：i=0; arr[i++] = temp[j]）。

时间复杂度：O(n+k)，两次遍历，一次待排序数组，一次新数组。（应该是2n吧？遍历新数组是for内还有while循环的，相当于又 遍历了n次）

空间复杂度：O(k)  。（申请了桶空间）

【输入的元素是 n 个 0到 k 之间的整数】

不稳定。其排序速度快于任何比较排序算法。

代码：

```java
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
        //遍历桶，对元素排序
        int j=0;
        for (int i = 0; i < buck.length; i++) {
            while(buck[i]>0){
                arr[j++] = i;
                buck[i]-=1;
            }
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
```

上述排序只适用于数字排序，且不稳定，因为是直接把数组下标当作了元素，桶里面是几，就直接拿出来几个索引作为元素。无法用于对象排序。

#### 第二代

累加桶：将桶中元素从1位置开始，累加为buck[i]+buck[i-1]，这样桶中元素-1就代表了某元素的实际位置。

倒序遍历原数组，将原数组元素作为索引值在桶中找到值后，这个值-1就是该元素应该出现的实际位置，直接赋值到该位置。且由于是倒序遍历，不会改变相同元素原有的相对顺序。

时间复杂度：O(n+k)，原数组过滤两边，桶过滤两边，2(n+k)，忽略常数项。

空间复杂度：O(n+k)，用了额外的数组：桶，用了额外的数组：result。

稳定。

代码：

```java
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

        //新增★：累加桶  这样桶中的元素-1就是该索引代表的数在原数组中的位置
        for (int i = 1; i < buck.length; i++) {
            buck[i] = buck[i] + buck[i-1];
        }

        //改进★：遍历桶，对元素排序
        int[] result = new int[arr.length];
        for (int i = arr.length-1; i >= 0; i--) {//倒序遍历是为了不打乱相同元素的相对位置【稳定性】，在后面的还是在后面。
            //通过待排序元素arr[i]作为索引值在桶中找到该元素实际的索引值--buck[arr[i]]，然后将该元素赋值上去。
            result[--buck[arr[i]]] = arr[i];
        }
        //新增★：将已排序数组赋值给原数组
        for (int i = 0; i < arr.length; i++) {
            arr[i] = result[i];
        }
    }
}
```



### 8、基数排序

> 多关键字排序，分治思想

思想：先将个位数用计数排序，再将十位数用计数排序，再将百位数用计数排序....

LSD  MSD 低位优先，高位优先。

时间复杂度：O(n*k)，空间复杂度：O(n+k)，稳定。

代码：

```java
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
```



## 9、桶排序

未完待续...



### 10、堆排序

未完待续...


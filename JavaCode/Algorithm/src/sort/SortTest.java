package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 
 * 内部排序一般可分为八中，这八种排序又可以分为五种类型
 * 
 * 一：插入排序
 * 	1、直接插入排序
 * 		将一个值插入到已经排序好的有序表中，从而得到一个新的记录数增1的表。即：先将序列的第一个记录看做一个有序的子序列，
 * 		然后从第二个记录逐个进行插入，直到整个序列有序为止。
 * 	2、希尔排序
 * 
 * 
 * 二：选择排序
 * 	3、简单选择排序
 * 
 * 	4、堆排序
 * 
 * 三：交换排序
 *  5、冒泡排序
 *  
 *  6、快速排序
 * 
 * 四：归并排序
 *  7、归并排序
 * 
 * 五：基数排序
 *  8、基数排序
 * 
 * 
 * @author liyang
 *
 */
public class SortTest {
	
	
	
	private static final int[] TEST_DATA_1 = { // 共60个测试数据
			21, 23, 26, 29, 32, 35, 38, 41, 44, 47, 50, 53, 56, 59, 11, 14, 17, 61, 64, 69,
			20, 24, 27, 30, 33, 36, 39, 42, 45, 48, 51, 54, 57, 60, 12, 15, 18, 62, 65, 68,
			22, 25, 28, 31, 34, 37, 40, 43, 46, 49, 52, 55, 58, 10, 13, 16, 19, 63, 66, 67
	};
	
	
	private static final int[] TEST_DATA_2 = {
			49, 38, 65, 97, 76, 13, 27, 49
	};
	
	
	
	/**
	 * 所有测试数据结果都是从小到大排序的
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = SimpleInsertSort.sort(TEST_DATA_1);
		System.out.println(Arrays.toString(a));
	}
	
}

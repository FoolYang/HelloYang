package reflect;

// 媳妇代码学校之路

//a++:先使用a，然后做加1操作
	// ++a：先做+1操作，然后使用a

public class ShaaTest {
	public static void main(String[] args) {
		int[] a = {1, 2, 2, 2, 3};
		int n = 2;
//		System.out.println(getTimes(a, n));
		
		int[] b = {5, 4, 3, 2, 1};
		 printA(b);
		 System.out.println("---------");
		bubbleSort(b);
	}
	
	// 1、一个排序数组中给定数出现的次数，给出测试用例
	public static int getTimes(int[] a, int n) {
		int times = 0;
		for (int i = 0; i < a.length; i++) {
			if (n == a[i]) {
				times++;
			}
		}
		return times;
	}
	
	// 2、冒泡排序
	 public static int[] bubbleSort(int[] a) {
		 for(int i = 0; i < a.length - 1; i++) {
			 for (int j= 0; j < a.length - i - 1; j++) {
				 if (a[j] > a[j+1]) {
					 int temp = a[j];
					 a[j] = a[j+1];
					 a[j+1] = temp;
				 }
			 }
			 printA(a);
			 System.out.println(" ");
		 }
		 System.out.println(" --------");
		 printA(a);
		 return a;
	 }
	
	 private static void printA(int[] a) {
		 for (int i = 0; i < a.length; i++) {
			 System.out.print(a[i] + " ");
		 }
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

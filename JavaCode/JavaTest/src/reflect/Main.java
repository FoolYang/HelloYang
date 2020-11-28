
package reflect;






import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		String[] str = line.split(" ");
		int n = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);
		
		int[][] arr =new int[n][2];
		for (int i = 0; i< n; i++) {
			String line1 = sc.nextLine();
			String[] str1 = line1.split(" ");
			arr[i][0] = Integer.parseInt(str1[0]);
			arr[i][1] = Integer.parseInt(str1[1]);
		}
		
		int score = 1;
		
		if (n>0 && n<4) {
			score = 1;
		} else if (n>=4 && n < 10 ) {
			score = 2;
		} else {
			score = 3;
		}
		
		System.out.println(score);
	}
	
}
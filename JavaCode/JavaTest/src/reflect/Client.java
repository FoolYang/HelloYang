package reflect;

public class Client {

	public static void main(String[] args) {
		
		
		int conut = 0;
		int x = 99999;
		while(x > 0) {
			conut ++;
			x = x&(x-1);
			System.out.println(" x:" + x);
		}
		
		System.out.println(" count:" + conut);

	}

}

package singleton_5;
/*
 * 单例模式
 * 
 * 双重检查加锁，在getInstance中减少使用同步
 * 
 * 此方法只会有一次同步，省性能
 */
public class Singleton3 {
	private static Singleton3 mInstance;
	private Singleton3(){}
	
	public static Singleton3 getInstance() {
		if (mInstance == null) {
			synchronized (Singleton3.class) {
				if (mInstance == null) {
					mInstance = new Singleton3();
				}
				
			}
		}
		return mInstance;
	}
	
}

package singleton_5;
/*
 * 单例模式
 * 
 * 同步会消耗性能，而且此方法每一次执行都会同步
 */
public class Singleton {
	private static Singleton mInstance;
	private Singleton(){}
	
	public synchronized Singleton getInstance() {
		if (mInstance == null) {
			mInstance = new Singleton();
		}
		return mInstance;
	}
}

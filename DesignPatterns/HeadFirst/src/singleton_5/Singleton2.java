package singleton_5;
/*
 * 单例模式
 * 
 * 在静态初始化器中创建单件，保证了线程安全
 * 
 * 如果有两个以上的类加载器，不同的类加载器可能会加载同一个类，从整个程序来看，同一个类会被加载多次。
 * 解决办法：自行指定类加载器，并指定同一个类加载器。
 */
public class Singleton2 {
	private static Singleton2 mInstance = new Singleton2();
	private Singleton2(){}
	
	public static Singleton2 getInstance() {
		return mInstance;
	}
	
}

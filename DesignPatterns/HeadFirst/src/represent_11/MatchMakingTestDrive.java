package represent_11;

import java.lang.reflect.Proxy;

public class MatchMakingTestDrive {
	public static void main(String[] args) {
		MatchMakingTestDrive test = new MatchMakingTestDrive();
		test.drive();
	}

	private  void drive() {
		PersonBean liyang = new PersonBeanImpl("liyang", "male", 24);
		System.out.println("-age:" + liyang.getAge());
		PersonBean liyangProxy = getPersonProxy(liyang);
		liyangProxy.setAge(25);
		System.out.println("=age:" + liyangProxy.getAge());
	}
	
	private  PersonBean getPersonProxy(PersonBean person) {
		return (PersonBean) Proxy.newProxyInstance(person.getClass().getClassLoader(),
				person.getClass().getInterfaces(), 
				new PersonInvocationHandler(person));
	}
}

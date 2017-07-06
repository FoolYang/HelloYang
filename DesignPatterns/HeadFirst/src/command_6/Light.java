package command_6;
/**
 * 电灯对象 命令的执行者或者接受者
 * @author liyang
 *
 */
public class Light {
	String sign;
	public Light() {
		
	}
	
	public Light(String sign) {
		this.sign = sign;
	}
	
	public void on() {
		System.out.println(sign+" light on ...");
	}
	
	public void off() {
		System.out.println(sign + " light off ...");
	}
}

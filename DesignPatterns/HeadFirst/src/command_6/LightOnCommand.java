package command_6;

/**
 * 打开灯的命令对象
 * @author liyang
 *
 */
public class LightOnCommand implements Command{
	Light light;
	
	public LightOnCommand(Light light) {
		this.light = light;
	}
	
	@Override
	public void execute() {
		if (light != null) {
			light.on();
		}
	}

}

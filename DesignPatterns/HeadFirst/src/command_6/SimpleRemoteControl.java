package command_6;

/**
 * 命令发射器 即  遥控器
 * @author liyang
 *
 */
public class SimpleRemoteControl {
	Command slot;
	
	public void setCommand(Command command) {
		slot = command;
	}
	
	public void buttonWasPressed() {
		if (slot != null) {
			slot.execute();
		}
	}

}

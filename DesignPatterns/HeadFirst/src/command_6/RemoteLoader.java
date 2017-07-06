package command_6;

public class RemoteLoader {
	public static void main(String[] args) {
		RemoteControl remoteComtrol = new RemoteControl();
		
		Light livingRoomLight = new Light("Living room");
		Light KichenLight = new Light("kichen");
		
		LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
		LightOffCommand livingRoomOff = new LightOffCommand(livingRoomLight);
		LightOnCommand kichenLightOn = new LightOnCommand(KichenLight);
		LightOffCommand kichenRoomOff = new LightOffCommand(KichenLight);
		
		remoteComtrol.setCommand(0, livingRoomLightOn, livingRoomOff);
		remoteComtrol.setCommand(1, kichenLightOn, kichenRoomOff);
		
		System.out.println(remoteComtrol);
		
		remoteComtrol.onButtonWasPushed(0);
		remoteComtrol.offButtonWasPushed(0);
		remoteComtrol.onButtonWasPushed(1);
		remoteComtrol.offButtonWasPushed(1);

	}
}

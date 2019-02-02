package frc.robot.utilities;

import edu.wpi.first.wpilibj.buttons.Button;
public class SharedButton extends Button {
	
	private Button[] buttons;
	
	public SharedButton(Button[] buttons){
		this.buttons = buttons;
	}

    public boolean get() {
		for(int i = 0; i < buttons.length; i++){
			if(buttons[i].get() == true){
				return true;
			}
		}
		return false;
	}
	
}

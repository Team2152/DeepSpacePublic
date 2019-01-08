package frc.robot.utilities;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class SharedButton extends Button{
	
	private Joystick joy1;
	private int button1;
	private boolean is1POV;
	private Joystick joy2;
	private int button2;
	private boolean is2POV;
	
	public SharedButton(Joystick joy1, int button1, boolean is1POV, Joystick joy2, int button2, boolean is2POV){
		this.joy1 = joy1;
		this.button1 = button1;
		this.is1POV = is1POV;
		this.joy2 = joy2;
		this.button2 = button2;
		this.is2POV = is2POV;
	}

    public boolean get() {
			
    		if(is1POV == true){
    			if(joy1.getPOV() == button1){
    				return true;
    			}
    		} 
    		
    		if (is1POV == false){
    			if(joy1.getRawButton(button1) == true){
    				return true;
    			}
    		}
    		
    		if (is2POV == true){
    			if(joy2.getPOV() == button2){
    				return true;
    			}
    		} 
    		
    		if (is2POV == false){
    			if(joy2.getRawButton(button2) == true){
    				return true;
    			}
    		}
    	return false;
	}
	
}

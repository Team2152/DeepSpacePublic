package frc.robot.utilities;

import edu.wpi.first.wpilibj.buttons.Button;
 /**
  * This class is used to allow the same <Code>Command</code> to be excecuted by different 
  * <code>Buttons</code> 

  * <p> This class should be used like a <code>Button</code> is used in <code>OI.java</code>
	<p> <b>Example:</b>
	<p> <pre>
private Button dButtonA = new Button(ID);


	public void setupDriverXboxButtons() {
		dButtonA.whenReleased(new ExampleCommand();
}
	</pre>

	<p> <b>Should be replaced with:</b>
	<pre>
private Button dButtonA = new Button(ID);
private Button dButtonB = new Button(ID);

private SharedButton button = 
	new SharedButton(new Button[] {dButtonA,dButtonB});

	public void setupSharedCommands() {
		button.whenReleased(new ExampleCommand());
  }
	</pre>
  */

public class SharedButton extends Button {
	
	private Button[] buttons;
	
	/**
	 * Constructor for SharedButton.java
	 * @param buttons array of button classes to share one command
	 */
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

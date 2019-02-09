/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;


/**
 * Add your docs here.
 */
public class MotorControllerPIDSource implements PIDSource {

     WPI_TalonSRX talon;
     CANSparkMax sparkMax;
     CANEncoder canEncoder;
     Encoder encoder;
     int isInternalEncoder = 1;
    //External Encoder
    public MotorControllerPIDSource(CANSparkMax sparkMax, Encoder encoder) {
        this.sparkMax = sparkMax;
        this.encoder = encoder;
        isInternalEncoder = 2;
        encoder.reset();
    }
    //Internal Encoder NEO
    public MotorControllerPIDSource(CANSparkMax sparkMax, CANEncoder canEncoder){
        this.sparkMax = sparkMax;
        this.canEncoder = canEncoder;       
    }
    // Talon data port
    public MotorControllerPIDSource(WPI_TalonSRX talon){
        this.talon = talon;
        talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        isInternalEncoder = 3;
    }

    private PIDSourceType pidSourceDistance = PIDSourceType.kDisplacement;

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
        this.pidSourceDistance = pidSource;
    }

    @Override
    public PIDSourceType getPIDSourceType(){
        return pidSourceDistance;
    }

    @Override
    public double pidGet(){
       if(isInternalEncoder == 1){
           return canEncoder.getPosition();

       }else if (isInternalEncoder == 2){
           return encoder.get();
       }else{
           return talon.getSelectedSensorPosition();
       }
        
    }
}

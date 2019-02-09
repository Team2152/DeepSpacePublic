/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;


/**
 * Add your docs here.
 */
public class SparkMaxPIDSource implements PIDSource {

     CANSparkMax sparkMax;
     CANEncoder canEncoder;
     Encoder encoder;
     Boolean isInternalEncoder = true;
    //External Encoder
    public SparkMaxPIDSource(CANSparkMax sparkMax, Encoder encoder) {
        this.sparkMax = sparkMax;
        this.encoder = encoder;
        isInternalEncoder = false;
        encoder.reset();
    }
    //Internal Encoder
    public SparkMaxPIDSource(CANSparkMax sparkMax, CANEncoder canEncoder){
        this.sparkMax = sparkMax;
        this.canEncoder = canEncoder;       
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
       if(isInternalEncoder == true){
           return canEncoder.getPosition();

       }else{
           return encoder.get();
       }
        
    }
}

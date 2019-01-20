/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class SparkMaxPIDSource implements PIDSource {

    CANSparkMax sparkMax;

    public SparkMaxPIDSource(CANSparkMax sparkMax) {
        this.sparkMax = sparkMax;
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
        return Robot.stageOneArmSubsystem.getEncoderValue();
    }
}

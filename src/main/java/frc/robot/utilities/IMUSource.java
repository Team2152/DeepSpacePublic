/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class IMUSource implements PIDSource {

    WPI_TalonSRX talon;

    public IMUSource(WPI_TalonSRX talon) {
        this.talon = talon;
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
        return Robot.stageTwoArmSubsystem.getPigeon();
    }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.interfaces.gyro; commented this because I don't know if it is right
/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private final PWMTalonSRX m_leftMotor = new PWMTalonSRX(0);
  private final PWMTalonSRX m_rightMotor = new PWMTalonSRX(1);
  private final XboxController m_controller = new XboxController(2);
  private final PWMTalonSRX m_shooter = new PWMTalonSRX(2);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor::set, m_rightMotor::set);
  private final Joystick m_stick = new Joystick(0);

  private Timer m_timer = new Timer();

  /** Called once at the beginning of the robot program. */
  public Robot() {
    SendableRegistry.addChild(m_robotDrive, m_leftMotor);
    SendableRegistry.addChild(m_robotDrive, m_rightMotor);

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightMotor.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    //m_robotDrive.arcadeDrive(-m_stick.getY()/2, -m_stick.getX()/2);
    m_robotDrive.tankDrive(-m_stick.getY()/2, -m_stick.getX()/2);
    if(m_controller.getYButton()){
      m_shooter.set(1);
    }
    else{
      m_shooter.set(0);
    }
  }

  @Override
  public void autonomousInit(){
    m_timer.start();
  }

  @Override
  public void autonomousPeriodic(){
    if (m_timer.hasElapsed(5)){
      m_timer.stop();
      m_robotDrive.tankDrive(0, 0);
    }
    else{
      m_robotDrive.tankDrive(1, 1);
    }
  }
  //autonPeriodic
  //teleopInit
  //autonInit
}

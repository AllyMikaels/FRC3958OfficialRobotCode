// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.Auton.MPController;
import frc.robot.commands.Auton.Trajectories;

public class AutoOne extends CommandBase {
  public static Timer m_autoTimer;
  Trajectories trajectories = new Trajectories();;
  public static Trajectory[] selectedTrajectory = new Trajectory[2];

  String selected_path;

  private String somethingPath = "C:/Users/roboticsstudent.ROBOTICS02-5188/Desktop/2023 Season Robot/2023OfficialRobotCode-Imported/src/main/deploy/pathplanner/generatedCSV/New Path.csv";
  MPController mpController;

  public static Command autoCommand;
  /** Creates a new AutoOne. */
  public AutoOne(MPController m) {
    // Use addRequirements() here to declare subsystem dependencies.
    mpController = m;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    selected_path = somethingPath;
    for(int i = 0; i < 2; i++){
     selectedTrajectory[i] = trajectories.getTrajectoryFromCSV(selected_path)[i];
    }

   System.out.println("start");
   //m_autonomousCommand = m_robotContainer.getAutonomousCommand();
   System.out.println("WORKDS");
   mpController.drive.resetEncoders();
   mpController.drive.initializeOdometry();
   mpController.drive.reset();
   mpController.drive.resetOdometry(selectedTrajectory[0].getInitialPose());
   mpController.drive.periodic();
   // schedule the autonomous command (example)
   autoCommand = mpController.createTrajectoryFollowerCommand(selectedTrajectory[0], selectedTrajectory[1], 2.5);

   autoCommand.schedule();

   m_autoTimer.reset();
   m_autoTimer.start();
   System.out.println("working");
 }

  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

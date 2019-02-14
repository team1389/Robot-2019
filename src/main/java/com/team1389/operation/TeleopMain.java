package com.team1389.operation;

import java.util.function.Supplier;

import com.team1389.hardware.controls.ControlBoard;
import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.robot.RobotSoftware;
import com.team1389.system.SystemManager;
import com.team1389.system.drive.CurvatureDriveSystem;
import com.team1389.watch.Watcher;
import com.team1389.system.Subsystem;
import com.team1389.systems.TeleopShooter;
import com.team1389.systems.C;
import com.team1389.systems.Climber;
import com.team1389.systems.ManualArm;

public class TeleopMain
{
	SystemManager manager;
	ControlBoard controls;
	RobotSoftware robot;

	public TeleopMain(RobotSoftware robot)
	{
		this.robot = robot;
	}

	public void init()
	{
		controls = ControlBoard.getInstance();
		Subsystem drive = setUpDrive();
		Subsystem shooter = setUpShooter();
		Subsystem arm = setUpArm();
		manager = new SystemManager();
		manager.init();
		Watcher watcher = new Watcher();
		watcher.watch();
		watcher.outputToDashboard();
	}

	private Subsystem setUpDrive()
	{
		return new CurvatureDriveSystem(robot.drive.getAsTank(), controls.xLeftDriveY(), controls.xRightDriveX(),
				controls.xRightBumper());
	}

	private Subsystem setUpShooter()
	{
		return new TeleopShooter(robot.rightShoot, robot.leftShoot, controls.driveBButton(), controls.driveYButton(),
				controls.driveAButton(), controls.driveXButton());
	}
	// (DigitalOut rightShooter, DigitalOut leftShooter,
	// DigitalIn shootRightCloseButton, DigitalIn shootRightFarButton,
	// DigitalIn shootLeftCloseButton, DigitalIn shootLeftFarButton)

	private Subsystem setUpClimber()
	{
		return new C(robot.climber, robot.climbWheel, controls.yButton(), controls.leftStickYAxis());
	}

	private Subsystem setUpArm()
	{
		return new ManualArm(robot.hatchOuttake, robot.cargoLauncher, robot.cargoIntake, robot.arm,
				new DigitalIn((Supplier<Boolean>) () -> false), controls.rightStickYAxis(), controls.bButton(),
				controls.aButton(), controls.xButton(), false);
	}
	// (DigitalOut hatchOuttake, DigitalOut cargoLauncher, RangeOut<Percent>
	// cargoIntake,
	// RangeOut<Percent> arm, DigitalIn cargoIntakeBeamBreak, RangeIn<Percent>
	// armAxis, DigitalIn outtakeHatchBtn,
	// DigitalIn intakeCargoBtn, DigitalIn outtakeCargoBtn, boolean
	// useBeamBreak)

	public void periodic()
	{
		manager.update();
	}
}
// ManualArm(DigitalOut hatchOuttake, DigitalOut cargoLauncher,
// RangeOut<Percent> cargoIntake,
// RangeOut<Percent> arm, DigitalIn cargoIntakeBeamBreak, RangeIn<Percent>
// armAxis, DigitalIn outtakeHatchBtn,
// DigitalIn intakeCargoBtn, DigitalIn outtakeCargoBtn, boolean useBeamBreak)

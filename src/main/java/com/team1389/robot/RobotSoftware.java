package com.team1389.robot;

import com.team1389.hardware.inputs.software.AngleIn;
import com.team1389.hardware.inputs.software.RangeIn;
import com.team1389.hardware.outputs.software.RangeOut;
import com.team1389.hardware.value_types.Percent;
import com.team1389.hardware.value_types.Position;
import com.team1389.system.drive.SixDriveOut;

public class RobotSoftware extends RobotHardware {
	private static RobotSoftware INSTANCE = new RobotSoftware();
	
	public static RobotSoftware getInstance() {
		return INSTANCE;
	}

	private final SixDriveOut<Percent> drive = new SixDriveOut<Percent>(driveLeftA.getVoltageController(), driveRightB.getVOltageController(), driveLeftB.getVoltageController(), 
		driveRightB.getVoltageController(), driveLeftC.getVoltageController(), driveRightC.getVoltageController());

	public RobotSoftware(){
		
	}
	

}

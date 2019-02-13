package com.team1389.systems;

import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.hardware.outputs.software.DigitalOut;
import com.team1389.system.Subsystem;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;
import com.team1389.systems.Shooter;
import com.team1389.command_framework.CommandScheduler;

public class TeleopShooter extends Subsystem
{
    //Controls
    private DigitalIn shootRightCloseButton;
    private DigitalIn shootRightFarButton;
    private DigitalIn shootLeftCloseButton;
    private DigitalIn shootLeftFarButton;
    //Output
    private DigitalOut rightShooter;
    private DigitalOut leftShooter;
    
    private Shooter shooter;


    /**
     * @param shootRightButton Input for shooting the ball to the right
     * 
     * @param shootLeftButton Input for shooting ball to the left
     * 
     * @param hasCargo Detects whether there is a ball in the shooter or not
     * 
     * @param rightShooter Controller for shooting ball to the right
     * 
     * @param leftShooter Controller for shooting ball to the left
     */


    public TeleopShooter(DigitalOut rightShooter, DigitalOut leftShooter, 
    DigitalIn shootRightCloseButton, DigitalIn shootLeftCloseButton) {
        this.rightShooter = rightShooter;
        this.leftShooter = leftShooter;
        this.shootLeftCloseButton = shootLeftCloseButton;
        this.shootLeftFarButton = shootLeftFarButton;
        this.shootRightCloseButton = shootRightCloseButton;
        this.shootRightFarButton = shootRightFarButton;
    }
    public AddList<Watchable> getSubWatchables(AddList<Watchable> stem)
    {
        return stem.put(shooter);
    }
    public String getName()
    {
        return "Teleop Shooter";
    }
    public void init()
    {
        shooter = new Shooter(rightShooter, leftShooter);
    }
    public void updateShooter()
    {
       if (shootRightCloseButton.get())
        {
            shooter.shootRightClose();
        }
        if (shootRightFarButton.get()) 
        {
            shooter.shootRightFar();
        }
        if (shootLeftCloseButton.get())
        {
            shooter.shootLeftClose();
        }
        if (shootLeftFarButton.get())
        {
            shooter.shootLeftFar();
        }
        shooter.update();

    }
    @Override
    public void update()
    {
        updateShooter();
    }
   
}
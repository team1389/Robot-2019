package com.team1389.systems;

import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.hardware.outputs.software.DigitalOut;
import com.team1389.system.Subsystem;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;
import com.team1389.systems.Shooter;

public class TeleopShooter extends Subsystem
{
    //Controls
    private DigitalIn shootRightBtn;
    private DigitalIn shootLeftBtn;
    //Output
    private DigitalOut rightShooter;
    private DigitalOut leftShooter;
    //Sensors
    private DigitalIn hasCargo;
    
    private Shooter shooter;

    /**
     * @param shootRightBtn Input for shooting the ball to the right
     * 
     * @param shootLeftBtn Input for shooting ball to the left
     * 
     * @param hasCargo Detects whether there is a ball in the shooter or not
     * 
     * @param rightShooter Controller for shooting ball to the right
     * 
     * @param leftShooter Controller for shooting ball to the left
     */

    public TeleopShooter(DigitalOut rightShooter, DigitalOut leftShooter, 
        DigitalIn shootRightBtn, DigitalIn shootLeftBtn, DigitalIn hasCargo)
    {
        this.rightShooter = rightShooter;
        this.leftShooter = leftShooter;
        this.hasCargo = hasCargo;
        this.shootRightBtn = shootRightBtn;
        this.shootLeftBtn = shootLeftBtn;
    }

    public TeleopShooter(DigitalOut rightShooter, DigitalOut leftShooter, 
    DigitalIn shootRightBtn, DigitalIn shootLeftBtn) {
        this(rightShooter, leftShooter, shootRightBtn, shootLeftBtn, null);
        System.err.println("Teleop shooter initialized without cargo sensor - brace for a NullPointerException");
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
        shooter = new Shooter(rightShooter, leftShooter, hasCargo);
    }
    public void updateShooter()
    {
       /* if (shootRightBtn.get())
        {
            shootRight();
        }
        if (shootLeftBtn.get())
        {
            shooter.shootLeft();
        }*/
        rightShooter.set(true);
        leftShooter.set(true);
    }
    public void update()
    {
        scheduler.update();
        updateShooter();
    }
   
}
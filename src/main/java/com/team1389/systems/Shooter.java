package com.team1389.systems;

import com.team1389.auto.command.WaitTimeCommand;
import com.team1389.command_framework.CommandUtil;
import com.team1389.command_framework.command_base.Command;
import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.hardware.outputs.software.DigitalOut;
import com.team1389.system.Subsystem;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;

public class Shooter extends Subsystem
{
   
    //Output
    private DigitalOut leftShooter;
    private DigitalOut rightShooter;
    //Constants
    private final int WAIT_UNTIL_EXTENDED = 1;

    /**
     * @param rightShooter Piston for shooting ball to the right
     * 
     * @param leftShooter Piston for shooting ball to the left
     * 
     */
    
    public Shooter(DigitalOut rightShooter, DigitalOut leftShooter)
    {
        this.rightShooter = rightShooter;
        this.leftShooter = leftShooter;
    }

    public AddList<Watchable> getSubWatchables(AddList<Watchable> stem)
    {
        return stem.put(scheduler);
    }

    @Override
    public String getName()
    {
        return "Shooter";
    }

    public void init()
    {
        
    }

    public void update()
    {
        scheduler.update();
    }

    private void shootRightPiston()
    {
        System.out.println("Gets to shootrightpiston");
        rightShooter.set(true);
    }

    private Command shootRightCommand()
    {
        return CommandUtil.createCommand(() -> rightShooter.set(true));
    }

    private void shootLeftPiston()
    {
        leftShooter.set(true);
    }

    private Command shootLeftCommand()
    {
        return CommandUtil.createCommand(this::shootLeftPiston);
    }

    private void resetShooters()
    {
        leftShooter.set(false);
        rightShooter.set(false);
    }

    private Command resetShootersCommand()
    {
        return CommandUtil.createCommand(this::resetShooters);
    }  

    private Command shootRightReset()
    {
        return CommandUtil.combineSequential(shootRightCommand(), new WaitTimeCommand(WAIT_UNTIL_EXTENDED),
            resetShootersCommand());
    }

    public void shootRight()
    {
        scheduler.cancelAll();
        scheduler.schedule(shootRightCommand());
    }

    private Command shootLeftReset()
    {
        return CommandUtil.combineSequential(shootLeftCommand(), new WaitTimeCommand(WAIT_UNTIL_EXTENDED),
            resetShootersCommand());
    }

    public void shootLeft()
    {
        scheduler.schedule(shootLeftReset());
    }
}
package system;

import java.util.TimerTask;

public class Alarm extends TimerTask {
    private int num;


public Alarm(int num){
        this.num = num;
}

    @Override
    public void run() {

            new popUP("Reminder for task number: " + num);
        }

    }


package system;

import java.util.TimerTask;

public class Alarm extends TimerTask {
    private int num;
    private String string;


public Alarm(int num){
        this.num = num;
}

    public Alarm(String string, int num){

    this.num = num;
    this.string = string;

    }

    @Override
    public void run() {

            if(string.isEmpty())
                new popUP("Reminder for task number: " + num);
            else
                new popUP(string + num);

        }

    }


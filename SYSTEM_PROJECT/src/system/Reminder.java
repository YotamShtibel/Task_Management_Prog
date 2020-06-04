package system;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class Reminder extends Assignment {
    private LocalDate reminderDate;
    private LocalTime reminderTime;

    public Reminder(int assignmentNum, LocalTime madeTime, LocalDate madeDate, Employee assignTo, int priority, int status,  String title, String context, LocalDate reminderDate, LocalTime reminderTime) {
        super(assignmentNum, madeTime, madeDate, assignTo, priority, status,  title, context);
        this.reminderDate = reminderDate;
        this.reminderTime = reminderTime;
        Alarm alarm = new Alarm(assignmentNum);
        Timer timer = new Timer();
        long delay = (((reminderTime.getHour() - LocalTime.now().getHour()) * 60) + ((reminderTime.getMinute() - LocalTime.now().getMinute()))) * 1000 * 60;
        timer.schedule(alarm, delay);


    }



}

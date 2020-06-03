package system;

import java.time.LocalDate;
<<<<<<< Updated upstream

public class Reminder extends Assignment{
    private LocalDate endDate;

    public Reminder(LocalDate endDate) {
        this.endDate = endDate;
=======
import java.time.LocalTime;
import java.util.Timer;

public class Reminder extends Assignment {
    private LocalDate reminderDate;
    private LocalTime reminderTime;

    public Reminder(int assignmentNum, LocalTime madeTime, LocalDate madeDate, Employee assignTo, int priority, String title, String context, LocalDate reminderDate, LocalTime reminderTime) {
        super(assignmentNum, madeTime, madeDate, assignTo, priority, title, context);
        this.reminderDate = reminderDate;
        this.reminderTime = reminderTime;
        int delay = (((reminderTime.getHour() - LocalTime.now().getHour()) * 60) + ((reminderTime.getMinute() - LocalTime.now().getMinute()))) * 1000 * 60;
       setAlarm(assignmentNum,delay);


    }

    public void setAlarm(int assignmentNum, int delay){
        Alarm alarm = new Alarm(assignmentNum);
        Timer timer = new Timer();
        timer.schedule(alarm, delay);
>>>>>>> Stashed changes
    }


}

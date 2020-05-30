package system;

import java.time.LocalDate;

public class Reminder extends Assignment{
    private LocalDate endDate;

    public Reminder(LocalDate endDate) {
        this.endDate = endDate;
    }


}

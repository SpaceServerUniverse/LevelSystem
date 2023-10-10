package space.yurisi.levelsystem.utils;

import java.util.Calendar;

public class DayOfWeek {

    private boolean holiday;

    private static DayOfWeek instance;

    public DayOfWeek(){
        holiday = isHoliday();
        instance = this;
    }

    public boolean isHoliday(){
        int week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        return (week == Calendar.SATURDAY) || week == (Calendar.SUNDAY);
    }

   public void checkHoliday(){
        this.holiday = isHoliday();
   }

    public static DayOfWeek getInstance(){
        return instance;
    }
}

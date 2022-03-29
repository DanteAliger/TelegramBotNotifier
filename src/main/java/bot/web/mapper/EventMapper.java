package bot.web.mapper;

import bot.web.request.EventAddRq;
import bot.constant.Event;
import bot.web.request.EventRq;
import java.time.*;
import java.time.temporal.TemporalAdjusters;

public interface EventMapper {

    static EventRq toRq (EventAddRq eventAddRq){
        return new EventRq()
                .setText(eventAddRq.getText())
                .setRepeatable(repeatable(eventAddRq.getRepeatable()))
                .setNextExecution(dataTime(eventAddRq.getTime(),eventAddRq.getData()));

    }

    static boolean repeatable(String str){
        if (str.equals("Да")) {
            return true;
        }else {
            return false;
        }
    }

    static LocalDateTime dataTime(String time, String data){
        switch (Event.of(data)){
            case MONDAY -> {return dayOfWeekAndTime( DayOfWeek.MONDAY, time);}
            case TUESDAY -> {return dayOfWeekAndTime(DayOfWeek.TUESDAY, time);}
            case WEDNESDAY ->{return dayOfWeekAndTime(DayOfWeek.WEDNESDAY, time);}
            case THURSDAY ->{ return dayOfWeekAndTime(DayOfWeek.THURSDAY, time);}
            case FRIDAY -> {return dayOfWeekAndTime(DayOfWeek.FRIDAY, time);}
            case SATURDAY -> {return dayOfWeekAndTime(DayOfWeek.SATURDAY, time);}
            case SUNDAY -> {return dayOfWeekAndTime(DayOfWeek.SUNDAY, time);}
        }
        return null;
    }

    static LocalDateTime dayOfWeekAndTime(DayOfWeek dayOfWeek, String time){
        if (LocalDate.now().getDayOfWeek() == dayOfWeek){
            if(LocalTime.now().isAfter(LocalTime.parse(time))){
                return LocalDateTime.parse(String.valueOf(LocalDate.now().with(TemporalAdjusters.next(dayOfWeek))).concat("T").concat(time).concat(":00"));
            }else{
                return LocalDateTime.parse(String.valueOf(LocalDate.now()).concat("T").concat(time).concat(":00"));
            }
        }
        return LocalDateTime.parse(String.valueOf(LocalDate.now().with(TemporalAdjusters.next(dayOfWeek))).concat("T").concat(time).concat(":00"));
    }
}


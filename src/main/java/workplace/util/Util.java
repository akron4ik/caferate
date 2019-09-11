package workplace.util;

import workplace.util.exception.TimeIncorrectException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Util {
    private static final LocalTime HOUR_X = LocalTime.of(11,00);

    private Util(){
    }
    public static void checkVoteTime(){
        LocalTime time = LocalTime.now();
        if(time.isAfter(HOUR_X)){
            throw new TimeIncorrectException("You can't vote after 11AM");
        }
    }

    public static void checkVoteDate(LocalDate actual, LocalDate expected){
       if(!actual.equals(expected)){
           throw new DateTimeException("This date Incorrect");
       }
    }
}

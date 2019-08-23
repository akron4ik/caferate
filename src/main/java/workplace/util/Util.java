package workplace.util;

import java.time.LocalTime;

public class Util {

    private Util(){
    }
    public static boolean checkVoteTime(){
        return LocalTime.now().isBefore(LocalTime.of(11,00));
    }
}

package workplace.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Util {
    private static final LocalDate MIN_DATE = LocalDate.of(1, 1, 1);
    private static final LocalDate MAX_DATE = LocalDate.of(3000, 1, 1);

    private Util(){
    }
    public static boolean checkVoteTime(){
        return LocalTime.now().isBefore(LocalTime.of(11,00));
    }

    public static LocalDate adjustStartDate(LocalDate localDate) {
        return localDate == null ? MIN_DATE : localDate;
    }

    public static LocalDate adjustEndDate(LocalDate localDate) {
        return localDate == null ? MAX_DATE : localDate;
    }
}

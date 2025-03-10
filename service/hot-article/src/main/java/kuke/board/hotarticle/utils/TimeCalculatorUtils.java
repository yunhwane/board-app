package kuke.board.hotarticle.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeCalculatorUtils {


    /**
     * 현재 시간에 자정까지 얼마나 남았는지 확인하는 utils
     * @return
     */
    public static Duration calculateDurationToMidnight() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = now.plusDays(1).with(LocalTime.MIDNIGHT);

        return Duration.between(now, midnight);
    }
}

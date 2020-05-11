package bike;

import java.time.LocalDateTime;

public class NormalPriceHandler implements PriceHandler {
    @Override
    public int compute(LocalDateTime start, LocalDateTime returnTime, int numberOfPenalties, int timesExtended) {
        int extra = timesExtended*5 + numberOfPenalties*10;
        int hourReturn = returnTime.getMinute() > 0 ? returnTime.getHour() + 1 : returnTime.getHour();
        return (hourReturn - start.getHour())*10 + extra;
    }
}

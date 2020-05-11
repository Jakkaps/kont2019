package bike;

import java.time.LocalDateTime;

@FunctionalInterface
public interface PriceHandler {
    int compute(LocalDateTime start, LocalDateTime returnTime, int numberOfPenalties, int timesExtended);
}

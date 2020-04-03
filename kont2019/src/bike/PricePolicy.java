package bike;

import java.time.LocalDateTime;

public interface PricePolicy {
	public int computePrice(Person person, Bike bike, LocalDateTime returnTime);
}

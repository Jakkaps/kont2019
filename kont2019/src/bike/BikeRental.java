package bike;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class BikeRental {
	// TODO: del 1, stations and bikes
	private Collection<GeoLocation> stations; // Could use array but I want to be able to use streams. Collection is the most general class that satisfies my needs.
	private Collection<Bike> bikes; // Will need to add and remove elements, but not more. Collection is the most general class that satisfies this.
	private PriceHandler priceHandler;

	public BikeRental(Collection<GeoLocation> stations, Collection<Bike> bikes, PriceHandler handler) {
		this.stations = stations;
		this.bikes = bikes;
		this.priceHandler = handler;
	}

	/*
	 * Counts the number of available bikes within a certain distance of a provided location.
	 * @param location
	 * @param distance
	 * @return the number of available bikes within a certain distance of a provided location
	 */
	private int countAvailableBikesNearby(final GeoLocation location, final double distance) {
		return (int) stations.stream().filter(station -> station.distance(location) < distance).count();
	}

	/*
	 * Finds the closest station (location) within the provided (maximum) distance of the provided bike
	 * @param bike
	 * @param minDistance
	 * @return the closest station (location) within the provided (maximum) distance of the provided bike
	 */
	private GeoLocation getStationNearby(final Bike bike, final double maxDistance) {
		return stations.stream().filter(station -> station.distance(bike.getLocation()) < maxDistance)
				.min(Comparator.comparingDouble(station -> station.distance(bike.getLocation())))
				.orElse(null);
	}

	/*
	 * @return the bikes that currently are rented
	 */
	private Collection<Bike> getRentedBikes() {
		return bikes.stream().filter(bike -> bike.getRenter() != null).collect(Collectors.toList());
	}

	/*
	 * @return the bikes that are close to a station (within 30 meters), but still are rented
	 */
	private Collection<Bike> getUnreturnedBikes() {
	    return getRentedBikes().stream().filter(bike -> getStationNearby(bike, 30) != null).collect(Collectors.toList());
	}

	/*
	 * Called when a person starts renting a bike by taking it from a station.
	 * Checks the arguments before registering all necessary info of the rental.
	 * @param person
	 * @param now the start time of the rental
	 * @param returnTime the expected return time
	 * @throws (some subclass of) RuntimeException if the now isn't before returnTime
	 * @throws (some subclass of) RuntimeException if the bike isn't available for rental
	 */
	public void rentBike(final Person person, final Bike bike, final LocalDateTime now, final LocalDateTime returnTime) {
	    bike.setRenter(person, now, returnTime); // Does the checking and throws if arguments are illegal
	}

	/*
	 * Called when a person extends an ongoing bike rental.
	 * Checks the arguments before registering all necessary info of the rental extension.
	 * @param person
	 * @param bike
	 * @param now the time the extension starts
	 * @param returnTime the (new) expected return time
	 * @throws (some subclass of) RuntimeException if the now isn't before returnTime
	 * @throws (some subclass of) RuntimeException if the bike isn't currently being rented
	 * @throws (some subclass of) RuntimeException if person isn't currently renting the bike
	 */
	public void extendRental(final Person person, final Bike bike, final LocalDateTime now, final LocalDateTime returnTime) {
		checkCorrespondence(person, bike);
		bike.extendRental(now, returnTime);
	}

	private void checkCorrespondence(Person person, Bike bike) {
		if (person != bike.getRenter()) {
			throw new IllegalArgumentException("This was not the person who rented");
		}
	}

	/*
	 * Called when a person returns a bike.
	 * Checks the arguments, computes the price, performs the payment and clears the rental info.
	 * Note that if the payment isn't completed, the rental info should not be cleared.
	 * @param person
	 * @param bike
	 * @param now the time the bike is returned
	 * @throws (some subclass of) RuntimeException if the bike isn't currently being rented by the person argument
	 * @throws (some subclass of) RuntimeException if person isn't near (within 30 meters of) a station
	 */
	public void returnBike(final Person person, final Bike bike, final LocalDateTime now) {
		checkCorrespondence(person, bike);
		bike.endRental(now);
		int price = priceHandler.compute(bike.getTimeRented(), bike.getReturnTime(), bike.getNumberOfPenalties(), bike.getTimesExtended());
    	System.out.println(price);
	}

	public static void main(final String[] args) {
		// Some geo-locations to use in testing:
		// In the hall outside F1: 63.416522, 10.403345
		// By the entrance to Realfagsbygget closest to F1: 63.416017, 10.404729
		// Another spot by the same entrance, closer than 30 meters: 63.416079, 10.404565


		GeoLocation loc1 = new GeoLocation(63.416522, 10.403345);
		GeoLocation loc2 = new GeoLocation(63.416017, 10.404729);
		Bike bike = new Bike(loc2);
		BikeRental rental = new BikeRental(Arrays.asList(loc1), Arrays.asList(bike), new NormalPriceHandler());

		Person person = new Person();
		LocalDateTime start = LocalDateTime.of(2020, 5, 11, 12, 0);
		LocalDateTime originalReturn = LocalDateTime.of(2020, 5, 11, 13, 0);
		rental.rentBike(person, bike, start, originalReturn);

		LocalDateTime firstReturn = LocalDateTime.of(2020, 5, 11, 13, 5);
		LocalDateTime extendedReturn = LocalDateTime.of(2020, 5, 11, 16, 0);
		rental.extendRental(person, bike, firstReturn, extendedReturn);

		LocalDateTime secondReturn =  LocalDateTime.of(2020, 5, 11, 15, 30);
		extendedReturn = LocalDateTime.of(2020, 5, 11, 17, 0);
		rental.extendRental(person, bike, secondReturn, extendedReturn);

		LocalDateTime finalReturn = LocalDateTime.of(2020, 5, 11, 16, 45);
		rental.returnBike(person, bike, finalReturn);
	}
}

package bike;

import java.time.LocalDateTime;

/*
 * @startuml
 * class Bike
 * Bike --> Location: location
 * Bike --> Person: renter
 * @enduml
 */
public class Bike {
  private GeoLocation location;
  private Person renter;
  private int timesExtended = 0;
  private int nPenalties = 0;

  private LocalDateTime timeRented;
  private LocalDateTime returnTime;

  public Bike(GeoLocation location) {
    this.location = location;
  }

  public GeoLocation getLocation() {
    return location;
  }

  public void setLocation(GeoLocation location) {
    this.location = location;
  }

  public Person getRenter() {
    return renter;
  }

  public void setRenter(Person renter, LocalDateTime start, LocalDateTime returnTime) {
    checkTime(start, returnTime);

    if (this.renter != null){
      throw new IllegalStateException("This bike is already rented");
    }

    this.renter = renter;
    this.timeRented = start;
    this.returnTime = returnTime;
  }

  private void checkTime(LocalDateTime start, LocalDateTime returnTime) {
    if (start.isAfter(returnTime)){
      throw new IllegalArgumentException("Start time has to be after end time");
    }
  }

  public void extendRental(LocalDateTime now, LocalDateTime returnTime){
    checkTime(now, returnTime);
    checkTime(this.timeRented, returnTime);

    if (now.isAfter(this.returnTime)){
      this.nPenalties++;
    }

    timesExtended++;
    this.returnTime = returnTime;
  }

  public int computePrice(LocalDateTime now) {
      int extra = timesExtended*5 + nPenalties*10;
      System.out.println("Extra " + extra);
      int hourReturn = now.getMinute() > 0 ? now.getHour() + 1 : now.getHour();
      return (hourReturn - timeRented.getHour())*10 + extra;
  }

  public void endRental() {
    this.renter = null;
    nPenalties = 0;
    timesExtended = 0;
  }
}

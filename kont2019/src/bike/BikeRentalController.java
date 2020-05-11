package bike;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.LocalTimeStringConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class BikeRentalController {

	private final BikeRental bikeRental;
	// Give start and end reasonable start and end values
	private LocalTime start = LocalTime.now();
	private LocalTime end = start.plusHours(1);
	private PriceHandler handler = new NormalPriceHandler();

	public BikeRentalController() {
		GeoLocation loc1 = new GeoLocation(63.416522, 10.403345);
		GeoLocation loc2 = new GeoLocation(63.416017, 10.404729);
		Bike selectedBike = new Bike(loc2);
		bikeRental = new BikeRental(Arrays.asList(loc1), Arrays.asList(selectedBike),new NormalPriceHandler());
	}

	// hint: LocalTimeStringConverter has two useful method, fromString(String) and toString(LocalTime)
	private final LocalTimeStringConverter localTimeStringConverter = new LocalTimeStringConverter();

	@FXML
	public void initialize() {
		fromInput.setText(localTimeStringConverter.toString(start));
		toInput.setText(localTimeStringConverter.toString(end));
	    calculatePriceAction();
	}

	@FXML
	private TextField fromInput;

	@FXML
	private TextField toInput;

	@FXML
	private Label priceLabel;

	@FXML
	public void plus1HourAction(){
		end = end.plusHours(1);
		toInput.setText(localTimeStringConverter.toString(end));
	}

	@FXML
	public void minus1HourAction(){
		end = end.minusHours(1);
		toInput.setText(localTimeStringConverter.toString(end));
	}

	@FXML
	public void calculatePriceAction(){
		LocalDate today = LocalDate.now();
		priceLabel.setText(handler.compute(LocalDateTime.of(today, start), LocalDateTime.of(today, end), 0, 0) + "kr");
	}
}

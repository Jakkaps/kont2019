module kont2019 {

	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
	requires junit;

	exports bike;
	exports stuff;

	opens bike to javafx.fxml;
}

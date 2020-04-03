module kont2019 {

	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;

	exports bike;

	opens bike to javafx.fxml;
}

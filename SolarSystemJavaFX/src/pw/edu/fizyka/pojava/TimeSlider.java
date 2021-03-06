//Anna Giszczak
package pw.edu.fizyka.pojava;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class TimeSlider {
	
	double previousValue;

	public TimeSlider() {
		Main.sliderT = MenuPane.timeSlider.getValue();
		MenuPane.timeSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				// TODO Auto-generated method stub
				Main.sliderT = MenuPane.timeSlider.getValue();
				
			}		
		});
		MenuPane.stop.setOnAction(event -> {
			previousValue=returnPreviousValue(previousValue);
			
            Main.sliderT = 0;
            MenuPane.timeSlider.setValue(0);
        });
		MenuPane.start.setOnAction(event -> {
			MenuPane.timeSlider.setValue(previousValue);
			Main.sliderT = previousValue;
        });
	}
	
	public double returnPreviousValue(double copy){
		double returnIfZero = copy;
		copy = MenuPane.timeSlider.getValue();
		if(copy!=0){
			return copy;
		}else {
			return returnIfZero;
		}
	}

}
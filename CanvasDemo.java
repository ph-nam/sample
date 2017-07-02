import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CanvasDemo extends Application {

	private static int GRID_SIZE = 50;

	private Canvas canvas = new Canvas(3860, 2860);

	private GraphicsContext gc = canvas.getGraphicsContext2D();

	private boolean rendered = false;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Canvas Test");

		canvas.setOnMouseClicked((evt) -> drawPins());

		ScrollPane scroll = new ScrollPane();
		scroll.setContent(canvas);

		primaryStage.setScene(new Scene(scroll, 800, 800));
		primaryStage.show();
	}

	private void drawPins() {
		if (rendered) {
			return;
		}
		rendered = true;

		long startTime = System.currentTimeMillis();

		drawCircles();
		drawLines();
		drawLables();

		long endTime = System.currentTimeMillis();
		long time = endTime - startTime;
		gc.setStroke(Color.web("#000000"));
		gc.strokeText("Rendered in " + time + " ms", 10, 15);
	}

	private void drawCircles() {
		gc.setFill(Color.web("#cccccc"));
		double radius = 50;
		for (int i = 0; i < GRID_SIZE; ++i) {
			for (int j = 0; j < GRID_SIZE; ++j) {
				double x = 7.5 + (radius + 7) * i;
				double y = 7.5 + (radius + 7) * j;
				gc.fillOval(x, y, radius, radius);
			}
		}

		gc.setFill(Color.web("#8ab6d0"));
		radius = 45;
		for (int i = 0; i < GRID_SIZE; ++i) {
			for (int j = 0; j < GRID_SIZE; ++j) {
				double x = 10 + (radius + 12) * i;
				double y = 10 + (radius + 12) * j;
				gc.fillOval(x, y, radius, radius);
			}
		}
	}

	private void drawLines() {
		gc.setStroke(Color.web("#f2eeee"));
		gc.setFill(Color.web("#f2eeee"));
		gc.setLineWidth(3);
		double radius = 25;
		for (int i = 0; i < GRID_SIZE; ++i) {
			for (int j = 0; j < GRID_SIZE; ++j) {
				double x = 19.5 + (radius + 32) * i;
				double y = 19.5 + (radius + 32) * j;
				gc.fillOval(x, y, radius, radius);
				gc.strokeLine(x - 3, y - 3, x + radius + 3, y + radius + 3);
				gc.strokeLine(x + radius + 3, y - 3, x - 3, y + radius + 3);
			}
		}
	}

	private void drawLables() {
		gc.setStroke(null);
		gc.setFont(Font.font(11));
		gc.setFill(Color.web("#000000"));
		gc.setLineWidth(1);
		double radius = 50;
		for (int i = 0; i < GRID_SIZE; ++i) {
			for (int j = 0; j < GRID_SIZE; ++j) {
				String name = "";
				name += (char) (65 + j);
				name += i;
				double x = ((name.length() == 3) ? 22 : 25) + (radius + 7) * i;
				double y = 35 + (radius + 7) * j;
				gc.fillText(name, x, y);
			}
		}
	}

}
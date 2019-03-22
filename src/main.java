import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.Student;
import util.StudentHandler;

public class main extends Application
{
    private static Stage stage;
    @Override
    public void start(Stage primaryStage)
    {
        stage = primaryStage;
        stage.setTitle("Seating chart");
        stage.toFront();

        StudentHandler studentHandler = new StudentHandler();
        AnchorPane anchorPane = new AnchorPane();

        for(int i = 0; i < studentHandler.students.size(); i++)
        {
            ImageView imageView = new ImageView(studentHandler.students.get(i).getImage());
            imageView.setFitHeight(Student.maxImageSize.getHeight());
            imageView.setFitWidth(Student.maxImageSize.getWidth());
            imageView.setPreserveRatio(true);

            imageView.setLayoutX(studentHandler.students.get(i).getSeat().getX());
            imageView.setLayoutY(studentHandler.students.get(i).getSeat().getY());

            anchorPane.getChildren().add(imageView);
        }

        Scene scene = new Scene(anchorPane, 1000, 1000);

        stage.setScene(scene);

        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

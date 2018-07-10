import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application{
    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        Model model = new Model(stage);
    }
}
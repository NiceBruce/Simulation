package code.rtfmyoumust.simulation;

import code.rtfmyoumust.simulation.model.livingEntities.Footman;
import code.rtfmyoumust.simulation.model.livingEntities.Grunt;
import code.rtfmyoumust.simulation.map.Coordinates;
import code.rtfmyoumust.simulation.map.World;
import code.rtfmyoumust.simulation.map.RenderMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        World map = new World();
        map.setEntities(new Coordinates(0,0), new Footman(new Coordinates(0,0)));
        map.setEntities(new Coordinates(9,9), new Grunt(new Coordinates(9,9)));

        RenderMap render = new RenderMap(10, 100, map);

        Scene scene = new Scene(render.renderMap());
        stage.setTitle("Warcraf2TSimulation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
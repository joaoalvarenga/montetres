package montetres;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import montetres.tela.Tela;
import montetres.tela.TelaFimJogo;
import montetres.tela.TelaInicial;
import montetres.tela.TelaJogo;

/**
 * Created by joao on 19/03/17.
 */
public class Main extends Application {

    private static long lastUpdate = 0;
    private static int index = 0;
    private static double[] frameRates = new double[100];

    private Tela telaAtual;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MonteTres");
        primaryStage.resizableProperty().setValue(false);
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        Canvas canvas = new Canvas(1000, 512);
        root.getChildren().add(canvas);

        telaAtual = new TelaInicial(canvas.getGraphicsContext2D());

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                telaAtual.aoClicarMouse(event);
            }
        });

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(now - lastUpdate >= 16_000_000) {
                    canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    if(telaAtual.isProximo()) {
                        if(telaAtual instanceof TelaInicial) {
                            telaAtual = new TelaJogo(canvas.getGraphicsContext2D());
                        }
                        else if(telaAtual instanceof TelaJogo) {
                            telaAtual = new TelaFimJogo(canvas.getGraphicsContext2D(), ((TelaJogo) telaAtual).getPontuacao());
                        }
                        else if(telaAtual instanceof TelaFimJogo) {
                            telaAtual = new TelaJogo(canvas.getGraphicsContext2D());
                        }
                    }
                    telaAtual.atualizar();
                    if (lastUpdate > 0) {
                        long nanosElapsed = now - lastUpdate;
                        double frameRate = 1000000000.0 / nanosElapsed;
                        index %= frameRates.length;
                        frameRates[index++] = frameRate;
                    }

                    lastUpdate = now;

                    /*canvas.getGraphicsContext2D().setTextBaseline(VPos.BOTTOM);
                    canvas.getGraphicsContext2D().setTextAlign(TextAlignment.LEFT);
                    canvas.getGraphicsContext2D().setFill(Color.WHITE);
                    canvas.getGraphicsContext2D().setFont(new Font("Times New Roman", 40));
                    canvas.getGraphicsContext2D().fillText(String.format("%f FPS", getInstantFPS()), 0, 40);*/
                }
            }
        }.start();

        primaryStage.show();
    }

    public static double getInstantFPS()
    {
        return frameRates[index % frameRates.length];
    }

}

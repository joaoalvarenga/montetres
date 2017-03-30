package montetres.tela;

import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import montetres.tela.Tela;

/**
 * Created by joao on 25/03/17.
 */
public class TelaInicial extends Tela {

    private GraphicsContext graphicsContext;
    private Image backgroundImage;
    private Font fontePequena;
    private Font fonteGrande;


    public TelaInicial(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        this.backgroundImage = new Image("file:assets/background.jpg");
        this.proximo = false;
        this.fontePequena = new Font("Arial", 40);
        this.fonteGrande = new Font("Arial", 100);
    }

    @Override
    public void atualizar() {
        graphicsContext.drawImage(backgroundImage, 0, 0);

        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setTextBaseline(VPos.CENTER);
        graphicsContext.setFont(fonteGrande);
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillText("MonteTres", graphicsContext.getCanvas().getWidth()/2, graphicsContext.getCanvas().getHeight()/2 - 100);

        graphicsContext.setFill(Color.GRAY);
        graphicsContext.fillRoundRect(graphicsContext.getCanvas().getWidth()/2 - (50) + 2, graphicsContext.getCanvas().getHeight()/2 + 2, 100, 100, 10, 10);
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRoundRect(graphicsContext.getCanvas().getWidth()/2 - (50), graphicsContext.getCanvas().getHeight()/2, 100, 100, 10, 10);
        graphicsContext.setFill(Color.GREY);
        graphicsContext.setFont(fontePequena);
        graphicsContext.fillText("▶", graphicsContext.getCanvas().getWidth()/2 + 2, graphicsContext.getCanvas().getHeight()/2 + 48);
    }

    @Override
    public void aoClicarMouse(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY) {
            if(new Rectangle2D(event.getSceneX(), event.getSceneY(), 1, 1).intersects(new Rectangle2D(graphicsContext.getCanvas().getWidth()/2 - (50), graphicsContext.getCanvas().getHeight()/2, 100, 100))) {
                proximo = true;
            }
        }

    }
}

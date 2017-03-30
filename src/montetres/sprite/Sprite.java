package montetres.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by joao on 19/03/17.
 */
public class Sprite {

    private double posicaoX;
    private double posicaoY;
    private double largura;
    private double altura;

    protected Image image;

    public Sprite(double x, double y, Image img) {
        posicaoX = x;
        posicaoY = y;
        setImage(img);
    }

    public double getPosicaoX() {
        return posicaoX;
    }

    public void setPosicaoX(double posicaoX) {
        this.posicaoX = posicaoX;
    }

    public double getPosicaoY() {
        return posicaoY;
    }

    public void setPosicaoY(double posicaoY) {
        this.posicaoY = posicaoY;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        setLargura(image.getWidth());
        setAltura(image.getHeight());
    }

    public void setPosicao(double x, double y) {
        setPosicaoX(x);
        setPosicaoY(y);
    }

    public void renderizar(GraphicsContext gc) {
        gc.drawImage(getImage(), getPosicaoX(), getPosicaoY());
    }

    public Rectangle2D getBordas() {
        return new Rectangle2D(posicaoX, posicaoY, largura, altura);
    }

    public boolean intercepta(Sprite s) {
        return s.getBordas().intersects(this.getBordas());
    }


}

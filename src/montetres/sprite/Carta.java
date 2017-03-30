package montetres.sprite;

import javafx.scene.image.Image;
import montetres.Naipe;

/**
 * Created by joao on 19/03/17.
 */
public class Carta extends Sprite {

    private int carta;
    private Naipe naipe;
    private boolean virada;

    public Carta(double x, double y, Naipe naipe, int carta) {
        super(x,y, new Image(String.format("file:assets/png/%d%s.png", carta, naipe.toString())));
        this.carta = carta;
        this.naipe = naipe;
        this.virada = false;
    }


    public void virarCarta() {
        if(virada) {
            this.setImage(new Image(String.format("file:assets/png/%d%s.png", this.carta, this.naipe.toString())));
        }
        else {
            this.setImage(new Image(String.format("file:assets/png/backred.png", carta, naipe.toString())));
        }
        virada = !virada;
    }

    public void updateImage() {
        this.setImage(new Image(String.format("file:assets/png/%d%s.png", this.carta, this.naipe.toString())));
    }

    public void setNaipe(Naipe naipe) {
        this.naipe = naipe;
        updateImage();
    }

    public void setCarta(int carta) {
        this.carta = carta;
        updateImage();
    }

    public void setVirada(boolean virada) {
        this.virada = virada;
    }

    public int getCarta() {
        return carta;
    }

    public Naipe getNaipe() {
        return naipe;
    }
}

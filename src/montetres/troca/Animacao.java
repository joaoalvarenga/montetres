package montetres.troca;

import javafx.geometry.Point2D;

import java.util.ArrayList;

/**
 * Created by joao on 23/03/17.
 */
public class Animacao {

    private int frame;
    private ArrayList<Point2D> chaves;
    private int velocidade;
    private boolean loop;



    public Animacao(ArrayList<Point2D> chaves, int velocidade, boolean loop) {
        this.frame = 0;
        this.chaves = chaves;
        this.velocidade = velocidade;
        this.loop = loop;
    }

    public Animacao(ArrayList<Point2D> chaves, int velocidade) {
       this(chaves, velocidade, false);
    }
    public boolean isTerminado() {
        return frame >= chaves.size();
    }

    public Point2D getProximoFrame() {

        this.frame += velocidade;
        if(!isTerminado()) {
            return chaves.get(frame);
        }
        else if(loop) {
            this.frame = 0;
            return chaves.get(frame);
        }

        return chaves.get(chaves.size()-1);
    }

    public void reiniciar() {
        this.frame = 0;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public ArrayList<Point2D> getChaves() {
        return chaves;
    }

    public void setChaves(ArrayList<Point2D> chaves) {
        this.chaves = chaves;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}

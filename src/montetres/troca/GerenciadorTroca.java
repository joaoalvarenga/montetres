package montetres.troca;
import javafx.geometry.Point2D;
import montetres.sprite.Carta;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by joao on 24/03/17.
 */
public class    GerenciadorTroca {

    private ArrayList<Troca> trocas;
    private int quantidade;
    private int velocidade;
    private int velocidadeMaxima;

    public GerenciadorTroca(int quantidade) {
        this(quantidade, 10);
    }

    public GerenciadorTroca(int quantidade, int velocidade) {
        this(quantidade, velocidade, 30);
    }

    public GerenciadorTroca(int quantidade, int velocidade, int velocidadeMaxima) {
        this.quantidade = quantidade;
        this.trocas = new ArrayList<>();
        this.velocidade = velocidade;
        this.velocidadeMaxima = velocidadeMaxima;
    }

    public void play(ArrayList<Carta> cartas) {
        int id1, id2;
        Random r = new Random();
        if(quantidade > 0) {
            id1 = r.nextInt(cartas.size());
            id2 = r.nextInt(cartas.size());
            while (id1 == id2) {
                id2 = r.nextInt(cartas.size());
            }
            if(trocas.size() == 0) {
                trocas.add(new Troca(new Point2D(cartas.get(id1).getPosicaoX(), cartas.get(id1).getPosicaoY()), new Point2D(cartas.get(id2).getPosicaoX(), cartas.get(id2).getPosicaoY()), id1, id2, velocidade));
            }
            else if(trocas.get(0).play(cartas)) {
                trocas.remove(0);
                trocas.add(new Troca(new Point2D(cartas.get(id1).getPosicaoX(), cartas.get(id1).getPosicaoY()), new Point2D(cartas.get(id2).getPosicaoX(), cartas.get(id2).getPosicaoY()), id1, id2, velocidade));
                quantidade--;
            }
        }
    }

    public boolean isTerminado() {
        return quantidade <= 0;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setVelocidade(int velocidade) {
        if(velocidade <= 0) {
            this.velocidade = 1;
        }
        else if(velocidade > velocidadeMaxima) {
            this.velocidade = velocidadeMaxima;
        }
        else {
            this.velocidade = velocidade > 0 ? velocidade : 1;
        }
    }

    public int getVelocidade() {
        return velocidade;
    }

    public int getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    public void limpar() {
        trocas.clear();
    }

    public void setVelocidadeMaxima(int velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }
}

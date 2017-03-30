package montetres.troca;

import javafx.geometry.Point2D;
import montetres.sprite.Carta;

import java.util.ArrayList;

/**
 * Created by joao on 23/03/17.
 */
public class Troca {

    private ArrayList<Animacao> animacoes;
    private int indiceAnimacao;
    private int idCarta1, idCarta2;

    public static ArrayList<Point2D> gerarRota(Point2D comeco, Point2D fim) {
        ArrayList<Point2D> rota = new ArrayList<Point2D>();
        int x = (int)comeco.getX();
        int y =  (int)comeco.getY();
        int w = (int)(fim.getX() - comeco.getX()) ;
        int h = (int)(fim.getY() - comeco.getY()) ;
        int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
        if (w<0) dx1 = -1 ; else if (w>0) dx1 = 1 ;
        if (h<0) dy1 = -1 ; else if (h>0) dy1 = 1 ;
        if (w<0) dx2 = -1 ; else if (w>0) dx2 = 1 ;
        int longest = Math.abs(w) ;
        int shortest = Math.abs(h) ;
        if (!(longest>shortest)) {
            longest = Math.abs(h) ;
            shortest = Math.abs(w) ;
            if (h<0) dy2 = -1 ; else if (h>0) dy2 = 1 ;
            dx2 = 0 ;
        }
        int numerator = longest >> 1 ;
        for (int i=0;i<=longest;i++) {
            rota.add(new Point2D(x, y));
            numerator += shortest ;
            if (!(numerator<longest)) {
                numerator -= longest ;
                x += dx1 ;
                y += dy1 ;
            } else {
                x += dx2 ;
                y += dy2 ;
            }
        }

        return rota;
    }
    public Troca(Point2D carta1, Point2D carta2, int idCarta1, int idCarta2, int velocidade) {
        animacoes = new ArrayList<>();
        animacoes.add(new Animacao(gerarRota(new Point2D(carta1.getX(), carta1.getY()), new Point2D(carta1.getX(), carta1.getY()-50)), velocidade));
        animacoes.add(new Animacao(gerarRota(new Point2D(carta2.getX(), carta2.getY()), new Point2D(carta1.getX(), carta2.getY())), velocidade));
        animacoes.add(new Animacao(gerarRota(new Point2D(carta1.getX(), carta1.getY()-50), new Point2D(carta2.getX(), carta2.getY())), velocidade));
        indiceAnimacao = 0;
        this.idCarta1 = idCarta1;
        this.idCarta2 = idCarta2;
    }

    public boolean play(ArrayList<Carta> cartas) {
        Carta c1 = cartas.get(idCarta1);
        Carta c2 = cartas.get(idCarta2);
        if(indiceAnimacao < animacoes.size()) {

            Animacao animacao = animacoes.get(indiceAnimacao);

            Point2D pos = animacao.getProximoFrame();
            System.out.printf("%d(%d,%d)\n", indiceAnimacao, (int)pos.getX(), (int)pos.getY());
            if (indiceAnimacao == 0 || indiceAnimacao == 2) {
                c1.setPosicao(pos.getX(), pos.getY());
            } else {
                c2.setPosicao(pos.getX(), pos.getY());
            }

            if (animacao.isTerminado()) {
                indiceAnimacao++;
            }
            return false;
        }
        System.out.println("FIM");
        return true;
    }

    public boolean isTerminado() {
        return indiceAnimacao == animacoes.size();
    }
}

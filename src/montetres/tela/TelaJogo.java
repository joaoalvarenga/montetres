package montetres.tela;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import montetres.Naipe;
import montetres.sprite.Carta;
import montetres.sprite.Sprite;
import montetres.tela.Tela;
import montetres.troca.Troca;
import montetres.troca.GerenciadorTroca;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by joao on 25/03/17.
 */
public class TelaJogo extends Tela {

    private GraphicsContext graphicsContext;

    private AudioClip errouClip;

    private AudioClip acertouClip;

    private Image backgroundImage;

    private ArrayList<Carta> cartas;

    private GerenciadorTroca gerenciadorTroca;

    private int estado;

    private int cartaSelecionada;

    private int pontuacao;

    private int virarCartas;

    private static final int TEMPO_VIRAR = 100;
    
    public TelaJogo(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;

        this.errouClip = new AudioClip("file:assets/errou.mp3");
        this.acertouClip = new AudioClip("file:assets/acertou.mp3");

        this.backgroundImage = new Image("file:assets/background.jpg");

        cartas = new ArrayList<>();

        Random r = new Random();
        for(int i = 0; i < 3; i++) {
            cartas.add(new Carta(0, 0, Naipe.values()[r.nextInt(Naipe.values().length)], r.nextInt(13)+1));
            System.out.printf("%s : %d\n", cartas.get(i).getNaipe(), cartas.get(i).getCarta());
        }
        alinharCartas();

        gerenciadorTroca = new GerenciadorTroca(0);

        estado = 0;

        virarCartas = 0;
        cartaSelecionada = -1;
        pontuacao = 0;

        this.proximo = false;

    }

    public void alinharCartas() {
        Canvas canvas = graphicsContext.getCanvas();
        int offset = (int)(cartas.size()*(cartas.get(0).getLargura() + 10));
        offset = ((int)canvas.getWidth()-offset) / 2;
        for(int i = 0; i < cartas.size(); i++) {
            Carta c = cartas.get(i);
            c.setPosicaoX(i*(c.getLargura() + 10) + offset);
            c.setPosicaoY((canvas.getHeight() / 2) - (c.getAltura()/2));
        }
    }

    @Override
    public void aoClicarMouse(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY) {
            if(estado == 0 || estado == 4) {
                for(int i = 0; i < cartas.size(); i++) {
                    Carta c = cartas.get(i);
                    if(c.getBordas().intersects(new Rectangle2D(event.getSceneX(), event.getSceneY(), 1, 1))) {
                        cartaSelecionada = i;
                        c.virarCarta();
                        virarCartas = TEMPO_VIRAR;
                        estado = 1;
                        pontuacao = 0;
                        for(int j = 0; j < cartas.size(); j++) {
                            if(i != j) {
                                cartas.get(j).setVirada(false);
                                cartas.get(j).virarCarta();
                            }
                        }
                        gerenciadorTroca.limpar();
                        gerenciadorTroca.setQuantidade(cartas.size());
                        break;
                    }
                }
            }
            if(estado == 2) {
                Random r = new Random();
                for(int i = 0; i < cartas.size(); i++) {
                    Carta c = cartas.get(i);
                    if(c.getBordas().intersects(new Rectangle2D(event.getSceneX(), event.getSceneY(), 1, 1))) {
                        c.virarCarta();
                        if(cartaSelecionada == i) {
                            acertouClip.play();
                            estado = 3;
                            pontuacao += (gerenciadorTroca.getVelocidade()-9);
                            virarCartas = TEMPO_VIRAR;
                            if(cartas.size() >= 6) {
                                gerenciadorTroca.setVelocidadeMaxima(80);
                            }
                            if(gerenciadorTroca.getVelocidade() == gerenciadorTroca.getVelocidadeMaxima() && cartas.size() < 6) {
                                cartas.add(new Carta(0, 0, Naipe.values()[r.nextInt(Naipe.values().length)], r.nextInt(13)));
                                cartas.get(cartas.size()-1).virarCarta();
                                alinharCartas();
                                gerenciadorTroca.setVelocidade(15);
                                gerenciadorTroca.limpar();
                            }
                            else {
                                gerenciadorTroca.setVelocidade(gerenciadorTroca.getVelocidade()+5);
                            }
                            gerenciadorTroca.setQuantidade(cartas.size()+1);
                        }
                        else {
                            errouClip.play();
                            this.proximo = true;
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void atualizar() {
        graphicsContext.drawImage(backgroundImage, 0, 0);
        graphicsContext.setFill(Color.WHITE);
        if (virarCartas == 1) {
            for (Carta tmp : cartas) {
                tmp.setVirada(false);
                tmp.virarCarta();
            }
            if (estado == 3) {
                estado = 1;
            }
        }
        if (virarCartas == 0) {
            gerenciadorTroca.play(cartas);
            if (estado == 1 && gerenciadorTroca.isTerminado()) {
                estado = 2;
            }
        }
        for (Sprite s : cartas) {
            s.renderizar(graphicsContext);
        }
        if (virarCartas > 0) {
            if (estado == 3) {
                graphicsContext.setFont(new Font("Times New Roman", 40));
                graphicsContext.fillText("Certou mizeravi", (graphicsContext.getCanvas().getWidth() / 2) - 120, graphicsContext.getCanvas().getHeight() - 75);
            }
            virarCartas--;
        }
        if (estado == 0) {
            graphicsContext.setFont(new Font("Times New Roman", 40));
            graphicsContext.fillText("Escolha uma das cartas", 260, (graphicsContext.getCanvas().getHeight() / 2) - 160);
        }

        if (estado > 0) {
            graphicsContext.setFont(new Font("Times New Roman", 40));
            graphicsContext.fillText(String.format("Pontuação: %d", pontuacao), graphicsContext.getCanvas().getWidth() - 7 * 40, (graphicsContext.getCanvas().getHeight() / 2) - 200);
        }
    }

    public int getPontuacao() {
        return pontuacao;
    }

}

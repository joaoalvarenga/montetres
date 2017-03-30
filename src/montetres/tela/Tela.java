package montetres.tela;


import javafx.scene.input.MouseEvent;

/**
 * Created by joao on 25/03/17.
 */
public abstract class Tela {

    protected boolean proximo;

    public abstract void atualizar();
    public abstract void aoClicarMouse(MouseEvent event);

    public boolean isProximo() {
        return proximo;
    }
}

package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MiJuegoGame;
import com.mygdx.game.Utiles;

import controlador.ControladorXogo;
import modelo.Mundo;
import renderer.RendererJuego;

/**
 * Created by Oscar on 14/02/2015.
 */
public class PantallaJuego implements Screen {

    private boolean pause;
    private boolean finJuego;
    private boolean salir;
    private MiJuegoGame miJuegoGame;
    private RendererJuego rendererJuego;
    private ControladorXogo controladorXogo;
    public static boolean disparando;
    Mundo mundo;

    public PantallaJuego(MiJuegoGame miJuegoGame) {
        mundo = new Mundo();
        this.miJuegoGame = miJuegoGame;
        rendererJuego = new RendererJuego(mundo);
        controladorXogo = new ControladorXogo(mundo,rendererJuego);
        disparando = false;
    }

    @Override
    public void show() {
       // Gdx.input.setInputProcessor(this);
        Utiles.imprimirLog("PantallaJuego" , "show" , "Hola");
    }

    @Override
    public void render(float delta) {
        rendererJuego.renderer(delta);
        controladorXogo.update(delta);


    }

    @Override
    public void resize(int width, int height) {
        Utiles.imprimirLog("PantallaJuego" , "resize" , "Hola");
        rendererJuego.resize(width ,height);


    }

    @Override
    public void pause() {
      //  Gdx.input.setInputProcessor(null);
        Utiles.imprimirLog("PantallaJuego" , "pause" , "Hola");

    }

    @Override
    public void resume() {
      //  Gdx.input.setInputProcessor(this);
        Utiles.imprimirLog("PantallaJuego" , "show" , "Hola");

    }

    @Override
    public void hide() {
      //  Gdx.input.setInputProcessor(null);
        Utiles.imprimirLog("PantallaJuego" , "hide" , "Hola");

    }

    @Override
    public void dispose() {
       // Gdx.input.setInputProcessor(null);
        Utiles.imprimirLog("PantallaJuego" , "dispose" , "Hola");
        rendererJuego.dispose();

    }
}

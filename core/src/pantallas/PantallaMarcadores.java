package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MiJuegoGame;

import modelo.Mundo;

/**
 * Created by Oscar on 14/02/2015.
 */
public class PantallaMarcadores implements Screen,InputProcessor {

    private OrthographicCamera camara2d;
    private SpriteBatch spriteBatch;
    private static Texture fondo;
    private MiJuegoGame miJuegoGame;
    private BitmapFont bitMapRank;
    private BitmapFont bitMapScore;
    private BitmapFont bitMapName;

    public PantallaMarcadores(MiJuegoGame miJuegoGame) {

        camara2d = new OrthographicCamera();
        spriteBatch = new SpriteBatch();
        fondo = new Texture(Gdx.files.internal("pantallas/PantallaScores.png"));
        this.miJuegoGame = miJuegoGame;

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        bitMapScore = new BitmapFont(Gdx.files.internal("texto/Calibri.fnt"),Gdx.files.internal("texto/Calibri.png"),false);
        bitMapRank = new BitmapFont(Gdx.files.internal("texto/Calibri.fnt"),Gdx.files.internal("texto/Calibri.png"),false);
        bitMapName = new BitmapFont(Gdx.files.internal("texto/Calibri.fnt"),Gdx.files.internal("texto/Calibri.png"),false);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        spriteBatch.draw(fondo,0,0, Mundo.ANCHO_MUNDO,Mundo.ALTO_MUNDO);

        bitMapRank.draw(spriteBatch, "1ST", 23, 291);
        bitMapScore.draw(spriteBatch, "3000", 111, 291);
        bitMapName.draw(spriteBatch, "Ralf", 223, 291);

        spriteBatch.end();

    }

    @Override
    public void resize(int width, int height) {
        camara2d.setToOrtho(false, Mundo.ANCHO_MUNDO, Mundo.ALTO_MUNDO);
        camara2d.update();
        spriteBatch.setProjectionMatrix(camara2d.combined);
        spriteBatch.disableBlending();

    }

    @Override
    public void pause() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resume() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void hide() {
    dispose();
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        spriteBatch.dispose();
        fondo.dispose();
        bitMapName.dispose();
        bitMapRank.dispose();
        bitMapScore.dispose();

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            miJuegoGame.setScreen(new PantallaInicio(miJuegoGame));
       // System.out.println("Cordenada" + screenX+"/"+screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

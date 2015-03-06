package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.AssetsJuego;
import com.mygdx.game.MiJuegoGame;

import modelo.Mundo;

/**
 * Created by Oscar on 14/02/2015.
 */
public class PantallaInicio implements Screen, InputProcessor {

    private MiJuegoGame miJuegoGame;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camara2D;
    private SpriteBatch spritebatch;
    private Rectangle[] botones = {
            new Rectangle(118,142,83,17),
            new Rectangle(118,107,90,17)
    };
    private Texture fondo;
    private Vector3 temp;
    private Vector3 temp2;
    private Vector3 tam;

    private Circle dedo;
   // private Texture fondo;

    public PantallaInicio(MiJuegoGame miJuegoGame) {
        this.miJuegoGame = miJuegoGame;
        camara2D = new OrthographicCamera();
        spritebatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        fondo = new Texture( Gdx.files.internal("pantallas/PantallaInicio.png"));
        temp = new Vector3();
        temp2 = new Vector3();
        tam = new Vector3();
        dedo = new Circle();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {

        spritebatch.begin();
        spritebatch.draw(fondo, 0, 0, Mundo.ANCHO_MUNDO, Mundo.ALTO_MUNDO);
        spritebatch.end();
        debuggerRender();

    }

    private void debuggerRender(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLUE);

        temp2.set(botones[0].x,botones[0].y,0 );
        tam.set(botones[0].getWidth(), botones[0].getHeight(),0);
        camara2D.project(temp2);
        camara2D.project(tam);
        shapeRenderer.rect(temp2.x, temp2.y, tam.x ,tam.y);

        temp2.set(botones[1].x,botones[1].y,0 );
        tam.set(botones[1].getWidth(), botones[1].getHeight(),0);
        camara2D.project(temp2);
        camara2D.project(tam);
        shapeRenderer.rect(temp2.x, temp2.y, tam.x ,tam.y);

        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        camara2D.setToOrtho(false, Mundo.ANCHO_MUNDO,
                Mundo.ALTO_MUNDO);
        camara2D.update();
        spritebatch.setProjectionMatrix(camara2D.combined);
        spritebatch.disableBlending();
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
        spritebatch.dispose();
        fondo.dispose();
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
        temp.set(screenX,screenY,0);
        camara2D.unproject(temp);
        dedo.setPosition(temp.x,temp.y);
       // dedo.setPosition(screenX,screenY);
        dedo.setRadius(2f);

        System.out.println("Cordenada" + screenX+"/"+screenY);

        if (Intersector.overlaps(dedo,botones[0])) {
            // novo xogo
            miJuegoGame.setScreen(new PantallaJuego(miJuegoGame));
        } else if (Intersector.overlaps(dedo, botones[1])) {
            //marcadores
            miJuegoGame.setScreen(new PantallaMarcadores(miJuegoGame));
            System.out.println("Pulsando player 2");
        }


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

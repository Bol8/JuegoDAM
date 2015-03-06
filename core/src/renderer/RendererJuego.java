package renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.AssetsJuego;
import com.mygdx.game.Controles;

import controlador.Cronometro;
import modelo.Bullet;
import modelo.Helicoptero;
import modelo.Mundo;
import modelo.Ralf;
import modelo.Tanque;

/**
 * Created by Oscar on 14/02/2015.
 */
public class RendererJuego implements InputProcessor {

    private SpriteBatch batch;
    private OrthographicCamera camara2D;
    private Vector3 temporal;
    private ShapeRenderer shapeRenderer;
    private boolean debugger = true;
    private Mundo mundo;
    private Ralf prota;
    private BitmapFont bitMapFont;
    private BitmapFont bitMapCrono;
    private Cronometro crono;

    public RendererJuego( Mundo mundo){
        this.mundo = mundo;
        prota = mundo.getRalf();
        camara2D = new OrthographicCamera();
        batch = new SpriteBatch();
        temporal = new Vector3();
        shapeRenderer = new ShapeRenderer();
        bitMapFont = new BitmapFont(Gdx.files.internal("texto/Calibri.fnt"),Gdx.files.internal("texto/Calibri.png"),false);
        bitMapCrono = new BitmapFont(Gdx.files.internal("texto/CalibriNegro.fnt"),Gdx.files.internal("texto/CalibriNegro.png"),false);
        Gdx.input.setInputProcessor(this);
    }

    public void renderer(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Cronometro.cuentaSegundos(delta);

        batch.begin();
        dibujarFondo();
        dibujarControles();
        dibujarTanques(batch);
        dibujarHelicopteros(batch);

        prota.draw(batch);
        dibujarBalas(batch);
        dibujarBitFonts(batch);
        batch.end();

        if(debugger){
            debugger();
        }
    }

    private void dibujarTanques(SpriteBatch batch){
        for(Tanque tanque : mundo.getTanques()){
            tanque.draw(batch);
            for(Bullet bullet: tanque.getBullets()){
                bullet.draw(batch);
            }
        }
    }

    private void dibujarHelicopteros(SpriteBatch batch){
        for(Helicoptero helicoptero: mundo.getHelicopteros()){
            helicoptero.draw(batch);
            for(Bullet bullet : helicoptero.getBullets()){
                bullet.draw(batch);
            }
        }
    }

    private void dibujarBalas(SpriteBatch batch){
            for(Bullet bala: prota.getBullets()){
                bala.draw(batch);
            }

       // prota.getBullet().draw(batch);
    }

    private void dibujarFondo(){
        batch.draw(AssetsJuego.textureFondo ,0, 0 , Mundo.ANCHO_MUNDO ,Mundo.ALTO_MUNDO);
    }

    private void dibujarControles(){
        batch.draw(AssetsJuego.getTextureRectFondo(), Controles.RECTANGLE_FONDO.x,Controles.RECTANGLE_FONDO.y,
                Controles.RECTANGLE_FONDO.getWidth() , Controles.RECTANGLE_FONDO.getHeight());
    }

    private void debugger(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLUE);
      /*  for(ElementoMobil emobil : mundo.getEmobile()){
            shapeRenderer.rect(emobil.getPosicion().x , emobil.getPosicion().y , emobil.getTamño().x , emobil.getTamño().y);
        }*/
        shapeRenderer.rect(prota.getPosicion().x , prota.getPosicion().y , prota.getTamaño().x , prota.getTamaño().y);
        shapeRenderer.end();
    }

    private void dibujarBitFonts(SpriteBatch batch){
        bitMapFont.draw(batch, "Score:" + prota.getImpactos(), 0, 20);
        bitMapFont.draw(batch, "V x " + prota.getVidas(), 90, 20);
        bitMapFont.draw(batch, "Salud:" + prota.getSalud(), 140, 20);
        bitMapFont.draw(batch, "Bullets:" + prota.getCargador(), 220, 20);
        if (Cronometro.getSegundos() < 10) {
            bitMapCrono.draw(batch,  Cronometro.getMinutos()+":0"+Cronometro.getSegundos(), 10, Mundo.ALTO_MUNDO - 20);
        } else {
            bitMapCrono.draw(batch,  Cronometro.getMinutos()+":"+Cronometro.getSegundos(), 10, Mundo.ALTO_MUNDO - 20);
        }

    }

    public void resize(int width , int height){
        camara2D.setToOrtho(false, Mundo.ANCHO_MUNDO , Mundo.ALTO_MUNDO);
        camara2D.update();
        batch.setProjectionMatrix(camara2D.combined);      //mirar este método
        shapeRenderer.setProjectionMatrix(camara2D.combined);

    }

    public void dispose(){
        Gdx.input.setInputProcessor(null);
        batch.dispose();

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

        temporal.set(screenX,screenY,0);
        camara2D.unproject(temporal);


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

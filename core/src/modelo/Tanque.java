package modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.AssetsJuego;
import com.mygdx.game.Crono;

/**
 * Created by Oscar on 18/02/2015.
 */
public class Tanque extends ElementoJuego {

    public  enum Estado {NORMAL,TOCADO};
    private Estado estado;
    private Animation tocado;
    private TextureRegion currentFrame;
    private TextureRegion normalFrame;
    private float stateTime;
    private Array<Bullet> bullets;
    public static final int VIDA = 100;
    public static final int VELOCIDAD_MAX = -20;
    public static final int DAÑO_TANQUE = 10;

    public Tanque(Vector2 posicion) {
        setTamaño(new Vector2(50, 60));
        setVelocidad_max(VELOCIDAD_MAX);
        setPosicion(posicion);
        setSalud(VIDA);
        setRectangle(new Rectangle(posicion.x, posicion.y, tamaño.x, tamaño.y));
        normalFrame =  new TextureRegion(new Sprite(AssetsJuego.textureTanque));
        currentFrame = normalFrame;
        bullets = new Array<Bullet>();
        estado = Estado.NORMAL;
        new Crono().start();
        tocado = new Animation(0.08f ,
                new Sprite(new Texture(Gdx.files.internal("vehiculos/tanque_tocado1.png"))),
                new Sprite(new Texture(Gdx.files.internal("vehiculos/tanque_tocado2.png"))),
                new Sprite(new Texture(Gdx.files.internal("vehiculos/tanque_tocado3.png"))));
    }

    @Override
    public void update(float delta) {
        stateTime += delta;
        actualizarAnimacion();
        setPosicion(getPosicion().add(0,velocidad_max * delta));
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(currentFrame, posicion.x , posicion.y , tamaño.x ,tamaño.y);
    }

    private void actualizarAnimacion(){

        switch (estado){
            case NORMAL:
                currentFrame = normalFrame;
                break;

            case TOCADO:
                currentFrame = tocado.getKeyFrame(stateTime,true);
                break;
        }

    }

    public float getPosicionBalaX(){
        return (getPosicion().x + getTamaño().x) - (getTamaño().x/2)-6 ;
    }

    public float getPosicionBalaY(){
        return getPosicion().y;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Array<Bullet> getBullets() {
        return bullets;
    }
}

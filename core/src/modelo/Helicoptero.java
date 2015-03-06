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

/**
 * Created by Oscar on 19/02/2015.
 */
public class Helicoptero extends ElementoJuego {

    private Animation animation;
    private TextureRegion currentFrame;
    private float stateTime;
    private Array<Bullet> bullets;
   public static final int VIDA = 200;
   public static final int VELOCIDAD_MAX = 30;
   public static final int DAÑO_HELICOPTERO = 10;

    public Helicoptero(Vector2 posicion) {
        setTamaño(new Vector2(80,100));
        setVelocidad_max(VELOCIDAD_MAX);
        setPosicion(posicion);
        setSalud(VIDA);
        setRectangle(new Rectangle(posicion.x, posicion.y, tamaño.x, tamaño.y));
        currentFrame =  new Sprite(new Texture(Gdx.files.internal("vehiculos/helicoptero1.png")));
        bullets = new Array<Bullet>();
        animation = new Animation(0.05f ,
                new Sprite(new Texture(Gdx.files.internal("vehiculos/helicoptero1.png"))),
                new Sprite(new Texture(Gdx.files.internal("vehiculos/helicoptero2.png"))),
                new Sprite(new Texture(Gdx.files.internal("vehiculos/helicoptero3.png"))),
                new Sprite(new Texture(Gdx.files.internal("vehiculos/helicoptero4.png"))));
    }

    @Override
    public void update(float delta) {
        stateTime += delta;
        currentFrame = animation.getKeyFrame(stateTime,true);
       //  Audio.iniciarHelicopter();
        setPosicion(getPosicion().add(velocidad_max * delta , 0));
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(currentFrame, posicion.x , posicion.y , tamaño.x ,tamaño.y);
    }

    public float getPosionBalaXIzq(){
       // return (getPosicion().x + getTamaño().x)-(getTamaño().x /2);
        return (getPosicion().x + 14);
    }

    public float getPosicionBalaY(){
        return ((getPosicion().y + getTamaño().y)-10) -((getTamaño().y /2));
    }

    public float getPosicionBalaXDrch(){
        return ((getPosicion().x + getTamaño().x) -16);
    }

    public Array<Bullet> getBullets() {
        return bullets;
    }
}

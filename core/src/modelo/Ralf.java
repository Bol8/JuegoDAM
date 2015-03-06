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
import com.mygdx.game.Controles;

/**
 * Created by Oscar on 15/02/2015.
 */
public class Ralf extends ElementoJuego {

    public enum Estado {UP,DOWN,RIGTH,LEFT,STOP};
    private Estado estado;
    private TextureRegion currentFrame;
    private float stateTime;
    private Animation rigthAnimation;
    private Animation leftAnimation;
    private Animation upAnimation;
    private Animation downAnimation;
    private Vector2 direccion;
    private Array<Bullet> bullets;
    private boolean disparando;
    public static final int SALUD = 100;
    private int cargador;
    private int impactos;
    private int vidas = 3;
    public static  Vector2 posInicio = new Vector2(Mundo.ANCHO_MUNDO/2 , Controles.RECTANGLE_FONDO.getHeight());
   // private Bullet bullet;


    public Ralf() {
        setVelocidad_max(50);
        setTamaño(new Vector2(20, 30));
        setPosicion(posInicio);
        setRectangle(new Rectangle(posicion.x, posicion.y, tamaño.x, tamaño.y));
        setSalud(SALUD);
        estado = Estado.STOP;
        direccion = new Vector2(0,0);
        currentFrame =  new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Clark.png")));
        disparando = false;
        cargador = 300;
        impactos = 0;
        bullets = new Array<Bullet>();
        upAnimation = new Animation(0.15f ,
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Up3.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Up4.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Clark.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Up2.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Up1.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Clark.png"))));

        downAnimation = new Animation(0.15f ,
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Down3.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Down4.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Down5.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Down3.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Down2.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Down1.png"))));

        leftAnimation = new Animation(0.15f ,
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Left3.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Left4.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Left5.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Left3.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Left2.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Left1.png"))));

        rigthAnimation = new Animation(0.15f ,
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Rigth3.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Rigth4.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Rigth5.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Rigth3.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Rigth2.png"))),
                new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Walk_Rigth1.png"))));

    }


    private void actualizarAnimacion(float delta){

       //Calcula el tiempo para cargar el frame que proceda de la animación
       stateTime += delta;

       //Cargamos el frame segun la posición y el momento del juego.
       switch (estado){
           case RIGTH:
               currentFrame = rigthAnimation.getKeyFrame(stateTime,true);
               break;

           case LEFT:
               currentFrame = leftAnimation.getKeyFrame(stateTime , true);
               break;

           case UP:
               currentFrame = upAnimation.getKeyFrame(stateTime , true);
               break;

           case DOWN:
               currentFrame = downAnimation.getKeyFrame(stateTime , true);
               break;

              case STOP:
                  currentFrame = new TextureRegion(new Sprite(new Texture(Gdx.files.internal("protagonista/Ralf_Clark.png"))));
               break;
       }

   }

    public float getPosicionBalaX(){
        return (getPosicion().x + getTamaño().x)-10;
    }

    public float getPosicionBalaY(){
        return getPosicion().y + getTamaño().y;
    }

    public void setDireccionX(float x){
        direccion.x = x;
    }

    public void setDireccionY(float y){
        direccion.y = y;
    }

    public Array<Bullet> getBullets() {
        return bullets;
    }

    public int getCargador() {
        return cargador;
    }

    public boolean isDisparando() {
        return disparando;
    }

    public void setDisparando(boolean disparando) {
        this.disparando = disparando;
    }

    public void recargar(){
        bullets.removeRange(0,99);
    }

    public void disparar(){
        cargador--;
    }

  /*  public Bullet getBullet() {
        return bullet;
    }*/

    @Override
    public void update(float delta) {
        setPosicion(posicion.x + direccion.x *delta, posicion.y + direccion.y * delta);
        actualizarAnimacion(delta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(currentFrame , posicion.x , posicion.y, tamaño.x , tamaño.y);
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public int getImpactos() {
        return impactos;
    }

    public void setImpactos(int impactos) {
        this.impactos = impactos;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
}

package modelo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.AssetsJuego;

/**
 * Created by Oscar on 19/02/2015.
 */
public class Bullet extends ElementoJuego {

    private Texture texture;
    private Ralf ralf;
    private Helicoptero helicoptero;
    private Tanque tanque;
    private static boolean posicionBala;
    private final Vector2 TAMAÑO_BALA_PROTA = new Vector2(10,15);
    private final Vector2 TAMAÑO_BALA_HELICOPTERO = new Vector2(5,30);
    private final Vector2 TAMAÑO_BALA_TANQUE = new Vector2(15,40);


    public Bullet(ElementoJuego element) {
        initComponents(element);
    }



    private void initComponents(ElementoJuego element){
        if(element instanceof Ralf){
            this.ralf = (Ralf) element;
            setTamaño(TAMAÑO_BALA_PROTA);
            setVelocidad_max(300);
           // setPosicion(new Vector2(ralf.getPosicionBalaX(), ralf.getPosicionBalaY()));
            setPosicion(ralf.getPosicionBalaX(), ralf.getPosicionBalaY());
            texture = AssetsJuego.textureShot;

        }else if(element instanceof Helicoptero){
            this.helicoptero = (Helicoptero) element;
            setTamaño(TAMAÑO_BALA_HELICOPTERO);
            setVelocidad_max(-300);
            if (!posicionBala) {
               // setPosicion(new Vector2(helicoptero.getPosionBalaXIzq() , helicoptero.getPosicionBalaY() - getTamaño().y));
                setPosicion(helicoptero.getPosionBalaXIzq() , helicoptero.getPosicionBalaY() - getTamaño().y);
                posicionBala = true;
            } else {
               // setPosicion(new Vector2(helicoptero.getPosicionBalaXDrch() , helicoptero.getPosicionBalaY() - getTamaño().y));
                setPosicion(helicoptero.getPosicionBalaXDrch() , helicoptero.getPosicionBalaY() - getTamaño().y);
                posicionBala = false;
            }

            texture = AssetsJuego.getTextureShotHelicopter();
        }else if(element instanceof  Tanque){
            this.tanque = (Tanque) element;
            setTamaño(TAMAÑO_BALA_TANQUE);
            setVelocidad_max(-300);
            setPosicion(tanque.getPosicionBalaX(),tanque.getPosicionBalaY());
            texture = AssetsJuego.getTextureShotTanque();

        }
    }

    @Override
    public void update(float delta) {
        setPosicion(getPosicion().add(0,velocidad_max * delta));
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture,posicion.x,posicion.y ,tamaño.x , tamaño.y);

    }
}

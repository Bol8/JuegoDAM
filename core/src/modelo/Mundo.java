package modelo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Controles;

/**
 * Created by Oscar on 14/02/2015.
 */
public class Mundo {

    public static final int ANCHO_MUNDO = 300;
    public static  final int ALTO_MUNDO = 500;

    private Ralf Ralf;
    private Array<Tanque> tanques;
    private Array<Helicoptero> helicopteros;

    public Mundo() {
        Ralf = new Ralf();
        tanques = new Array<Tanque>();
        helicopteros = new Array<Helicoptero>();
        añadirTanques();
       // añadirHelicoptero();
    }

    public Ralf getRalf() {
        return Ralf;
    }

    public void añadirTanques(){
        tanques.add(new Tanque(new Vector2(100,500)));
        tanques.add(new Tanque(new Vector2(200,500)));
    }

    public void añadirHelicoptero(){
        helicopteros.add(new Helicoptero(new Vector2(-100,300)));
    }

    public Array<Tanque> getTanques() {
        return tanques;
    }

    public Array<Helicoptero> getHelicopteros() {
        return helicopteros;
    }

}

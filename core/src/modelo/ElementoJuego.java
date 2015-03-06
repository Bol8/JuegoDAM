package modelo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Oscar on 18/02/2015.
 */
public abstract class ElementoJuego {

    protected int velocidad_max;
    protected   Vector2 tamaño;
    protected Vector2 posicion = new Vector2(0,0);
    private Rectangle rectangle;
    private int salud;

    public ElementoJuego() {
       // posicion = new Vector2(0,0);
    }

    public int getVelocidad_max() {
        return velocidad_max;
    }

    public void setVelocidad_max(int velocidad_max) {
        this.velocidad_max = velocidad_max;
    }

    public Vector2 getTamaño() {
        return tamaño;
    }

    public void setTamaño(Vector2 tamaño) {
        this.tamaño = tamaño;
    }

    public Vector2 getPosicion() {
        return posicion;
    }

    public void setPosicion(Vector2 posicion) {
        this.posicion = posicion;
    }

    //Método sobrecargado para actualiar posición
    public void setPosicion(float x ,float y){
        posicion.x = x;
        posicion.y = y;
    }

    public Rectangle getRectangle() {
        return new Rectangle(posicion.x , posicion.y ,tamaño.x ,tamaño.y);
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        System.out.println("Ponemos salud = " + salud);
        this.salud = salud;
    }

    public abstract void update(float delta);

    public abstract void draw(SpriteBatch batch);
}

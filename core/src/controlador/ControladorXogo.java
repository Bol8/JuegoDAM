package controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.mygdx.game.AssetsJuego;
import com.mygdx.game.Audio;
import com.mygdx.game.Controles;

import modelo.Bullet;
import modelo.Helicoptero;
import modelo.Mundo;
import modelo.Ralf;
import modelo.Tanque;
import renderer.RendererJuego;

/**
 * Created by Oscar on 15/02/2015.
 */
public class ControladorXogo {
    Mundo mundo;
    private Ralf protagonista;
    private Tanque tanque;
   // private ControladorBalas controladorBalas;
    private RendererJuego rendererJuego;
    public static boolean disparando;
    private float tiempo = 0;
    private float tiempoH = 0;
    private float tiempoT = 0;
    private float tiempo_impacto = 0;
    private Tanque tank;
    private int pasadasHelicoptero = 0;
    private float cadenciaBalaHelicoptero = 0.5f;
    private final float LIMITE_FONDO = AssetsJuego.textureRectFondo.getHeight() + 25;
    private boolean helicopteroAñadido;

    public ControladorXogo(Mundo mundo) {
        this.mundo = mundo;
        protagonista = mundo.getRalf();
        disparando = false;
        Audio.iniciarMusica();
    }

    public ControladorXogo(Mundo mundo,RendererJuego rendererJuego) {
        this.mundo = mundo;
        this.rendererJuego = rendererJuego;
      //  controladorBalas = rendererJuego.getControladorBalas();
        protagonista = mundo.getRalf();
        Audio.iniciarMusica();
    }

    public void update(float delta){
        controlarTanques(delta);
        controlarHelicopteros(delta);
        controlarProta(delta);
        controlarBulletsProta(delta);
        controlarNumeroElementos();
        procesarEntradas(delta);
    }

    private void controlarTanques(float delta) {

        for(Tanque tanque: mundo.getTanques()){
            tanque.update(delta);
            Audio.tank.play();
                if(tanque.getPosicion().y <= Mundo.ALTO_MUNDO / 2){
                   tanque.setVelocidad_max(20);
                }
                if(tanque.getPosicion().y + tanque.getTamaño().y >= Mundo.ALTO_MUNDO){
                    tanque.setVelocidad_max(-20);
                }

            if(tanque.getPosicion().y <= Mundo.ALTO_MUNDO -(Mundo.ALTO_MUNDO / 4)){
                tiempoT += delta;
                if(tiempoT > 1){
                    tanque.getBullets().add(new Bullet(tanque));
                    Audio.shotTank.play();
                    tiempoT = 0;
                }
            }
            controlarBulletTanque(tanque,delta);
        }
    }

    private void controlarBulletTanque(Tanque tanque, float delta){
        for(Bullet bullet: tanque.getBullets()){
            bullet.update(delta);
            if(bullet.getPosicion().y <= LIMITE_FONDO ){
                tanque.getBullets().removeValue(bullet,true);
            }else if (Intersector.overlaps(protagonista.getRectangle() , bullet.getRectangle())){
                tanque.getBullets().removeValue(bullet,true);
                protagonista.setSalud(protagonista.getSalud() - Tanque.DAÑO_TANQUE);
                if(protagonista.getSalud() < 1){
                    protagonista.setPosicion(Mundo.ANCHO_MUNDO/2 , Controles.RECTANGLE_FONDO.getHeight());
                    protagonista.setVidas(protagonista.getVidas() -1);
                    protagonista.setSalud(Ralf.SALUD);
                }
                System.out.println("Vida: " + protagonista.getSalud());
            }
        }
    }

    private void controlarHelicopteros(float delta){
        for(Helicoptero helicoptero : mundo.getHelicopteros()){
            helicoptero.update(delta);
            Audio.helicopter.play();
            if(helicoptero.getPosicion().x + helicoptero.getTamaño().x >= Mundo.ANCHO_MUNDO){
                helicoptero.setVelocidad_max(-30);
                pasadasHelicoptero++;
                if(pasadasHelicoptero > 3){
                    cadenciaBalaHelicoptero = 0.1f;
                }
            }

            if(helicoptero.getPosicion().x <= 0){
                helicoptero.setVelocidad_max(30);
            }

            if(helicoptero.getPosicion().x >= 40 && helicoptero.getPosicion().x + helicoptero.getTamaño().x <= Mundo.ANCHO_MUNDO -40){
                tiempoH += delta;
                if (tiempoH > cadenciaBalaHelicoptero) {
                    helicoptero.getBullets().add(new Bullet(helicoptero));
                    tiempoH = 0;
                }
            }
            controlarBulletsHelicopter(helicoptero,delta);
        }
    }

    private void controlarBulletsHelicopter(Helicoptero helicoptero, float delta){
        for(Bullet bullet : helicoptero.getBullets()){
            bullet.update(delta);
            if(bullet.getPosicion().y <= LIMITE_FONDO || Intersector.overlaps(protagonista.getRectangle() , bullet.getRectangle())){
                helicoptero.getBullets().removeValue(bullet, true);
            }else if(Intersector.overlaps(bullet.getRectangle(),protagonista.getRectangle())){
                 helicoptero.getBullets().removeValue(bullet,true);
                 protagonista.setSalud(protagonista.getSalud() - Helicoptero.DAÑO_HELICOPTERO);
                if(protagonista.getSalud() < 1){
                    protagonista.setVidas(protagonista.getVidas() -1);
                    protagonista.setSalud(100);

                }
                 System.out.println(protagonista.getSalud());
            }


        }
    }

    private void controlarBulletsProta(float delta) {

        for (Bullet bullet : protagonista.getBullets()) {
            bullet.update(delta);
            for (Tanque tanque : mundo.getTanques()) {
                if (Intersector.overlaps(bullet.getRectangle(), tanque.getRectangle())) {
                    protagonista.getBullets().removeValue(bullet, true);
                    protagonista.setImpactos(protagonista.getImpactos() +10);
                    if(tanque.getSalud() > 0){
                        tanque.setSalud(tanque.getSalud() - 1);
                    }else{
                        mundo.getTanques().removeValue(tanque,true);
                        Audio.tank.stop();
                    }
                }
            }

            for(Helicoptero helicoptero : mundo.getHelicopteros()){
                if(Intersector.overlaps(bullet.getRectangle(),helicoptero.getRectangle())){
                    protagonista.getBullets().removeValue(bullet, true);
                    protagonista.setImpactos(protagonista.getImpactos() + 20);
                    if(helicoptero.getSalud() > 0){
                        helicoptero.setSalud(helicoptero.getSalud() - 1);
                    }else{
                        mundo.getHelicopteros().removeValue(helicoptero,true);
                        Audio.helicopter.stop();
                    }
                }
            }
        }
    }

    private void controlarProta(float delta){
        protagonista.update(delta);

        //Contolamos que no se salga por los laterales
        if(protagonista.getPosicion().x <= 0){
            protagonista.setPosicion(0 , protagonista.getPosicion().y);
        }else if(protagonista.getPosicion().x >= Mundo.ANCHO_MUNDO - protagonista.getTamaño().x){
            protagonista.setPosicion(Mundo.ANCHO_MUNDO - protagonista.getTamaño().x,protagonista.getPosicion().y);
        }

        //Controlamos que no se salga por arriba y por abajo
        if(protagonista.getPosicion().y <= Controles.RECTANGLE_FONDO.getHeight()){
            protagonista.setPosicion(protagonista.getPosicion().x , Controles.RECTANGLE_FONDO.getHeight());
        }else if(protagonista.getPosicion().y >= Mundo.ALTO_MUNDO - protagonista.getTamaño().y){
            protagonista.setPosicion(protagonista.getPosicion().x , Mundo.ALTO_MUNDO - protagonista.getTamaño().y);
        }

      /*  //Controlamos que el protagonista choque con los elementos móbiles
        for(T emobil: mundo.getEmobile()){
            if(Intersector.overlaps(emobil.getRectangle(),protagonista.getRectangle())){
                protagonista.setPosicion(protagonista.getPosicion().x , emobil.getPosicion().y - protagonista.getTamaño().y);
            }
        }*/

    }

    private void controlarNumeroElementos(){
        if(mundo.getTanques().size <= 0 && !helicopteroAñadido ){
            mundo.añadirHelicoptero();
          //  Audio.iniciarHelicopter();
            helicopteroAñadido = true;
        }
    }

    private void procesarEntradas(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            protagonista.setEstado(Ralf.Estado.UP);
            protagonista.setDireccionY(protagonista.getVelocidad_max());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            protagonista.setEstado(Ralf.Estado.DOWN);
            protagonista.setDireccionY(-protagonista.getVelocidad_max());
        }

        if(!Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S) ){
            protagonista.setEstado(Ralf.Estado.STOP);
            protagonista.setDireccionY(0);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            protagonista.setEstado(Ralf.Estado.LEFT);
            protagonista.setDireccionX(-protagonista.getVelocidad_max());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            protagonista.setEstado(Ralf.Estado.RIGTH);
            protagonista.setDireccionX(protagonista.getVelocidad_max());
        }

        if(!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D)){
            protagonista.setDireccionX(0);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && (protagonista.getEstado() == Ralf.Estado.STOP || protagonista.getEstado() == Ralf.Estado.UP)){
             tiempo += delta;
              if(tiempo > 0.1){
                  if (protagonista.getCargador() < 1) {
                      System.out.println("Sin munición");
                    //  Audio.clipEmpty.play();
                    //  protagonista.recargar();
                  } else {
                      protagonista.getBullets().add(new Bullet(protagonista));
                      protagonista.disparar();
                      Audio.gun.play();
                      tiempo = 0;
                  }
              }
            System.out.println(protagonista.getBullets().size);

        }else{
            Audio.gun.stop();
        }

    }


}

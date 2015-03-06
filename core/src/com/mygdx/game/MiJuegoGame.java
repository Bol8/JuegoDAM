package com.mygdx.game;

import com.badlogic.gdx.Game;

import pantallas.PantallaInicio;
import pantallas.PantallaJuego;

/**
 * Created by Oscar on 14/02/2015.
 */
public class MiJuegoGame extends Game {

    @Override
    public void create() {


        AssetsJuego.cargarTexturas();
        Audio.cargarSonidos();
       // setScreen(new PantallaJuego(this));
        setScreen(new PantallaInicio(this));

    }

    @Override
    public void dispose() {
        super.dispose();
        AssetsJuego.liberarTexturas();
        Utiles.imprimirLog("MiJuegoGame" , "dispose" , "hola");
    }


}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by Oscar on 14/02/2015.
 */
public class Utiles {

    private static final String LOG = "Juego2D";


    public static void imprimirLog(String clase, String metodo, String mensaje){
        Gdx.app.log(LOG, clase +":"+ metodo +":"+ mensaje);
    }


}

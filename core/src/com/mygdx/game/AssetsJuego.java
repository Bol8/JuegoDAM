package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Oscar on 15/02/2015.
 */
public class AssetsJuego {

    public static Texture textureRalf;
    public static Texture textureTanque;
    public static  Texture textureFondo;
    public static Texture textureRectFondo;
    public static Texture textureShot;
    public static Texture textureShotHelicopter;
    public static Texture textureShotTanque;


    public static  void cargarTexturas(){
        textureTanque = new Texture(Gdx.files.internal("vehiculos/tanque.png"));
        textureFondo = new Texture(Gdx.files.internal("escenario.png"));
        textureRectFondo = new Texture(Gdx.files.internal("rectNegro.jpg"));
        textureShot = new Texture(Gdx.files.internal("protagonista/shot3.png"));
        textureShotHelicopter = new Texture(Gdx.files.internal("vehiculos/shot4.png"));
        textureShotTanque = new Texture(Gdx.files.internal("vehiculos/shot5.png"));

    }

    public static void liberarTexturas(){
       // textureRalf.dispose();
    }

    public static Texture getTextureRalf() {
        return textureRalf;
    }

    public static Texture getTextureTanque() {
        return textureTanque;
    }

    public static Texture getTextureFondo() {
        return textureFondo;
    }

    public static Texture getTextureRectFondo() {
        return textureRectFondo;
    }

    public static Texture getTextureShotHelicopter() {
        return textureShotHelicopter;
    }

    public static Texture getTextureShotTanque() {
        return textureShotTanque;
    }



}

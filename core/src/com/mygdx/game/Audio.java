package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.sun.javafx.tk.Toolkit;

import javafx.concurrent.Task;

/**
 * Created by Oscar on 20/02/2015.
 */
public class Audio {

    public static Music helicopter;
    public static Music music;
    public static Music gun;
    public static Music tank;
    public static Sound shotTank;
    public static Sound clipEmpty;
    private static Timer.Task sonidoHelicoptero;

    public static void cargarSonidos(){
        helicopter = Gdx.audio.newMusic(Gdx.files.internal("sonido/helicopter.wav"));
        gun = Gdx.audio.newMusic(Gdx.files.internal("sonido/shot2.wav"));
      //  clipEmpty = Gdx.audio.newSound(Gdx.files.internal("sonido/ClipEmpty_Rifle.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sonido/music.mp3"));
        tank = Gdx.audio.newMusic(Gdx.files.internal("sonido/tank2.mp3"));
        shotTank = Gdx.audio.newSound(Gdx.files.internal("sonido/shot_tank.wav"));


    }


    public static void iniciarHelicopter(){
      //  sonidoHelicoptero = new Timer.Task() {
        //    @Override
        //    public void run() {
         //       System.out.println("Sonando");
                helicopter.play();
         //   }
       // };
      //  sonidoHelicoptero.run();
    }

    public static void iniciarMusica(){
        music.play();
    }

    public static void iniciarTanque(){
       tank.play();
    }
}

package com.mygdx.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MiJuegoGame;

import java.awt.DisplayMode;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Ikari Warriors";
       /* Graphics.DisplayMode displayMode = LwjglApplicationConfiguration.getDesktopDisplayMode();
        config.width = displayMode.width;
        config.height = displayMode.height;*/
       // config.fullscreen = true;
        config.width = 300;
        config.height = 500;
       // config.addIcon("Ralf_Clark.png",FileType.Internal);
       // config.overrideDensity = 240;

		new LwjglApplication(new MiJuegoGame(), config);

	}
}

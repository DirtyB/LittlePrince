package com.whiletrue.littleprince.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.whiletrue.littleprince.LittlePrinceGame;

public class DesktopLauncher  {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new LittlePrinceGame(), config);
	    }

}






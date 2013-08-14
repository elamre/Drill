package com.drill.main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static Texture background;
	public static TextureRegion backgroundRegion;
	public static Texture startButton;
	public static TextureRegion startButtonRegion;
	public static Texture items;
	public static TextureRegion playerRegion;
	public static TextureRegion xBlockRegion;
	public static TextureRegion yBlockRegion;
	public static TextureRegion jBlockRegion;
	public static TextureRegion zBlockRegion;
	public static TextureRegion arrowButton;
	public static TextureRegion digButton;

	public static Sprite playerSprite;
	public static Sprite xBlockSprite;
	public static Sprite yBlockSprite;
	public static Sprite fBlockSprite;
	public static Sprite zBlockSprite;
	public static Sprite arrowButtonSprite;
	public static Sprite digButtonSprite;
	//public static Font font;

	public static void load() {
		background = new Texture(Gdx.files.internal("background.png"));
		background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		backgroundRegion = new TextureRegion(background, 0, 0, 288, 448);

		startButton = new Texture(Gdx.files.internal("startbutton.png"));
		startButton.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		startButtonRegion = new TextureRegion(startButton, 0, 0, 150, 100);

		items = new Texture(Gdx.files.internal("items.png"));
		items.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		playerRegion = new TextureRegion(items, 0, 0, 32, 32);
		xBlockRegion = new TextureRegion(items, 32, 0, 32, 32);
		yBlockRegion = new TextureRegion(items, 64, 0, 32, 32);
		jBlockRegion = new TextureRegion(items, 32, 96, 32, 32);
		zBlockRegion = new TextureRegion(items, 0, 96, 32, 32);
		arrowButton = new TextureRegion(items, 0, 32, 64, 64);
		digButton = new TextureRegion(items, 64, 32, 64, 64);
		//font = new Font(items, 160, 0, 16, 16, 16);

	}

	public static void reload() {
		//background.reload();

	}
}
package com.space_rescue.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class SpaceRescue extends ApplicationAdapter {
	private Random rn = new Random();
	private SpriteBatch batch;
	private Texture img;

	private Vector2 initalShipPosition;

	private Vector2 initialAstronautPosition;
	private Vector2 initialAstronautVelocity;

	private ship playerShip;
	private astronaut bob;

	private Sprite shipSprite;
	private Sprite astronautSprite;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("img.png");

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		shipSprite = new Sprite(img);
		astronautSprite = new Sprite(img);

		initalShipPosition = new Vector2(0, 0);
		initialAstronautPosition = new Vector2( rn.nextInt(10), rn.nextInt(10) );
		initialAstronautVelocity = new Vector2( rn.nextInt(10), rn.nextInt(10) );
		playerShip = new ship( initalShipPosition );
		bob = new astronaut( initialAstronautPosition, initialAstronautVelocity );
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// update
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
				playerShip.changeDirection(new Vector2( 1, 0 ));
				playerShip.accelerate();
			}
			else {
				playerShip.changeDirection(new Vector2( 1, 0 ));
				playerShip.accelerate();
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
				playerShip.changeDirection(new Vector2( -1, 0 ));
				playerShip.accelerate();
			}
			else {
				playerShip.changeDirection(new Vector2( -1, 0 ));
				playerShip.accelerate();
			}
		}

		// todo: getter method
		shipSprite.setPosition( playerShip.position.x, playerShip.position.y );
		astronautSprite.setPosition( bob.position.x, bob.position.y );

		playerShip.updatePosition();
		playerShip.applyDrag();
		bob.updatePosition();

		batch.begin();
		shipSprite.draw(batch);
		astronautSprite.draw(batch);
		batch.end();
	}
}

class ship {
	Vector2 direction = new Vector2();
	Vector2 position = new Vector2();
	Vector2 velocity = new Vector2();

	public ship ( Vector2 startPosition ) {
		position = startPosition;
	}

	public void accelerate () {
		velocity.add(direction);
	}

	public void applyDrag () {
		velocity.sub(velocity.x/10, velocity.y/10);
	}

	public void changeDirection ( Vector2 newDirection ) {
		direction = newDirection;
	}

	public void updatePosition () {
		position.add( velocity );
	}
}

class astronaut {
	Vector2 position = new Vector2();
	Vector2 velocity = new Vector2();

	public astronaut ( Vector2 startPosition, Vector2 startVelocity ) {
		position = startPosition;
		velocity = startVelocity;
	}

	public void updatePosition () {
		position.add(velocity);
	}
}

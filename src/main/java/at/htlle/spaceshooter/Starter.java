package at.htlle.spaceshooter;

import java.util.ArrayList;
import java.util.List;

import at.htlle.spaceshooter.gameobjects.Asteroid;
import at.htlle.spaceshooter.gameobjects.Rocket;
import at.htlle.spaceshooter.gameobjects.SpaceShip;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Starter extends Application implements EventHandler<KeyEvent>
{

	// Raumschiff erzeugen und hinpinseln
	SpaceShip enterprise = new SpaceShip(100,100);
	List<Asteroid> asteroiden = new ArrayList<>();
	List<Rocket> raketen = new ArrayList<>();
	
	final int WIDTH = 1024;
	final int HEIGHT = 768;
	
	public static void main(String[] args)
	{
		Application.launch(Starter.class, args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		primaryStage.setTitle("SpaceShooter - Hutter");
		
		Group root = new Group();
		
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(this);
		root.getChildren().add(canvas);
		
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		
		// Raumschiff hinmalen
		enterprise.paint(canvas.getGraphicsContext2D());

		// Asteroiden erzeugen
		for(int i = 0; i < 25; i++)
		{
			Asteroid a = new Asteroid(0, 0, 0);
			a.reposition(WIDTH/2, HEIGHT);
			asteroiden.add(a);
		}
		
		// GameLoop
		new AnimationTimer()
		{
			@Override
			public void handle(long currentNanoTime) 
			{
				canvas.getGraphicsContext2D().clearRect(0,0,WIDTH,HEIGHT);
				enterprise.paint(canvas.getGraphicsContext2D());
				
				// Asteroiden Handling
				for(Asteroid ast : asteroiden)
				{
					ast.paint(canvas.getGraphicsContext2D());
					ast.move();
					
					// Alte Asteroiden neu positionieren
					if(ast.isVisible() == false)
					{
						ast.reposition(WIDTH, HEIGHT);
					}
					
				}
				
				// Raketen Handling
				for(Rocket r : raketen)
				{
					r.paint(canvas.getGraphicsContext2D());
					r.move();
				}
				
				// Aus dem viewport geflogene Raketen entfernen
				List<Rocket> zuLoeschendeRaketen = new ArrayList<>();
				for(Rocket r : raketen)
				{
					if(r.isVisible() == false)
					{
						zuLoeschendeRaketen.add(r);
					}
				}
				
				// Tats�chliches L�schen der Raketen aus der ursprungsliste
				raketen.removeAll(zuLoeschendeRaketen);
				
				
			}
		}.start();
	}

	/**
	 * K�mmert sich um die Tastatureingaben
	 */
	@Override
	public void handle(KeyEvent evnt) 
	{
		System.out.println(evnt.getCode().getName());
		
		int accellerator = 1;
		// Wir pr�fen ob SHIFT gedr�ckt ist
		if(evnt.isShiftDown())
		{
			accellerator = 3;
		}
		
		switch(evnt.getCode().getName())
		{
			case "Up": 
				enterprise.moveUp(3 * accellerator);
				break;
				
			case "Down":
				enterprise.moveDown(3 * accellerator);
				break;

			case "Left":
				enterprise.moveLeft(3 * accellerator);
				break;

			case "Right":
				enterprise.moveRight(3 * accellerator);
				break;

			case "Space":
				Rocket r = enterprise.fire();
				raketen.add(r);
				break;
				
		}
		
	}
}

package at.htlle.spaceshooter.gameobjects;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class Asteroid 
{
	private int x;
	private int y;
	private int speed;
	private String asteroidString ="*";
	private int size = 12;
	
	/**
	 * ctor
	 * @param x Startposition X 
	 * @param y Startposition Y
	 * @param speed Geschwindigkeit des Asteroiden 
	 */
	public Asteroid(int x, int y, int speed)
	{
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.size += new Random().nextInt(10);
	}
	
	/**
	 * Bewegt den Asteroiden weiter
	 */
	public void move()
	{
		this.x -= this.speed;
	}

	/**
	 * �berschreiben der toString Methode, damit wir den Asteroiden bequem
	 * ausgeben k�nnen
	 */
	public String toString()
	{
		return "Asteroid(" + this.x + "|" + this.y + ")";
	}
	
	/**
	 * Gibt true zur�ck wenn der Asteroid zerst�rt werden kann weil er nicht
	 * mehr sichtbar ist
	 * @return
	 */
	public boolean isVisible()
	{
		return (this.x > 0);
	}
	
	/**
	 * Zeichnet den Asteroiden in den �bergebenen GraphicsContext
	 * @param gc
	 */
	public void paint(GraphicsContext gc)
	{
		gc.setFont(Font.font("Verdana", this.size));
		gc.fillText(this.asteroidString, this.x, this.y);
	}

	/**
	 * Repositioniert den Asteroiden
	 * @param width
	 * @param height
	 */
	public void reposition(int width, int height) 
	{
		Random rnd = new Random();
		
		this.x = width + rnd.nextInt(width);
		this.y = rnd.nextInt(height);
		this.speed = 1 + rnd.nextInt(3);
	}
}

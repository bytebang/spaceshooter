package at.htlle.spaceshooter.gameobjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class Rocket 
{
	private int x;
	private int y;
	private int speed;
	private String rocketString ="~=>";
	
	private int[][] unnuetzerSpeicher = new int[1000][1000]; 
	
	/**
	 * ctor
	 * @param x Startposition X 
	 * @param y Startposition Y
	 * @param speed Geschwindigkeit der Rakete 
	 */
	public Rocket(int x, int y, int speed)
	{
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.unnuetzerSpeicher[0][0] = this.x;
	}
	
	/**
	 * Bewegt die Rakete weiter
	 */
	public void move()
	{
		this.x += this.speed;
	}

	/**
	 * �berschreiben der toString Methode, damit wir den Asteroiden bequem
	 * ausgeben k�nnen
	 */
	public String toString()
	{
		return "Rocket(" + this.x + "|" + this.y + ")";
	}
	
	/**
	 * Gibt true zur�ck wenn der Asteroid zerst�rt werden kann weil er nicht
	 * mehr sichtbar ist
	 * @return
	 */
	public boolean isVisible()
	{
		return (this.x < 600);
	}
	
	/**
	 * Zeichnet die Rakete in den �bergebenen GraphicsContext
	 * @param gc
	 */
	public void paint(GraphicsContext gc)
	{
		gc.setFont(Font.font("Verdana", 12));
		gc.fillText(this.rocketString, this.x, this.y);
	}

}


import java.util.Random;
import java.util.concurrent.Semaphore;
public class Grilo {
    	public Grilo(String name, int time) {
    		this.griloName = name;
    		this.teamID = time;
    		this.controller = new Semaphore(3);
    	}
    	Semaphore controller;
        Times time[] = null;
    	String griloName;
    	int distancia;
    	Random rand = new Random();
    	int currentPosition = 0;
    	int pulos = 0;
    	boolean finalizou = false;
    	int teamID = 0;
    	boolean first = false;
    	
    	public synchronized void Jump() {
    		try {
    			controller.acquire();
    			distancia = rand.nextInt(70);
    			currentPosition += distancia;
    			pulos += 1;
    			Main.time[teamID].Update(distancia);
    			System.out.println("O " + griloName + " pulou " + distancia + "cm e já percorreu : " 
    			+ currentPosition + "cm");
    		}
    		catch(InterruptedException e1) {
                e1.printStackTrace();
    		}
    		finally {		
    			controller.release();
    		}
    	}
    	public void Vencedor() {
    		first = true;
    		Main.time[teamID].vencedor = true;
    	}
    }
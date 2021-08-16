import java.util.concurrent.Semaphore;
public class Times {
	Semaphore mutex;
	public Times(int id) {
	this.id = id;
	this.mutex = new Semaphore(1);
	}
	
	int id = 0;
    boolean vencedor = false;
    int pulos = 0;
    int Distancia;
  

	public synchronized void Update(int distance) {
		try {
		mutex.acquire();
		pulos += 1;
		Distancia += distance;
		}
		catch(InterruptedException e1) {
            e1.printStackTrace();
		}
		finally {
		mutex.release();
		}
	}
	public void Final() {

		System.out.println("Time " + (id+1) + " : Total de pulos: " + pulos + " Distância Percorrida: " + Distancia + "cm");
		
	}
	public void MostrarVencedor() {
		
		if(vencedor) {
			System.out.println("Time " + (id+1) + " foi o vencedor");

			System.out.println("Fim da Corrida!");
		}
	}
}


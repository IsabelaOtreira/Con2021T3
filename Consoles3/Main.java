import java.util.Scanner;

public class Main {
	static int grilosfinal = 0;
	static int chegada = 200;
	public static Times[] time;
	static int Qtimes = 0;
	static boolean venceu = false;
	static int colocacao = 1;		
	
	public static void main(String[] args) {
		        int Qtgrilos = 0;
		        int timesdivididos = 2;
		        
		        Scanner scanner = new Scanner(System.in);
		        System.out.println("Quantos grilos se juntarão a essa corrida? ");  
		        Qtgrilos = scanner.nextInt();
		        System.out.println("Grilos na corrida " + Qtgrilos);
		        System.out.println("Qual será a distância da corrida? ");
		        
		        
		        chegada = scanner.nextInt();

		        CtlThread threads[] = null;
		        Grilo[] cricketMain = new Grilo[Qtgrilos];
		        scanner.close();
		        

		        if(Qtgrilos % timesdivididos == 0) {
		        	Qtimes = (int)(Qtgrilos / timesdivididos);
		        }
		        else {
		        	Qtimes = (int) Math.ceil(Qtgrilos / timesdivididos); 
		        }

		        time = new Times[Qtimes];      
		        
				System.out.println("Times : " + Qtimes );
				
		        for(int i = 0; i < Qtgrilos; i++) {
		        	
		        	cricketMain[i] = new Grilo("Grilo_" + (i+1),  i % Qtimes);;
		        	
		        }
		        for(int i = 0; i < Qtimes; i++) {
		        	
		        time[i] = new Times(i);
		        
		        }
		        IniciarCorrida(Qtgrilos, threads, cricketMain);	

			}
			
		    public static void IniciarCorrida(int Qtgrilos, CtlThread[] thread, Grilo[] grilo) {
		    	thread = new CtlThread[Qtgrilos];
		    	
		    	for(int i = 0; i < Qtgrilos; i++) 
		    	{
		    		thread[i] = new CtlThread(grilo[i]);
		    		thread[i].start();
		    	}
		        for (int i = 0; i < thread.length; i++) 
		        {
		          try 
		          {
		            thread[i].join();
		          } 
		          catch (InterruptedException e ) 
		          { 	
		              e.printStackTrace();
		          }
		        }
		        
		    	while(grilosfinal < Qtgrilos) {
		    	Corrida(Qtgrilos, thread, grilo);    	
		    	}
				for(int i = 0; i < Qtimes; i++) 
				{
					time[i].Final();
				}
				for(int i = 0; i < Qtimes; i++) 
				{
					time[i].MostrarVencedor();
					
				}
		    }

		    public static void Corrida(int Qtgrilo, CtlThread[] thread, Grilo[] grilo) {
		    	for(int i = 0; i < Qtgrilo; i++) 
		        {
		    		if(grilo[i].finalizou != true)
		    		{
		    			if(grilo[i].currentPosition >= chegada) 
		    			{	
		    			grilosfinal ++;
		    			System.out.println("O " + grilo[i].griloName +" foi o "+ colocacao +"º colocado com " + grilo[i].pulos + " pulos.");
		    			colocacao++;
		    			grilo[i].finalizou = true;
		    			if(!venceu) {
		    				grilo[i].Vencedor();
		    				venceu = true;
		    				}
		    			}
		    			else 
		    			{
		    				thread[i].run();
		        		}
		    		}
		        }
		            for (int i = 0; i < thread.length; i++) 
		            {
		              try 
		              {
		                thread[i].join();
		              } 
		              catch (InterruptedException e ) 
		              {
		                  e.printStackTrace();
		              }
		            }
		    }
		}
public class CtlThread extends Thread {
	Grilo grilos;

    public CtlThread(Grilo grilos) 
    {
        this.grilos = grilos;
    }

    @Override
    public void run() 
    {
    	grilos.Jump();
    }

}

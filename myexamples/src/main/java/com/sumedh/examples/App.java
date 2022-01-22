package com.sumedh.examples;
/**
 * Hello world!
 *
 */

class MyPinger implements Runnable{
	//prints ping if value is true
	App myinst;
	MyPinger(App inst){myinst=inst;}
	public void run(){
		while(!Thread.interrupted()){
			synchronized(myinst.mylock) {
				try{
				while(!myinst.flag)
					
						myinst.mylock.wait();
					
				myinst.flag=false;
				System.out.println("Pinging..");
				myinst.mylock.notifyAll();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	}
	
}

class MyPonger implements Runnable{
	//prints pong if value is false
	App myinst;
	MyPonger(App inst){myinst=inst;}
	public void run(){
		while(!Thread.interrupted()){
			synchronized(myinst.mylock) {
				try {
				while(myinst.flag)
					
						myinst.mylock.wait();
					
				myinst.flag=true;
				System.out.println("Ponging..");
				
				myinst.mylock.notifyAll();
				Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}
	
}

public class App 
{
	boolean flag=false;Object mylock=new Object();
    public static void main( String[] args )
    {
    	System.out.println( "Hello World!" );
    	App myobj=new App();
    	Thread t1=new Thread(new MyPinger(myobj));
    	Thread t2=new Thread(new MyPonger(myobj));
    	t1.start();
    	t2.start();
        //System.out.println( "Hello World!" );
    }
}

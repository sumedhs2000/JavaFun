package com.sumedh.examples;

public class MyEvenOdd {

	Object thelock=new Object();
	boolean evenflag=false;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyEvenOdd cont=new MyEvenOdd();
		MyEvenOdd.MyEven o1=cont.new MyEven();
		Thread t1=new Thread(o1);
		t1.start();
		MyEvenOdd.MyOdd o2=cont.new MyOdd();
		Thread t2=new Thread(o2);
		t2.start();
		

	}
	
	class MyEven implements Runnable
	{
		Object obj;
		int i=2;
		MyEven(){}
		public void run(){
			while(!Thread.interrupted()){
				try{
					synchronized(thelock){
						while(!evenflag) thelock.wait();
						System.out.println("i="+i);
						i=i+2;
						evenflag=false;
						thelock.notifyAll();
						Thread.sleep(1000);
					}
				}
				catch(Exception e){}
			}
		}
	}
	
	class MyOdd implements Runnable
	{
		Object obj;
		int i=1;
		MyOdd(){}
		public void run(){
			while(!Thread.interrupted()){
				try{
					synchronized(thelock){
						while(evenflag) thelock.wait();
						System.out.println("i="+i);
						i=i+2;
						evenflag=true;
						thelock.notifyAll();
						Thread.sleep(1000);
					}
				}
				catch(Exception e){}
			}
		}
	}

}

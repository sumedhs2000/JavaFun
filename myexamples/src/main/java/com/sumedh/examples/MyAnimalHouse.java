package com.sumedh.examples;

import java.util.LinkedList;
import java.util.List;

public class MyAnimalHouse {

	List<AnimalHolder> catList=new LinkedList<>();
	List<AnimalHolder> dogList=new LinkedList<>();
	
	public void enqueueAnimal(Animal animal){
		int ts=TSGen.getTS();
		AnimalHolder holder=new AnimalHolder();
		holder.timestamp=ts;
		holder.myanimal=animal;
		if(animal instanceof Cat) catList.add(holder);
		else dogList.add(holder);	
		
	}
	
	public Animal dequeAnyAnimal(){
		AnimalHolder cat=null,dog=null;
		if(!catList.isEmpty())
			cat=catList.get(0);
		if(!dogList.isEmpty())
			dog=dogList.get(0);
		
		if(cat!=null && dog!=null){
			if(cat.timestamp<dog.timestamp){
			cat=catList.remove(0);
			return cat.myanimal;
		}
		else{
			dogList.remove(0);
			return dog.myanimal;
		}
		}
		else if(cat!=null) return cat.myanimal;
		else return dog.myanimal;
		
	}
	
	public Animal dequeDog(){
		return null;
	}
	
	public static void main(String [] args){
		MyAnimalHouse house=new MyAnimalHouse();
		house.enqueueAnimal(new Cat());
		house.enqueueAnimal(new Dog());
		house.enqueueAnimal(new Cat());
		Animal a=house.dequeAnyAnimal();
		
		a.print();
		a=house.dequeAnyAnimal();
		a.print();
	}
	
	private class AnimalHolder{
		Animal myanimal;
		int timestamp;
		
	}
	
}

abstract class Animal{
	public abstract void print();
}

class Dog extends Animal{
	public void print(){
		System.out.println("am dog here");
	}
}

class Cat extends Animal{
	public void print(){
		System.out.println("am cat here");
	}
}

class TSGen{
	private static int counter=0;
	static int getTS(){return counter++;}
}
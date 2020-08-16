package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class TestComp implements Testing{
public TestComp(){
	System.out.println("am created now in ctor");
}

public void printing() {
	// TODO Auto-generated method stub
	System.out.println("in printing");
}

}

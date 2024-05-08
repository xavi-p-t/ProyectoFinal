package main;

import partPlanetas.*;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		planeta mipla = new planeta(15000,10000);
		mipla.setArray();
		try {
			mipla.newLigthHunter(10);
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

}

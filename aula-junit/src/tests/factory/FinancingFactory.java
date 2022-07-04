package tests.factory;

import entities.Financing;

public class FinancingFactory {
	
	public static Financing createValid() {
		return new Financing(100000.0, 2000.0, 80);
	}
	
	public static Financing createValid(double totalAmount) {
		return new Financing(totalAmount, 2000.0, 80);
	}
	
	public static Financing createInvalid() {
		return new Financing(100000.0, 2000.0, 20);
	}

}

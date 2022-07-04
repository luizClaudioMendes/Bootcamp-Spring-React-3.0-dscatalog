package tests.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entities.Financing;
import tests.factory.FinancingFactory;

public class FinancingTests {

	@Test
	public void constructorShouldCreateObjectWhenValidData() {
		double totalAmount = 100000.0;
		double income = 2000.0;
		int months = 80;

		Financing fin = new Financing(totalAmount, income, months);

		Assertions.assertNotNull(fin);
		Assertions.assertEquals(2000.0, fin.getIncome());
		Assertions.assertEquals(totalAmount, fin.getTotalAmount());
		Assertions.assertEquals(months, fin.getMonths());
	}

	@Test
	public void constructorShouldThrowExceptionWhenInvalidData() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			double totalAmount = 100000.0;
			double income = 2000.0;
			int months = 20;

			new Financing(totalAmount, income, months);

		});

	}

	@Test
	public void setTotalAmountShouldUpdateTotalAmount() {
		Financing fin = FinancingFactory.createValid();

		fin.setTotalAmount(80000.0);

		Assertions.assertEquals(80000.0, fin.getTotalAmount());
	}

	@Test
	public void shouldThrowExceptionWhenUpdatingFinancingWithInvalidData() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Financing fin = FinancingFactory.createValid();

			fin.setTotalAmount(120000.0);

		});
	}

	@Test
	public void setIncomeShouldUpdateIncome() {
		Financing fin = FinancingFactory.createValid();

		fin.setIncome(3000.0);

		Assertions.assertEquals(3000.0, fin.getIncome());
	}

	@Test
	public void setIncomeShouldThrowExceptionWhenInvalidIncome() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Financing fin = FinancingFactory.createValid();

			fin.setIncome(1000.0);
		});
	}

	@Test
	public void setMonthsShouldUpdateMonths() {
		Financing fin = FinancingFactory.createValid();

		fin.setMonths(81);

		Assertions.assertEquals(81, fin.getMonths());
	}

	@Test
	public void setMonthsShouldThrowExceptionWhenUpdatingFinancingWithInvalidMonths() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Financing fin = FinancingFactory.createValid();

			fin.setMonths(79);
		});
	}

	@Test
	public void entryShouldCalculateCorrectEntry() {
		double totalAmount = 100000.0;
		double expectedValue = totalAmount * 0.2;
		Financing fin = FinancingFactory.createValid(totalAmount);

		double entry = fin.entry();

		Assertions.assertEquals(expectedValue, entry);
	}

	@Test
	public void quotaShouldCalculateCorrectQuota() {
		double totalAmount = 100000.0;
		double expectedValue = 1000.0;
		Financing fin = FinancingFactory.createValid(totalAmount);

		double entry = fin.quota();

		Assertions.assertEquals(expectedValue, entry);
	}

}

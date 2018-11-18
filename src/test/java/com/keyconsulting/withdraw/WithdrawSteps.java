package com.keyconsulting.withdraw;

import java.math.BigDecimal;

import com.keyconsulting.withdraw.model.Client;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class WithdrawSteps {

	private Client client;

	@Given("^an existing client named \"pierre-jean\" with 100.0 EUR in his account$")
	public void client() throws Throwable {
		client = new Client("pierre-jean", BigDecimal.valueOf(100));
	}

	@When("^he withdraws 10.0 EUR from his account$")
	public void withdraw() throws Throwable {
		client.withdraw(BigDecimal.valueOf(10));
	}

	@Then("^the new balance is 90.0 EUR$")
	public void it_should_have_been_a_success() throws Throwable {
		// J'ai pas utilisé Assert.assertEquals() puisqu'elle est deprecated
		Assert.assertArrayEquals(new double[] { 90 }, new double[] { client.balance().doubleValue() }, 0);
	}
}

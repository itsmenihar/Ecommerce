@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file
  
Background:
Given I landed on Ecommerce page

  @tag1
  Scenario: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I clicked on product cetagory options <headerName> and add the product <productName> to cart
    And Checkout <productName> and submit the order
    Then <message> message is displayed on conformation page


    Examples: 
      | name 						| password |	headerName	| productName  |					message							|
      | admin@gmail.com |  12345 	 | Phones & PDAs|		iPhone 		 | Your order has been placed!	|

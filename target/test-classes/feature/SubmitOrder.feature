#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@submitOrder
Feature: Submit Order As a user 
I want to place an order 
So that I can purchase products
  
  Background:
  Given I am on the landing page
   
  @Regression
  Scenario Outline: SubmitOrder - happy path Test
    When I login with username <userName> and password <password> 
    And I add product <productName> to cart
    When I checkout <productName> and Submit the order
    Then I should see a confirmation message "Thankyou for the order."
    

    Examples: 
    | userName           | password      | productName |
    | jimmydoe@gmail.com | Santorini@123 | ZARA COAT 3 |
    #| jimmydoe@gmail.com  |incorrect@123 | ZARA COAT 3 |

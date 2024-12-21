@TestErrorScenario
Feature: Login Error Validation feature
  As a user
  I want to see an error message when I enter incorrect credentials
  So that I know the login attempt was unsuccessful

  @incorrectLogin
  Scenario Outline: Validate error message for incorrect login
    Given I am on the landing page
    When I login with username <userName> and password <password> 
    Then I should see an error message "Incorrect email or password."
    
     Examples: 
    | userName           | password      | productName |
    | jimmydoe@gmail.com | Santorii@123 | ZARA COAT 3 |
   
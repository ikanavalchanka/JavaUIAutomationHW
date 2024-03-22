Feature:  Testing of LogIn page
  Background:
    Given set up driver
  @Test1
  Scenario: Negative test : Verify that the system does not accept incorrect passwords

    Given User is on LogIn page
    When  I enter email
    And  I enter incorrect password
    And  I click on the SignIn button
    Then I see an error message above the password string
@Test2
  Scenario: Negative test : heck if the system properly handles submitting login form with empty fields
    Given User is on LogIn page
    When  I click on the SignIn button
    Then I see an error message above the email and password strings
  @Test3
  Scenario: Verify Registration button redirects the user to the registration page
    Given User is on LogIn page
    When I click on the Registration button
    Then Registration page opens
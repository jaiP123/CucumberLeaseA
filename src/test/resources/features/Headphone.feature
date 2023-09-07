Feature: Add to Cart and checkout

  As a user of Amazon website
  I want to login
  So that I can add items to cart and checkout

  Background: user is logged in on amazon website
    Given I am on Amazon website
    When I submit username and password
    Then I should be logged in

  @smoke
  Scenario: Search headphones and add it to the cart
    Given I click on the "Headphone" search filter
    When I add first two results to cart
    Then cart should display added items
    And I logout from amazon website


  @smoke
  Scenario: checkout cart items
    Given I click on cart
    And cart contains two headphones
    When I add click on checkout
    Then cart items should be checkout
    And I logout from amazon website
# language: en
Feature: Movie Management
  As a movie platform user
  I want to create movie
  So that I can manage my movie content efficiently

  # Create Movie successfully
  Scenario: Movie creation successful
    Given I send the following information:
      | name            |      description        | path |
      | My Travel Movie | Traveling in Mauritius .| F:\\ |
    When I save a movie with those information
    Then The movie should be saved successfully
    And the status is 201

  # Fail to Create Movie
  Scenario: Movie creation failed
    When I save a movie with the information "Hero" and "Description"
    Then The movie is expected to fail
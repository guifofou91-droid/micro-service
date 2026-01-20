Feature: Create movie
  As a user, I need to be able to create a video

  Scenario: Create movie with the valid information
    Given The user send the following information:
      | name    |            description               | path |
      | IronMan | Genie, armor, Tony Stark, superhero. | F:\\ |
    When The user save the movie
    Then The film is successfully created with the information provided and with an identity.
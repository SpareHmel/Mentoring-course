@all
Feature: Basic mail functions

  Background:
    Given I open mail site 'MAIL_LINK'
    And I login to the account
    Then Browser title equals 'TITLE'

  Scenario: Create draft and send mail
    When I start writing letter
    And I fill in the fields using template with the desired values
    And I save mail as draft
    And I close the message window
    And I open Drafts page
    Then Drafts addressee is displayed
    And Drafts subject contains 'MAIL_SUBJECT'
    When I select draft and send mail
    And I open Sent page
    Then Sent page contains 'MAIL_SUBJECT'
    When I open Drafts page
    Then I see in the Drafts box 'EMPTY_DRAFTS_MESSAGE'

  Scenario: Save mail as template and send it
    When I start writing letter
    And I fill in the fields using template with the desired values
    And I save mail as template
    And I open Template page
    And I open template
    And I send mail
    And I open Sent page
    Then Mail details text contains 'MAIL_SUBJECT'

  Scenario Outline: Save mail as draft then delete it
    When I start writing letter
    And I create mail with the desired '<template>'
    And I save mail as draft
    And I open Drafts page
    And I delete saved draft
    Then I see in the Drafts box 'EMPTY_DRAFTS_MESSAGE'
    Examples:
      | template           |
      | simpleMail         |
      | mailWithoutSubject |
      | mailWithCustomBody |

  Scenario: Start writing mail and move addressee to the copy field
    When I start writing letter
    And I fill in the fields using template with the desired values
    And I move addressee to the copy field
    Then I addressee is displayed in the copy field
    And I close the message window

  Scenario: Scrolling to the last sent mail
    When I open Sent page
    And I scroll to the last sent mail
    Then the last sent mail is displayed



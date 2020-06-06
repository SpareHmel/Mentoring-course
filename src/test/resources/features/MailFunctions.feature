@all
Feature: Basic mail functions

  Background:
    Given I open 'https://mail.ru' page
    And I login to the account
    Then Browser title equals 'Входящие - Почта Mail.ru'

  Scenario: Create draft and send mail
    When I start writing letter
    And I create mail with 'default fields' using a template
    And I create mail with 'custom body' using a template
    And I create mail with 'missing subject' using a template
    And I save mail as draft
    And I close the message window
    And I open Drafts page
    Then Drafts addressee is displayed
    And Drafts subject contains 'Elimination details'
    When I select draft and send mail
    And I open Sent page
    Then Sent page contains 'Elimination details'
    When I open Drafts page
    Then I see in the Drafts box
    """
    У вас нет незаконченных
    или неотправленных писем
    """

  Scenario: Save mail as template and send it
    When I start writing letter
    And I create mail with 'default fields' using a template
    And I save mail as template
    And I open Template page
    And I open template
    And I send mail
    And I open Sent page
    Then Mail details text contains 'Elimination details'

  Scenario Outline: Save mail as draft then delete it
    When I start writing letter
    And I create mail with '<desired fields>' using a template
    And I save mail as draft
    And I open Drafts page
    And I delete saved draft
    Then I see in the Drafts box
    """
    У вас нет незаконченных
    или неотправленных писем
    """
    Examples:
      | desired fields  |
      | default fields  |
      | missing subject |
      | custom body     |

  Scenario: Start writing mail and move addressee to the copy field
    When I start writing letter
    And I create mail with 'default fields' using a template
    And I move addressee to the copy field
    Then I addressee is displayed in the copy field
    And I close the message window

  Scenario: Scrolling to the last sent mail
    When I open Sent page
    And I scroll to the last sent mail
    Then The last sent mail is displayed



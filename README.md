Java Wordle GUI
---------------
Welcome to the Wordle Clone program! This application creates a window/frame where you can play a clone of the popular word puzzle game, Wordle. 
The objective of the game is to guess a secret five-letter word by submitting guesses through a text field and receiving feedback on the correctness of your guesses.

Gameplay
--------------
Guessing a Word: Type a valid word that consists of exactly 5 letters into the text field provided.
Submitting Guess: Click the submit button to submit your guess.

Feedback: After submitting a guess, the program will process the word and display it with each letter being colored either Grey, Green, or Yellow:
  - Green: The letter is located in the correct position in the secret five-letter word.
  - Yellow: The letter is in the secret five-letter word, but in a different position.
  - Grey: The letter is not in the secret five-letter word or the number of occurrences of the same letter before this location equals the total number of occurrences of this letter in the secret five-letter word.

Winning: If you are able to guess the word correctly within 5 tries, a congratulatory message will be displayed.

How to Run
----------
Clone this repository to your local machine.
Ensure you have the necessary dependencies installed (e.g., Java).
Compile and run the program.
Follow the on-screen instructions to play Wordle Clone

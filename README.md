# EGR221A-HW2
This homework assignment was from a Data Structures course. The goal of this project was to recreate an "evil" version the game of Hangman by having it decide on the word depending on the user's choices.
The primary data strucutures that were used was lists, TreeMap and HashMap, and Set and SortedSet. This allowed the program to keep track of how many guesses the player had left, develop possible patterns to use, and which position the chosen letter is in according to the game's own pattern.
In order to "cheat", the program was implemented to always choose the pattern that has the least number of letters in it or the most words in the pattern's family.
The TreeMap is used to map the patterns to their word families.

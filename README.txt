# Baccarat
Calculate probabilities of banker win, player win, or tie

---------------------------------------------------
Group members

Eric Tran        101154728
Kevin Zhang      101147715
Luke Stichhaller 101072891

---------------------------------------------------
How to run

- Download file BaccaratProbabilities.java
- Open a terminal in the folder continaing BaccaratProbabilities.java
- Compile with: javac BaccaratProbabilites.java
- Run with:  java BaccaratProbabilites
- If any issues with comppiling and running please email lukestichhaller@cmail.carleton.ca

---------------------------------------------------
Assumptions

- Expected outputs: 
    - Player wins with ~= 0.446147 probability
    - Banker wins with ~= 0.458428 probability
    - Tie occurs with  ~= 0.095426
- suit not important, only the 13 face values
- infinite decks, so assume there are replacements 
    ex/ every face value is of equal chance of appearing 
-   face        value     
    A           1   
    2           2
    3           3
    4           4
    5           5
    6           6
    7           7
    8           8
    9           9
    10          0
    J           0
    Q           0
    K           0

- value 1-9 each have 1/13 odds of being pulled
- value 0 has a 4/13 chance of being pulled

---------------------------------------------------
Rules for drawing 3rd card      (see lecture 12 for Baccarat info)

- if either hand is natural (8 or 9), no third cards drawn

- player hand draws first, if value < 6 

- banker hand draws if value = 2
- banker stands if value = 7
- banker draws third(case: player stands on 2 cards that = 6 or 7):
    if (player hand stands on 6 or 7) {
        if (2 card banker hand value = 3,4, or 5) {
            banker draw
        }
        else if (2 card banker hand = 6) (
            stand
        }
    }
- banker draws third(case: player draws third card)
    if (banker total is 3 && player third card is = {0,1,2,3,4,5,6,7,9} ) {
        draw
    }
    else if (banker total is 4 && player third card is = {2,3,4,5,6,7}){
        draw
    }
    else if (banker total is 5 && player third card is = {4,5,6,7}){
        draw
    }
    else if (banker total is 6 && player third card is = {6,7}){
        draw
    }
    else{
        stand
    }

---------------------------------------------------
Calculate Winning probabilites

*Goal: loop through every player and banker 2 card combination and every scenario of drawing third card and add the probability of each hand to its outcome* 

for (all player 2 card combinations){
    for (all banker 2 card combinations){
        if (player draws third card){
            for (every possible player third card draw){
                if(banker draws third card){
                    for (every possible banker third card draw){
                        if (player value larger){
                            //add up odds of each card in each hand and add to player total
                                addToPlayerWin()
                            }
                            else if (banker value larger){
                                //add up odds of each card in each hand and add to banker total
                                addToBankerWin()
                            }
                            else{
                                //add up odds of each card in each hand and add to tie total
                                addToTie()
                            }
                        }
                    }
                }
                else{
                    if (player value larger){
                       //add up odds of each card in each hand and add to player total
                        addToPlayerWin()
                    }
                    else if (banker value larger){
                        //add up odds of each card in each hand and add to banker total
                        addToBankerWin()
                    }
                    else{
                         //add up odds of each card in each hand and add to tie total
                        addToTie()
                    }
                }
            }
        }
        else{
            if(banker draws third card){
                for (every possible banker third card draw){
                    if (player value larger){
                       //add up odds of each card in each hand and add to player total
                        addToPlayerWin()
                    }
                    else if (banker value larger){
                        //add up odds of each card in each hand and add to banker total
                        addToBankerWin()
                    }
                    else{
                         //add up odds of each card in each hand and add to tie total
                        addToTie()
                    }
                }
            }
            else{
                if (player value larger){
                    //add up odds of each card in each hand and add to player total
                    addToPlayerWin()
                }
                else if (banker value larger){
                    //add up odds of each card in each hand and add to banker total
                    addToBankerWin()
                }
                else{
                     //add up odds of each card in each hand and add to tie total
                    addToTie()
                }
            }
        }
    }
}
display results
        
---------------------------------------------------
Helper: Card hand data type

- stores the 3 cards in a hand 
- stores whether hand is a natural (stored so code is more readable rather than having to calculate multiple times)
- getters and setters
- method to calculate value of hand

---------------------------------------------------
Helper functions

- check if player draws a third card
- check if banker draws a third card
- add up probability of hand being drawn
    ex/ {3,9,0} probability is  = 1/13 * 1/13 * 4/13
- initialize array of player or banker card hands

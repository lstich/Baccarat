# Baccarat
Calculate probabilities of banker win, player win, or tie

---------------------------------------------------
Assumptions

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

- value 1-9 each have 4/52 odds of being pulled
- value 0 has a 16/52 chance of being pulled

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


- 


---------------------------------------------------
Winning probability of player

for (all player 2 card combinations){
    for (all banker 2 card combinations){
        if (player draws third card){
            for (every possible player third card draw){
                if(banker draws third card){
                    for (every possible banker third card draw){
                        if (player value larger){
                            calculate(add up odds of each card in each hand being pulled and add to player win total)
                        }
                    }
                }
            }
        }
        else{
            if(banker draws third card){
                for (every possible banker third card draw){
                    if (player value larger){
                        calculate(add up odds of each card in each hand being pulled and add to player win total)
                    }
                }
            }
        }
    }
}
divide player win total by C(52,2)
        


---------------------------------------------------
Winning probability of banker
    
for (all player 2 card combinations){
    for (all banker 2 card combinations){
        if (player draws third card){
            for (every possible player third card draw){
                if(banker draws third card){
                    for (every possible banker third card draw){
                        if (banker value larger){
                            calculate(add up odds of each card in each hand being pulled and add to player win total)
                        }
                    }
                }
            }
        }
        else{
            if(banker draws third card){
                for (every possible banker third card draw){
                    if (banker value larger){
                        calculate(add up odds of each card in each hand being pulled and add to banker win total)
                    }
                }
            }
        }
    }
}
divide banker win total by C(52,2)



---------------------------------------------------
Probability of tie

for (all player 2 card combinations){
    for (all banker 2 card combinations){
        if (player draws third card){
            for (every possible player third card draw){
                if(banker draws third card){
                    for (every possible banker third card draw){
                        if (player value = banker value){
                            calculate(add up odds of each card in each hand being pulled and add to tie total)
                        }
                    }
                }
            }
        }
        else{
            if(banker draws third card){
                for (every possible banker third card draw){
                    if (player value = banker value){
                        calculate(add up odds of each card in each hand being pulled and add to tie total)
                    }
                }
            }
        }
    }
}
divide tie total by C(52,2)

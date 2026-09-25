/**
 * STUDENT FILE
 *
 * Name: Ben Krasnik
 * AI Code Name: WYATTSAI
 *
 * Strategy Description:
 Offense: My ai will originally attack still life squares because this is a 4 for 1 trade off
 if it doesn't find any still life squares it will attack the enemy with the most amount of profit
    Defense: My ai will try to build a still life, it will orginally try to fill a beehive as it is the most profitable move in the game(that I can thought of or tested)
    if there are no beehives it will look for a still life to build, if it can't find one it will attack the enemy 
 */
public class WyattSAI extends CellAI {

    @Override
    public String getAIName() {
        return "WYATTSAI";
    }

    @Override
        public Location select(Grid grid) {
        /*
         * Replace this starter strategy.
         *
         * Helpful information:
         *   getID()                     -> your cell ID
         *   grid.getRows()              -> number of rows
         *   grid.getCols()              -> number of columns
         *   grid.getCell(r, c)          -> -1 if dead, otherwise an AI ID
         *   GridFunctions.getNeighbors  -> number of living neighbors
         *   GridFunctions.mostCommonNeighbor -> most common neighboring AI
         *   randomInt(bound)            -> reproducible random integer
         */
         Location beeHive = fillBeeHive(grid);
         if(beeHive != null) {
            return beeHive;
         }
        int rand = (int)(Math.random() * 2) +1 ;

        if(rand == 1) {
            Location defend = defend(grid);
                return defend;
            
        } else {
             Location attack = attack(grid);
                return attack;
        }
    }

    public Location defend(Grid grid) {
        if(BuildStillLife(grid) != null) {
            return BuildStillLife(grid);
        }
        return attack(grid);
    }

    public Location attack(Grid grid) {
       if(checkTheirStillLife(grid) != null) {
            return checkTheirStillLife(grid);
        }
        int me = super.getID();
        Location best = null;
        int bestScore = -100;
        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
               if(isEnemy(grid, r, c)) {
                    int score = killScore(grid, r, c);
                    if (score > bestScore) {
                        bestScore = score;
                        best = new Location(r, c);
                    }

                }
            }
        }

        if (best != null) {
            return best;
        }

        

       return new Location(randomInt(grid.getRows()), randomInt(grid.getCols()));
    }


    public int killScore(Grid grid, int r, int c) {
        int score = 0;
        if(GridFunctions.getNeighbors(grid, r, c) == 3 || GridFunctions.getNeighbors(grid, r, c) == 2) {
            score += 1;
        }
        for(int i = r-1; i <= r+1; i++) {
            for(int j = c-1; j <= c+1; j++) {
                if(i< 0 || j < 0 || i >= grid.getRows() || j >= grid.getCols()|| (i == r && j == c)) {
                    continue;
                }
                if(grid.getCell(i, j) != -1 && GridFunctions.getNeighbors(grid, i, j) == 2) {
                    if(grid.getCell(i, j) == super.getID()) {
                        score -= 1;
                    } else {
                        score += 1;
                    }
                }
            }
        }
        return score;
    }


    

    public Location checkTheirStillLife(Grid Grid){
        int me = super.getID();
        for (int r = 0; r < Grid.getRows()-1; r++) {
            for (int c = 0; c < Grid.getCols()-1; c++) {
                if(isEnemy(Grid, r, c) && isEnemy(Grid, r+1, c) && isEnemy(Grid, r, c+1) && isEnemy(Grid, r+1, c+1)) {
                    if(GridFunctions.getNeighbors(Grid, r, c) == 3 && GridFunctions.getNeighbors(Grid, r+1, c) == 3 && GridFunctions.getNeighbors(Grid, r, c+1) == 3 && GridFunctions.getNeighbors(Grid, r+1, c+1) == 3) {
                        if(r>0){
                        return new Location(r-1, c);
                        } else {
                            return new Location(r+2, c);
                        }

                    }
                    }
            }
        }
        return null;
    }


    public Location BuildStillLife(Grid Grid) {
        int me = super.getID();
            for (int r = 0; r < Grid.getRows() - 1; r++) {
                for (int c = 0; c < Grid.getCols() - 1; c++) {
                    if (Grid.getCell(r, c) == me && Grid.getCell(r + 1, c) == me && GridFunctions.getNeighbors(Grid, r, c) == 1 && GridFunctions.getNeighbors(Grid, r + 1, c) == 1 && Grid.getCell(r, c - 1) == -1 && Grid.getCell(r + 1, c - 1) == -1 && GridFunctions.getNeighbors(Grid, r, c - 1) == 2 && GridFunctions.getNeighbors(Grid, r + 1, c - 1) == 2) {
                        return new Location(r, c - 1);
                    }
                    if (Grid.getCell(r, c) == me && Grid.getCell(r, c + 1) == me && GridFunctions.getNeighbors(Grid, r, c) == 1 && GridFunctions.getNeighbors(Grid, r, c + 1) == 1 && Grid.getCell(r - 1, c) == -1 && Grid.getCell(r - 1, c + 1) == -1 && GridFunctions.getNeighbors(Grid, r - 1, c) == 2 && GridFunctions.getNeighbors(Grid, r - 1, c + 1) == 2) {
                        return new Location(r - 1, c);
                    }
                }
            }
        return null;
        }


        public boolean isEnemy(Grid grid, int r, int c) {
                int me = super.getID();
                if (grid.getCell(r, c) != me && grid.getCell(r, c) != -1) {
                    return true;
                }
                return false;
            }

        public Location fillBeeHive(Grid grid) {
            int me = super.getID();
            for(int r = 0; r < grid.getRows(); r++) {
                for (int c = 0; c < grid.getCols(); c++) {
                    if(r+ 3 < grid.getRows() && c> 0 && c + 1< grid.getCols() && hiveCellCheck(grid, r, c, me) && hiveCellCheck(grid, r+1, c-1, me) && hiveCellCheck(grid, r+1, c+1, me) && hiveCellCheck(grid, r+2, c+1, me) && hiveCellCheck(grid, r+2, c-1, me)&& hiveCellCheck(grid, r+3, c, me) && grid.getCell(r+1, c) == -1 && grid.getCell(r+2, c) == -1) {
                        return new Location(r+1, c);
                    }


                     if(c+ 3 < grid.getCols() && r> 0 && r+ 1< grid.getRows() && hiveCellCheck(grid, r, c, me) && hiveCellCheck(grid, r-1, c+1, me) && hiveCellCheck(grid, r+1, c+1, me) && hiveCellCheck(grid, r+1, c+2, me) && hiveCellCheck(grid, r-1, c+2, me)&& hiveCellCheck(grid, r, c+3, me) && grid.getCell(r, c+1) == -1 && grid.getCell(r, c+2) == -1) {
                        return new Location(r, c+1);
                    }
                        
                }
            }

            
            return null;
        
        }

        public boolean hiveCellCheck(Grid grid, int r, int c, int me) {
            if (grid.getCell(r, c) == me && GridFunctions.getNeighbors(grid, r, c) == 2 ) {
                return true;
            }
            return false;
        }
    }






    
    

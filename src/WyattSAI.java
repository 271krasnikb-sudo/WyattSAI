/**
 * STUDENT FILE
 *
 * Name: ______________________________
 * AI Code Name: ______________________
 *
 * Strategy Description:
 * Replace this comment with a short explanation of the strategy your AI uses.
 * Your final strategy must be fundamentally different from the sample AIs.
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
        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                if (isEnemy(grid, r, c) && GridFunctions.getNeighbors(grid, r, c) >= 2) {
                    return new Location(r, c);
                }
            }
        }

        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                if (isEnemy(grid, r, c)) {
                    return new Location(r, c);
                }
            }
        }

       return new Location(randomInt(grid.getRows()), randomInt(grid.getCols()));
    }


    

    public Location checkTheirStillLife(Grid Grid){
        int me = super.getID();
        for (int r = 1; r < Grid.getRows()-1; r++) {
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
            for (int r = 1; r < Grid.getRows() - 1; r++) {
                for (int c = 1; c < Grid.getCols() - 1; c++) {
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






    
    

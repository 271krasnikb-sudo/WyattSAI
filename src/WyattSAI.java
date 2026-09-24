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
       while(true) {
        
        int rand = (int)(Math.random() * 2) +1 ;

        if(rand == 1) {
            Location defend = defend(grid);
            if(defend != null) {
                return defend;
            }
        } else {
             Location attack = attack(grid);
            if(attack != null) {
                return attack;
            }
        }
    }
    }

    public Location defend(Grid grid) {
        if(BuildStillLife(grid) != null) {
            return BuildStillLife(grid);
        }
        return new Location(randomInt(grid.getRows()), randomInt(grid.getCols()));
    }

    public Location attack(Grid grid) {
       if(checkTheirStillLife(grid) != null) {
            return checkTheirStillLife(grid);
        }
        return new Location(randomInt(grid.getRows()), randomInt(grid.getCols()));
    }


    

    public Location checkTheirStillLife(Grid Grid){
        for (int r = 1; r < Grid.getRows()-1; r++) {
            for (int c = 1; c < Grid.getCols()-1; c++) {
                if(Grid.getCell(r, c) != super.getID() && Grid.getCell(r, c) != -1 && Grid.getCell(r+1, c) != super.getID() && Grid.getCell(r+1, c) != -1 && GridFunctions.getNeighbors(Grid, r, c) == 2   ) {
                    if(GridFunctions.getNeighbors(Grid, r, c) == 3 && GridFunctions.getNeighbors(Grid, r+1, c) == 3 && GridFunctions.getNeighbors(Grid, r, c+1) == 3 && GridFunctions.getNeighbors(Grid, r+1, c+1) == 3) {
                        return new Location(r-1, c);
                    }
                    
                }
            }
        }
        return null;
    }


    public Location BuildStillLife(Grid Grid){
        for (int r = 1; r < Grid.getRows()-1; r++) {
            for (int c = 1; c < Grid.getCols()-1; c++) {
                if(Grid.getCell(r, c) == super.getID() && Grid.getCell(r+1, c) == super.getID() ) {
                    if(GridFunctions.getNeighbors(Grid, r, c) == 1 && GridFunctions.getNeighbors(Grid, r+1, c) == 1) {
                        if (Grid.getCell(r, c-1) == -1) {
                            return new Location(r, c-1);
                        }
                    }
                    
                } 
                if(Grid.getCell(r, c) == super.getID() && Grid.getCell(r, c+1) == super.getID() ) {
                    if(GridFunctions.getNeighbors(Grid, r, c) == 1 && GridFunctions.getNeighbors(Grid, r, c+1) == 1) {
                        if (Grid.getCell(r-1, c) == -1) {
                            return new Location(r-1, c);
                        }
                    }
                    
                }
            }
        }
        return attack(Grid);
    }


}
    
    

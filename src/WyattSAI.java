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
            Location attack = attack(grid);
            if(attack != null) {
                return attack;
            }
            Location defend = defend(grid);
            if(defend != null) {
                return defend;
            }
            attack = attack(grid);
            if(attack != null) {
                return attack;
            }

        }
    }

    public Location defend(Grid grid) {
        for (int r = 1; r < grid.getRows()-1; r++) {
            for (int c = 1; c < grid.getCols()-1; c++) {
                if(grid.getCell(r, c) == -1  && grid.getCell(r-1, c) == super.getID() && grid.getCell(r+1, c) == super.getID() && grid.getCell(r, c-1) == super.getID() && grid.getCell(r, c+1) == super.getID() && GridFunctions.getNeighbors(grid, r, c) == 2   ) {
                    return new Location(r, c);
                }
            }
        }
        return new Location(randomInt(grid.getRows()), randomInt(grid.getCols()));
    }

    public Location attack(Grid grid) {
        for (int r = 1; r < grid.getRows()-1; r++) {
            for (int c = 1; c < grid.getCols()-1; c++) {
                if(grid.getCell(r, c) != -1  && grid.getCell(r-1, c) != super.getID() && grid.getCell(r+1, c) != super.getID() && grid.getCell(r, c-1) != super.getID() && grid.getCell(r, c+1) != super.getID()&& GridFunctions.getNeighbors(grid, r, c) == 2   ) {
                    return new Location(r, c);
                }
            }
        }
        return new Location(randomInt(grid.getRows()), randomInt(grid.getCols()));
    }
}
    
    

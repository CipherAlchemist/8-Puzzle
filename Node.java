import java.util.HashMap;
import java.util.LinkedList;

//This node a graphical representation of a puzzle state in a state space / search tree.
public class Node {

        State theState;
        Node parent;
        int gFunction;
        int hFunction;
        int costFunction;
        String theHeuristic;
        LinkedList<Node>childNodes = new LinkedList<Node>();

        //the desired goal state
    final char goalState[][] = {{'b','1','2'},{'3','4','5'},{'6','7','8'}};

        public Node(State theState, Node parent, String theHeuristic) {

            this.theState = theState;

            this.parent = parent;

            if (this.parent != null) {

                gFunction = 1 + parent.getGfunction();
            }

            //heuristic function intialization when it is misplaced tiles
            if (theHeuristic.equals("h1")) {


                hFunction = this.helperMisplacedTiles(this.getState());


            }

            //heuristic function intialization when it is manhattan distance
            else if (theHeuristic.equals("h2")) {

                hFunction = this.helperManhattanDistance(this.getState());

            }

            //initializing the total cost function based on upon g(x) + h(x)
            costFunction = this.getGfunction() + this.getHfunction();


        }

        //getter for the state
        public State getState() {

            return this.theState;
        }

        //setter for the state
        public void setState(State state) {

            this.theState = state;

        }

        //get the function of the cost of the path from the starting node (distance).
        public int getGfunction() {

            return this.gFunction;


        }

        //gets the function of the cost of the path based on the given heuristic
        public int getHfunction() {

            return this.hFunction;


        }

        //gets the function that gives total cost of the path c(x) = (h(x) + g(x)).
        public int getCostFunction() {

            return this.costFunction;

        }


        //helper function to calculate the h1 heuristic of a state (misplaced tiles).
        private int helperMisplacedTiles(State state) {
            int countOfMisplacedTiles = 0;

            for (int i = 0; i < goalState.length; i++) {
                for (int j = 0; j < goalState.length; j++) {

                    if (goalState[i][j] != 'b') {
                        if (goalState[i][j] != state.getPuzzleState()[i][j]) {

                            countOfMisplacedTiles++;

                        }
                    }

                }

            }

            return countOfMisplacedTiles;

        }

        //helper function to calculate the h2 heuristic of a state (manhattan distance).
        private int helperManhattanDistance(State state) {

                //Stores the row number
                HashMap<Character, Integer> rowNumber = new HashMap<Character, Integer>();

                //Stores the column number
                HashMap<Character, Integer> colNumber = new HashMap<Character, Integer>();

                for (int i = 0; i < goalState.length; i++){

                    for (int j = 0; j < goalState.length; j++){

                        rowNumber.put(goalState[i][j], i);

                        colNumber.put(goalState[i][j], j);

                    }

                }

                int totalDistance = 0;

                //prev puzzle length
                for (int i = 0; i < goalState.length; i++) {

                    for (int j = 0; j < goalState.length; j++) {

                        if (state.getPuzzleState()[i][j] != 'b') {

                            totalDistance = totalDistance + (Math.abs(i - rowNumber.get(state.getPuzzleState()[i][j]))) + (Math.abs(j - colNumber.get(state.getPuzzleState()[i][j])));

                        }
                    }

                }

                return totalDistance;
        }
}

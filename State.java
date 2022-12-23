import java.util.Arrays;
import java.util.ArrayList;

//A state of the puzzle
public class State {


    private char[][] state;
    private int blankColumn;
    private int blankRow;


    public State(char[][] state) {

        this.state = state;

        blankColumn = this.helperBlank(state)[1];

        blankRow = this.helperBlank(state)[0];


    }

    //getter for the state
    public char[][] getPuzzleState() {

        return this.state;

    }

    //String representation of the puzzle
    public String getToString() {

        return Arrays.deepToString(state);


    }

    //Multi line string representation of the puzzle
    public String getToStringArray() {


       StringBuilder builder = new StringBuilder();

       String separator = System.lineSeparator();

       for (char[] row : this.getPuzzleState()) {

          builder.append(Arrays.toString(row)).append(separator);
       }

       String result = builder.toString();

       return result;

    }
    //getter for the blank row
    public int getBlankRow() {

        return this.blankRow;

    }

    //getter for the blank column
    public int getBlankColumn() {

        return this.blankColumn;

    }



    //This is a helper method to find the row and column of the blank tile.
    public int[] helperBlank(char[][]state) {

        char blankRow = 0;
        char blankColumn = 0;
        int[] arr = new int [2];

        //Iterates through array to find the blank value
        for (int i = 0; i < state.length; i++){
            for (int j = 0; j < state[i].length; j++){

                if (state[i][j] == 'b') {

                    arr[0] = i;
                    arr[1] = j;


                }
            }
        }
        return arr;
    }

    /*Determines whether the given state is solvable via parity/inversions
     * If the inversions are even it is solvable but if it is odd it is unsolvable.
     */
    public boolean isSolvable() {

        //ArrayList<Character> oneDarray = new ArrayList<Character>();

        char arry[] = new char [9];

        int inversions = 0;

        int count = 0;



        //Converting the 2D array into 1D array because it simplifies inversion calculation
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                    arry[count] = this.getPuzzleState().clone()[i][j];

                    count++;

            }

        }

        //Counts the number of inversions
        for (int i = 0; i < arry.length - 1; i++) {
            for (int j = i + 1; j < arry.length; j++) {

                if ((arry[i] != 'b' && arry[j] != 'b') && (arry[i] > arry[j])) {

                    inversions++;


                }


            }
        }

        //Determines if inversions are even or odd
        if (inversions % 2 != 0) {

            return false;

        }

        else {
            return true;
        }


    }


}

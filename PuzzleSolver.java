import java.util.Locale;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.*;

/*
 * This is a class used to manipulate / solve a given puzzle
 * The puzzle is specified via an input txt
 */
public class PuzzleSolver {



        char sameState[][] = {{'b', '1', '2'}, {'3', '4', '5'}, {'6', '7', '8'}};
        State a = new State(sameState);

        Puzzle thePuzzle = new Puzzle(a, Integer.MAX_VALUE);

    public PuzzleSolver(String fileName) throws IOException {

        Scanner file = new Scanner(new File(fileName));


        while (file.hasNextLine()) {


            String line = file.nextLine();


            if (line.contains("setState")) {

                String numbers = line;

                String values = numbers.substring(9);

                values = values.replaceAll("\\s", "");

                char[] charArray = values.toCharArray();

                //System.out.println(charArray);

                char twoDarray[][] = new char[3][3];

                for (int i = 0; i < twoDarray.length; i++) {

                    for (int j = 0; j < twoDarray.length; j++) {

                        twoDarray[j][i] = charArray[(j * 3) + i];

                    }

                }

                State state = new State(twoDarray);

                thePuzzle.setState(state);


            } else if (line.contains("printState")) {

                thePuzzle.printState();

            } else if (line.contains("move")) {

                line.toLowerCase();

                if (line.contains("up")) {

                    thePuzzle.move("move up");

                } else if (line.contains("down")) {

                    thePuzzle.move("move down");

                } else if (line.contains("left")) {

                    thePuzzle.move("move left");

                } else if (line.contains("right")) {

                    thePuzzle.move("move right");

                }


            } else if (line.contains("randomizeState")) {

                String x = line;
                x = x.replaceAll("[^0-9]", "");


                thePuzzle.randomizeState(Integer.parseInt(x));


            } else if (line.contains("solve A-star")) {

                if (line.contains("h1")) {

                    thePuzzle.aStarAlgorithm("h1");

                } else if (line.contains("h2")) {

                    thePuzzle.aStarAlgorithm("h2");

                } else {

                    ///write an exception here
                }

                //System.out.println("astar");

            } else if (line.contains("solve beam")) {

                String x = line;
                x = x.replaceAll("[^0-9]", "");

                thePuzzle.localBeamSearch(Integer.parseInt(x), "h2");

                //System.out.println("beam");

            } else if (line.contains("maxNodes")) {

                //System.out.println("the max nodes");

                String x = line;
                x = x.replaceAll("[^0-9]", "");

                thePuzzle.maxNodes(Integer.parseInt(x));
                //  System.out.println(thePuzzle.getMaxNodes());

            }

            else {

                System.out.println("That is not a command that can be computed.");

        }
    }

    }



    public static void main (String args []) {

        try {
            PuzzleSolver aPuzzle = new PuzzleSolver("aSecondSimpleTextFile.txt");


        }

        catch (IOException e) {

            System.out.println("That is not a command that can be computed or file not read.");


        }


    }

}
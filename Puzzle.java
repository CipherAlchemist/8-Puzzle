import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.lang.Math.*;
import java.util.Comparator;
import java.util.Collections;
import java.util.Random;
import java.util.HashSet;


public class Puzzle {

    final char goalState[][] = {{'b', '1', '2'}, {'3', '4', '5'}, {'6', '7', '8'}};

    private State currentState;

    private int maxNodes;

    public Puzzle(State currentState, int maxNodes) {

        this.currentState = currentState;
        this.maxNodes = maxNodes;

    }

    //getter for the state
    public State getState() {

        return this.currentState;
    }

    //setter for the state
    public void setState(State state) {

        this.currentState = state;

    }

    //getter for the max number of nodes considered for a search
    public int getMaxNodes() {

        return this.maxNodes;

    }

    //setter for the max number of nodes considered for a search
    public void setMaxNodes(int newMaxNodes) {

        this.maxNodes = newMaxNodes;


    }

    //prints the current state of the puzzle
    public void printState() {

        for (char[] row : this.getState().getPuzzleState()) {

            System.out.println(Arrays.toString(row));


        }


    }

    //randomizes the current state from the goal state from a given number of input moves
    public void randomizeState(int moves) {

        char exampleGoal[][] = {{'b', '1', '2'}, {'3', '4', '5'}, {'6', '7', '8'}};

        State state = new State(exampleGoal);

        this.setState(state);

        Random move = new Random();

        //since this is a demonstration a predictable seed makes sense... also asked in code correctness
        move.setSeed(1000);

        Node node = new Node(state, null, "h2");

        for (int i = 0; i < moves; i++) {


            this.expansion(node, "h2");
            node.setState(node.childNodes.get(move.nextInt(node.childNodes.size())).getState());
            //this.printState();
            node.childNodes.clear();

        }

        this.setState(node.getState());

    }

    //makes a move on the current puzzle state if such a move is possible
    public void move(String move) {

        if (move.equals("move left")) {

            if (this.possibleMoveLeft(this.getState()) != null) {

                this.setState(this.possibleMoveLeft(this.getState()));

            }

        } else if (move.equals("move right")) {

            if (this.possibleMoveRight(this.getState()) != null) {

                this.setState(this.possibleMoveRight(this.getState()));

            }

        } else if (move.equals("move up")) {

            if (this.possibleMoveUp(this.getState()) != null) {

                this.setState(this.possibleMoveUp(this.getState()));

            }

        } else if (move.equals("move down")) {

            if (this.possibleMoveDown(this.getState()) != null) {

                this.setState(this.possibleMoveDown(this.getState()));

            }

        }
    }

    //gets the possible move for left if such a move in the current state is possible
    private State possibleMoveLeft(State state) {

        if (state.getBlankColumn() > 0) {

            char puzzleState[][] = new char[3][3];
            for (int i = 0; i < state.getPuzzleState().length; i++) {
                for (int j = 0; j < state.getPuzzleState()[i].length; j++) {

                    puzzleState[i][j] = state.getPuzzleState()[i][j];

                }
            }
            char temp = puzzleState[state.getBlankRow()][state.getBlankColumn() - 1];

            puzzleState[state.getBlankRow()][state.getBlankColumn() - 1] = puzzleState[state.getBlankRow()][state.getBlankColumn()];

            puzzleState[state.getBlankRow()][state.getBlankColumn()] = temp;


            State newState = new State(puzzleState);

            return newState;

            //State aNewState = new State();

        }

        return null;

    }

    //gets the possible move for right if such a move in the current state is possible
    private State possibleMoveRight(State state) {

        if (state.getBlankColumn() < 2) {

            char puzzleState[][] = new char[3][3];
            for (int i = 0; i < state.getPuzzleState().length; i++) {
                for (int j = 0; j < state.getPuzzleState()[i].length; j++) {

                    puzzleState[i][j] = state.getPuzzleState()[i][j];


                }
            }
            char temp = puzzleState[state.getBlankRow()][state.getBlankColumn() + 1];

            puzzleState[state.getBlankRow()][state.getBlankColumn() + 1] = puzzleState[state.getBlankRow()][state.getBlankColumn()];

            puzzleState[state.getBlankRow()][state.getBlankColumn()] = temp;


            State newState = new State(puzzleState);

            return newState;

            //State aNewState = new State();

        }

        return null;

    }

    //gets the possible move for up if such a move in the current state is possible
    private State possibleMoveUp(State state) {

        if (state.getBlankRow() > 0) {

            char puzzleState[][] = new char[3][3];
            for (int i = 0; i < state.getPuzzleState().length; i++) {
                for (int j = 0; j < state.getPuzzleState()[i].length; j++) {

                    puzzleState[i][j] = state.getPuzzleState()[i][j];


                }
            }
            char temp = puzzleState[state.getBlankRow() - 1][state.getBlankColumn()];

            puzzleState[state.getBlankRow() - 1][state.getBlankColumn()] = puzzleState[state.getBlankRow()][state.getBlankColumn()];

            puzzleState[state.getBlankRow()][state.getBlankColumn()] = temp;


            State newState = new State(puzzleState);

            return newState;

            //State aNewState = new State();

        }

        return null;
    }

    //gets the possible move for down if such a move in the current state is possible
    private State possibleMoveDown(State state) {

        if (state.getBlankRow() < 2) {

            char puzzleState[][] = new char[3][3];
            for (int i = 0; i < state.getPuzzleState().length; i++) {
                for (int j = 0; j < state.getPuzzleState()[i].length; j++) {

                    puzzleState[i][j] = state.getPuzzleState()[i][j];


                }
            }
            char temp = puzzleState[state.getBlankRow() + 1][state.getBlankColumn()];

            puzzleState[state.getBlankRow() + 1][state.getBlankColumn()] = puzzleState[state.getBlankRow()][state.getBlankColumn()];

            puzzleState[state.getBlankRow()][state.getBlankColumn()] = temp;


            State newState = new State(puzzleState);

            return newState;

            //State aNewState = new State();

        }

        return null;


    }

    //expands a given node... generates the child nodes of a node... gets the next possible moves to take from the state
    public void expansion(Node node, String heuristic) {

        //adds a left move as a child node if possible
        if (this.possibleMoveLeft(node.getState()) != null) {

            //node.childNodes.add(this.possibleMoveLeft(node));

            Node newNode = new Node(this.possibleMoveLeft(node.getState()), node, heuristic);

            node.childNodes.add(newNode);

        }

        //adds a right move as a child node if possible
        if (this.possibleMoveRight(node.getState()) != null) {

            //node.childNodes.add(this.possibleMoveLeft(node));

            Node newNode = new Node(this.possibleMoveRight(node.getState()), node, heuristic);

            node.childNodes.add(newNode);


        }

        //adds a up move as a child node if possible
        if (this.possibleMoveUp(node.getState()) != null) {

            //node.childNodes.add(this.possibleMoveLeft(node));

            Node newNode = new Node(this.possibleMoveUp(node.getState()), node, heuristic);

            node.childNodes.add(newNode);

        }

        //adds a down move as a child node if possible
        if (this.possibleMoveDown(node.getState()) != null) {

            //node.childNodes.add(this.possibleMoveLeft(node));

            Node newNode = new Node(this.possibleMoveDown(node.getState()), node, heuristic);

            node.childNodes.add(newNode);

        }

    }


    //BELOW THIS POINT ARE THE SEARCH ALGOS

    /*computes the A* algorithm on the current state
     * Finds the path and number of moves to reach goal state if possible
     */
    public void aStarAlgorithm(String heuristic) {

        int nodesGenerated = 0;

        if (this.getState().isSolvable() == false) {

            System.out.println("This puzzle is not solvable");


            return;


        } else {



            /*An implemented comparator so the priority queue is order based on the cost function.
             * The lower the cost function the higher precedence the node will be on the queue.
             */
            class HeuristicComparator implements Comparator<Node> {


                public int compare(Node node1, Node node2) {

                    if (node1.getCostFunction() > node2.getCostFunction()) {

                        return 1;
                    } else if (node1.getCostFunction() < node2.getCostFunction()) {

                        return -1;

                    }

                    return 0;

                }
            }

            //Priority queue of the nodes
            PriorityQueue<Node> queue = new PriorityQueue<Node>(new HeuristicComparator());

            //Visited list for the nodes already visited
            HashMap<String, Node> visited = new HashMap<String, Node>();

            //Get a node of the current state of the puzzle.

            //Stores the length of the path
            int lengthOfPath = 0;


            //The intial state is the starting node
            Node start = new Node(this.getState(), null, heuristic);

            //put start node on the queue
            queue.add(start);


            while (!queue.isEmpty()) {


               // if (visited.size() >= this.getMaxNodes()) {
                if (nodesGenerated >= this.getMaxNodes()) {

                    System.out.println("Max Node limit reached");

                    System.out.println("Number of nodes generated for A* is " + nodesGenerated);
                    return;
                }

                //Take the top node off the queue and mark it as visited
                Node theTop = queue.poll();
                visited.put(theTop.getState().getToString(), theTop);

                //If the top node on the queue has the goal state find the path
                if ((Arrays.deepEquals(goalState, theTop.getState().getPuzzleState())) == true) {

                    //ArrayList<String> statePath = new ArrayList<String>();
                    LinkedList<String> statePath = new LinkedList<String>();

                    Node ptr = theTop;

                    //statePath.add(ptr.getState().getToString());
                    statePath.addFirst(ptr.getState().getToStringArray());

                    while (ptr.parent != null) {

                        ptr = ptr.parent;

                        statePath.addFirst(ptr.getState().getToStringArray());
                        lengthOfPath++;

                    }
                    //Collections.reverse(statePath);

                    System.out.println(lengthOfPath);
                    System.out.println(statePath);
                    System.out.println("Number of nodes generated for A* is " + nodesGenerated);


                    break;
                }

                //get the children of the top node.
                this.expansion(theTop, heuristic);

                //Add the children to the queue if not in visited
                for (Node node : theTop.childNodes) {

                    nodesGenerated++;

                    //if (visited.size() >= this.getMaxNodes()) {
                    if (nodesGenerated >= this.getMaxNodes()) {

                        System.out.println("Max Node limit reached");

                        System.out.println("Number of nodes generated for A* is " + nodesGenerated);
                        return;
                    }

                    if (!visited.containsKey(node.getState().getToString())) {

                        // if (!visited.containsKey(node.getState().getToString()) && !queue.contains(node)) {

                        queue.add(node);

                    }

                }

            }
        }

    }


    /* computes the Local Beam Search algorithm on the current state
     * Finds the path and number of moves to reach goal state if possible
     */
    public void localBeamSearch(int k, String heuristic) {



        int nodesGenerated = 0;

        //determine if unsolvable
        if (this.getState().isSolvable() == false) {

            System.out.println("This puzzle is not solvable");


            return;


        }

        //k must be acceptable for search
        if (k <= 0) {

            System.out.println("k must be at least one");

            return;

        }

        else {


            /*An implemented comparator so the priority queue is order based on the heuristic function.
             * The lower the heursitic function cost the higher precedence the node will be on the queue.
             * C(x) = h(x)
             */
            class HeuristicComparator implements Comparator<Node> {


                public int compare(Node node1, Node node2) {

                    if (node1.getHfunction() > node2.getHfunction()) {

                        return 1;
                    } else if (node1.getHfunction() < node2.getHfunction()) {

                        return -1;

                    }

                    return 0;

                }
            }

            //Priority queue of nodes
            PriorityQueue<Node> queue = new PriorityQueue<Node>(new HeuristicComparator());

            //PQ of the saved kNodes
            PriorityQueue<Node> kList = new PriorityQueue<Node>(new HeuristicComparator());



            //length og the path
            int lengthOfPath = 0;

            //The intial state is the starting node


            //the intial state of the search
            Node start = new Node(this.getState(), null, heuristic);

            //number of nodes that can be considered
            int numberOfNodesConsidered = 0;


            //if goal return path and length...when start is goal
            if ((Arrays.deepEquals(goalState, start.getState().getPuzzleState())) == true) {

                //ArrayList<String> statePath = new ArrayList<String>();
                LinkedList<String> statePath = new LinkedList<String>();

                Node ptr = start;

                //statePath.add(ptr.getState().getToString());
                statePath.addFirst(ptr.getState().getToStringArray());

                while (ptr.parent != null) {

                    ptr = ptr.parent;

                    statePath.addFirst(ptr.getState().getToStringArray());
                    lengthOfPath++;

                }
                //Collections.reverse(statePath);


                System.out.println(lengthOfPath);
                System.out.println(statePath);



                return;
            }





            //getting children of initial node
            this.expansion(start, "h2");

            //adding them to queue where only k best kept
            queue.addAll(start.childNodes);


            //Loop of generating k best successors for each iteration
            while (nodesGenerated < this.getMaxNodes()) {


                //keeps on the k best to be searched
                for (int i = 0; i < k; i++) {

                    Node topNode = queue.poll();

                    if (topNode == null) {

                        break;

                    }

                    else {

                        kList.add(topNode);
                    }

                }

                queue.clear();


                //each node of the k best
                for (Node node : kList) {

                    nodesGenerated++;


                    //if goal return path and length...LBM does not gurantee optimal or complete solution
                    if ((Arrays.deepEquals(goalState, node.getState().getPuzzleState())) == true) {

                        //ArrayList<String> statePath = new ArrayList<String>();
                        LinkedList<String> statePath = new LinkedList<String>();

                        Node ptr = node;

                        //statePath.add(ptr.getState().getToString());
                        statePath.addFirst(ptr.getState().getToStringArray());

                        while (ptr.parent != null) {

                            ptr = ptr.parent;

                            statePath.addFirst(ptr.getState().getToStringArray());
                            lengthOfPath++;


                        }
                        //Collections.reverse(statePath);

                        System.out.println(lengthOfPath);
                        System.out.println(statePath);
                        System.out.println("Number of nodes generated for local beam search is " + nodesGenerated);

                        return;
                    }

                    //get the children of one of the k nodes
                    this.expansion(node, "h2");

                    //add these children to the queue
                    queue.addAll(node.childNodes);
                   // numberOfNodesConsidered++;

                    //if limit has been reached return error statement
                    if (nodesGenerated >= this.getMaxNodes()) {

                        System.out.println("Max Node limit reached");
                        System.out.println("Number of nodes generated for local beam search is " + nodesGenerated);
                        return;

                    }


                }

                kList.clear();


            }


        }
    }


    //defines the max number of nodes to be considered for a search
    public void maxNodes(int numberOfMaxNodes) {

        this.setMaxNodes(numberOfMaxNodes);


    }

    public void experiment() {



    }






}

import java.util.Scanner;

/**rules of conway's game of life:
 -if the cell is alive then it stays alive if it has either 2 or 3 live neighbors
 -if the cell is dead, then it springs to life only in the case that it has 3 live neighbors
 **/

public class Life {
    public static void show(boolean[][] grid){
        String s = "";                           //initialize a blank string
        for(boolean[] row : grid){              //iterate through each row of the grid
            for(boolean val : row)              //iterate through each value of the row
                if(val)                         //if value is true (i.e. alive cell)
                    s += "*";                   //add a * to the string
                else
                    s += ".";                   //otherwise, add a . to the string
            s += "\n";                          //add a new line character at the end of each row
        }
        System.out.println(s);                  //print the string
    }

    public static boolean[][] gen(){
        boolean[][] grid = new boolean[10][10]; //create a 10x10 boolean array
        for(int r = 0; r < 10; r++)              //iterate through each row
            for(int c = 0; c < 10; c++)          //iterate through each column
                if( Math.random() > 0.7 )        //randomly set cells to true (i.e. alive) based on a probability of 0.3
                    grid[r][c] = true;
        return grid;                            //return the 10x10 boolean array
    }

    public static void main(String[] args){
        boolean[][] world = gen();              //create a new 10x10 boolean array using the gen() method
        show(world);                            //display the initial grid using the show() method
        System.out.println();                   //print an empty line
        world = nextGen(world);                 //generate the next generation of cells using the nextGen() method
        show(world);                            //display the next generation of cells
        Scanner s = new Scanner(System.in);     //create a scanner object to read user input
        while(s.nextLine().length() == 0){      //while the length of the user input is zero (i.e. user presses enter without typing anything)
            System.out.println();               //print an empty line
            world = nextGen(world);             //generate the next generation of cells
            show(world);                        //display the next generation of cells
        }
    }

    public static boolean[][] nextGen(boolean[][] world){
        boolean[][] newWorld
                = new boolean[world.length][world[0].length]; //create a new boolean array of the same size as the original
        int num;
        for(int r = 0; r < world.length; r++){            //iterate through each row
            for(int c = 0; c < world[0].length; c++){     //iterate through each column
                num = numNeighbors(world, r, c);         //count the number of live neighbors for the current cell
                if( occupiedNext(num, world[r][c]) )     //check if the current cell should be alive or dead in the next generation
                    newWorld[r][c] = true;              //set the value in the new boolean array to true if the current cell should be alive in the next generation
            }
        }
        return newWorld;                                //return the new boolean array
    }

    public static boolean occupiedNext(int numNeighbors, boolean occupied){
        if( occupied && (numNeighbors

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AllPaths {

    int[][] graph;
    ArrayList<ArrayList<Integer>> paths = new ArrayList<>();

    /*creates an adjacency matrix of sorts where
    * the row is the "node" and the column is what it points to
    * where 1 means it points to it and 0 means it does not
     */
    public int[][] createGraph(int[][] input){
        graph = new int[input.length][input.length];
        for(int i = 0; i < input.length; i++){
//            graph[i] = new int[input[i].length];
            for(int j = 0; j < input[i].length; j++){
                graph[i][input[i][j]] = 1;
            }
        }
        return graph;
    }

    /*
    *make sure paths is clear since its global to start a new graph
    * create the graph datastructure as a 2d array
    * try path from 0
    * return int[][] of all possible paths from 0 -> final index
     */
    public int[][] allPaths(int[][] input) {
        paths.clear();
        graph = createGraph(input);
        ArrayList prevPath = new ArrayList<>();
        tryPath(prevPath,0);
        return convertToIntArr(paths);
    }

    //convert to int[][](couldn't find a method that worked for me so manual)
    public int[][] convertToIntArr(ArrayList<ArrayList<Integer>> arr){
        int[][] arrPaths = new int[paths.size()][];
        for(int i = 0; i < paths.size(); i++){
            arrPaths[i] = new int[paths.get(i).size()];
            for(int j = 0; j < paths.get(i).size(); j++) {
                arrPaths[i][j] = paths.get(i).get(j);
            }
        }
        return arrPaths;
    }

    public boolean tryPath(ArrayList<Integer> prevPath, int current){
        ArrayList<Integer> path = (ArrayList)prevPath.clone(); //creates a clone of the previous path to continue
        path.add(current);
        for(int i = 0; i < graph[current].length; i++){
            if(graph[current][i] == 1){
                if(i == graph.length-1) {//if we reach the target (end of path)
                    path.add(i);
                    paths.add(path);
                    return true;
                }else {//else go again at the next path
                    tryPath(path, i);
                }
            }
        }
        return false;
    }

    private void print(int[][] arr){
        for(int row = 0; row < arr.length; row++) {
            for (int i = 0; i < arr[row].length; i++) {
                System.out.print(" " + arr[row][i]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args){

        int[][] test1 = new int[][] {{1,2},{3},{3},{}};

        int[][] test2 = new int[][] {{4,3,1},{3,2,4},{3},{4},{}};

        int[][] test3 = new int[][] {{1},{}};

        AllPaths test = new AllPaths();

        int[][] paths1 = test.allPaths(test1);

        System.out.println("TEST1:");
        test.print(paths1);

        int[][] paths2 = test.allPaths(test2);

        System.out.println("TEST2:");
        test.print(paths2);

        int[][] paths3 = test.allPaths(test3);

        System.out.println("TEST3:");
        test.print(paths3);



    }

}

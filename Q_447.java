import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matthew on 12/20/17.
 */
public class Q_447 {

   /*
   Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that
   the distance between i and j equals the distance between i and k (the order of the tuple matters).
   Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range
    [-10000, 10000] (inclusive).

    Example:
    Input:
    [[0,0],[1,0],[2,0]]

    Output:
    2

    Explanation:
    The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]

   */


    /**
     * In essence, what is being done here is that for each point we are computing its distance to the other points and
     * there are multiple distances that are the same for a single point, then that point serves as the "focal" point
     * for a valid tuple (if the tuple if (i,j,k), then the current point is the j point - the intersection of the
     * edges essentially) - then for the number of times that the distance repeats, we permute that number since the
     * order does matter - for example - if there were 3 distances (edges) that were the same, that means that there
     * are three other points for the current point we are looking at that have a distance 5 away from our current point
     * which means that we just need to choose 2 of those points (the i and the k for the (i,j,k) tuple - where j is our
     * current point), so we have 3 P 2 - which is 3 * (3 - 1) or 3!/(3-2)!. So we have 6 "boomerangs" or unique
     * tuples of points (i,j,k) with the current point being j. The map clearing is an optimization so that we don't
     * need to create a new map each time.
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {

        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int i = 0; i < points.length; i++){
            for(int j = 0; j < points.length; j++){
                if(j != i){

                    int distance = calcSquareDistance(points[i],points[j]);
                    map.put(distance, map.getOrDefault(distance,0) + 1);

                }
            }
            for(Integer val : map.values()){
                result += (val * (val-1));
            }
            map.clear();

        }

        return result;
    }

    public int calcSquareDistance(int a[], int b[]){
        return (int) (Math.pow(a[0]-b[0],2) + Math.pow(a[1]-b[1],2));
    }

    public static void main(String[] args) {
        Q_447 test = new Q_447();


        // [[3,6],[7,5],[3,5],[6,2],[9,1],[2,7],[0,9],[0,6],[2,6]], should return 10
        int[][] points = new int[][]{{3, 6}, {7, 5}, {3, 5}, {6, 2}, {9, 1}, {2, 7},
                {0, 9}, {0, 6}, {2, 6},};

        // [[0,0],[1,0],[-1,0],[0,1],[0,-1]] should return 20
        System.out.println(test.numberOfBoomerangs(points));

    }

}

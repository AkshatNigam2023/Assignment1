import java.util.Scanner;

public class DronePathFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter grid size rows & Columns: ");
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();

        int[][] grid = new int[rows][columns];

        System.out.print("Enter target position rows & Columns: ");
        int targetRow = scanner.nextInt();
        int targetColumn = scanner.nextInt();
        int[] target = {targetRow, targetColumn};

        System.out.println("Enter positions for drones");
        int[][] dronePositions = new int[4][2];
        for(int i=0;i<4;i++) {
            System.out.print("Drone " + (i + 1) + " position rows & Columns: ");
            dronePositions[i][0] = scanner.nextInt();
            dronePositions[i][1] = scanner.nextInt();
        }

        for(int i=0;i<4;i++) {
            int[] start = dronePositions[i];

            System.out.print("Path for Drone " + (i + 1) + ": ");
            boolean pathFound = findPath(grid, start, target);
            if (pathFound) {
                printPath(grid);
            } else {
                System.out.println("Path not found");
            }

            resetGrid(grid);
        }
    }

    private static boolean findPath(int[][] grid, int[] current, int[] target) {
        int currentRow = current[0];
        int currentCol = current[1];
        grid[currentRow][currentCol]=1;
        while (currentRow != target[0] || currentCol != target[1]) {

            if (currentRow < target[0]) {
                currentRow++;
            } else if (currentRow > target[0]) {
                currentRow--;
            }
            if (currentCol < target[1]) {
                currentCol++;
            } else if (currentCol > target[1]) {
                currentCol--;
            }

           grid[currentRow][currentCol]=1;
        }
        return true;
    }

    private static void resetGrid(int[][] grid) {
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                grid[i][j]=0;
            }
        }
    }

    private static void printPath(int[][] grid) {
        for(int[] row : grid) {
            for(int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class PracticeProblem {
    public static void main(String[] args) {
        String[][] maze = {
            {"", "", "", "F"},
            {"", "*", "", ""},
            {"S", "", "", ""}
        };

        System.out.println("Minimum Moves: " + searchMazeMoves(maze)); // Example output: 5
    }

    public static int searchMazeMoves(String[][] arr) {
        int startRow = arr.length - 1; // start at bottom left
        int startCol = 0;
        boolean[][] visited = new boolean[arr.length][arr[0].length];

        int result = searchMazeMovesHelper(arr, startRow, startCol, 0, visited);
        return (result == Integer.MAX_VALUE) ? -1 : result;
    }

    public static int searchMazeMovesHelper(String[][] arr, int curRow, int curCol, int moves, boolean[][] visited) {
        // base case 1: out of bounds
        if (curRow < 0 || curRow >= arr.length || curCol < 0 || curCol >= arr[0].length) {
            return Integer.MAX_VALUE;
        }

        // base case 2: hit a wall
        if (arr[curRow][curCol].equals("*")) {
            return Integer.MAX_VALUE;
        }

        // base case 3: already visited
        if (visited[curRow][curCol]) {
            return Integer.MAX_VALUE;
        }

        // base case 4: reached the finish
        if (arr[curRow][curCol].equals("F")) {
            return moves;
        }

        // mark as visited
        visited[curRow][curCol] = true;

        // recursive exploration (all 4 directions)
        int right = searchMazeMovesHelper(arr, curRow, curCol + 1, moves + 1, visited);
        int left  = searchMazeMovesHelper(arr, curRow, curCol - 1, moves + 1, visited);
        int up    = searchMazeMovesHelper(arr, curRow - 1, curCol, moves + 1, visited);
        int down  = searchMazeMovesHelper(arr, curRow + 1, curCol, moves + 1, visited);

        // backtrack (unmark current cell)
        visited[curRow][curCol] = false;

        // return shortest valid move count
        return Math.min(Math.min(right, left), Math.min(up, down));
    }
}

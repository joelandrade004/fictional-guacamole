public class SudokuBacktracking {
    public static boolean isSafe(int[][] board, int row, int col, int num) {
        for (int i = 0; i < board.length; i++)
        {
            if (board[row][i] == num) {
                return false;
            }
        }

        int j;
        for (j  = 0;j < board.length; j++) {
            if (board[j][col] == num) {
                return false;
            }
        }

        int sqrt = (int)Math.sqrt(board.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int i = boxRowStart; i < boxRowStart + sqrt; i++) {
            for (j = boxColStart; j < boxColStart + sqrt; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean solveSudoku(int[][] board, int num) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        if (isEmpty) {
            return true;
        }

        for (int x = 1; x <= num; x++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board, x)) {
                    return true;
                } else {
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

    public static void print(int[][] board, int num) {
        for (int x = 0; x < num; x++) {
            for (int y = 0; y < num; y++) {
                System.out.print(board[x][y]);
                System.out.print(" ");
            }
            System.out.print("\n");

            if ((x + 1) % (int)Math.sqrt(num) == 0) {
                System.out.print("");
            }
        }
    }

    public static void main(String[] args) {
        int[][] board =  new int[][] {
                {0,0,0,0,7,8,5,0,0},
                {0,0,4,0,0,0,0,2,0},
                {1,0,0,0,0,0,0,6,7},
                {0,0,0,0,0,0,9,0,0},
                {0,0,0,9,0,0,0,7,0},
                {6,4,0,2,0,0,0,0,8},
                {0,0,8,0,0,0,0,0,0},
                {7,0,0,1,0,6,0,0,2},
                {0,9,0,0,0,0,0,5,0}
        };
        int num = board.length;

        if (solveSudoku(board, num)) {
            print(board, num);
        } else {
            System.out.println("No solution");
        }
    }

}

/**
 * Created by dharmaveerkalluri on 23/2/2018.
 */
public class OthelloGameBoard {

    private char[][] board;

    private final char[] boardColHeaders = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    private static final int BOARD_SIZE=8;
    public static final char player1 = 'X';
    public static final char player2 = 'O';
    private static final char empty = '_';
    private static final String GAME_DRAW_MSG = "Game Draw. ( %s vs %s )";
    private static final String WINNING_MSG = "Player %s wins. ( %s vs %s)";

    private int player1Score, player2Score;

    private static final int[] ROW_DIRECTION_TRAVERSAL_OFFSETS = {-1, -1, -1,  0,  0,  1,  1,  1};
    private static final int[] COL_DIRECTION_TRAVERSAL_OFFSETS = {-1,  0,  1, -1,  1, -1,  0,  1};

    public OthelloGameBoard() {
        board = new char[][] {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_',player2,player1,'_','_','_'},
                {'_','_','_',player1,player2,'_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'}
        };
    }

    protected void init(char[][] board){
        this.board = board;
    }

    public boolean isValidMove(int row, int col, char player) {
        // check whether this square is empty
        if (board[row][col] != empty) {
            return false;
        }

        char oppPlayer = (player == player1) ? player2 : player1;

        boolean isValid = false;
        // check in all 8 directions
        for (int i = 0; i < 8; ++i) {
            int curRow = row + ROW_DIRECTION_TRAVERSAL_OFFSETS[i];
            int curCol = col + COL_DIRECTION_TRAVERSAL_OFFSETS[i];
            boolean hasOppPlayerInBetween = false;
            while (curRow >=0 && curRow < BOARD_SIZE && curCol >= 0 && curCol < BOARD_SIZE) {

                if (board[curRow][curCol] == oppPlayer) {
                    hasOppPlayerInBetween = true;
                }
                else if ((board[curRow][curCol] == player) && hasOppPlayerInBetween) {
                    isValid = true;
                    break;
                }
                else {
                    break;
                }

                curRow += ROW_DIRECTION_TRAVERSAL_OFFSETS[i];
                curCol += COL_DIRECTION_TRAVERSAL_OFFSETS[i];
            }

            if (isValid) {
                break;
            }
        }

        return isValid;
    }

    public void placeMove(int row, int col, char player) {
        board[row][col] = player;

        // check in all 8 directions
        for (int i = 0; i < BOARD_SIZE; i++) {
            int curRow = row + ROW_DIRECTION_TRAVERSAL_OFFSETS[i];
            int curCol = col + COL_DIRECTION_TRAVERSAL_OFFSETS[i];

            boolean hasOppPlayerInBetween = false;
            while (curRow >=0 && curRow < BOARD_SIZE && curCol >= 0 && curCol < BOARD_SIZE) {
                // if empty square, break
                if (board[curRow][curCol] == empty) {
                    //go to next direction.
                    break;
                }

                if (board[curRow][curCol] != player) {
                    hasOppPlayerInBetween = true;
                }

                if ((board[curRow][curCol] == player) && hasOppPlayerInBetween) {
                    int changeCellRow = row + ROW_DIRECTION_TRAVERSAL_OFFSETS[i];
                    int changeCellCol = col + COL_DIRECTION_TRAVERSAL_OFFSETS[i];
                    while (changeCellRow != curRow || changeCellCol != curCol) {
                        board[changeCellRow][changeCellCol] = player;
                        changeCellRow += ROW_DIRECTION_TRAVERSAL_OFFSETS[i];
                        changeCellCol += COL_DIRECTION_TRAVERSAL_OFFSETS[i];
                    }

                    // go to next direction.
                    break;
                }

                curRow += ROW_DIRECTION_TRAVERSAL_OFFSETS[i];
                curCol += COL_DIRECTION_TRAVERSAL_OFFSETS[i];
            }
        }

        displayBoard();
    }

    public void displayBoard() {
        System.out.println();
        for(int i=0; i<BOARD_SIZE; i++){
            System.out.print(i+1+ " ");
            for(int j=0; j<BOARD_SIZE;j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print("\n  ");
        for(int i=0; i<BOARD_SIZE; i++){
            System.out.print(boardColHeaders[i] + " ");
        }
        System.out.println("\n");
    }

    public boolean atleastOneValidMoveAvailable(char player){
        for(int i=0;i <BOARD_SIZE; i++){
            for(int j=0; j<BOARD_SIZE; j++){
                if(isValidMove(i, j, player)){
                    return true;
                }
            }
        }
        return false;
    }

    public String judgeGameResult() {
        updateScores();
        int result = player1Score -  player2Score;
        if(result == 0){
            return String.format(GAME_DRAW_MSG, player1Score, player2Score);
        } else if(result > 0) {
            return String.format(WINNING_MSG, player1, player1Score, player2Score);
        } else {
            return String.format(WINNING_MSG, player2, player2Score, player1Score);
        }
    }

    private void updateScores() {
        player1Score =0; player2Score = 0;
        for(int i=0; i<BOARD_SIZE; i++){
            for(int j=0; j<BOARD_SIZE; j++){
                if(board[i][j] == player1){
                    player1Score++;
                }
                else if(board[i][j] == player2){
                    player2Score++;
                }
            }
        }
    }


    protected char[][] getBoardState() {
        return board;
    }
}

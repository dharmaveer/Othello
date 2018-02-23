import java.util.Scanner;

/**
 * Created by dharmaveerkalluri on 23/2/2018.
 */
public class OthelloGamePlay {

    private OthelloGameBoard othelloGameBoard;

    private Scanner scan ;

    private boolean player1Turn = true;

    public OthelloGamePlay(OthelloGameBoard othelloGameBoard) {
        this.othelloGameBoard = othelloGameBoard;
    }

    public static void main(String[] args){

        OthelloGamePlay othelloGamePlay = new OthelloGamePlay(new OthelloGameBoard());

        othelloGamePlay.startGame();

    }

    public void startGame(){
        othelloGameBoard.displayBoard();
        scan = new Scanner(System.in);
        getNextMove();
    }

    private void getNextMove() {
        char player, oppPlayer;
        if(player1Turn){
            player = OthelloGameBoard.player1;
            oppPlayer = OthelloGameBoard.player2;
        } else {
            player = OthelloGameBoard.player2;
            oppPlayer = OthelloGameBoard.player1;
        }

        if(othelloGameBoard.atleastOneValidMoveAvailable(player)) {
            System.out.println("Place move for player " + player + ":");
            String input = scan.next();

            MoveCoordinates moveCoordinates = new MoveCoordinates(input);
            if (othelloGameBoard.isValidMove(moveCoordinates.getX(), moveCoordinates.getY(), player)) {
                othelloGameBoard.placeMove(moveCoordinates.getX(), moveCoordinates.getY(), player);
                changeTurn();
            } else {
                System.out.println("Invalid move for player " + player);
            }
        } else if(othelloGameBoard.atleastOneValidMoveAvailable(oppPlayer)) {
            System.out.println("Player " + player + " does not have any valid moves, Passing to " + oppPlayer);
            changeTurn();
        } else {
            System.out.println("Game finished");
            System.out.println(othelloGameBoard.judgeGameResult());
            return;
        }
        getNextMove();
    }

    private void changeTurn() {
        player1Turn = !player1Turn;
    }
}

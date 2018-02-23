import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.*;

/**
 * Created by dharmaveerkalluri on 23/2/2018.
 */
public class OthelloGameBoardTest {

    private OthelloGameBoard othelloGameBoard;

    @Before
    public void setUp() throws Exception {
        othelloGameBoard = new OthelloGameBoard();
        othelloGameBoard.init(new char[][] {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','O','X','_','_','_'},
                {'_','_','_','X','X','_','_','_'},
                {'_','_','_','_','X','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'}});
    }

    @Test
    public void isValidMove() throws Exception {
        assertThat(othelloGameBoard.isValidMove(2, 2, 'X')).isTrue();
        assertThat(othelloGameBoard.isValidMove(2, 2, 'O')).isFalse();
        assertThat(othelloGameBoard.isValidMove(3, 2, 'X')).isTrue();
        assertThat(othelloGameBoard.isValidMove(5, 3, 'O')).isTrue();
    }

    @Test
    public void placeMoveVertically() {
        othelloGameBoard.placeMove(5, 3, 'O');

        assertThat(othelloGameBoard.getBoardState()).isEqualTo(new char[][] {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','O','X','_','_','_'},
                {'_','_','_','O','X','_','_','_'},
                {'_','_','_','O','X','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'}});
    }

    @Test
    public void placeMoveDiagonally() {
        othelloGameBoard.placeMove(5, 5, 'O');

        assertThat(othelloGameBoard.getBoardState()).isEqualTo(new char[][] {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','O','X','_','_','_'},
                {'_','_','_','X','O','_','_','_'},
                {'_','_','_','_','X','O','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'}});
    }

    @Test
    public void placeMoveHorizontally() {
        othelloGameBoard.placeMove(3, 5, 'O');

        assertThat(othelloGameBoard.getBoardState()).isEqualTo(new char[][] {
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','O','O','O','_','_'},
                {'_','_','_','X','X','_','_','_'},
                {'_','_','_','_','X','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'}});
    }

    @Test
    public void atleastOneValidMoveAvailableReturnsTrue() {
        assertThat(othelloGameBoard.atleastOneValidMoveAvailable('O')).isTrue();
    }

    @Test
    public void atleastOneValidMoveAvailableReturnsFalse() {
        othelloGameBoard.init(new char[][] {
                {'_','O','_','_','_','_','_','_'},
                {'_','O','_','_','_','_','_','O'},
                {'_','O','O','O','O','_','O','_'},
                {'_','O','O','O','O','O','_','_'},
                {'X','O','O','X','X','O','O','O'},
                {'X','X','X','X','O','X','X','X'},
                {'X','X','O','O','X','O','X','X'},
                {'X','X','X','X','X','X','X','X'}});
        assertThat(othelloGameBoard.atleastOneValidMoveAvailable('O')).isFalse();
    }

    @Test
    public void judgeGameResult(){
        othelloGameBoard.init(new char[][] {
                {'_','O','_','_','_','_','_','_'},
                {'_','O','_','_','_','_','_','O'},
                {'_','O','O','O','O','_','O','_'},
                {'_','O','O','O','O','O','_','_'},
                {'X','O','O','X','X','O','O','O'},
                {'X','X','X','X','O','X','X','X'},
                {'X','X','O','O','X','O','X','X'},
                {'X','X','X','X','X','X','X','X'}});
        assertThat(othelloGameBoard.judgeGameResult()).isEqualTo("Player X wins. ( 23 vs 22)");
    }


}
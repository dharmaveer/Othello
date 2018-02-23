import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dharmaveerkalluri on 23/2/2018.
 */
public class MoveCoordinatesTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void parseInputCoordinates() {
        MoveCoordinates moveCoordinates = new MoveCoordinates("d3");
        assertThat(moveCoordinates.toString()).isEqualTo("(2, 3)");

        moveCoordinates = new MoveCoordinates("3d");
        assertThat(moveCoordinates.toString()).isEqualTo("(2, 3)");

        moveCoordinates = new MoveCoordinates("3D");
        assertThat(moveCoordinates.toString()).isEqualTo("(2, 3)");
    }

    @Test (expected = IllegalArgumentException.class)
    public void parseInvalidInputThrowsException() {
        MoveCoordinates moveCoordinates = new MoveCoordinates("d9");
    }

}
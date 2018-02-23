/**
 * Created by dharmaveerkalluri on 23/2/2018.
 */
public class MoveCoordinates {
    private int x, y;

    public MoveCoordinates(String inputCoordinates) {
        if(Character.isDigit(inputCoordinates.charAt(0))){
            x = Character.getNumericValue(inputCoordinates.charAt(0)) - 1;
            y = Character.getNumericValue(inputCoordinates.charAt(1)) - 10;
        } else if(Character.isDigit(inputCoordinates.charAt(1))){
            x = Character.getNumericValue(inputCoordinates.charAt(1)) - 1;
            y = Character.getNumericValue(inputCoordinates.charAt(0)) - 10;
        } else {
            throw new IllegalArgumentException();
        }

        if(x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IllegalArgumentException();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}

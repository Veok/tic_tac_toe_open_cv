package TicTacToe.model;

/**
 * @author Lelental on 16.04.2017.
 */
public class Player {

    private int score;
    private Mark mark;

    public Player(Mark mark) {
        this.mark = mark;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}

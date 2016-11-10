package udesc.br.rakesfoot.game.rules;

public class Result {

    public static final int DEFEAT_POINTS  = 0,
                            DRAW_POINTS    = 1,
                            VICTORY_POINTS = 3;

    public static int getPointsByResult(udesc.br.rakesfoot.game.model.Result result) {
        switch(result) {
            case NONE:
            case GUEST:
                return DEFEAT_POINTS;

            case DRAW:
                return DRAW_POINTS;

            default:
                return VICTORY_POINTS;
        }
    }

}

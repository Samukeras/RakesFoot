package udesc.br.rakesfoot.game.model;

/**
 * Created by Ricardo on 10/11/2016.
 */
public enum FormationType {

    TYPE_451(4,5,1),
    TYPE_442(4,4,2),
    TYPE_433(4,3,3),
    TYPE_424(4,2,4),
    TYPE_541(5,4,1),
    TYPE_532(5,3,2),
    TYPE_523(5,2,3),
    TYPE_352(3,5,2),
    TYPE_343(3,4,3),
    TYPE_334(3,3,4);

    private final int defender;
    private final int midfielder;
    private final int forward;

    FormationType(int defender, int midfielder, int forward) {
        this.defender = defender;
        this.midfielder = midfielder;
        this.forward = forward;
    }

    public int getFirstTeamQuantity(Position position) {
        switch (position) {
            case DEFENDER:
                return defender;
            case MIDFIELDER:
                return midfielder;
            case FORWARD:
                return forward;
            default:
                return 1;
        }
    }

    public int getSubstituteQuantity(Position position) {
        switch (position) {
            case GOALKEEPER:
                return 1;
            default:
                return 2;
        }
    }
}

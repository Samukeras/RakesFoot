package udesc.br.rakesfoot.game.simulator;

/**
 * Created by Ricardo on 03/11/2016.
 */
public interface SimulatorListener<Type extends Object> {
    void onTrigger(Type event);
}

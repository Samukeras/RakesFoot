package udesc.br.rakesfoot.game.simulator;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import udesc.br.rakesfoot.game.model.Event;

/**
 * Created by Ricardo on 02/11/2016.
 */
public abstract class Simulator<Transient extends Object> implements Runnable {

    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    private List<Transient> transients = new ArrayList<>();

    private List<SimulatorListener> listeners = new ArrayList<>();

    private Transient current;

    public Transient getCurrent() {
        return current;
    }

    public void setCurrent(Transient current) {
        this.current = current;
    }

    public boolean register(Transient object) {
        return transients.add(object);
    }

    public List<Transient> getTransients() {
        return transients;
    }

    public boolean addListener(SimulatorListener listener) {
        return listeners.add(listener);
    }

    protected void notifyListeners(Object event) {
        for (SimulatorListener listener : listeners) {
            listener.onTrigger(event);
        }
    }

    @Override
    public void run() {
        iterate();
    }

    private void iterate() {
        for (Transient match : transients) {
            setCurrent(match);
            simulate();
        }
    }

    abstract void simulate();

}

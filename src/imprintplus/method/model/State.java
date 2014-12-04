/**
 * 
 */
package imprintplus.method.model;

import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.Collection;

/**
 * @author Sandhya
 *
 */
public class State<C> {
	
	private C key;

    private Random random;

    private TreeMap<Double, State<C>> transitions;

    private double sum_of_probabilities = 0.0;

    /**
     *       
     * @param key
     *            the key for the state
     * @throws IllegalArgumentException
     *             if key is null
     */
    public State(C _key) {
        this(_key, new Random());
    }

    /**
     * Constructs a {@code State}.
     * 
     * @param key
     *            the key for the state
     * @param random
     *            source of random numbers for calculating the next state
     * @throws IllegalArgumentException
     *             if key or random is null
     */
    public State(C _key, Random _random) {
        if (_key == null) {
            throw new IllegalArgumentException("key must not be null");
        }

        if (_random == null) {
            throw new IllegalArgumentException("random must not be null");
        }

        this.key = _key;
        this.random = _random;
        this.transitions = new TreeMap<>();
    }

    /**
     * Adds a transition to another state with the given probability.
     * 
     * @param otherState
     *            the to-state for the transition
     * @param probability
     *            the probability that the transition will occur
     * @throws ImprintTransitionAlreadyDefinedException 
     *             if a transition has already been defined to the given state
     * @throws IllegalArgumentException
     *             if the probability is not between 0 and 1 inclusive or the
     *             sum of probabilities for all transitions exceeds 1
     */
    public void addTransition(State<C> _otherstate, double _probability) throws ImprintTransitionAlreadyDefinedException {
        if (_probability < 0.0 || _probability > 1.0) {
            throw new IllegalArgumentException("probability must be between 0 and 1 inclusive");
        }

        if (transitions.containsValue(_otherstate)) {
            throw new ImprintTransitionAlreadyDefinedException(String.format(
                    "Transition already defined from %s to %s", this.key.toString(),
                    _otherstate.toString()));
        }

        if (sum_of_probabilities + _probability > 1.01) {
            throw new IllegalArgumentException(
                    "The sum of probabilities after adding this new transition must not exceed 1.0");
        }

        this.transitions.put(_probability + sum_of_probabilities, _otherstate);
        this.sum_of_probabilities += _probability;
    }

    /**
     * Gets all the states this state has a transition to
     * 
     * @return collection of transition states
     */
    public Collection<State<C>> getTransitions() {
        return this.transitions.values();
    }

    /**
     * Returns one of the states in the defined transitions based on the probabilities.
     * If the sum of probabilities for all transitions is less than 1,
     * then the rest of the probability space will represent a
     * transition back onto the same state.
     * 
     * @return a state from the transitions
     */
    public State<C> getNextState() {
        double rand = this.random.nextDouble();

        Entry<Double, State<C>> e = this.transitions.higherEntry(rand);

        return e != null ? e.getValue() : this;
    }

    /**
     * Gets the key for this state.
     * 
     * @return the key
     */
    public C getKey() {
        return this.key;
    }

    @Override
    public String toString() {
        return this.key.toString();
    }
}

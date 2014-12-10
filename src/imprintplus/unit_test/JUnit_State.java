/**
 * @author Raj
 */
package imprintplus.unit_test;

import static org.junit.Assert.*;

import java.util.Random;

import imprintplus.method.model.State;
import imprintplus.method.model.ImprintTransitionAlreadyDefinedException;

import org.junit.Test;

public class JUnit_State {

	/**
     * If a state has no transitions then {@link State#getNextState()} should
     * return itself.
     */
    @Test
    public void getNextStateWithNoTransitions() {
        Object _key = new Object();
        State<Object> _state = new State<Object>(_key);
        State<Object> _new_state = _state.getNextState();
        assertEquals(_state, _new_state);
    }

    /**
     * If there is one transition with probability 1.0 then that transition
     * should be taken.
     * @throws ImprintTransitionAlreadyDefinedException 
     */
    @Test
    public void getNextStateWithOneTransition() throws ImprintTransitionAlreadyDefinedException {
        State<Object> _state1 = new State<>(new Object());
        State<Object> _state2 = new State<>(new Object());

        _state1.addTransition(_state2, 1);

        State<Object> _next_state = _state1.getNextState();

        assertEquals(_state2, _next_state);
    }

    /**
     * Test transition to self
     * @throws ImprintTransitionAlreadyDefinedException 
     */
    @Test
    public void selfTransition() throws ImprintTransitionAlreadyDefinedException {
        State<Object> _state = new State<>(new Object());
        _state.addTransition(_state, 1.0);

        State<Object> _next_state = _state.getNextState();

        assertEquals(_state, _next_state);
    }

    /**
     * If a transition with 0 probability is defined, then it should not be
     * taken
     * @throws ImprintTransitionAlreadyDefinedException 
     */
    @Test
    public void zeroProbabilityTransition() throws ImprintTransitionAlreadyDefinedException {
        State<Object> _state1 = new State<>(new Object());
        State<Object> _state2 = new State<>(new Object());

        _state1.addTransition(_state2, 0);

        State<Object> _next_state = _state1.getNextState();

        assertEquals(_state1, _next_state);
    }

    /**
     * If a transition to the same state is defined twice then
     * 
     * IllegalArgumentException should be thrown
     * 
     * @throws ImprintTransitionAlreadyDefinedException 
     */
    @Test(expected = ImprintTransitionAlreadyDefinedException.class)
    public void addTransitionToSameStateTwice() throws ImprintTransitionAlreadyDefinedException {
        State<Object> _state1 = new State<>(new Object());
        State<Object> _state2 = new State<>(new Object());

        _state1.addTransition(_state2, 0);
        _state1.addTransition(_state2, 0);
    }

    /**
     * Passing in a negative probability should result in an
     * IllegalArgumentException
     * 
     * @throws ImprintTransitionAlreadyDefinedException 
     */
    @Test(expected = IllegalArgumentException.class)
    public void negativeProbability() throws ImprintTransitionAlreadyDefinedException {
        State<Object> _state = new State<>(new Object());
        _state.addTransition(_state, -1);
    }

    /**
     * Passing in a probability > 1 should result in an 
     * IllegalArgumentException
     * 
     * @throws ImprintTransitionAlreadyDefinedException 
     */
    @Test(expected = IllegalArgumentException.class)
    public void greaterThanOneProbability() throws ImprintTransitionAlreadyDefinedException {
        State<Object> _state = new State<>(new Object());
        _state.addTransition(_state, 1.1);
    }

    /**
     * If the sum of probabilities over all transitions would exceed 1, then
     * IllegalArgumentException should be thrown
     * 
     * @throws ImprintTransitionAlreadyDefinedException 
     */
    @Test(expected = IllegalArgumentException.class)
    public void sumOfProbabilitiesExceedsOne() throws ImprintTransitionAlreadyDefinedException {
        State<Object> _state1 = new State<>(new Object());
        State<Object> _state2 = new State<>(new Object());

        _state1.addTransition(_state1, 0.9);
        _state1.addTransition(_state2, 0.2);
    }
       
  	/**
     * getKey() should return the correct key
     */
    @Test
    public void testGetKey() {
        Object _key = new Object();
        State<Object> _state = new State<>(_key);
        assertEquals(_key, _state.getKey());
    }

    /**
     * toString() should return toString() on the key
     */
    @Test
    public void testToString() {
        String s = "test";
        State<String> _state = new State<>(s);
        assertEquals(s, _state.toString());
    }

    /**
     * Should throw an exception if key is null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullKey() {
        new State<Object>(null);
    }

    /**
     * Should throw an exception if random is null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullRandom() {
        new State<Object>("state", null);
    }

}
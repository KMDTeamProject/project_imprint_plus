/**
 * @author Raj
 */
package imprintplus.unit_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import imprintplus.method.model.ImprintTransitionAlreadyDefinedException;
import imprintplus.method.model.MarkovChain;
import imprintplus.method.model.ImprintStateAlreadyDefinedException;
import imprintplus.method.model.ImprintStateNotDefinedException;

public class JUnit_MarkovChain {
	
	    private MarkovChain<String> markovChain;

	    /**
	     * Setup the test object
	     */
	    @Before
	    public void setUp() {
	        this.markovChain = new MarkovChain<String>();
	    }

	    /**
	     * Make sure adding, setting, getting, and checking for a state to work
	     * 
	     * @throws ImprintStateAlreadyDefinedException 
	     * @throws ImprintStateNotDefinedException 
	     */
	    @Test
	    public void addGetSetContainsState() throws ImprintStateAlreadyDefinedException, ImprintStateNotDefinedException {
	        markovChain.addState("test");
	        markovChain.setState("test");
	        
	        assertTrue(markovChain.containsState("test"));
	        assertFalse(markovChain.containsState("test1"));
	        assertFalse(markovChain.containsState("random"));
	        assertFalse(markovChain.containsState("null"));
	        assertEquals("test", markovChain.getCurrentState());
	    }

	    /**
	     *
	     * Setting state that hasn't been defined 
	     * should throw exception
	     * 
	     * @throws ImprintStateNotDefinedException 
	     */
	    @Test(expected = ImprintStateNotDefinedException.class)
	    public void stateNotDefined() throws ImprintStateNotDefinedException {
	        markovChain.setState("not defined");
	        // TODO IMPLEMENT
	    }

	    /**
	     * Adding two states with same key should throw exception
	     * 
	     * @throws ImprintStateAlreadyDefinedException 
	     */
	    @Test(expected = ImprintStateAlreadyDefinedException.class)
	    public void stateAlreadyDefined() throws ImprintStateAlreadyDefinedException {
	        markovChain.addState("already defined");
	        markovChain.addState("already defined");
	    }

	    /**
	     * Make sure defining a transition and calling transition() works as expected
	     * 
	     * @throws ImprintStateAlreadyDefinedException 
	     * @throws ImprintTransitionAlreadyDefinedException 
	     * @throws ImprintStateNotDefinedException 
	     */
	    @Test
	    public void testTransition() throws ImprintStateAlreadyDefinedException, ImprintStateNotDefinedException, ImprintTransitionAlreadyDefinedException {
	        markovChain.addState("s1");
	        markovChain.addState("s2");
	        markovChain.addTransition("s1", "s2", 1.0);
	        markovChain.setState("s1");
	        markovChain.transition();
	        assertEquals("s2", markovChain.getCurrentState());

	        Set<String> transitionStates = markovChain.getTransitionsForState("s1");
	        assertEquals(1, transitionStates.size());
	        assertTrue(transitionStates.contains("s2"));
	    }

	    /**
	     * Defining a transition where the "from" state hasn't been defined should
	     * throw an exception
	     * 
	     * @throws ImprintStateAlreadyDefinedException 
	     * @throws ImprintTransitionAlreadyDefinedException 
	     * @throws ImprintStateNotDefinedException 
	     */
	    @Test(expected = ImprintStateNotDefinedException.class)
	    public void fromImprintStateNotDefinedTransition() throws ImprintStateAlreadyDefinedException, ImprintStateNotDefinedException, ImprintTransitionAlreadyDefinedException {
	        markovChain.addState("s1");
	        markovChain.addTransition("s2", "s1", 0.5);
	    }

	    /**
	     * Defining a transition where the "to" state hasn't been defined should
	     * throw an exception
	     * @throws ImprintStateAlreadyDefinedException 
	     * @throws ImprintTransitionAlreadyDefinedException 
	     * @throws ImprintStateNotDefinedException 
	     */
	    @Test(expected = ImprintStateNotDefinedException.class)
	    public void toImprintStateNotDefinedTransition() throws ImprintStateAlreadyDefinedException, ImprintStateNotDefinedException, ImprintTransitionAlreadyDefinedException {
	        markovChain.addState("s1");
	        markovChain.addTransition("s1", "s2", 0.5);
	    }

	    /**
	     * Shouldn't accept a negative probability
	     * @throws ImprintStateAlreadyDefinedException 
	     * @throws ImprintTransitionAlreadyDefinedException 
	     * @throws ImprintStateNotDefinedException 
	     */
	    @Test(expected = IllegalArgumentException.class)
	    public void negativeProbability() throws ImprintStateAlreadyDefinedException, ImprintStateNotDefinedException, ImprintTransitionAlreadyDefinedException {
	        markovChain.addState("s1");
	        markovChain.addTransition("s1", "s1", -1);
	    }

	    /**
	     * Shouldn't accept a probability > 1
	     * @throws ImprintStateAlreadyDefinedException 
	     * @throws ImprintTransitionAlreadyDefinedException 
	     * @throws ImprintStateNotDefinedException 
	     */
	    @Test(expected = IllegalArgumentException.class)
	    public void greaterThanOneProbability() throws ImprintStateAlreadyDefinedException, ImprintStateNotDefinedException, ImprintTransitionAlreadyDefinedException {
	        markovChain.addState("s1");
	        markovChain.addTransition("s1", "s1", 1.1);
	    }

	    /**
	     * null should be returned if no states are defined as well as after
	     * transition() is called
	     */
	    @Test
	    public void noStatesDefinedTransition() {
	        assertEquals(null, markovChain.getCurrentState());
	        markovChain.transition();
	        assertEquals(null, markovChain.getCurrentState());
	    }

	    /**
	     * Exception should be thrown if from-state for transition is null
	     * @throws ImprintStateAlreadyDefinedException 
	     * @throws ImprintTransitionAlreadyDefinedException 
	     * @throws ImprintStateNotDefinedException 
	     */
	    @Test
	    public void nullFromTransition() throws ImprintStateAlreadyDefinedException, ImprintStateNotDefinedException, ImprintTransitionAlreadyDefinedException {
	        markovChain.addState("s1");
	        markovChain.addState("s2");
	        try {
		        markovChain.addTransition(null, "s1", 0.5);
		        fail("Was expecting IllegalArgumentException.");
	        } catch (IllegalArgumentException e) {
	        	assertTrue(true);
	        } catch (Exception e) {
		        fail("Was expecting IllegalArgumentException.");	        	
	        }
	        
	        try {
		        markovChain.addTransition("s1", null, 0.5);
		        fail("Was expecting IllegalArgumentException.");
	        } catch (IllegalArgumentException e) {
	        	assertTrue(true);
	        } catch (Exception e) {
		        fail("Was expecting IllegalArgumentException.");	        	
	        } 
	        
	        try {
		        markovChain.addTransition("s1", "s2", 1.5);
		        fail("Was expecting IllegalArgumentException.");
	        } catch (IllegalArgumentException e) {
	        	assertTrue(true);
	        } catch (Exception e) {
		        fail("Was expecting IllegalArgumentException.");	        	
	        } 

	    }

	    /**
	     * Null shouldn't be a state
	     * @throws ImprintStateAlreadyDefinedException 
	     */
	    @Test(expected = IllegalArgumentException.class)
	    public void nullState() throws ImprintStateAlreadyDefinedException {
	        assertFalse(markovChain.containsState(null));
	        markovChain.addState(null);
	    }

	    /**
	     * Test getStates()
	     * @throws ImprintStateAlreadyDefinedException 
	     */
	    @Test
	    public void getStates() throws ImprintStateAlreadyDefinedException {
	        assertEquals(0, markovChain.getStates().size());
	        markovChain.addState("state");
	        assertEquals(1, markovChain.getStates().size());
	    }

	    /**
	     * Should throw IllegalArgumentException when getting transitions for null
	     * @throws ImprintStateNotDefinedException 
	     */
	    @Test(expected = IllegalArgumentException.class)
	    public void getTransitionsForNull() throws ImprintStateNotDefinedException {
	        markovChain.getTransitionsForState(null);
	    }

	    /**
	     * Should throw IllegalArgumentException when getting transitions for
	     * undefined state
	     * @throws ImprintStateNotDefinedException 
	     */
	    @Test(expected = ImprintStateNotDefinedException.class)
	    public void getTransitionsForNotDefined() throws ImprintStateNotDefinedException {
	        markovChain.getTransitionsForState("state");
	    }

	    /**
	     * Tests the fromStrings factory method
	     * @throws ImprintTransitionAlreadyDefinedException 
	     * @throws ImprintStateNotDefinedException 
	     * @throws ImprintStateAlreadyDefinedException 
	     */
	    @Test
	    public void fromStrings() throws ImprintStateAlreadyDefinedException, ImprintStateNotDefinedException, ImprintTransitionAlreadyDefinedException {
	        List<String> strings = new ArrayList<>();
	        strings.add("play");
	        strings.add("game");
	        strings.add("and");
	        strings.add("play");
	        strings.add("game");

	        MarkovChain<String> markovChain = MarkovChain.fromStrings(strings.iterator());

	        assertTrue(markovChain.containsState("play"));
	        assertTrue(markovChain.containsState("game"));
	        assertTrue(markovChain.containsState("and"));

	        Set<String> playTransitions = markovChain.getTransitionsForState("play");
	        assertEquals(1, playTransitions.size());
	        assertTrue(playTransitions.contains("game"));

	        Set<String> gameTransitions = markovChain.getTransitionsForState("game");
	        assertEquals(1, gameTransitions.size());
	        assertTrue(gameTransitions.contains("and"));

	        Set<String> andTransitions = markovChain.getTransitionsForState("and");
	        assertEquals(1, andTransitions.size());
	        assertTrue(andTransitions.contains("play"));
	    }

	    /**
	     * giving fromStrings a null argument should throw IllegalArgumentException
	     * @throws ImprintTransitionAlreadyDefinedException 
	     * @throws ImprintStateNotDefinedException 
	     * @throws ImprintStateAlreadyDefinedException 
	     */
	    @Test(expected = IllegalArgumentException.class)
	    public void fromStringNullIterator() throws ImprintStateAlreadyDefinedException, ImprintStateNotDefinedException, ImprintTransitionAlreadyDefinedException {
	        MarkovChain.fromStrings(null);
	    }
}

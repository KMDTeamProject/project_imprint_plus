package imprintplus.method.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * Implementation of Markov Chain using cluster series
 * 
 * @author Sandhya
 *
 * @param <C>
 *            The type to use as the key for states.
 */

public class MarkovChain<C> {

	private Map<C, State<C>> init_states;

	private State<C> current_state;

	/**
	 * Constructs {@code MarkovChain) with no states
	 */
	public MarkovChain() {
		this.init_states = new HashMap<>();
		this.current_state = null;
	}

	/**
	 * Sets the current state. The state must already been defined.
	 * 
	 * @param key
	 *            the key for the state
	 * @throws ImprintStateNotDefinedException
	 *             if the given state has not been defined
	 */
	public void setState(C _key) throws ImprintStateNotDefinedException {
		if (init_states.containsKey(_key)) {
			this.current_state = init_states.get(_key);
		} else {
			throw new ImprintStateNotDefinedException(
					"Cannot set state because it hasn't been defined");
		}
	}

	/**
	 * Adds a state with the given key
	 * 
	 * @param key
	 *            the key of the new state
	 * @throws ImprintStateAlreadyDefinedException
	 *             if the state has already been defined
	 * @throws IllegalArgumentException
	 *             if the key is null
	 */
	public void addState(C _key) throws ImprintStateAlreadyDefinedException {
		if (_key == null) {
			throw new IllegalArgumentException("key must not be null");
		}

		if (this.init_states.containsKey(_key)) {
			throw new ImprintStateAlreadyDefinedException(
					"State already defined");
		}

		this.init_states.put(_key, new State<>(_key));
	}

	/**
	 * Checks whether the state given with the given key has been defined.
	 * 
	 * @param key
	 *            the key of the state
	 * @return true if the state has been defined, false otherwise
	 */
	public boolean containsState(C _key) {
		return this.init_states.containsKey(_key);
	}

	/**
	 * Gets the current state.
	 * 
	 * @return the current state or {@code null} if no current state exists
	 */
	public C getCurrentState() {
		if (this.current_state != null) {
			return this.current_state.getKey();
		}

		return null;
	}

	/**
	 * Returns a set of all the defined states
	 * 
	 * @return the states
	 */
	public Set<C> getStates() {
		return this.init_states.keySet();
	}

	/**
	 * Adds a transition from one state to another with the given probability.
	 * Both of the given states must have already been defined and the
	 * probability must be between 0 and 1 inclusive.
	 * 
	 * @param from
	 *            the from-state for the transition
	 * @param to
	 *            the to-state for the transition
	 * @param probability
	 *            the probability that the transition will be taken
	 * @throws ImprintStateNotDefinedException
	 *             if any of the given states are not yet defined
	 * @throws ImprintTransitionAlreadyDefinedException
	 *             if any of transitions already defined
	 * @throws IllegalArgumentException
	 *             if the probability isn't in the correct range or if the
	 *             states given are null
	 */
	public void addTransition(C _from, C _to, double _probability)
			throws ImprintStateNotDefinedException,
			ImprintTransitionAlreadyDefinedException {
		if (_from == null || _to == null) {
			throw new IllegalArgumentException("from/to cannot be null");
		}

		if (!this.init_states.containsKey(_from)
				|| !this.init_states.containsKey(_to)) {
			throw new ImprintStateNotDefinedException(
					"From and to states must be defined already to create a transition");
		}

		if (_probability < 0.0 || _probability > 1.0) {
			throw new IllegalArgumentException(
					"probability must be between 0 and 1 inclusive");
		}

		State<C> _fromState = this.init_states.get(_from);
		State<C> _toState = this.init_states.get(_to);

		_fromState.addTransition(_toState, _probability);
	}

	/**
	 * Gets all the transition states for the state with the given key
	 * 
	 * @param key
	 *            the key for the state
	 * @return a set of states
	 * @throw ImprintStateNotDefinedException
	 */
	public Set<C> getTransitionsForState(C _key)
			throws ImprintStateNotDefinedException {
		if (_key == null) {
			throw new IllegalArgumentException("key must not be null");
		}

		if (!this.init_states.containsKey(_key)) {
			throw new ImprintStateNotDefinedException(
					"Cannot get transitions for state because it's not defined");
		}

		Set<C> _transitionstates = new HashSet<>();

		for (State<C> _s : init_states.get(_key).getTransitions()) {
			_transitionstates.add(_s.getKey());
		}

		return _transitionstates;
	}

	/**
	 * Transitions from the current state to another state based on the
	 * probabilities of the defined transitions. If the current state (as given
	 * by {@link MarkovChain#getCurrentState()}) is {@code null}, then the new
	 * state will also be {@code null}.
	 */
	public void transition() {
		if (this.current_state == null) {
			return;
		}

		this.current_state = this.current_state.getNextState();
	}
}
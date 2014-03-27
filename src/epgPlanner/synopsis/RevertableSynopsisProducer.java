package epgPlanner.synopsis;

import java.util.ArrayList;

/**
 * A synopsis producer which can be generate synopsis from backwards (using
 * negative index). For example, "-1" would indicate the last item of the
 * synopses and "-2" indicates the second last item.
 * 
 * @author Yu Lu
 */
public abstract class RevertableSynopsisProducer<E> implements SynopsisProducer<E> {
	protected ArrayList<E> synopses;

	public RevertableSynopsisProducer() {
		this.synopses = new ArrayList<E>();
	}

	@Override
	public E getSynopsisFor(int index) {
		int positiveIndex = index;
		// work out the real page number if it starts backward
		if (index < 0) {
			positiveIndex = this.synopses.size() + index;
		}

		if (positiveIndex >= this.synopses.size()) {
			return null;
		}

		return this.synopses.get(positiveIndex);
	}
}

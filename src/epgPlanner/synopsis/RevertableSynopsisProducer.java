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
		//make sure the index falls into the size of synopses.
		int offset = index % this.synopses.size();
		
		// work out the offset if it counts backward
		if (offset < 0) {
			offset += this.synopses.size();
		}

		return this.synopses.get(offset);
	}
}

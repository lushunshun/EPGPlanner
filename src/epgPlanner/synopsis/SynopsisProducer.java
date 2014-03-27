package epgPlanner.synopsis;

/**
 * A generator that produces synopsis for recordings.
 * 
 * @author Yu Lu
 *
 * @param E
 *            anything that represents a single synopsis of a recording
 */
public interface SynopsisProducer<E> {

	/**
	 * Get the synopsis at the specified index within all synopses of the
	 * recording.
	 * <p>
	 * The semantics of "index" should be specified by implementing classes,
	 * e.g. it could indicate the page number or screen number depends on how
	 * the synopsis is going to be displayed.
	 * 
	 * @param index
	 *            the index within all synopses.
	 * @return Synopsis at the specified index. Null if the specified index is
	 *         out of range and no more synopsis available.
	 */
	public E getSynopsisFor(int index);

}

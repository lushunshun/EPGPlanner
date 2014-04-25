package epgPlanner;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import epgPlanner.synopsis.RevertableSynopsisProducer;

public class RevertableSynopsisProducerTest {
	private DefaultRevertableSynopsisProducer producer;
	
	@Before
	public void setUp() {
		producer = new DefaultRevertableSynopsisProducer(5);
	}

	@Test
	public void testPositiveSynopsisIndexWithinRange() {
		String synopsis = producer.getSynopsisFor(2);
		assertEquals("Test synopsis index 2", synopsis);
	}
	
	@Test
	public void testPositiveSynopsisIndexOutOfRange() {
		String synopsis = producer.getSynopsisFor(23);
		assertEquals("Test synopsis index 3", synopsis);
	}
	
	@Test
	public void testSynopsisIndexZero() {
		String synopsis = producer.getSynopsisFor(0);
		assertEquals("Test synopsis index 0", synopsis);
	}
	
	@Test
	public void testNegativeSynopsisIndexWithinRange() {
		String synopsis = producer.getSynopsisFor(-4);
		assertEquals("Test synopsis index 1", synopsis);
	}
	
	@Test
	public void testNegativeSynopsisIndexOutOfRange() {
		String synopsis = producer.getSynopsisFor(-52);
		assertEquals("Test synopsis index 3", synopsis);
	}
	
	private class DefaultRevertableSynopsisProducer extends RevertableSynopsisProducer<String> {
		private int noOfSynposes;
		
		public DefaultRevertableSynopsisProducer(int noOfSynposes) {
			super();
			this.noOfSynposes = noOfSynposes;
			initSynopses();
		}
		
		private void initSynopses() {
			for(int i = 0; i < this.noOfSynposes; i++) {
				this.synopses.add("Test synopsis index " + i);
			}
		}
	}

}

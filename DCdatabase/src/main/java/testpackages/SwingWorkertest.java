/*  +__^_________,_________,_____,________^-.-------------------,
 *  | |||||||||   `--------'     |          |                   O
 *  `+-------------USMC----------^----------|___________________|
 *    `\_,---------,---------,--------------'
 *      / X MK X /'|       /'
 *     / X MK X /  `\    /'
 *    / X MK X /`-------'
 *   / X MK X /
 *  / X MK X /
 * (________(                @author m.c.kunkel
 *  `------'
*/
package testpackages;

import javax.swing.SwingUtilities;

public abstract class SwingWorkertest {
	private Object value;
	private Thread thread;

	/**
	 * Compute the value to be returned by the <code>get</code> method.
	 */
	public abstract Object construct();

	/**
	 * Called on the event dispatching thread (not on the worker thread) after
	 * the <code>construct</code> method has returned.
	 */
	public void finished() {
	}

	/**
	 * Return the value created by the <code>construct</code> method.
	 */
	public Object get() {
		while (true) { // keep trying if we're interrupted
			Thread t;
			synchronized (SwingWorker.this) {
				t = thread;
				if (t == null) {
					return value;
				}
			}
			try {
				t.join();
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * Start a thread that will call the <code>construct</code> method and then
	 * exit.
	 */
	public SwingWorker() {
		final Runnable doFinished = new Runnable() {
			public void run() {
				finished();
			}
		};

		Runnable doConstruct = new Runnable() {
			public void run() {
				synchronized (SwingWorker.this) {
					value = construct();
					thread = null;
				}
				SwingUtilities.invokeLater(doFinished);
			}
		};

		thread = new Thread(doConstruct);
		thread.start();
	}
}

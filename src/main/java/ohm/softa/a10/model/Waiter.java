package ohm.softa.a10.model;

import ohm.softa.a10.internals.displaying.ProgressReporter;
import ohm.softa.a10.kitchen.KitchenHatch;

import java.util.Random;


public class Waiter implements Runnable {
	private String name;
	private ProgressReporter progressReporter;
	private KitchenHatch kitchenHatch;

	public Waiter(String name, KitchenHatch kitchenHatch, ProgressReporter progressReporter) {
		this.name = name;
		this.progressReporter = progressReporter;
		this.kitchenHatch = kitchenHatch;
	}

	@Override
	 public void run() {

			try {
				int i = 0;
				while (i != kitchenHatch.getMaxDishes()) {
					synchronized (this.kitchenHatch) {
						if (kitchenHatch.getDishesCount() == 0) kitchenHatch.wait();
					}

					System.out.println("woke up");

					Thread.sleep(new Random().nextInt(1000));
					kitchenHatch.dequeueDish();

					progressReporter.updateProgress();
					synchronized (this.kitchenHatch) {
						kitchenHatch.notifyAll();
					}
					i++;
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		progressReporter.notifyWaiterLeaving();

	}
}

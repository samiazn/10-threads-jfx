package ohm.softa.a10.model;

import ohm.softa.a10.internals.displaying.ProgressReporter;
import ohm.softa.a10.kitchen.KitchenHatch;

import java.util.Random;


public class Waiter implements Runnable{
	private String name;
	private ProgressReporter progressReporter;
	private KitchenHatch kitchenHatch;

	public Waiter(String name, KitchenHatch kitchenHatch,ProgressReporter progressReporter) {
		this.name = name;
		this.progressReporter = progressReporter;
		this.kitchenHatch = kitchenHatch;
	}

	@Override
	public void run() {
	 while (kitchenHatch.getDishesCount() != 0) {
			System.out.println(288);
			try {
				kitchenHatch.dequeueDish();
				Thread.sleep(new Random().nextInt(1000));
				progressReporter.updateProgress();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		progressReporter.notifyWaiterLeaving();
	}
}

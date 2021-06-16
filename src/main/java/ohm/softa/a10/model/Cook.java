package ohm.softa.a10.model;

import ohm.softa.a10.internals.displaying.ProgressReporter;
import ohm.softa.a10.kitchen.KitchenHatch;

public class Cook implements Runnable {
	private String name;
	private ProgressReporter progressReporter;
	private KitchenHatch kitchenHatch;

	public Cook(String name, KitchenHatch kitchenHatch, ProgressReporter progressReporter) {
		this.name = name;
		this.kitchenHatch = kitchenHatch;
		this.progressReporter = progressReporter;
	}

	@Override
	public void run() {
		synchronized (kitchenHatch) {

			while (kitchenHatch.getOrderCount() != 0) {
				try {
					Order order = kitchenHatch.dequeueOrder();
					Dish dish = new Dish(order.getMealName());

					Thread.sleep(dish.getCookingTime());

					if (kitchenHatch.getDishesCount() == kitchenHatch.getMaxDishes()) kitchenHatch.wait();


					kitchenHatch.enqueueDish(dish);
					kitchenHatch.notifyAll();

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				progressReporter.updateProgress();
			}
		}
		progressReporter.notifyCookLeaving();

	}
}

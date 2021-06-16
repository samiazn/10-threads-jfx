package ohm.softa.a10.kitchen;

import ohm.softa.a10.model.Dish;
import ohm.softa.a10.model.Order;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class KitchenHatchImpl implements KitchenHatch{
	Deque<Dish> dishes;
	Deque<Order> orders;

	public KitchenHatchImpl(int maxMeals, Deque<Order> orders) {
		this.orders = orders;
		dishes = new ArrayDeque<>(maxMeals);
	}

	@Override
	public int getMaxDishes() {
		return dishes.size();

	}

	@Override
	public Order dequeueOrder(long timeout) {
		return orders.poll();
	}

	@Override
	public int getOrderCount() {
		return (int)orders.stream().count();

	}

	@Override
	public Dish dequeueDish(long timeout) {
		return dishes.poll();
	}

	@Override
	public void enqueueDish(Dish m) {
		dishes.offer(m);
	}

	@Override
	public int getDishesCount() {
		return (int)dishes.stream().count();
	}
}

package it.grational.entity

import groovy.transform.ToString
import it.grational.log.Logger

@ToString(includeNames = true)
class Orders {
	@Delegate
	private List<Order> orders = []
	private Logger logger
	private int preparation = 0

	void leftShift(Order order) {
		orders << order
		preparation += order.preparation
	}

	void remove(Order order) {
		orders.remove(order)
		preparation -= order.preparation
	}

	Order popBest(int time) {
		logger.debug "total spent -> ${time}"

		Order best = orders.min { order -> 
			logger.debug("order (${order.getClass()}) -> ${order}", false)
			int score = score(order, time)
			if ( order.gone(time) )
				score = Integer.MAX_VALUE
			logger.debug ", score: ${score}"
			return score
		}

		if ( score(best, time) == (orders.size()-1) )
			if ( best.gone(time) )
				return

		remove(best)
		return best
	}

	private int score(Order candidate, int time) {
		def others = orders - candidate
		return others.grep { order ->
			order.gone(candidate.preparation+time)
		}.size()
	}

}

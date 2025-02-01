package it.grational.entity

import groovy.transform.*

@EqualsAndHashCode(includes='id')
@ToString(includeNames = true)
class Order {
	int id
	int preparation
	int deadline

	boolean gone(int time) {
		(time+preparation > deadline)
	}
}

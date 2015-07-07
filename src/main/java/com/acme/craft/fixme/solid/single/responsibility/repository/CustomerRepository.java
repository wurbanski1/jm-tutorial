package com.acme.craft.fixme.solid.single.responsibility.repository;

import lombok.extern.java.Log;

@Log
public class CustomerRepository {

	public void add() {
		try {
			/*
			 * do some database stuff here
			 */
		} catch (Exception e) {
			FileHandlerErrors.handleError("database error");
		}
	}
}



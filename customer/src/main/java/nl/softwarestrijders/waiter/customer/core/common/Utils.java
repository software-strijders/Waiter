package nl.softwarestrijders.waiter.customer.core.common;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * Utility class with static methods to aid with common things that can be easily extracted.
 */
public class Utils {
	private Utils() {
	}

	/**
	 * Function that checks if the email address given has a valid composition of characters.
	 *
	 * @param email email address
	 * @return if the email address has a valid composition.
	 */
	public static boolean isEmailValid(String email) {
		return EmailValidator.getInstance().isValid(email);
	}
}

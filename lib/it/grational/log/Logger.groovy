package it.grational.log

class Logger {
	private final boolean debug
	private int lastLines = 0

	Logger(boolean b = false) {
		this.debug = b
	}

	boolean debug() {
		debug
	}

	String read() {
		lastLines++
		System.console().readLine()
	}

	void debug (
		String message,
		boolean newLine = true
	) {
		if ( newLine )
			message += '\n'
		if (debug) {
			System.err.print message
			lastLines += (newlines(message) + 1)
		}
	}

	private int newlines(String input) {
		input.findAll('\n').size()
	}

	void clear() {
		lastLines.times {
			print cursorUp(1) + clearCurrentLine()
		}
		lastLines = 0
	}

	private String cursorUp(int n) {
		"\u001B[${n}F"
	}

	private String clearCurrentLine() {
		"\u001B[2K"
	}

	void info(String message) {
		println message
	}

	void error(String message) {
		System.err.println message
	}
}

//Nicholas Henrique Barbosa Oliveira e Caio Fabricio dos Santos
package com.main;

public class Token {
	public final TokenType type;
	public final String value;

	public Token(TokenType type, String value) {
		this.type = type;
		this.value = value;
	}

	public String toString() {
		return type + "('" + value + "')";
	}
}

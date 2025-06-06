//Nicholas Henrique Barbosa Oliveira e Caio Fabricio dos Santos
package com.main;

public class Parser {
	private final Lexer lexer;
	private Token current;

	public Parser(Lexer lexer) {
		this.lexer = lexer;
		this.current = lexer.nextToken();
	}

	private void eat(TokenType type) throws Exception {
		if (current.type == type) {
			current = lexer.nextToken();
		} else {
			throw new Exception("Unexpected token: " + current);
		}
	}

	public boolean parseFormula() {
		try {
			formula();
			if (current.type != TokenType.EOF)
				throw new Exception("Extra input");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void formula() throws Exception {
		if (current.type == TokenType.TRUE || current.type == TokenType.FALSE) {
			eat(current.type);
		} else if (current.type == TokenType.PROP) {
			eat(TokenType.PROP);
		} else if (current.type == TokenType.LPAREN) {
			eat(TokenType.LPAREN);
			if (current.type == TokenType.NOT) {
				eat(TokenType.NOT);
				formula();
				eat(TokenType.RPAREN);
			} else if (current.type == TokenType.AND || current.type == TokenType.OR
					|| current.type == TokenType.IMPLIES || current.type == TokenType.IFF) {
				eat(current.type);
				formula();
				formula();
				eat(TokenType.RPAREN);
			} else {
				throw new Exception("Expected operator after (");
			}
		} else {
			throw new Exception("Invalid formula start");
		}
	}
}

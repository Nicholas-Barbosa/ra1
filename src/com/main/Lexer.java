//Nicholas Henrique Barbosa Oliveira e Caio Fabricio dos Santos
package com.main;

public class Lexer {

	private final String input;
	private int pos = 0;

	public Lexer(String input) {
		this.input = input.trim();
	}

	private char peek() {
		return pos < input.length() ? input.charAt(pos) : '\0';
	}

	private void advance() {
		pos++;
	}

	public Token nextToken() {
		while (Character.isWhitespace(peek()))
			advance();

		if (pos >= input.length())
			return new Token(TokenType.EOF, "");

		char ch = peek();

		if (Character.isDigit(ch)) {
			StringBuilder sb = new StringBuilder();
			sb.append(ch);
			advance();
			while (Character.isLetterOrDigit(peek())) {
				sb.append(peek());
				advance();
			}
			return new Token(TokenType.PROP, sb.toString());
		}

		if (ch == '(') {
			advance();
			return new Token(TokenType.LPAREN, "(");
		}

		if (ch == ')') {
			advance();
			return new Token(TokenType.RPAREN, ")");
		}

		if (input.startsWith("\\neg", pos)) {
			pos += 4;
			return new Token(TokenType.NOT, "\\neg");
		}

		if (input.startsWith("\\wedge", pos)) {
			pos += 6;
			return new Token(TokenType.AND, "\\wedge");
		}

		if (input.startsWith("\\vee", pos)) {
			pos += 4;
			return new Token(TokenType.OR, "\\vee");
		}

		if (input.startsWith("\\rightarrow", pos)) {
			pos += 10;
			return new Token(TokenType.IMPLIES, "\\rightarrow");
		}

		if (input.startsWith("\\leftrightarrow", pos)) {
			pos += 14;
			return new Token(TokenType.IFF, "\\leftrightarrow");
		}

		if (input.startsWith("true", pos)) {
			pos += 4;
			return new Token(TokenType.TRUE, "true");
		}

		if (input.startsWith("false", pos)) {
			pos += 5;
			return new Token(TokenType.FALSE, "false");
		}

		return new Token(TokenType.INVALID, String.valueOf(peek()));
	}
}

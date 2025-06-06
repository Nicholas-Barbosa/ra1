//Nicholas Henrique Barbosa Oliveira e Caio Fabricio dos Santos
package com.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in);) {
			System.out.print("Digite o caminho do arquivo: ");
			String caminho = scanner.nextLine();
			try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {

				String line = null;
				while ((line = reader.readLine()) != null) {

					Lexer lexer = new Lexer(line);
					Parser parser = new Parser(lexer);

					boolean valid = parser.parseFormula();
					System.out.println(valid ? "valida" : "invalida");
				}

			}
		} catch (IOException e) {
			System.out.println("Erro ao processar o arquivo: " + e.getMessage());
		}
	}
}

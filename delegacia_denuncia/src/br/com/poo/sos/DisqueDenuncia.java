package br.com.poo.sos;

import java.io.IOException;
import br.com.poo.sos.io.LeituraEscrita;

public class DisqueDenuncia {

	public static void main(String[] args) throws IOException {

		// le os txts e cria os objetos
		LeituraEscrita.leitor("Delegacias");
		LeituraEscrita.leitor("Denuncias");

		// chama o primeiro menu
		Menus.menuInicial();

	}

}

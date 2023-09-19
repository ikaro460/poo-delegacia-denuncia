package br.com.poo.sos.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import br.com.poo.sos.Delegacia;
import br.com.poo.sos.Denuncia;

public class LeituraEscrita {
	// constantes
	static final String PATH_BASICO = "./temp/";
	static final String EXTENSAO = ".txt";
	static int contador = 0;

	// método estático
	public static void leitor(String path) throws IOException {

		// permissão da leitura do arquivo
		BufferedReader buffRead = new BufferedReader(new FileReader(PATH_BASICO + path + EXTENSAO));

		// definir uma variavel string
		String linha = "";

		// faço um enquanto para ler
		while (true) {
			linha = buffRead.readLine();
			if (linha != null) {
				// separação da informação por ; e digo que as linhas são string
				String[] dados = linha.split(";");

				// verifica se é uma delegacia ou uma denuncia
				if (path.equalsIgnoreCase("DELEGACIAS")) {

					// cria objeto
					Delegacia dl = new Delegacia(Integer.parseInt(dados[0]), dados[1], dados[2], dados[3]);

					// insere a conta poupanca nos mapas
					Delegacia.mapaDelegacias.put(dados[0], dl);

				} else if (path.equalsIgnoreCase("DENUNCIAS")) {
					// cria objeto
					Denuncia dn = new Denuncia(dados[0], dados[1], dados[2],
							Integer.parseInt(dados[3]));

					// insere a conta poupanca nos mapas
					Denuncia.mapaDenuncias.computeIfAbsent(dados[3], k -> new ArrayList<>()).add(dn);
				}

			} else {
				break;
			}
		}

		// fechar o buff
		buffRead.close();
	}

	public static void escritor(String path, String cliente, String conta) throws IOException {

		try (BufferedReader buffRead = new BufferedReader(new FileReader(PATH_BASICO + path + EXTENSAO))) {
			String dado = "";

			for (int i = 0; i < contador + 1; i++) {
				dado = buffRead.readLine();
				if (dado == null) {
					try (BufferedWriter buffWriter = new BufferedWriter(
							new FileWriter(PATH_BASICO + path + EXTENSAO, true))) {
						buffWriter.append(cliente);
						buffWriter.append(conta);
					}
				}
			}
		}
	}
}
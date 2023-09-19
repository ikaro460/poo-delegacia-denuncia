package br.com.poo.sos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import br.com.poo.sos.io.LeituraEscrita;
import br.com.poo.sos.util.Util;

public class DisqueDenuncia {
	
	static final String PATH_BASICO = "./temp/";
	static final String EXTENSAO = ".txt";

	// instancia scanner
	static Scanner sc = new Scanner(System.in);

	public static void menuInicial() throws IOException {
		Util.limpa();
		System.out.println("\nMENU\n");
		System.out.println("1. Relatório na tela");
		System.out.println("2. Relatório impresso");
		System.out.println("3. Filtrar denúncias por Delegacia.");

		// entrada de dados
		String opc = sc.nextLine();

		switch (opc) {
		case "1":
			// abrir rel na tela
			menuTela();
			break;
		case "2":
			// abrir rel impresso
			menuImpresso();
			break;
		case "3":
			// filtrar
			menuFiltrar();
			break;
		default:
			break;
		}
	}

	public static void menuTela() throws IOException {
		Util.limpa();
		System.out.println("\nRelatorio na tela:\n");
		System.out.println("1. Delegacias");
		System.out.println("2. Denuncias");
		System.out.println("3. Voltar");

		String aux = sc.nextLine();
		switch (aux) {
		case "1":
			// Delegacias na tela
			System.out.println("\nDELEGACIAS: \n");
			for (Entry<String, Delegacia> entry : Delegacia.mapaDelegacias.entrySet()) {
				Delegacia delegacia = entry.getValue();
				System.out.println(delegacia.getNome());
				System.out.println(delegacia.getEnd());
				System.out.println(delegacia.getTel());
				System.out.println("");

			}
			// opcao voltar
			System.out.println("1. Voltar");
			aux = sc.nextLine();
			if (aux.equalsIgnoreCase("1")) {
				menuTela();
			}
			break;
		case "2":
			// Denuncias na tela
			System.out.println("\nDENUNCIAS: \n");
			for (Entry<String, List<Denuncia>> entry : Denuncia.mapaDenuncias.entrySet()) {
				List<Denuncia> denuncias = entry.getValue();
				for (Denuncia denuncia : denuncias) {
					System.out.println(denuncia.getDenunciante());
					System.out.println(denuncia.getData());
					System.out.println(denuncia.getMotivo());
					System.out.println("");

				}
			}
			// opcao voltar
			System.out.println("1. Voltar");
			aux = sc.nextLine();
			if (aux.equalsIgnoreCase("1")) {
				menuTela();
			}
			break;
		case "3":
			// volta ao menu inicial
			menuInicial();
			break;
		default:
			break;
		}
	}

	public static void menuImpresso() throws IOException {

		Util.limpa();
		System.out.println("\nRelatorio impresso:\n");
		System.out.println("1. Delegacias");
		System.out.println("2. Denuncias");
		System.out.println("3. Voltar");

		String aux = sc.nextLine();
		switch (aux) {
		case "1":
			// Delegacias impresso
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(PATH_BASICO + "DelegaciasList" + EXTENSAO));

			for (Entry<String, Delegacia> entry : Delegacia.mapaDelegacias.entrySet()) {
				Delegacia delegacia = entry.getValue();
				buffWrite.append("Delegacia: " + delegacia.getNome() + "\n" + delegacia.getEnd() + "\n\n");
			}
			buffWrite.close();
			System.out.println("Relatório imprimido com sucesso.");
			// volta
			menuImpresso();
			break;
		case "2":
			// Denuncias impresso
			BufferedWriter buffWrite1 = new BufferedWriter(new FileWriter(PATH_BASICO + "DenunciasList" + EXTENSAO));

			for (Entry<String, List<Denuncia>> entry : Denuncia.mapaDenuncias.entrySet()) {
				List<Denuncia> denuncias = entry.getValue();
				for (Denuncia denuncia : denuncias) {
					buffWrite1.append("Denuncia: " + denuncia.getDenunciante() + "\n" + denuncia.getMotivo() + "\n"
							+ denuncia.getData() + "\n\n");

				}
			}
			buffWrite1.close();
			System.out.println("Relatório imprimido com sucesso.");
			// volta
			menuImpresso();
			break;
		case "3":
			// volta ao menu inicial
			menuInicial();
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("null")
	public static void menuFiltrar() throws IOException {
		Scanner sc = new Scanner(System.in);
		Delegacia delegacia = null;
		String result = "";

		System.out.println("Digite o nome da delegacia: \n");
		String nomeDel = sc.nextLine();

		// encontra delegacia
		for (Entry<String, Delegacia> entry : Delegacia.mapaDelegacias.entrySet()) {
			Delegacia dl = entry.getValue();
			if (dl.getNome().equalsIgnoreCase(nomeDel)) {
				String delFormat = "Delegacia selecionada: " + dl.getNome();
				System.out.println(delFormat);
				result += delFormat;
				delegacia = dl;
			}
		}

		// encontra denuncias referentes a delegacia
		for (Entry<String, List<Denuncia>> entry : Denuncia.mapaDenuncias.entrySet()) {
			List<Denuncia> denuncias = entry.getValue();
			for (Denuncia denuncia : denuncias) {
				if (denuncia.getDelId().equals(delegacia.getDelId())) {
					String denFormat = "\n" + denuncia.getDenunciante() + "\n" + denuncia.getMotivo() + "\n"
							+ denuncia.getData();
					System.out.println(denFormat);
					result += denFormat;
				}

			}
		}

		System.out.println("1. Imprimir Relatório.");
		System.out.println("2. Voltar.");
		String aux = sc.next();

		switch (aux) {
		case "1":
			// imprimir relatorio
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(PATH_BASICO+delegacia.getNome()+EXTENSAO));
			buffWrite.append(result);
			buffWrite.close();
			menuInicial();
			break;
		case "2":
			// volta
			menuInicial();
			break;
		default:
			break;
		}

		sc.close();
	}

	public static void main(String[] args) throws IOException {

		LeituraEscrita.leitor("Delegacias");
		LeituraEscrita.leitor("Denuncias");

		Delegacia dl = Delegacia.mapaDelegacias.get("3");

		for (Denuncia denuncia : Denuncia.mapaDenuncias.get("3")) {
			System.out.println(denuncia);
		}

		// imprime denuncias
		for (Entry<String, List<Denuncia>> entry : Denuncia.mapaDenuncias.entrySet()) {
			List<Denuncia> denuncias = entry.getValue();
			for (Denuncia denuncia : denuncias) {
				System.out.println(denuncia.getDenunciante());
			}
		}

		menuInicial();

		// fecha scanner
		sc.close();
	}

}

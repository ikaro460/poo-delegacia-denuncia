package br.com.poo.sos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.poo.sos.util.Util;

public class Menus {
	
	static final String PATH_BASICO = "./temp/";
	static final String EXTENSAO = ".txt";
	private static Logger customLogger = Util.setupLogger();
	
	// SCANNER
	static Scanner sc = new Scanner(System.in);
	
	public static void menuInicial() throws IOException {
		Util.limpa();
		Util.customizer();
		customLogger.log(Level.INFO, "\nMENU\n");
		customLogger.log(Level.INFO, "1. Relatório na tela");
		customLogger.log(Level.INFO, "2. Relatório impresso");
		customLogger.log(Level.INFO, "3. Filtrar denúncias por Delegacia");
		customLogger.log(Level.INFO, "4. Sair\n");

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
		case "4":
			// sair
			Util.limpa();
			customLogger.log(Level.INFO, "Programa encerrado");
			break;
		default:
			break;
		}
		
		// fecha scanner
		sc.close();
		
	}

	public static void menuTela() throws IOException {
		Util.limpa();
		
		customLogger.log(Level.INFO, "\nRelatorio na tela:\n");
		customLogger.log(Level.INFO, "1. Delegacias");
		customLogger.log(Level.INFO, "2. Denuncias");
		customLogger.log(Level.INFO, "3. Voltar\n");

		String aux = sc.nextLine();
		switch (aux) {
		case "1":
			// Delegacias na tela
			customLogger.log(Level.INFO,"\nDELEGACIAS:\n");
			for (Entry<String, Delegacia> entry : Delegacia.mapaDelegacias.entrySet()) {
				Delegacia delegacia = entry.getValue();
				
				//tela
				customLogger.log(Level.INFO,() -> "\nNome da Delegacia: \n" + delegacia.getNome());
				customLogger.log(Level.INFO,() -> "Enderenço:\n" + delegacia.getEnd());
				customLogger.log(Level.INFO,() -> "Tel:\n" + delegacia.getTel() + "\n");

			}
			// opcao voltar
			customLogger.log(Level.INFO, "1. Voltar\n");
			aux = sc.nextLine();
			if (aux.equalsIgnoreCase("1")) {
				menuTela();
			}
			break;
		case "2":
			// Denuncias na tela
			customLogger.log(Level.INFO,"\nDENUNCIAS:\n");
			for (Entry<String, List<Denuncia>> entry : Denuncia.mapaDenuncias.entrySet()) {
				List<Denuncia> denuncias = entry.getValue();
				for (Denuncia denuncia : denuncias) {
					//tela
					customLogger.log(Level.INFO,() -> "\nDenunciante:\n" + denuncia.getDenunciante());
					customLogger.log(Level.INFO,() -> "Data:\n" + denuncia.getData());
					customLogger.log(Level.INFO,() -> "Motivo:\n" + denuncia.getMotivo() + "\n");
				}
			}
			// opcao voltar
			customLogger.log(Level.INFO,"1. Voltar");
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
		
		//tela
		customLogger.log(Level.INFO,"\nRelatorio impresso:\n");
		customLogger.log(Level.INFO,"1. Delegacias");
		customLogger.log(Level.INFO,"2. Denuncias");
		customLogger.log(Level.INFO,"3. Voltar\n");

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
			customLogger.log(Level.INFO,"Relatório imprimido com sucesso.");
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
			customLogger.log(Level.INFO,"Relatório imprimido com sucesso.");
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

	public static void menuFiltrar() throws IOException {
		Delegacia delegacia = null;
		String result = "";

		customLogger.log(Level.INFO,"Digite o nome da delegacia: \n");
		String nomeDel = sc.nextLine();

		// encontra delegacia
		for (Entry<String, Delegacia> entry : Delegacia.mapaDelegacias.entrySet()) {
			Delegacia dl = entry.getValue();
			if (dl.getNome().equalsIgnoreCase(nomeDel)) {
				String delFormat = "Delegacia selecionada: " + dl.getNome();
				customLogger.log(Level.INFO,delFormat);
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
					customLogger.log(Level.INFO,denFormat);
					result += denFormat;
				}

			}
		}

		customLogger.log(Level.INFO,"1. Imprimir Relatório.");
		customLogger.log(Level.INFO,"2. Voltar.");
		String aux = sc.nextLine();

		switch (aux) {
		case "1":
			// imprimir relatorio
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(PATH_BASICO + delegacia.getNome() + EXTENSAO));
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
	}
}

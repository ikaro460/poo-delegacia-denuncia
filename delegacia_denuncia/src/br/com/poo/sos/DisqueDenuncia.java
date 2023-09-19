package br.com.poo.sos;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import br.com.poo.sos.io.LeituraEscrita;

public class DisqueDenuncia {

	public static void main(String[] args) throws IOException {
		LeituraEscrita.leitor("Delegacias");
		LeituraEscrita.leitor("Denuncias");

		Delegacia dl = Delegacia.mapaDelegacias.get("3");
		
		for(Denuncia denuncia : Denuncia.mapaDenuncias.get("3")) {
			System.out.println(denuncia);
		}
		
		for (Entry<String, List<Denuncia>> entry : Denuncia.mapaDenuncias.entrySet()) {
			List<Denuncia> denuncias = entry.getValue();
			for (Denuncia denuncia : denuncias) {
//				System.out.println(denuncias);
			}
		}
	}

}

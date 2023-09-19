package br.com.poo.sos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.poo.sos.util.Util;

public class Denuncia {
	
	private String denunciante;
	private String motivo;
	private String data;
	private Integer delId;
	
	private static final String OBJ_CRIADO = "Objeto criado";
	private Logger logger = Util.setupLogger();
	
	//criar map
	public static Map<String, List<Denuncia>> mapaDenuncias = new HashMap<>();
	
	public Denuncia() {
		Util.customizer();
		logger.log(Level.INFO, OBJ_CRIADO);
	}
	
	public Denuncia(String denunciante, String motivo, String data, Integer delId) {
		Util.customizer();
		this.denunciante = denunciante;
		this.motivo = motivo;
		this.data = data;
		this.delId = delId;
		logger.log(Level.INFO, OBJ_CRIADO);
	}
	
	public String getDenunciante() {
		return denunciante;
	}

	public String getMotivo() {
		return motivo;
	} 

	public String getData() {
		return data;
	}

	public Integer getDelId() {
		return delId;
	}
	
	@Override
	public String toString() {
		return "Denunciante: " + denunciante + "\nMotivo: " + motivo + "\nData: " + data + "\n";
	}
	
}

package br.com.poo.sos;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.poo.sos.util.Util;

public class Delegacia {
	
	private Integer delId;
	private String nome;
	private String end;
	private String tel;
	
	private static final String OBJ_CRIADO = "Objeto criado";
	private Logger logger = Util.setupLogger();
	
	//criar map
	public static Map<String, Delegacia> mapaDelegacias = new HashMap<>();
	
	public Delegacia() {
		Util.customizer();
		logger.log(Level.INFO, OBJ_CRIADO);
	}
	
	public Delegacia(Integer delId, String nome, String end, String tel) {
		Util.customizer();
		this.delId = delId;
		this.nome = nome;
		this.end = end;
		this.tel = tel;
		logger.log(Level.INFO, OBJ_CRIADO);
	}
	
	@Override
	public String toString() {
		return "Delegacia: " + nome + "\nEndereço: " + end + "\ntel: " + tel + "\n";
	}

	public Integer getDelId() {
		return delId;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getEnd() {
		return end;
	}
	
	public String getTel() {
		return tel;
	}

}

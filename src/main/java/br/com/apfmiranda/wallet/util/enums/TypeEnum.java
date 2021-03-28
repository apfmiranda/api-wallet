package br.com.apfmiranda.wallet.util.enums;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 28 de mar de 2021
 *
 */
public enum TypeEnum {
	
	EN("ENTRADA"),
	SD("SAIDA");
	
	private final String value;

	TypeEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public static TypeEnum getEnum(String value) {
		for(TypeEnum t: values()) {
			if(value.equals(t.getValue())) {
				return t; 
			}
		}
		return null;
	}
	

}

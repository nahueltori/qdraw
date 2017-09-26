package interpreter;
import java.text.ParseException;

public class QDraw{

	private String codigo;
	private String entorno;
	private Tokenizer tokenizer;
	
	public QDraw(){
		tokenizer = new Tokenizer();
		tokenizer.add("programa|procedimiento|entonces|veces", 1); // indicadores
		tokenizer.add("arr|aba|der|izq|N|V|R|X|\\?|\\=|\\$|repetir|si|sino",2); // primitivas
		tokenizer.add("\\(", 3); // open bracket
		tokenizer.add("\\)", 4); // close bracket
		tokenizer.add("\\{", 5); // open curl
		tokenizer.add("\\}", 6); // close curl
		tokenizer.add("[0-9]+",7); // integer number
		tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 8); // user names
	}
	
	public String tokenize(String programa) {
		String response = "";
		codigo = programa;
		//Procesamos el código fuente y obtenemos los tokens del lenguaje
		try{
			tokenizer.tokenize(codigo);
		}
		catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		
		for (Tokenizer.Token tok : tokenizer.getTokens()) {
			response += tok.token + " " + tok.sequence;
		}
		return response;
	}

}
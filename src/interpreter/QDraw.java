package interpreter;

public class QDraw{

	private String codigo;
	private String entorno;
	
	public static void main(String[] args) {
		//Seteamos la configuracion del tokenizer
		Tokenizer tokenizer = new Tokenizer();
		addTokens(tokenizer);
		
		//Leemos el codigo fuente
		codigo = new String(Files.readAllBytes(Paths.get(args[0])), StandardCharsets.UTF_8);
		//Leemos el estado actual del tablero
		entorno = new String(Files.readAllBytes(Paths.get(args[1])), StandardCharsets.UTF_8);
		
		//Cargamos el entorno a la memoria de trabajo
		cargarEntorno();
		
		
		//Procesamos el código fuente y obtenemos los tokens predefinidos
		try{
			tokenizer.tokenize(codigo);
		}
		catch (ParserException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	private void addTokens(Tokenizer tokenizer){
		tokenizer.add("programa|procedimiento|entonces|veces", 1); // indicadores
		tokenizer.add("arr|aba|der|izq|N|V|R|X|\\?|\\=|\\$|repetir|si|sino",2); // primitivas
		tokenizer.add("\\(", 3); // open bracket
		tokenizer.add("\\)", 4); // close bracket
		tokenizer.add("\\{", 5); // open curl
		tokenizer.add("\\}", 6); // close curl
		tokenizer.add("[0-9]+",7); // integer number
		tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 8); // user names
	}
	
}
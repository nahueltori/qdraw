package interpreter;

import java.util.regex.Pattern;


public class Tokenizer{

	private class TokenInfo {
		public final Pattern regex;
		public final int token;
		
		public TokenInfo(Pattern regex, int token) {
			super();
			this.regex = regex;
			this.token = token;
		}
	}
	
	public class Token {
		
		public final int token;
		public final String sequence;

		public Token(int token, String sequence) {
			super();
			this.token = token;
			this.sequence = sequence;
		}
	}

	private LinkedList<TokenInfo> tokenInfos;
	private LinkedList<Token> tokens;

	public Tokenizer() {
		tokenInfos = new LinkedList<TokenInfo>();
		tokens = new LinkedList<Token>();
	}

	public void add(String regex, int token) {
		tokenInfos.add( new TokenInfo(
			Pattern.compile("^("+regex+")"), token) );
	}

	public void tokenize(String str) {
		String s = new String(str);
		tokens.clear();
		
		while (!s.equals("")) {
			boolean match = false;
			for (TokenInfo info : tokenInfos) {
				Matcher m = info.regex.matcher(s);
				if (m.find()) {
					match = true;
					
					String tok = m.group().trim();
					tokens.add(new Token(info.token, tok));
					
					s = m.replaceFirst("");
					break;
				}
			}
			
			if (!match) throw new ParserException("Caracter inesperado: "+s);
		}
	}

	public LinkedList<Token> getTokens() {
		return tokens;
	}

}
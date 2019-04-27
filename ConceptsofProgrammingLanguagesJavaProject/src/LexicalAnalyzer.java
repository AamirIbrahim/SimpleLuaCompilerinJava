import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LexicalAnalyzer {
	private List<Token> tokenList;
	/**
	 * @param fileName- cannot be null
	 * @throws FileNotFoundException
	 * @throws LexicalException 
	 * @throws InvalidArgumentException if fileName is null
	 */
	public LexicalAnalyzer(String fileName) throws FileNotFoundException, LexicalException {
		if (fileName == null)
			throw new IllegalArgumentException ("null file name argument");
		tokenList = new ArrayList<Token>();
		Scanner input = new Scanner (new File(fileName));
		int lineNumber = 0;
		while (input.hasNext())
		{
			String line = input.nextLine();
			lineNumber++;
			processLine (line, lineNumber);
		}
		input.close();
		tokenList.add(new Token (TokenType.EOS_TOK, "EOS", lineNumber, 1));
	}
	/**
	 * @param line cannot be null
	 * @param lineNumber > 0
	 * @throws LexicalException 
	 * @throws IllegalArgumentException if line is null or lineNumber <= 0
	 * postcondition: source code line has been processed with all tokens
	 * 				  placed in the token list
	 */
	private void processLine(String line, int lineNumber) throws LexicalException {
		if (line == null)
			throw new IllegalArgumentException ("null line argument");
		if (lineNumber <= 0)
			throw new IllegalArgumentException ("invalid line number argument");
		int index = skipWhiteSpace(line, 0);
		while (index < line.length()) {
			String lexeme = getLexeme (line, index);
			TokenType tokType = getTokenType (lexeme, lineNumber, index + 1);
			tokenList.add(new Token (tokType, lexeme, lineNumber, index + 1));
			index += lexeme.length();
			index = skipWhiteSpace(line, index);
		}		
	}

	/**
	 * @param lexeme cannot be null & cannot be empty
	 * @return type of token representing lexeme
	 * @throws IllegalArgumentException if lexeme is null
	 * @throws LexicalException if lexeme is not a valid representation
	 *         of a token
	 */
	private TokenType getTokenType(String lexeme, int rowNumber, int columnNumber) throws LexicalException {
		if (lexeme == null || lexeme.length() == 0)
			throw new IllegalArgumentException ("invalid string argument");
		TokenType tokType = TokenType.EOS_TOK;
		if (Character.isDigit(lexeme.charAt(0)))
			if (allDigits (lexeme))
				tokType = TokenType.LITERAL_INTEGER_TOK;
			else	
				throw new LexicalException ("literal integer expecated "+ " at row " +
					rowNumber  + " and column " + columnNumber);
		else if (Character.isLetter(lexeme.charAt(0)))
		{
			if (lexeme.length() == 1)
				tokType = TokenType.ID_TOK;
			else if (lexeme.equals("if"))
				tokType = TokenType.IF_TOK;
			else if (lexeme.equals("function"))
				tokType = TokenType.FUNCTION_TOK;
			else if (lexeme.equals("then"))
				tokType = TokenType.THEN_TOK;
			else if (lexeme.equals("end"))
				tokType = TokenType.END_TOK;
			else if (lexeme.equals("else"))
				tokType = TokenType.ELSE_TOK;
			else if (lexeme.equals("while"))
				tokType = TokenType.WHILE_TOK;
			else if (lexeme.equals("do"))
				tokType = TokenType.DO_TOK;
			else if (lexeme.equals("print"))
				tokType = TokenType.PRINT_TOK;
			else if (lexeme.equals("repeat"))
				tokType = TokenType.REPEAT_TOK;
			else if (lexeme.equals("until"))
				tokType = TokenType.UNTIL_TOK;
			else
				throw new LexicalException ("invalid lexeme "+ " at row " +
					rowNumber  + " and column " + columnNumber);
		}
		else if (lexeme.equals("("))
			tokType = TokenType.LEFT_PAREN_TOK;
		else if (lexeme.equals(")"))
			tokType = TokenType.RIGHT_PAREN_TOK;
		else if (lexeme.equals(">="))
			tokType = TokenType.GE_TOK;
		else if (lexeme.equals(">"))
			tokType = TokenType.GT_TOK;
		else if (lexeme.equals("<="))
			tokType = TokenType.LE_TOK;
		else if (lexeme.equals("<"))
			tokType = TokenType.LT_TOK;
		else if (lexeme.equals("=="))
			tokType = TokenType.EQ_TOK;
		else if (lexeme.equals("~="))
			tokType = TokenType.NE_TOK;
		else if (lexeme.equals("+"))
			tokType = TokenType.ADD_TOK;
		else if (lexeme.equals("-"))
			tokType = TokenType.SUB_TOK;
		else if (lexeme.equals("*"))
			tokType = TokenType.MUL_TOK;
		else if (lexeme.equals("/"))
			tokType = TokenType.DIV_TOK;
		else if (lexeme.equals ("="))
			tokType = TokenType.ASSIGN_TOK;
		else
			throw new LexicalException ("invalid lexeme "+ " at row " +
					rowNumber  + " and column " + columnNumber);			
		return tokType;
	}

	/**
	 * @param lexeme cannot be null
	 * @return whether all characters in lexeme are digits
	 * @throws IllegalArgumertException if lexeme is null
	 */
	private boolean allDigits(String lexeme) {
		if (lexeme == null)
			throw new IllegalArgumentException ("null string argument");
		int i = 0;
		while (i < lexeme.length() && Character.isDigit(lexeme.charAt(i)))
			i++;
		return i == lexeme.length();
	}

	/**
	 * @param line - cannot be null
	 * @param index >= 0
	 * @return next lexeme
	 * @throws IllegalArgumentExceptio if line is null or line < 0
	 */
	private String getLexeme(String line, int index) {
		if (line == null)
			throw new IllegalArgumentException ("null string argument");
		if (index < 0)
			throw new IllegalArgumentException ("invalid index argument");
		int i = index;
		while (i < line.length() && !Character.isWhitespace(line.charAt(i)))
			i++;
		return line.substring(index, i);
	}

	/**
	 * @param line
	 * @param index
	 * @return index of the first non white space character following position index
	 */
	private int skipWhiteSpace(String line, int index) {
		while (index < line.length() && Character.isWhitespace(line.charAt(index)))
			index++;
		return index;
	}

	/**
	 * @return copy of the next token
	 * @throws LexicalException if there is not another token
	 */
	public Token getLookaheadToken() throws LexicalException {
		if (tokenList.isEmpty())
			throw new LexicalException ("no more tokens");
		return tokenList.get(0);
	}

	/**
	 * @return next token (token is removed)
	 * @throws LexicalException if there is not another token
	 */
	public Token getNextToken() throws LexicalException {
		if (tokenList.isEmpty())
			throw new LexicalException ("no more tokens");
		return tokenList.remove(0);
	}

}

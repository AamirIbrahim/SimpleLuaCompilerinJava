public class Id implements ArithmeticExpression
{
	private char ch;
	/**
	 * @param ch - must be a valid identifier
	 * @throws IllegalArgument exception if ch if not a valid identifier
	 */
	public Id(char ch) {
		if (!Character.isLetter(ch))
			throw new IllegalArgumentException ("invalid identifier argument");
		this.ch = ch;
	}
	public char getChar()
	{
		return ch;
	}
	@Override
	public int evaluate()
	{
		return Memory.fetch (ch);
	}
}

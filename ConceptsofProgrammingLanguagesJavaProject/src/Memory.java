public class Memory
{
	private static int[] mem = new int[52];
	/**
	 * @param ch must be a letter
	 * @param value to be stored
	 * postcondition: value has been stored in the memory location
	 *                associated with ch
	 */
	public static void store(char ch, int value)
	{
		mem[indexOf(ch)] = value;
	}

	/**
	 * @param ch - must be a letter
	 * @return index in memory array associated with ch
	 * @throws IllegalArgumentException if ch is not a letter
	 */
	private static int indexOf(char ch) {
		if (!Character.isLetter(ch))
			throw new IllegalArgumentException ("invalid identifier argument");
		int index;
		if (Character.isLowerCase(ch))
			index = ch - 'a';
		else
			index = 26 + ch - 'A';
		return index;
	}
	/**
	 * @param ch must be a letter
	 * @return value stored at memory location associated with ch
	 */
	public static int fetch(char ch)
	{
		return mem[indexOf(ch)];
	}
}
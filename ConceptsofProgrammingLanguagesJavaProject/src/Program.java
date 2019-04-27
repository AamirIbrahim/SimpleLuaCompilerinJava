
public class Program
{
	private Block blk;
	/**
	 * @param blk cannot be null
	 * @throws IllegalArgumentException if blk is null
	 */
	public Program(Block blk)
	{
		if (blk == null)
			throw new IllegalArgumentException ("null block argument");
		this.blk = blk;
	}
	/**
	 * postcondition: program has been executed
	 */
	public void execute ()
	{
		blk.execute();
	}
}
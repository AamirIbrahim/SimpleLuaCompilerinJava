
public class RepeatStatement implements Statement
{
	private Block blk;
	private BooleanExpression expr;
	/**
	 * @param blk  cannot be null
	 * @param expr cannot be null
	 * @throws IllegalArgumentException if either parameter is null
	 */
	public RepeatStatement(Block blk, BooleanExpression expr) {
		if (blk == null)
			throw new IllegalArgumentException ("null block argument");
		if (expr == null)
			throw new IllegalArgumentException("null boolean expression argument ");
		this.blk = blk;
		this.expr = expr;
	}
	@Override
	public void execute() {
		do
		{
			blk.execute();
		}
		while (!expr.evaluate());		
	}
}
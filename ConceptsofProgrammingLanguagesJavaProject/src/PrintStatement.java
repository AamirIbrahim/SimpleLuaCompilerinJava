
public class PrintStatement implements Statement
{
	private ArithmeticExpression expr;
	/**
	 * @param expr - cannot be null
	 * @throws IllegalArgumentException if expr is null
	 */
	public PrintStatement(ArithmeticExpression expr) {
		if (expr == null)
			throw new IllegalArgumentException ("null arithmetic expression argument");
		this.expr = expr;
	}
	@Override
	public void execute() {
		System.out.println (expr.evaluate());
	}
}

public class BinaryExpression implements ArithmeticExpression {
	public enum ArithmeticOperator {ADD_OP, SUB_OP, MUL_OP, DIV_OP}
	private ArithmeticOperator op;
	private ArithmeticExpression expr1, expr2;
	/**
	 * @param op cannot be null
	 * @param expr1 cannot be null
	 * @param expr2 cannot be null
	 * @throws IllegalArgumentException if any argument is null
	 */
	public BinaryExpression(ArithmeticOperator op, ArithmeticExpression expr1,
			ArithmeticExpression expr2)
	{
		if (op == null)
			throw new IllegalArgumentException ("null arithmetic operator argument");
		if (expr1 == null || expr2 == null)
			throw new IllegalArgumentException ("null arithmetic expression argument");
		this.op = op;
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	@Override
	public int evaluate() {
		int value = 0;
		switch (op) {
			case ADD_OP:
				value = expr1.evaluate() + expr2.evaluate();
				break;
			case SUB_OP:
				value = expr1.evaluate() - expr2.evaluate();
				break;
			case MUL_OP:
				value = expr1.evaluate() * expr2.evaluate();
				break;
			case DIV_OP:
				value = expr1.evaluate() / expr2.evaluate();
				break;
		}
		return value;
	};
}
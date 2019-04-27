public class BooleanExpression {
	public enum RelationalOperator	{EQ_OP, NE_OP, GT_OP, GE_OP, LT_OP, LE_OP}
	private RelationalOperator op;
	private ArithmeticExpression expr1, expr2;
	/**
	 * @param op cannot be null
	 * @param expr1 cannot be null
	 * @param expr2 cannot be null
	 */
	public BooleanExpression(RelationalOperator op, ArithmeticExpression expr1,
			ArithmeticExpression expr2) {
		if (op == null)
			throw new IllegalArgumentException ("null relational operator argument");
		if (expr1 == null || expr2 == null)
			throw new IllegalArgumentException ("null arithmetic expression argument");
		this.op = op;
		this.expr1 = expr1;
		this.expr2 = expr2;
	};
	
	/**
	 * @return value of the boolean expression
	 */
	public boolean evaluate () {
		boolean result = false;
		switch (op) {
			case EQ_OP:
				result = expr1.evaluate() == expr2.evaluate();
				break;
			case NE_OP:
				result = expr1.evaluate() != expr2.evaluate();
				break;
			case LT_OP:
				result = expr1.evaluate() < expr2.evaluate();
				break;
			case LE_OP:
				result = expr1.evaluate() <= expr2.evaluate();
				break;
			case GT_OP:
				result = expr1.evaluate() > expr2.evaluate();
				break;
			case GE_OP:
				result = expr1.evaluate() >= expr2.evaluate();
				break;
		}
		return result;
	}
}
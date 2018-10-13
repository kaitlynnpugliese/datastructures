public class Problem1 {
	
	public static void main(String[] args){
		String postfix = "34 2 - 5 *";
		ExpressionTree tree = new ExpressionTree(postfix);
		int result = tree.eval();
		System.out.println("The evaluation is: "+ result);
		String post = tree.postfix();
		System.out.println("The postfix expression is: "+ post);
		String pre = tree.prefix();
		System.out.println("The prefix expression is: "+ pre);
		String in = tree.infix();
		System.out.println("The infix expression is: "+ in);
		
	}
}

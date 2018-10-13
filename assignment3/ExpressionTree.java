
public class ExpressionTree {
	//instance variables
	private ExpressionNodes root;
	private ExpressionNodes N1;
	private ExpressionNodes N2;
	
	//constructor
	public ExpressionTree(String postIn){
		
		//break expression into a list of strings by spaces
		String[] symbol = postIn.split(" ");
		
		//constructs expression tree
		final MyStack<ExpressionNodes> nodes = new MyStack<>();
		//create entire tree and push onto stack
		for(int i=0; i<symbol.length; i++){
			//if symbol is an operand
			if (isOperand(symbol[i])){
				//if operand create one node tree and push onto stack
				ExpressionNodes node = new ExpressionNodes(symbol[i]);
				nodes.push(node);	
			}
			//if symbol is an operator
			else if (isOperator(symbol[i])){
				//if operator, pop two trees from stack, form new tree whose root is 
				//operator and children are popped trees
				if (!(nodes.isEmpty())){
					N1 = nodes.pop();
					if (!(nodes.isEmpty())){
						N2 = nodes.pop();				
						ExpressionNodes newNode = new ExpressionNodes(symbol[i], N2, N1);
						nodes.push(newNode);
					}
				}
				//if you can't pop the stack twice, then the user input
				//was not a proper postfix expression
				else{
					System.out.println("The expression you entered "
						+ "was not a valid postfix expression, "
						+ "try another input.");
				}
			}
			//if symbol is neither operator or operand*****should I throw an exception/error?
			else{
				System.out.println("Can only input operators and "
						+ "operands, try another input.");
			}
		}
		//whole tree is on the stack, now pop the stack once and that is the tree
		root = nodes.pop();	
	}
	
		public int eval(){
			if (root==null){
				throw new ArithmeticException("The tree is empty");
			}
			else{
				return recursiveEval(root);
			}
		}
		
		public int recursiveEval(ExpressionNodes leaves){
			if (leaves.getElement().equals("-")){
				return recursiveEval(leaves.getLeft()) - recursiveEval(leaves.getRight());
			}
			else if (leaves.getElement().equals("+")){
				return recursiveEval(leaves.getLeft()) + recursiveEval(leaves.getRight());
			}
			else if (leaves.getElement().equals("*")){
				return recursiveEval(leaves.getLeft()) * recursiveEval(leaves.getRight());
			}
			else if (leaves.getElement().equals("/")){
				return recursiveEval(leaves.getLeft()) / recursiveEval(leaves.getRight());
			}
			else{
				String x = leaves.getElement();
				return Integer.parseInt(x);
			}
		}
		

		public String postfix(){
			if (root==null){
				throw new ArithmeticException("The tree is empty");
			}
			else{
				return recPostfix(root);
			}
		}
		
		private String recPostfix(ExpressionNodes leaves){
			if (leaves.getLeft()==null && leaves.getRight()==null){
				return leaves.toString();
			}
			else{
				String x = recPostfix(leaves.getLeft());
				String y = recPostfix(leaves.getRight());
				return x + " " + y + " " + leaves.toString() + " ";
			}
		}
		
		public String prefix(){
			if (root==null){
				throw new ArithmeticException("The tree is empty");
			}
			else{
				return recPrefix(root);
			}
		}
		
		private String recPrefix(ExpressionNodes leaves){
			if (leaves.getLeft()==null && leaves.getRight()==null){
				return leaves.toString();
			}
			else{
				String x = leaves.toString();
				String y = recPrefix(leaves.getLeft());
				String z = recPrefix(leaves.getRight());
				return x + " " + y + " " + z + " ";
			}
		}
		
		public String infix(){
			if (root==null){
				throw new ArithmeticException("The tree is empty");
			}
			else{
				return recInfix(root);
			}
		}
		
		public String recInfix(ExpressionNodes leaves){
			if (leaves.getLeft()==null && leaves.getRight()==null){
				return leaves.toString();
			}
			else{
				String x = recInfix(leaves.getLeft());
				String y = leaves.toString();
				String z = recInfix(leaves.getRight());
				return x + " " + y + " " + z + " ";
			}
		}

		
		//check if operator
		private boolean isOperand(String s){
			try{
				Integer.parseInt(s);
			}
			catch(NumberFormatException e){
				return false;
			}
			catch(NullPointerException e){
				return false;
			}
			return true;
		}
	
		
		//check if operand
		private boolean isOperator(String s){
			if (s.equals("-") || s.equals("+") || s.equals("*") || s.equals("/")){
				return true;
			}
			return false;
		}
	}

	//expression nodes class
	class ExpressionNodes{
		//instance variables 
		String element;
		ExpressionNodes left;
		ExpressionNodes right;
		
		public ExpressionNodes(String x){
			element = x;
			left = null;
			right = null;
		}
		
		public ExpressionNodes(String x, ExpressionNodes lt, ExpressionNodes rt){
			element = x;
			left = lt;
			right = rt;
		}
		public String getElement(){
			return element;
		}
		public ExpressionNodes getLeft(){
			return left;
		}
		public ExpressionNodes getRight(){
			return right;
		}
		public String toString(){
			return element;
		}
	}


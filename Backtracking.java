package backtracking;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Backtracking {
	
	public static Stack<Character> solve(int[] puzzle){
		Stack<Character> stack = new Stack<Character>();
		boolean[] visited = new boolean[puzzle.length];
		for (boolean b : visited) {
			b = false;
		}

		moveForward(0, puzzle, stack, visited);
		if(stack.isEmpty()) {
			return null;
		}
		return stack;
	}
	
	public static boolean moveForward(int position, int[] puzzle, Stack<Character> stack, boolean[] visited) {
		if(position < 0 || position > puzzle.length - 1) {
			return false;
		}
		if (visited[position]) {
			return false;
		}
		visited[position] = true;
		if (position == puzzle.length - 1) {
			return true;
		}
		
		if (moveForward(position - puzzle[position], puzzle, stack, visited)) {
			stack.push('L');
			return true;
		} else if (moveForward(position + puzzle[position], puzzle, stack, visited)) {
			stack.push('R');
			return true;
		}
		
		return false;
	}
	
	public static Set<Stack<Character>> findAllSolutions(int[] puzzle){
		Set<Stack<Character>> allSolution = new HashSet<Stack<Character>>();
		boolean[] visited = new boolean[puzzle.length];
		for (boolean b : visited) {
			b = false;
		}
		helpAllSolutions(puzzle, allSolution, visited);
		
		return allSolution;
	}
	
	public static void helpAllSolutions(int[] puzzle, 
			Set<Stack<Character>> allSolution, boolean[] visited){
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Character> direction = new Stack<Character>();
		for (Character c: direction) {
			c = null;
		}
		stack.push(0);
		visited[0] = true;
		Integer popedNum = 0;
		boolean justpoped = false;
		Character lastd = null;
		while (!stack.isEmpty()) {
			//System.out.println("stack peek is " + stack.peek());
			if (stack.peek() == puzzle.length-1) {
				Stack<Character> currentResult = new Stack<Character>();
				Stack<Character> savedResult = new Stack<Character>();
				while(!direction.isEmpty()) {
					Character c = direction.pop();
					currentResult.push(c);
					savedResult.push(c);
				}
				allSolution.add(savedResult);
				while(!currentResult.isEmpty()) {
					direction.push(currentResult.pop());
				}
				popedNum = stack.pop();
				if (!direction.isEmpty()) {
					lastd = direction.pop();
				}
				
				visited[popedNum] = false;
				justpoped = true;
			} else if ((!justpoped || direction.isEmpty()) && stack.peek() - puzzle[stack.peek()] >= 0 && !visited[stack.peek() - puzzle[stack.peek()]]) {
				int temp = stack.peek() - puzzle[stack.peek()];
				stack.push(temp);
				visited[temp] = true;
				direction.push('L');
				justpoped = false;
			} else if ((!justpoped || (justpoped && lastd.equals('L'))) && stack.peek() + puzzle[stack.peek()] < puzzle.length && !visited[stack.peek() + puzzle[stack.peek()]]) {
				int temp = stack.peek() + puzzle[stack.peek()];
				stack.push(temp);
				visited[temp] = true;
				direction.push('R');
				justpoped = false;
			} else {
				popedNum = stack.pop();
				if (!direction.isEmpty()) {
					lastd = direction.pop();
				}
				
				visited[popedNum] = false;
				justpoped = true;
			}
			
			
		}
	}

}

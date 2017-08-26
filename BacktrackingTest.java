package backtracking;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class BacktrackingTest {
	int[] puzzle1, puzzle2;

	@Before
	public void setUp() throws Exception {
		puzzle1 = new int[] {3, 6, 4, 1, 3, 4, 2, 5, 3, 0};
		puzzle2 = new int[] {3, 1, 2, 3, 0};
	}

	@Test
	public void testSolve() {
		Stack<Character> r;
		r = Backtracking.solve(puzzle1);
		int position = 0;
		while (!r.empty()) {
			if (r.pop() == 'R') {
				position += puzzle1[position];
			} else {
				position -= puzzle1[position];
			}
		}
		assertEquals(9, position);
		
		assertEquals(null, Backtracking.solve(puzzle2));
	}
	
	@Test
	public void testAllSolution() {
		Set<Stack<Character>> allSolution = Backtracking.findAllSolutions(puzzle1);
		for (Stack<Character> sc : allSolution) {
			while (!sc.isEmpty()) {
				System.out.print(sc.pop());
			}
			System.out.println();
		}
	}

}

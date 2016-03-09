package navigation;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class NavigationTest {

	private Algorithm navigation;
	private PathChecker pathChecker;
	private Graph graph;
	
	@Before
    public void setUp() {
        navigation = new AlgorithmImpl();
        graph = new GraphImpl();
        graph.build("graph.xml");
        navigation.initialize(graph);
        pathChecker = new PathChecker();
        pathChecker.initialize("checker.cfg");
    }
	
	@Test
	public void test1() {
		assertTrue(true);
	}
}

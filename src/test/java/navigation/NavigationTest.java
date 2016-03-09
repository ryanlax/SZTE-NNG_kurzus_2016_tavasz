package navigation;

import org.junit.Before;

public class NavigationTest {

	private Algorithm navigation;
	private PathChecker pathChecker;
	private Graph graph;
	
	@Before
    public void setUp() {
        navigation = new AlgorithmImpl();
        graph = new GraphImpl();
        graph.initialize("graph.xml");
        navigation.initialize(graph);
        pathChecker = new PathChecker();
        pathChecker.initialize("checker.cfg");
    }
	
}

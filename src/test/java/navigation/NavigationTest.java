package navigation;

import org.junit.Before;

public class NavigationTest {

	private Algorithm navigation;
	private PathChecker pathChecker;
	
	@Before
    public void setUp() {
        navigation = new AlgorithmImpl();
        Graph graph = new GraphImpl();
        graph.build("graph.xml");
        navigation.initialize(graph);
        pathChecker = new PathChecker();
        pathChecker.init("checker.config");
    }
}

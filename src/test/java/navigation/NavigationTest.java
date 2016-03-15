package navigation;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NavigationTest {

	private Algorithm navigation;
	private PathChecker pathChecker;
	private File inputGraph;
	private ThreadMXBean threadMxBean;

	@Before
	void setUp() {
		pathChecker = new PathChecker();
		pathChecker.initialize("checker.cfg");
		inputGraph = new File("graph.xml");
		threadMxBean = ManagementFactory.getThreadMXBean();
	}

	@Test
	void shortestPathTest01() {
		final int startNodeId = 40644;
		final int destinationNodeId = 7521;
		navigation = createNavigation();
		DistanceResult result = navigation.findShortestPath(startNodeId, destinationNodeId);
		assertShortestPathValid(result, startNodeId, destinationNodeId, 1);
	}

	@Test
	void shortestPathTest02() {
		navigation = createNavigation();
		for (int i = 0; i < 100; i++) {
			DistanceResult result = navigation.findShortestPath(40644, 7521);
			assertTrue(pathChecker.isContinuous(result.getResultPath())
					&& isValueInInterval(1,
							result.getTravelDistanceOfResultPath(), 1 * 3));
		}
	}

	private Algorithm createNavigation() {
		Graph graph = new GraphImpl();
		graph.initializeFromFile(inputGraph);
		Algorithm navigation = new AlgorithmImpl();
		navigation.preprocess(graph);
		return navigation;
	}

	private void assertShortestPathValid(DistanceResult result,
			int startNodeId, int destinationNodeId, double distanceOptimum) {
		assertPathValid(result.getResultPath(), startNodeId, destinationNodeId);
		assertTrue(isValueInInterval(0.95 * distanceOptimum,
				result.getTravelDistanceOfResultPath(), 2.0 * distanceOptimum));
	}

	private void assertFastestPathValid(TimeResult result, int startNodeId,
			int destinationNodeId, double timeOptimum) {
		assertPathValid(result.getResultPath(), startNodeId, destinationNodeId);
		assertTrue(isValueInInterval(0.95 * timeOptimum,
				result.getTravelTimeOfResultPath(), 2.0 * timeOptimum));
	}

	private void assertPathValid(List<Integer> path, int startNodeId,
			int destinationNodeId) {
		assertTrue(pathChecker.isStartingWith(path, startNodeId)
				&& pathChecker.isEndingWith(path, destinationNodeId)
				&& pathChecker.isContinuous(path));
	}

	private static boolean isValueInInterval(double lowerBound,
			double objectiveFunctionValue, double upperBound) {
		return lowerBound <= objectiveFunctionValue
				&& objectiveFunctionValue <= upperBound;
	}
}

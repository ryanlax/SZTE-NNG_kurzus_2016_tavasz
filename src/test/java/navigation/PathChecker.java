package navigation;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PathChecker {

	private Map<Integer, Integer> edges;

	public PathChecker() {
		this.edges = new HashMap<Integer, Integer>();
	}

	public void init(String path) {
		try {
			for (String line : Files.readAllLines(Paths.get(path))) {
				String[] data = line.split("\\s+");
				Integer from = Integer.valueOf(data[0]);
				Integer to = Integer.valueOf(data[1]);
				edges.put(from, to);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isContinuous(Iterable<Integer> path) {
		Iterator<Integer> it = path.iterator();
		if (it.hasNext()) {
			Integer previousVertex = it.next();
			while (it.hasNext()) {
				int currentVertex = it.next();
				if (edges.get(previousVertex).intValue() != currentVertex) {
					return false;
				}
				previousVertex = currentVertex;
			}
		}
		return true;
	}
}

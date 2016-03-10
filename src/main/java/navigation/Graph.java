package navigation;

import java.io.File;

/**
 * @author blevai
 *
 */
public interface Graph {

	/**
	 * Implement your graph construction here using an xml file of predefined format.
	 * 
	 * @param inputXmlFile
	 *            the xml format file containing the graph data
	 */
	void initializeFromFile(File inputXmlFile);

}

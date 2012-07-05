package co.ntier.cms;

import java.io.PrintWriter;

public interface PluginDescriptor {

	
	String getIdentifier();
	String getName();
	
	void writeData(PrintWriter writer);
	
}

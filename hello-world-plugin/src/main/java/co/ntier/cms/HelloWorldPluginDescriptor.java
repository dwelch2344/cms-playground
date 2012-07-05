package co.ntier.cms;

import java.io.PrintWriter;

public class HelloWorldPluginDescriptor implements PluginDescriptor{

	public String getIdentifier() {
		return "co.ntier.cms.HelloWorldPlugin";
	}

	public String getName() {
		return "Hello World Plugin";
	}

	public void writeData(PrintWriter writer) {
		writer.write("Hello world from plugin!");
	}

}

package net.unit8.zurui.less.example;

import java.io.File;

import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.scan.StandardJarScanner;

public class Main {
	public static void main(String[] args) throws Exception {
		String appBase = new File("src/main/webapp").getAbsolutePath();
		Integer port = 8082;
		String contextPath = "/";

		Tomcat tomcat = new Tomcat();
		tomcat.enableNaming();
		tomcat.setPort(port);

		tomcat.setBaseDir(".");
		tomcat.getHost().setAppBase(appBase);

		StandardServer server = (StandardServer) tomcat.getServer();
		AprLifecycleListener listener = new AprLifecycleListener();
		server.addLifecycleListener(listener);


		StandardContext ctx = (StandardContext) tomcat.addWebapp(contextPath, appBase);
		((StandardJarScanner) ctx.getJarScanner()).setScanAllDirectories(true);
		/*
		VirtualDirContext resources = new VirtualDirContext();
		resources.setExtraResourcePaths("/WEB-INF/classes=" + "../zurui-less/target/classes");
		ctx.setResources(resources);
		*/
		tomcat.start();
		tomcat.getServer().await();
	}
}

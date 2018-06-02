package my.demo.app.interactive;

import com.vaadin.server.VaadinServlet;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    private static int port = 8585;

    public static void main(String[] args) {

        try {
            String propertiesFilePath = parseArgs(args);
            readPropertiesFile(propertiesFilePath);

            Server server = new Server(port);

            ServletContextHandler contextHandler
                    = new ServletContextHandler(ServletContextHandler.SESSIONS);
            contextHandler.setContextPath("/");

            ServletHolder sh = new ServletHolder(new VaadinServlet());
            contextHandler.addServlet(sh, "/*");
            contextHandler.setInitParameter("ui", DemoUi.class.getCanonicalName());
            // Set other init params like Vaadin productionMode
            contextHandler.setInitParameter("productionMode", "true");

            server.setHandler(contextHandler);

            server.start();
            server.join();

            logger.info("Portal process stopped");

        } catch (Exception e) {
            logger.error("Error starting server", e);
            System.exit(-1);
        }
    }


    private static void readPropertiesFile(String filePath) {
        Properties properties = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream(filePath)) {
            properties.load(input);
            port = Integer.parseInt(properties.getProperty("webapp.port"));
        } catch (IOException ex) {
            logger.error("Failed to load properties file" + filePath);
        }
    }

    private static String parseArgs(String[] args) {
        if (args.length < 1) {
            return "application.properties";
        } else {
            return args[0];
        }
    }

}

import Services.Service;
import Services.ServiceInit;
import freemarker.template.Configuration;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.modelAndView;

public class Main {
    public static void main(String[] args) throws SQLException {
        ServiceInit.iniciaDb();
        Service.getInstancia().testConnection();
        ServiceInit.createTable();
        ServiceInit.stopDb();


        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setClassForTemplateLoading(Main.class, "/Template");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        Spark.get("/", (request, response) -> {
            String Algo = "Test";
            Map<String, Object> attributes = new HashMap<>();
            //attributes.put("Algo", Algo);
            return modelAndView(attributes, "home.ftl");
        }, freeMarkerEngine);

    }
}

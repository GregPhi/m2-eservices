import annotation.Controller;
import annotation.RequestMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/*", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    private Map<String, Method> uriMappings = new HashMap<>();

    @Override
    /**
     *
     Une fois les annotations analysées, le routage des requêtes se fait de la manière suivante :

     Récupération de l’URI entrante (depuis l’objet HttpServletRequest)

     Récupération de la méthode implémentant l’URI (issue de l’analyse du code)

     Si aucune méthode n’est trouvée, renvoyer une erreur 404

     Instanciation du controller

     Récupération des paramètres (depuis l’objet HttpServletRequest)

     Appel de la méthode (avec les paramètres ou non)

     En cas d’exception, renvoyer une erreur 500 avec le message de l’exception

     En cas de succès, récupérer le résultat de l’appel, et renvoyer le résultat convertit en chaîne de caractères
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Method method = this.uriMappings.get(uri);
        if(method == null){
            req.setAttribute("status",404);
            req.setAttribute("error","no mapping found for request uri "+uri);
            this.getServletContext().getRequestDispatcher("").forward(req,resp);
        }else{
            Enumeration<String> attributes = req.getAttributeNames();
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // on enregistre notre controller au démarrage de la servlet
        this.registerController(HelloController.class);
    }

    /**
     * This methods checks the following rules :
     * - The controllerClass is annotated with @annotation.Controller
     * Then all methods are scanned and processed by the registerMethod method
     * @param controllerClass the controller to scan
     */
    protected void registerController(Class controllerClass) throws IllegalArgumentException{
        if(null == controllerClass.getAnnotation(Controller.class)){
            throw new IllegalArgumentException();
        }else{
            Method[] methods = controllerClass.getMethods();
            for(Method m : methods){
                registerMethod(m);
            }
        }
    }

    /**
     * This methods checks the following rules :
     * - The method is annotated with @annotation.RequestMapping
     * - The @annotation.RequestMapping annotation has a URI
     * - The method does not return void
     * If these rules are followed, the method and its URI are added to the uriMapping map.
     * @param method the method to scan
     */
    protected void registerMethod(Method method) {
        RequestMapping annotat = method.getDeclaredAnnotation(RequestMapping.class);
        if(annotat != null){
            String uri = annotat.uri();
            if(uri != null){
                if(!method.getReturnType().equals(Void.TYPE)) {
                    this.uriMappings.put(uri,method);
                }
            }
        }
    }

    protected Map<String, Method> getMappings(){
        return this.uriMappings;
    }

    protected Method getMappingForUri(String uri){
        return this.uriMappings.get(uri);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dai.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author AD
 */
public class MyServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Map<String, String> siteMap = new HashMap<>();
//        siteMap.put("", "login.html");
//        siteMap.put("login", "LoginServlet");
//        siteMap.put("search", "SearchServlet");
//        siteMap.put("delete", "DeleteAccountServlet");
//        siteMap.put("update", "UpdateAccountServlet");
//        siteMap.put("BookStore", "BookStore.html");
//        siteMap.put("cart", "CartServlet");
//        siteMap.put("view", "ViewServlet");
//        siteMap.put("createNewAccount", "createNewAccount.html");

        ServletContext context = sce.getServletContext();
        String realPath = context.getRealPath("/SiteMap.txt");
        try {
            File f = new File(realPath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.substring(0, 1).equals("+")) {
                    siteMap.put("", line.substring(1));
                } else {
                    StringTokenizer st = new StringTokenizer(line, "+");
                    String key = st.nextToken();
                    String value = st.nextToken();
                    siteMap.put(key, value);
                }

            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        context.setAttribute("SITE_MAP", siteMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

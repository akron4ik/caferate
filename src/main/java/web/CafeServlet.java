package web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import web.cafe.CafeRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class CafeServlet extends HttpServlet {
    private ConfigurableApplicationContext springContext;
    private CafeRestController cafeController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        cafeController = springContext.getBean(CafeRestController.class);
    }
}

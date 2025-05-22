import com.frank.spring.ioc.SimpleBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: maxinhang.
 */
public class Test {
    public static void main(String[] args) {
        String xmlPath = "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        SimpleBean simpleBean = (SimpleBean) applicationContext.getBean("simpleBean");
        simpleBean.sayHello("mh");
    }
}

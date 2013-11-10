import org.slf4j.LoggerFactory
import com.smartmeal.app._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  	override def init(context: ServletContext) {
    	context.mount(new MyScalatraServlet, "/*")
  	}
}

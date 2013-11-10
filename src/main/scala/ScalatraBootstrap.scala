import com.mchange.v2.c3p0.ComboPooledDataSource
import org.slf4j.LoggerFactory
import scala.slick.session.Database
import com.smartmeal.app._
import org.scalatra._
import javax.servlet.ServletContext



class ScalatraBootstrap extends LifeCycle {
	val logger = LoggerFactory.getLogger(getClass)

  	val cpds = new ComboPooledDataSource
  	logger.info("Created c3p0 connection pool")
  	override def init(context: ServletContext) {
  		val db = Database.forDataSource(cpds)
    	context.mount(new MyScalatraServlet, "/*")
  	}

  	private def closeDbConnection() {
    	logger.info("Closing c3po connection pool")
    	cpds.close
  	}

  	override def destroy(context: ServletContext) {
    	super.destroy(context)
    	closeDbConnection
    }
}

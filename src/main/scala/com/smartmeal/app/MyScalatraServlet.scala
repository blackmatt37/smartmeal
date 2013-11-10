package com.smartmeal.app

import scala.slick.session.Database
import org.scalatra._
import scalate.ScalateSupport
import oscar.linprog.modeling._
import oscar.linprog._
import oscar.algebra._
import scala.slick.session._
import scala.slick.lifted.TypeMapper._
import scala.slick.jdbc.{ GetResult, StaticQuery => Q }
import Database.threadLocalSession

case class SlickApp(db: Database) extends MyScalatraServlet with SlickRoutes

case class Supplier(id: Int, name: String, street: String, city: String, state: String, zip: String)

class MyScalatraServlet extends SmartmealStack with GZipSupport{

object MyLPProblem {
   def solve =  {
      val lp = LPSolver() 
      val x0 = LPVar(lp,"x0",0,40) // can take value in continuous interval [0,40]
      val x1 = LPVar(lp,"x1",0, 1000) 
      val x2 = LPVar(lp,"x2",0 ,17) 
      val x3 = LPVar(lp,"x3",2,3)  
     
      lp.maximize(x0+2*x1+3*x2+x3) subjectTo {
      lp.add(-1*x0 + x1 + x2 + 10*x3 <= 20)
      lp.add(x0 - 3.0*x1 + x2 <= 30)
      lp.add(x1 - 3.5*x3 == 0 )
      }
      // println("objective"+lp.getObjectiveValue()) 
      x1.value
   }
}



  get("/") {
    contentType = "text/html"
    jade("home.jade")
  }
  get("/home") {
      contentType = "text/html"
      jade("home.jade")
  }
  post("/home") {
    params("sex")
    params("email")
  }
  get("/meal") {
    contentType = "text/html"
    // MyLPProblem.solve
    jade("meal.jade")
  }
  post("/meal") {
    params("meal")
  }
  
}
trait SlickRoutes extends MyScalatraServlet {
  val db: Database
  get("/db") {
    db withSession {
      Q.updateNA("create table suppliers("+
  "id int not null primary key, "+
  "name varchar not null, "+
  "street varchar not null, "+
  "city varchar not null, "+
  "state varchar not null, "+
  "zip varchar not null)").execute
    }
    "DONE"
  }
}









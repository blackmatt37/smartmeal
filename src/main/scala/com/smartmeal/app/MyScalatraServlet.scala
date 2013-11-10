package com.smartmeal.app

import org.scalatra._
import scalate.ScalateSupport
import oscar.linprog.modeling._
import oscar.linprog._
import oscar.algebra._

class MyScalatraServlet extends SmartmealStack {

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
      x1
   }
}




  get("/") {
    contentType = "text/html"
    MyLPProblem.solve
    // jade("home.jade")
  }
  get("/home") {
      contentType = "text/html"
      jade("home.jade")
  }
  get("/meal") {
    contentType = "text/html"
    jade("meal.jade")
  }
  
}

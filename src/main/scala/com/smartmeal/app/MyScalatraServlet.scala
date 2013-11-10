package com.smartmeal.app


import org.scalatra._
import scalate.ScalateSupport
import oscar.linprog.modeling._
import oscar.linprog._
import oscar.algebra._
import com.almworks.sqlite4java._
import java.io._

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

object calc {
  def protein(weight: Int, activity: Int): Double = {
    if(activity == 0) weight*(2.0/5) else {
      if(activity == 1) weight*(1.0/2) else {
        if(activity == 2) weight*(3.0/5) else weight*(7.0/10)
      }
    }
  }

  def calories(age: Int, gender: String, activity: Int, height: Int, weight: Int): Double = {
    if(gender equals "male") {
      val prelim: Double = 66.47 + 6.25*weight + 12.7*height - 6.76*age
      if(activity==0) prelim*1.3 else {if(activity==1) prelim*1.6 else {if(activity==2) prelim*1.8 else prelim*2.2}}
    }
    else {
      val prelim: Double = 65.51 + 4.386*weight + 4.6736*height - 4.68*age
      if(activity==0) prelim*1.3 else {if(activity==1) prelim*1.5 else {if(activity==2) prelim*1.7 else prelim*1.9}}
    }
  }

  def inches(feet: Int, inch: Int): Int = {
    feet*12 + inch
  }

  def calcium(age: Int, gender: String): Int = {
     0
  }

  def fat(age: Int, gender: String, activity: Int, height: Int, weight: Int): Double = {
    calories(age, gender, activity, height, weight)/36.0
  }

  def carbs(age: Int, gender: String, activity: Int, height: Int, weight: Int): Double = {
    calories(age, gender, activity, height, weight)*0.15378
  }

  def sugar(gender: String): Int = {
    if(gender equals "male") 30 else 20
  }

}



  get("/") {
    contentType = "text/html"
    jade("home.jade")
  }
  get("/db") {
    val db : SQLiteConnection = new SQLiteConnection(new File("/tmp/database"));
    db.open(true)
    db.exec("CREATE TABLE Test (test varchar(255));")

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









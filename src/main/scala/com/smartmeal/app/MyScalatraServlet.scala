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
      val cal = Array(255, 467, 287, 100, 311, 132, 225, 50, 117, 32, 35, 204, 404, 200, 272, 207, 65, 115, 150, 93, 188, 143, 142, 157, 147, 139, 166, 142, 100, 68, 91, 105, 72, 197)
      val mip = MIPSolver()
      val x = Array.fill(30)(MIPVar(mip, "x", 0 to 1))
     
      mip.minimize(sum(x)) subjectTo {
        mip.add(sum(0 to x.size)(i => x(i)*cal(i)) >= 200)
      }
      x(1).getValue
  
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
     if(age < 1) 200 else {
      if(age > 1 && age < 3) 700 else {
        if(age > 4 && age < 8) 1000 else {
          if(age > 9 && age < 18) 1300 else {
            if(age > 19 && age < 50) {if(gender equals "male") 1000 else 1200} else 1200
          }
        }
      }
     }
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

  def nutri(age: Int, gender: String, activity: Int, feet: Int, inch: Int, weight: Int) = {
    val height: Int = inches(feet, inch)
    (calories(age, gender, activity, height, weight), carbs(age, gender, activity, height, weight), sugar(gender), fat(age, gender, activity, height, height), protein(weight, activity), calcium(age, gender))
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
    val age: Int = params("age").toInt
    val gender: String = params("sex")
    val activity: Int = 1 //params("activity").toInt
    val feet: Int = params("feet").toInt
    val inch: Int = params("inches").toInt
    val weight: Int = params("weight").toInt
    info = calc.nutri(age, gender, activity, feet, inch, weight)
    
  }
  get("/meal") {
    contentType = "text/html"
    MyLPProblem.solve
    // println("AGAIN")
    // jade("meal.jade")
  }
  post("/meal") {
    params("meal")
  }
  var info = ()
}









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
      val x0 = MIPVar(mip,"x0",0 to 1)
      val x1 = MIPVar(mip,"x1",0 to 1)
      val x2 = MIPVar(mip,"x2",0 to 1)
      val x3 = MIPVar(mip,"x3",0 to 1)
      val x4 = MIPVar(mip,"x4",0 to 1)
      val x5 = MIPVar(mip,"x5",0 to 1)
      val x6 = MIPVar(mip,"x6",0 to 1)
      val x7 = MIPVar(mip,"x7",0 to 1)
      val x8 = MIPVar(mip,"x8",0 to 1)
      val x9 = MIPVar(mip,"x9",0 to 1)
      val x10 = MIPVar(mip,"x10",0 to 1)
      val x11 = MIPVar(mip,"x11",0 to 1)
      val x12 = MIPVar(mip,"x12",0 to 1)
      val x13 = MIPVar(mip,"x13",0 to 1)
      val x14 = MIPVar(mip,"x14",0 to 1)
      val x15 = MIPVar(mip,"x15",0 to 1)
      val x16 = MIPVar(mip,"x16",0 to 1)
      val x17 = MIPVar(mip,"x17",0 to 1)
      val x18 = MIPVar(mip,"x18",0 to 1)
      val x19 = MIPVar(mip,"x19",0 to 1)
      val x20 = MIPVar(mip,"x20",0 to 1)
      val x = Array(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18, x19, x20)
      mip.minimize(sum(0 to 10)(i => x(i)*cal(i))) subjectTo {
        mip.add(x0 == 1)
        mip.add(x1 == 1)
      }
      x map (_.getValue)
  
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
    // jade("meal.jade")
    println(MyLPProblem.solve.mkString(" "))
    // println("AGAIN")
    
  }
  post("/meal") {
    params("meal")
  }
  var info = ()
}









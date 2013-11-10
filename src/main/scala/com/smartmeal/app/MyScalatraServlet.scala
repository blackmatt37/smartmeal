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
      val cal =   Array(255, 467, 287, 100, 311, 132, 225, 50, 117, 32, 35, 204, 404, 200, 272, 207, 65, 115, 150, 93, 188, 143, 142, 157, 147, 139, 166, 142, 100, 68, 91, 105, 72, 197)
      val carb =  Array(38, 93, 38, 15, 70, 15, 28, 26, 35, 0, 9, 25, 47, 22, 43, 41, 2, 27, 12, 13, 39, 35, 29, 32, 29, 30, 32, 1, 1, 0.29, 24, 27, 18, 31)
      val sugar = Array(8, 57, 8, 2, 51, 2, 14, 2, 11, 0, 9, 6, 27, 21, 5, 4, 1, 21, 11, 13, 0, 15, 14, 9, 12, 15, 13, 0.611, 0.771, 0.14, 9.5, 7, 8, 26)
      val fat =   Array(8, 8, 12, 4, 3, 8, 12, 2, 6, 4, 0, 10, 21, 11, 6, 1, 5, 0.368, 8, 0, 3, 0.101, 1, 2, 2, 2, 4, 10, 8, 5, 0.273, 0.389, 0, 3)
      val prot =  Array(7, 7, 7, 2, 2, 2, 3, 5, 5, 0, 0, 5, 8, 3, 11, 8, 3, 2, 8, 9, 4, 2, 2, 6, 4, 2, 2, 11, 7, 5, 0.482, 1, 0.508, 11)
      val calc =  Array(189, 270, 190.1, 79, 160, 80.1, 30, 46, 47.1, 1.1, 0, 20, 60, 40, 90, 30, 60, 160, 300, 320, 150, 1.2, 140, 20, 130, 120, 180, 190, 30, 20, 10, 10, 0, 391)

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
      val x21 = MIPVar(mip,"x21",0 to 1)
      val x22 = MIPVar(mip,"x22",0 to 1)
      val x23 = MIPVar(mip,"x23",0 to 1)
      val x24 = MIPVar(mip,"x24",0 to 1)
      val x25 = MIPVar(mip,"x25",0 to 1)
      val x26 = MIPVar(mip,"x26",0 to 1)
      val x27 = MIPVar(mip,"x27",0 to 1)
      val x28 = MIPVar(mip,"x28",0 to 1)
      val x29 = MIPVar(mip,"x29",0 to 1)
      val x30 = MIPVar(mip,"x30",0 to 1)
      val x31 = MIPVar(mip,"x31",0 to 1)
      val x32 = MIPVar(mip,"x32",0 to 1)
      val x33 = MIPVar(mip,"x33",0 to 1)
      val x = Array(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18, x19, x20, x21, x22, x23, x24, x25, x26, x27, x28, x29, x30, x31, x32, x33)
      mip.minimize(sum(0 to 33)(i => x(i)*cal(i))) subjectTo {
        // mip.add(sum(0 to 33)(i => x(i)*carb(i)) <= 150)
        // mip.add(sum(0 to 33)(i => x(i)*cal(i)) == ))
        mip.add(x1 == 1)
        mip.add(x17 + x18 + x19 == 1)
        mip.add(x20 +x21 + x22 + x23 + x24 + x25 + x6 == 1)
        mip.add(x30 + x31 + x32 + x33 == 1)
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
    calc.nutri(age, gender, activity, feet, inch, weight)
    
  }
  get("/meal") {
    contentType = "text/html"
    // jade("meal.jade")
    MyLPProblem.solve.mkString(" ")
    // println("AGAIN")
    
  }
  post("/meal") {
    params("meal")
  }
  var info = ()
}









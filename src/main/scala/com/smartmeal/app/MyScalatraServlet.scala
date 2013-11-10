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
   def solve(breakfast: Int, lunch: Int, dinner: Int) =  {
      val cal =   Array(255, 467, 287, 100, 311, 132, 225, 50, 117, 32, 35, 204, 404, 200, 272, 207, 65, 115, 150, 93, 188, 143, 142, 157, 147, 139, 166, 142, 100, 68, 91, 105, 72, 197, 110, 70, 50, 240, 269, 120, 419, 389, 15, 250, 345, 70, 222, 90, 220, 421, 548, 249, 200, 286, 43, 61, 228, 257, 115, 115, 379)
      //add the rest of nutrition data
      val carb =  Array(38, 93, 38, 15, 70, 15, 28, 26, 35, 0, 9, 25, 47, 22, 43, 41, 2, 27, 12, 13, 39, 35, 29, 32, 29, 30, 32, 1, 1, 0.29, 24, 27, 18, 31, 1, 0, 26, 52, 24, 23, 55, 47, 4, 28, 40, 3, 5, 2, 37, 38, 40, 37, 6, 12, 3, 12, 43, 55, 27, 28, 0)
      val sugar = Array(8, 57, 8, 2, 51, 2, 14, 2, 11, 0, 9, 6, 27, 21, 5, 4, 1, 21, 11, 13, 0, 15, 14, 9, 12, 15, 13, 0.611, 0.771, 0.14, 9.5, 7, 8, 26, 0, 0, 2, 4, 0, 4, 12, 4, 4, 3, 4, 3, 0.125, 0, 0.375, 8, 8, 4, 1, 2.39, 0.695, 3, 0.588, 0.135, 21, 25, 0)
      val fat =   Array(8, 8, 12, 4, 3, 8, 12, 2, 6, 4, 0, 10, 21, 11, 6, 1, 5, 0.368, 8, 0, 3, 0.101, 1, 2, 2, 2, 4, 10, 8, 5, 0.273, 0.389, 0, 3, 9, 5, 2, 14, 14, 1, 15, 15, 0, 11, 14, 2, 12, 2, 7, 20, 30, 10, 16, 24, 4, 0.61, 3, 1, 0.368, 0.285, 20)
      val prot =  Array(7, 7, 7, 2, 2, 2, 3, 5, 5, 0, 0, 5, 8, 3, 11, 8, 3, 2, 8, 9, 4, 2, 2, 6, 4, 2, 2, 11, 7, 5, 0.482, 1, 0.508, 11, 7, 5, 5, 20, 12, 4, 16, 16, 0, 10, 15, 10, 22, 16, 4, 22, 30, 5, 11, 11.362, 0.181, 3, 7, 5, 2, 0.298, 46)
      val calcium =  Array(189, 270, 190.1, 79, 160, 80.1, 30, 46, 47.1, 1.1, 0, 20, 60, 40, 90, 30, 60, 160, 300, 320, 150, 1.2, 140, 20, 130, 120, 180, 190, 30, 20, 10, 10, 0, 391, 200, 200, 46, 492, 20, 100, 120, 120, 0, 150, 140, 0, 20, 0, 20, 120, 260, 70, 150, 170, 10, 80, 20, 20, 160, 20, 10)
      val primary = Array("Pancake", "Pancake", "Pancake", "Waffle", "Waffle", "Waffle", "Donut", "Toast", "Toast", "", "", "Croissant", "Croissant", "", "Bagel", "Bagel", "", "Orange Juice", "Milk", "Milk", "Oatmeal", "Cereal", "Cereal", "Cereal", "Cereal", "Cereal", "Cereal", "Egg", "Egg", "Egg", "Apple", "Banana", "Mixed Fruit", "Plain Yogurt", "Cheese", "Cheese", "Toast", "Cheese Sandwitch", "", "", "Hot Dog", "Hot Dog", "", "Pizza", "Pizza", "Ham", "Fried Chicken", "Buffalo Wings", "French Fries", "Hamburger", "Cheeseburger", "Mashed Potatoes", "Caesar Salad", "Caesar Salad", "", "Roasted Vegetables", "Rice", "Rice", "Orange Juice", "Apple Juice", "Steak")
      val second  = Array("", "Maple Syrup", "Butter", "", "Maple Syrup", "Butter", "", "", "Butter, Jam", "", "", "", "Nutela", "", "Cream Cheese", "", "", "", "Whole", "Skim", "", "Frosted Flakes", "Lucky Charmes", "Specieal K", "Honey Nut Cheerios", "Cocoa Puffs", "Cinnamon Toast Crunch", "Omelette", "Scrambled", "Fried", "", "", "", "", "Cheddar", "Swiss", "", "", "", "", "Ketchup", "", "", "Cheese", "Pepperoni", "", "", "", "", "", "", "", "", "Ranch", "", "", "Fried", "White", "", "", "")
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
      val x34 = MIPVar(mip,"x34",0 to 1)
      val x35 = MIPVar(mip,"x35",0 to 1)
      val x36 = MIPVar(mip,"x36",0 to 1)
      val x37 = MIPVar(mip,"x37",0 to 1)
      val x38 = MIPVar(mip,"x38",0 to 1)
      val x39 = MIPVar(mip,"x39",0 to 1)
      val x40 = MIPVar(mip,"x40",0 to 1)
      val x41 = MIPVar(mip,"x41",0 to 1)
      val x42 = MIPVar(mip,"x42",0 to 1)
      val x43 = MIPVar(mip,"x43",0 to 1)
      val x44 = MIPVar(mip,"x44",0 to 1)
      val x45 = MIPVar(mip,"x45",0 to 1)
      val x46 = MIPVar(mip,"x46",0 to 1)
      val x47 = MIPVar(mip,"x47",0 to 1)
      val x48 = MIPVar(mip,"x48",0 to 1)
      val x49 = MIPVar(mip,"x49",0 to 1)
      val x50 = MIPVar(mip,"x50",0 to 1)
      val x51 = MIPVar(mip,"x51",0 to 1)
      val x52 = MIPVar(mip,"x52",0 to 1)
      val x53 = MIPVar(mip,"x53",0 to 1)
      val x54 = MIPVar(mip,"x54",0 to 1)
      val x55 = MIPVar(mip,"x55",0 to 1)
      val x56 = MIPVar(mip,"x56",0 to 1)
      val x57 = MIPVar(mip,"x57",0 to 1)
      val x58 = MIPVar(mip,"x58",0 to 1)
      val x59 = MIPVar(mip,"x59",0 to 1)
      val x60 = MIPVar(mip,"x60",0 to 1)
      val x = Array(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18, x19, x20, x21, x22, x23, x24, x25, x26, x27, x28, x29, x30, x31, x32, x33, x34, x35, x36, x37, x38, x39, x40, x41, x42, x43, x44, x45, x46, x47, x48, x49, x50, x51, x52, x53, x54, x55, x56, x57, x58, x59, x60)
      mip.minimize(sum(0 to 33)(i => x(i)*cal(i))) subjectTo {

        mip.add(x(breakfast) == 1) //force picked meal
        mip.add(x17 + x18 + x19 == 1) //one drink
        mip.add(x20 +x21 + x22 + x23 + x24 + x25 + x6 == 1) // one cereal
        mip.add(x30 + x31 + x32 + x33 == 1) //one snack

        mip.add(x(lunch) == 1) //force lunch choice
        mip.add(x37 + x40 + x41 + x43 + x44 + x45 + x46 + x47 + x60 == 2)
        mip.add(x48 + x51 + x52 + x53 + x55 + x56 + x57 == 3)
        mip.add(x58 + x59 == 2)
        mip.add(x(dinner) == 1) // force dinner

        mip.add(sum(0 to 60)(i => x(i)*carb(i)) <= info._2+200)
        // mip.add(sum(0 to 60)(i => x(i)*sugar(i)) <= info._3+1000)
        // mip.add(sum(0 to 60)(i => x(i)*fat(i)) <= info._4+1000)
        // mip.add(sum(0 to 60)(i => x(i)*prot(i)) <= info._5+1000)
        // mip.add(sum(0 to 60)(i => x(i)*calcium(i)) <= info._6+3000)
      }
      val temp = ((x map (p => (p.getName.substring(1).toInt, p.getValue))))
      (temp filter ( _._2 == 1.0)) map {case (k,v) => k}
  
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
    contentType = "text/html"
    val age: Int = params("age").toInt
    val gender: String = params("sex")
    val activity: Int = 1 //params("activity").toInt
    val feet: Int = params("feet").toInt
    val inch: Int = params("inches").toInt
    val weight: Int = params("weight").toInt
    info = calc.nutri(age, gender, activity, feet, inch, weight).asInstanceOf[(Double, Double, Double, Double, Double, Double)]
    redirect("/your")
    
  }
  get("/your") {
    contentType = "text/html"
    jade("your.jade", "cal" -> info._1.toString.substring(0,4), "carbs" -> info._2.toString.substring(0,3), "sugar" -> info._3.toString, "fat" -> info._4.toString.substring(0,4), "protein" -> info._5.toString, "calc" -> info._6.toString)
  }
  get("/meal") {
    contentType = "text/html"
    jade("meal.jade")
    // MyLPProblem.solve.mkString(" ")
  }
  post("/meal") {
    val break: Int  = params("break").toInt
    val lunch: Int  = params("lunch").toInt
    val dinner: Int = params("dinner").toInt
    jade("plan.jade", "menu" -> MyLPProblem.solve(break, lunch, dinner), "cal" -> info._1.toString.substring(0,4), "carbs" -> info._2.toString.substring(0,3), "sugar" -> info._3.toString, "fat" -> info._4.toString.substring(0,4), "protein" -> info._5.toString, "calc" -> info._6.toString)
  }
  var info = new Tuple6(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
}









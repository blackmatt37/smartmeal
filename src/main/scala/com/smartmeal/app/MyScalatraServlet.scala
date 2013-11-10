package com.smartmeal.app

import org.scalatra._
import scalate.ScalateSupport

class MyScalatraServlet extends SmartmealStack {

  get("/") {
    contentType = "text/html"
    jade("home.jade")
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

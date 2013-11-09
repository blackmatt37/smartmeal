package com.smartmeal.app

import org.scalatra._
import scalate.ScalateSupport

class MyScalatraServlet extends SmartmealStack {

  val pages = Array("")
  get("/") {
    contentType = "text/html"
    jade("home.jade")
  }
  get("/:id/?") {
    if (pages.contains(params("id"))) {
      contentType = "text/html"
      jade(params("id") + ".jade")
    } else pass()
  }
  
}

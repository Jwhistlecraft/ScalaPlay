package controllers

import play.api
import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok("hello world")

  }

  def redirect = Action {
    Redirect("http://www.google.com", 302)
  }





}
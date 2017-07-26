package controllers


import play.api.mvc._

class Application extends Controller {

  private val exampleList: List[Int] = (1 to 100).toList


  //Map static resources from the /public folder to the /assets URL path

  def redirect = Action {
    Redirect("http://www.google.com", 302)
  }


  //Use all the available common result helpers like Ok

  def homePage(): Action[AnyContent] = Action {
    Ok(<h1>Home Page</h1>).as(HTML)
  }

  def toDo(): Action[AnyContent] = TODO

  def pageNotFound(): Action[AnyContent] = Action {
    NotFound(<H1>Page Not Found</H1>).as(HTML)
  }

  def badRequest(): Action[AnyContent] = Action {
    BadRequest("bad request")
  }

  def internalServerError(): Action[AnyContent] = Action {
    InternalServerError("WOOOOOOOoooooops, something went wrong")
  }

  def anyStatus(): Action[AnyContent] = Action {
    val okResponse: Int = 288
    Status(okResponse)("all seems fine")
  }

  /*Have a URIs for dynamic, static, default, set, optional values with corresponding methods
    and pages that would have them displayed in a nice way*/

  def default(id: String): Action[AnyContent] = Action {
    Ok(id.toString)
  }

  def dynamic(id: Int): Action[AnyContent] = Action {
    Ok(id.toString)
  }

  def displayList(): Action[AnyContent] = Action {
    Ok(exampleList.toString)
  }

  def set(input: String): Action[AnyContent] = Action {
    Ok(input)
  }

  def optional(id: Option[String]): Action[AnyContent] = Action {
    Ok(id.toString)
  }

  //Have at least one use case of reverse routing

  def reverseRoutingHome(): Action[AnyContent] = Action {
    Redirect(routes.Application.homePage())
  }

  // One use of the TO-DO Action

  def useOfTODO(): Action[AnyContent] = TODO

  def cookiePage(): Action[AnyContent] = Action {
    Ok("cookies established").withCookies(
      Cookie("theme", "blue"))
  }

  def deleteCookies(): Action[AnyContent] = Action {
    Ok("cookie be gone").discardingCookies(
      DiscardingCookie("theme"))
  }

  def showCookies(): Action[AnyContent] = Action { request =>
    request.cookies.get("theme").map {
      theme => Ok(theme.value)
    }
     .getOrElse(Ok("No cookies :("))
  }

  def editCookies(): Action[AnyContent] = Action {
    Ok("Cookieis now red").discardingCookies(
      DiscardingCookie("theme"))withCookies Cookie("theme","red")
  }

  def session() :Action[AnyContent] = Action {
    Ok("Session has begun").withSession(
      "connect" -> "user@gmail.com"
    )
  }

  def addToSession() :Action[AnyContent] = Action {
    request => Ok("data added to session").withSession(
      request.session + ("theme" -> "magnolia")
    )
  }

  def removeFromSession() :Action[AnyContent] = Action {
    request => Ok("data removed from session").withSession(
      request.session - "theme")
  }

  def showSession() :Action[AnyContent] = Action {
    request => request.session.get("theme").map{
      theme => Ok("the colour is " + theme)
     }
     .getOrElse(Ok("not connected")
     )
  }

  def showSessionData(): Action[AnyContent] = Action{ request =>
    Ok(request.session.data.toString())
  }

  def Flash(): Action[AnyContent] = Action{
    Redirect("/flash/Data").flashing(
      "Data" -> "This was passed via flashing"
    )
  }

  def fetchFlash(): Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
      request.flash.get("Data").map{
        theme => Ok(theme)
      }.getOrElse(Ok("no data"))
  }

}


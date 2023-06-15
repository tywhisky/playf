package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import repositories._
import play.api.libs.json.Json

/** This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject() (
    val controllerComponents: ControllerComponents,
    val dataRepository: DataRepository
) extends BaseController {

  /** Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method will be
    * called when the application receives a `GET` request with a path of `/`.
    */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def getPost(postId: Int) = Action { implicit request =>
    dataRepository.getPost(postId) map { post =>
      Ok(Json.toJson(post))
    } getOrElse NotFound
  }

  def getComments(postId: Int) = Action { implicit request =>
    Ok(Json.toJson(dataRepository.getComments(postId)))
  }
}

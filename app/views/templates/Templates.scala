package views.templates

import scala.util._
import helpers._

/**
 * Controls what templates are installed, and gets the reference to the template objects.
 */
object Templates {

  var templateList:Iterable[Template] = Iterable.empty[Template]
  var currentTemplate:Option[Template] = None 
  
  /**
   * This function should be run on startup. Loads and gets ready the templates
   * @returns Returns Failure if the configuration or templates are missing. In this case, the server should display an error and then stop.
   */
  def initTemplates:Try[Template] = 
  {
    loadTemplates()
    var nowTemplate = loadCurrentTemplate
    
    nowTemplate match 
    {
      
      case Success(template) => {currentTemplate = Some(template); return nowTemplate}
      case Failure(e) => return nowTemplate
    }
    
    
  }
  
  /**
   * Loads all of the found templates into the templateList field. 
   * Call this whenever you want to refresh the templates on the server. 
   */
  private def loadTemplates()
  {
    templateList = Plugins.findAllPlugins[Template]("views.templates.Template")
  }
  
  /**
   * Takes the list of templates and sets currentTemplate it it's appropriate value.
   */
  private def loadCurrentTemplate(): Try[Template] = 
  {
    //Check and see if templateList is filled
    if(templateList.size == 0)
    {
      loadTemplates()
    }
    
    //Look in config file for template name
    var templateNameOpt:Option[String] = helpers.Configuration.getString("template.pluginName")
    var templateVersionOpt:Option[String] = Configuration.getString("template.version")
    
    templateNameOpt match 
    {
      case None => return Failure(new PluginException("Configuration key \"template.pluginName\" was not found in the configuration file. Cannot load template plugin."))
    }
    
    templateVersionOpt match 
    {
      case None => return Failure(new PluginException("Configuration key \"template.version\" was not found in the configuration file. Cannot load template plugin."))
    }
    
    //Iterate through all known template classes to find one with Templte.templateName = templateName
    var filteredList = templateList.filter(template => ((templateNameOpt.get == template.pluginName)&&(templateVersionOpt.get == template.version)))
    
    //Did we get anything?
    if(filteredList.size == 0)
    {
      val listOfPlugins:StringBuilder =  new StringBuilder
      templateList.foreach(template => listOfPlugins.append("\n"+ template.pluginName))
      return Failure(new PluginException("No plugin with name \""+templateNameOpt.get+"\" was found. List of installed template plugins: "+ listOfPlugins.toString))
    }
    else
    {
      return Success(filteredList.head)
    }
  }
  
  /**
   * Gets a reference to the template defined in the play configuration. 
   * Should not throw an exception if initTemplates was run successfully
   * @returns The Template object required to render the pages.
   */
  def getTemplate():Template = 
  {
	currentTemplate match
	{
	  case Some(template) => return template
	  case None => throw new PluginException("The view template plugin is missing/was not loaded. Not able to render webpages. This is a fatal error.")
	}
    
  }
  
  
  
  
}
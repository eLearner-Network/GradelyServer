package helpers

import scala.reflect.runtime.universe._
import org.clapper.classutil._
import java.io._

/**
 * Helper functions to handel plugin and classpath stuff.
 */
object Plugins 
{

//This section taken from http://vikashazrati.wordpress.com/2011/09/15/building-a-plugin-based-architecture-in-scala/
 
  /**
   * Returns a list containing an instance of all classes that implement, partially or fully a superclass or trait.
   * @param superclassName The fully written out, qualified, name of the superclass we want subclasses of.
   * @returns A list of instantiated classes 
   */
	def findAllPlugins[A](superclassName: String): Iterable[A] = 
	{
		val classpath = List(".").map(new File(_))
		val finder = ClassFinder(classpath)
		val classes = finder.getClasses
		val classMap = ClassFinder.classInfoMap(classes)
		val plugins = ClassFinder.concreteSubclasses(superclassName, classMap).toIterable
		 
		plugins.map
		{
		  classInfo => Class.forName(classInfo.name).newInstance().asInstanceOf[A]
		}
	}

}
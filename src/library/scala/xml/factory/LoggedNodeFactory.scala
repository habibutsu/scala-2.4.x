/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2002-2006, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

// $Id$


package scala.xml.factory


/** <p>
 *    This class logs what the nodefactory is actually doing.
 *    If you want to see what happens during loading, use it like this:
 *  </p><pre>
 *  <b>object</b> testLogged <b>extends</b> Application {
 *
 *    <b>val</b> x = <b>new</b> scala.xml.nobinding.NoBindingFactoryAdapter
 *          <b>with</b> scala.xml.LoggedNodeFactory[scala.xml.Elem]()
 *          <b>with</b> scala.util.logging.ConsoleLogger;
 *
 *    Console.println("Start");
 *
 *    <b>val</b> doc = x.loadXML(new org.xml.sax.InputSource("http://lamp.epfl.ch/~buraq"));
 *
 *    Console.println("End");
 *
 *    Console.println(doc);
 *  }</pre>
 *
 *  @author  Burak Emir
 *  @version 1.0
 */
abstract class LoggedNodeFactory[A <: Node]
extends NodeFactory[A]
with scala.util.logging.Logged {

  // configuration values;
  val logNode      = true
  val logText      = false
  val logComment   = false
  val logProcInstr = false

  final val NONE  = 0
  final val CACHE = 1
  final val FULL  = 2
  /** 0 = no loggging, 1 = cache hits, 2 = detail */
  val logCompressLevel  = 1

  // methods of NodeFactory

  /** logged version of makeNode method */
  override def makeNode(pre: String, label: String, attrSeq: MetaData,
                        scope: NamespaceBinding, children: Seq[Node]): A = {
    if (logNode)
      log("[makeNode for "+label+"]");

    val hash = Utility.hashCode(pre, label, attrSeq.hashCode(), scope.hashCode(), children)

    /*
    if(logCompressLevel >= FULL) {
      log("[hashcode total:"+hash);
      log(" elem name "+uname+" hash "+ ? ));
      log(" attrs     "+attrSeq+" hash "+attrSeq.hashCode());
      log(" children :"+children+" hash "+children.hashCode());
    }
    */
    if (!cache.get( hash ).isEmpty && (logCompressLevel >= CACHE))
      log("[cache hit !]");

    super.makeNode(pre, label, attrSeq, scope, children)
  }

  override def makeText(s: String) = {
    if (logText)
      log("[makeText:\""+s+"\"]");
    super.makeText(s)
  }

  override def makeComment(s: String): Seq[Comment] = {
    if (logComment)
      log("[makeComment:\""+s+"\"]");
    super.makeComment(s)
  }

  override def makeProcInstr(t: String, s: String): Seq[ProcInstr] = {
    if (logProcInstr)
      log("[makeProcInstr:\""+t+" "+ s+"\"]");
    super.makeProcInstr(t, s)
  }

}

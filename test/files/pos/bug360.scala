// $Id$

abstract class Bug360A requires Bug360C {
  def f: String = "hello";
}
trait Bug360B requires Bug360C {
  object d {
    Console.println(f);
  }
}
abstract class Bug360C extends Bug360A with Bug360B;

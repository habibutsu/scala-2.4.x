package expAbstractData;

/** A base class consisting of
 *   - a root trait (i.e. abstract class) `Exp' with an `eval' function
 *   - an abstract type `exp' bounded by `Exp'
 *   - a concrete instance class `Num' of `Exp' for numeric literals
 */
trait Base {
  type exp <: Exp;

  trait Exp {
    def eval: int
  }
  class Num(v: int) requires exp extends Exp {
    val value = v;
    def eval = value
  }
}

object testBase extends Application with Base {
  type exp = Exp;
  val term = new Num(2);
  Console.println(term.eval);
}

/** Data extension: An extension of `Base' with `Plus' expressions
 */
trait BasePlus extends Base {
  class Plus(l: exp, r: exp) requires exp extends Exp {
    val left = l;
    val right = r;
    def eval = left.eval + right.eval
  }
}

/** Operation extension: An extension of `Base' with 'show' methods.
 */
trait Show extends Base {
  type exp <: Exp1;

  trait Exp1 extends Exp {
    def show: String;
  }
  class Num1(v: int) requires (exp with Num1) extends Num(v) with Exp1 {
    def show = value.toString();
  }
}

/** Operation extension: An extension of `BasePlus' with 'show' methods.
 */
trait ShowPlus extends BasePlus with Show {
  class Plus1(l: exp, r: exp) requires (exp with Plus1) extends Plus(l, r) with Exp1 {
    def show = left.show + " + " + right.show
  }
}

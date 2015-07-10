
/** Test that constructor operations are reordered correctly.  */
class Outer {

  object global {
    val x = 10;
  }

  class X extends AnyRef with M1 {
    /* The constructor of X should set this.$outer to the outer instance
     * *before* calling the super constructors. This is tested by
     * mixin M1, which tries to access global from the enclosing class.
     */
    val outer = Outer.this;
  }

  trait M1 requires X {
    Console.println(global.x);
    Console.println(outer.global.x);
  }

}

object Test extends AnyRef with Application {
  val o = new Outer;

  new o.X;
}
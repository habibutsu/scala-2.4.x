/* $Id$
 * Test file for immutable queues.
 */

import scala.collection.immutable.Queue;

object iq {
  def main = {
    /* Create an empty queue. */
    val q:Queue[Int] = Queue.Empty;

    /* Test isEmpty.
     * Expected: Empty
     */
    if(q.isEmpty) {
      Console.println("Empty");
    }

    /* Test infix enqueing. */
    val q2 = q + 42 + 0;

    /* Test is empty and dequeue.
     * Expected: Head: 42
     */
    val q4 =
      if(q2.isEmpty) {
        Console.println("Empty");
        q2;
      } else {
	  val Pair(head,q3) = q2.dequeue;
	  Console.println("Head: " + head);
	  q3;
      };

    /* Test sequence enqueing. */
    val q5:Queue[Any] = q4.enqueue(1,2,3,4,5,6,7,8,9);
    /* Test toString.
     * Expected: Head: q5: Queue(0,1,2,3,4,5,6,7,8,9)
     */
    Console.println("q5: " + q5);
    /* Test apply
     * Expected: q5[5]: 5
     */
    Console.println("q5[5]: " + q5(5));



    val q5c:Queue[int] = Queue.Empty.enqueue(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    /* Testing ==
     *  Expected: q5 == q9: true
     *            q9 == q5: true
     */
    Console.println("q5 == q5c: " + (q5 == q5c));
    Console.println("q5c == q5: " + (q5c == q5));

    val Pair(_,q6) = q5.dequeue;
    val Pair(_,q7) = q6.dequeue;
    val q8 = q7 + 10 + 11;
    /* Test dequeu
     * Expected: q8: Queue(2,3,4,5,6,7,8,9,10,11)
     */
    Console.println("q8: " + q8);
    val q9 = new Queue(2,3,4,5,6,7,8,9,10,11);

    /* Testing ==
     *  Expected: q8 == q9: true
     */
    Console.println("q8 == q9: " + (q8 == q9));

    /* Testing elements
     *  Expected: Elements:  1  2  3  4  5  6  7  8  9
     */
    Console.print("Elements: ");
    q6.elements.foreach(e => Console.print(" "+ e + " "));
    Console.println;

   /* Testing mkString
     *  Expected: String: <1-2-3-4-5-6-7-8-9>
     */
    Console.println("String: " + q6.mkString("<","-",">"));

    /* Testing length
     *  Expected: Length: 9
     */
    Console.println("Length: " + q6.length);

    /* Testing front
     *  Expected: Front: 1
     */
    Console.println("Front: " + q6.front);
  }
}

object Test {
  def main(args: Array[String]): Unit = {
    iq.main;
    ();
  }
}

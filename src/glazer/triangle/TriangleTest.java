package glazer.triangle;

import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {

	@Test
	public void testTriangle() {
		Triangle triangle = new Triangle(3);

		Assert.assertEquals("  *\n * *\n*****", triangle.toString());
		Triangle triangle2 = new Triangle(6);
		
		Assert.assertEquals(
				"     *\n    * *\n   *   *\n  *     *\n *       *\n***********",
				triangle2.toString());
		Triangle triangle3 = new Triangle(10);
		
		Assert.assertEquals(
				"         *\n        * *\n       *   *\n      *     *\n     *       *\n    *         *\n   *           *\n  *             *\n *               *\n*******************",
				triangle3.toString());
	}
}

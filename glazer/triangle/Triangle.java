package glazer.triangle;

public class Triangle {
	int height;

	public Triangle(int height) {
		this.height = height;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		int total = (height * 2) - 1;

		for (int j = 1; j < height; j++) {
			int leadingSpaces = height - j;

			// total amount of middle spaces is total- the 2 stars-
			// leadingSpaces and -trailingSpaces
			int middleSpaces = total - 2 - (leadingSpaces * 2);
			for (int k = 1; k <= leadingSpaces; k++) {
				builder.append(' ');
			}

			builder.append('*');
			for (int i = 1; i <= middleSpaces; i++) {
				builder.append(' ');
			}
			if (j != 1) {
				builder.append('*');

			}
			builder.append("\n");
		}
		for (int i = 1; i <= total; i++) {
			builder.append('*');
		}
		return builder.toString();

	}

}

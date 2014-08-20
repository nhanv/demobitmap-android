package nhanv.example.utils;

/**
 * class ImageUtils provide the method support to load image
 * 
 * @author philipsnull
 * 
 */
public class ImageUtils {

	/**
	 * calculate the attribute inSampleSize to scale image
	 * 
	 * @param pHeight
	 *            : height of image
	 * @param pWidth
	 *            : width of image
	 * @param reqHeight
	 *            : height of imageView
	 * @param reqWidth
	 *            : width of imageView
	 * @return
	 */
	public static int calculateSampleSize(int pHeight, int pWidth,
			int reqHeight, int reqWidth) {
		int inSampleSize = 1;

		if (pHeight > reqHeight || pWidth > reqWidth) {
			final int halfHeight = pHeight / 2;
			final int halfWidth = pWidth / 2;

			while (halfHeight / inSampleSize > reqHeight
					&& halfWidth / inSampleSize > reqWidth) {
				inSampleSize *= 2;
			}
		}
		return inSampleSize;
	}
}

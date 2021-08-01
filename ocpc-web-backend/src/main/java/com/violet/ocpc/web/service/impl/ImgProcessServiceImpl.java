package com.violet.ocpc.web.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.violet.ocpc.web.service.ImgProcessService;

/**
 * @author HYJ
 *
 */
@Service("ImgProcessService")
public class ImgProcessServiceImpl implements ImgProcessService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ImgProcessServiceImpl.class);

	@Override
	public int grayImageNoRtn(int status, String imgSourcePath, String outPath) throws Exception {
		File file = new File(imgSourcePath);
		BufferedImage image = ImageIO.read(file);

		int width = image.getWidth();
		int height = image.getHeight();

		List<Integer> lineList = null;
		Map<Integer, List<BigDecimal>> map = new HashMap<>();

		BufferedImage grayImage = new BufferedImage(width, height, image.getType());

		LOGGER.info("File : " + imgSourcePath + " total line : " + height);

		for (int i = 0; i < height; i++) {
			lineList = new ArrayList<>();
			for (int j = 0; j < width; j++) {
				int color = image.getRGB(j, i);
				final int r = (color >> 16) & 0xff;
				final int g = (color >> 8) & 0xff;
				final int b = color & 0xff;

				int gray = 0;

				if (status == 1) {
					gray = getBigger(r, g, b);// 最大值法灰度化
				} else if (status == 2) {
					gray = getSmall(r, g, b);// 最小值法灰度化
				} else if (status == 3) {
					gray = getAvg(r, g, b);// 均值法灰度化
				} else if (status == 4) {
					gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);// 加权法灰度化
				}

				lineList.add(gray);
				grayImage.setRGB(j, i, colorToRGB(0, gray, gray, gray));
			}
		}
		File newFile = new File(outPath);
		if (!newFile.exists()) {
			ImageIO.write(grayImage, "jpg", newFile);
		}

		return height;
	}
	
	@Override
	public Map<Integer, List<BigDecimal>> grayImage(int status, String imgSourcePath, String outPath) throws Exception {

		File file = new File(imgSourcePath);
		BufferedImage image = ImageIO.read(file);

		int width = image.getWidth();
		int height = image.getHeight();

		List<Integer> lineList = null;
		Map<Integer, List<BigDecimal>> map = new HashMap<>();

		BufferedImage grayImage = new BufferedImage(width, height, image.getType());

		LOGGER.info("File : " + imgSourcePath + " total line : " + height);

		List<BigDecimal> afterFilterList = null;

		for (int i = 0; i < height; i++) {
			lineList = new ArrayList<>();
			for (int j = 0; j < width; j++) {
				int color = image.getRGB(j, i);
				final int r = (color >> 16) & 0xff;
				final int g = (color >> 8) & 0xff;
				final int b = color & 0xff;

				int gray = 0;

				if (status == 1) {
					gray = getBigger(r, g, b);// 最大值法灰度化
				} else if (status == 2) {
					gray = getSmall(r, g, b);// 最小值法灰度化
				} else if (status == 3) {
					gray = getAvg(r, g, b);// 均值法灰度化
				} else if (status == 4) {
					gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);// 加权法灰度化
				}

				lineList.add(gray);
				grayImage.setRGB(j, i, colorToRGB(0, gray, gray, gray));
			}
			List<Integer> listNoWhiteEdges = removeWhiteEdges(lineList);

			Integer[] pixs = new Integer[listNoWhiteEdges.size()];
			listNoWhiteEdges.toArray(pixs);
			double[] afterFilterArr = new double[pixs.length];
			filterList(pixs, afterFilterArr, pixs.length);
			afterFilterList = new ArrayList(afterFilterArr.length);
			for (double s : afterFilterArr) {
				afterFilterList.add(new BigDecimal(s));
			}

			map.put(i, afterFilterList);
		}
		File newFile = new File(outPath);
		if (!newFile.exists()) {
			ImageIO.write(grayImage, "jpg", newFile);
		}

		return map;
	}

	private int getBigger(int x, int y, int z) {
		if (x >= y && x >= z) {
			return x;
		} else if (y >= x && y >= z) {
			return y;
		} else if (z >= x && z >= y) {
			return z;
		} else {
			return 0;
		}
	}

	private int getSmall(int x, int y, int z) {
		if (x <= y && x <= z) {
			return x;
		} else if (y >= x && y >= z) {
			return y;
		} else if (z >= x && z >= y) {
			return z;
		} else {
			return 0;
		}
	}

	private int getAvg(int x, int y, int z) {
		int avg = (x + y + z) / 3;
		return avg;
	}

	private int colorToRGB(int alpha, int red, int green, int blue) {
		int newPixel = 0;
		newPixel += alpha;
		newPixel = newPixel << 8;
		newPixel += red;
		newPixel = newPixel << 8;
		newPixel += green;
		newPixel = newPixel << 8;
		newPixel += blue;

		return newPixel;
	}

	private List removeWhiteEdges(List<Integer> list) {
		List returnList = new ArrayList<>();
		int firstNum = 0;
		int lastNum = 0;

		LOGGER.info("------> [removeWhiteEdges] list: " + list);

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == 0) {
				firstNum = i;
				break;
			}
		}

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == 0) {
				lastNum = i;
			}
		}

		for (int i = firstNum; i <= lastNum; i++) {
			returnList.add(list.get(i));
		}

		return returnList;
	}

	private void filterList(Integer in[], double out[], int N) {
		int i;
		if (N < 7) {
			for (i = 0; i <= N - 1; i++) {
				out[i] = in[i];
			}
		} else {
			out[0] = (39.0 * in[0] + 8.0 * in[1] - 4.0 * in[2] - 4.0 * in[3] + 1.0 * in[4] + 4.0 * in[5] - 2.0 * in[6])
					/ 42.0;
			out[1] = (8.0 * in[0] + 19.0 * in[1] + 16.0 * in[2] + 6.0 * in[3] - 4.0 * in[4] - 7.0 * in[5] + 4.0 * in[6])
					/ 42.0;
			out[2] = (-4.0 * in[0] + 16.0 * in[1] + 19.0 * in[2] + 12.0 * in[3] + 2.0 * in[4] - 4.0 * in[5]
					+ 1.0 * in[6]) / 42.0;
			for (i = 3; i <= N - 4; i++) {
				out[i] = (-2.0 * (in[i - 3] + in[i + 3]) + 3.0 * (in[i - 2] + in[i + 2]) + 6.0 * (in[i - 1] + in[i + 1])
						+ 7.0 * in[i]) / 21.0;
			}
			out[N - 3] = (-4.0 * in[N - 1] + 16.0 * in[N - 2] + 19.0 * in[N - 3] + 12.0 * in[N - 4] + 2.0 * in[N - 5]
					- 4.0 * in[N - 6] + 1.0 * in[N - 7]) / 42.0;
			out[N - 2] = (8.0 * in[N - 1] + 19.0 * in[N - 2] + 16.0 * in[N - 3] + 6.0 * in[N - 4] - 4.0 * in[N - 5]
					- 7.0 * in[N - 6] + 4.0 * in[N - 7]) / 42.0;
			out[N - 1] = (39.0 * in[N - 1] + 8.0 * in[N - 2] - 4.0 * in[N - 3] - 4.0 * in[N - 4] + 1.0 * in[N - 5]
					+ 4.0 * in[N - 6] - 2.0 * in[N - 7]) / 42.0;
		}
	}

}

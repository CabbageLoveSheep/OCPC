package com.violet.ocpc.web.holder;

import java.math.BigDecimal;

/**
 * @author HYJ
 *
 */
public class ProjectFileHolder extends BaseHolder {
	private BigDecimal fileOid;
	private String filename;
	private BigDecimal projOid;
	private String newFilename;
	private String filePath;
	private String grayscale;// 灰度图路径
	private int recommendLine;// 推荐行

	public BigDecimal getFileOid() {
		return fileOid;
	}

	public void setFileOid(BigDecimal fileOid) {
		this.fileOid = fileOid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public BigDecimal getProjOid() {
		return projOid;
	}

	public void setProjOid(BigDecimal projOid) {
		this.projOid = projOid;
	}

	public String getNewFilename() {
		return newFilename;
	}

	public void setNewFilename(String newFilename) {
		this.newFilename = newFilename;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getGrayscale() {
		return grayscale;
	}

	public void setGrayscale(String grayscale) {
		this.grayscale = grayscale;
	}

	public int getRecommendLine() {
		return recommendLine;
	}

	public void setRecommendLine(int recommendLine) {
		this.recommendLine = recommendLine;
	}
}

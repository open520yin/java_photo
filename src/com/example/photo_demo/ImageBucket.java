package com.example.photo_demo;

import java.util.ArrayList;
import java.util.List;

public class ImageBucket {
    public String getBucketName() {
		return bucketName;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	public List<ImageItem> getImageList() {
		return imageList;
	}
	public void setImageList(List<ImageItem> imageList) {
		this.imageList = imageList;
	}
	public List<ImageItem> getSelectedImageList() {
		return selectedImageList;
	}
	public void setSelectedImageList(List<ImageItem> selectedImageList) {
		this.selectedImageList = selectedImageList;
	}
    public String getCover_path() {
		return cover_path;
	}
	public void setCover_path(String cover_path) {
		this.cover_path = cover_path;
	}
	private String bucketName;//相册名称

	public String getBacketPath() {
		return backetPath;
	}

	public void setBacketPath(String backetPath) {
		this.backetPath = backetPath;
	}

	private String backetPath;//相册路径
	private String cover_path; //相册封面图

	private List<ImageItem> imageList;//图片列表
    private List<ImageItem> selectedImageList = new ArrayList<>();//选中的图片
}

package com.example.photo_demo;

public class ImageItem {

    private String imageId;//图像ID
    private String imagePath;//图像路径
    private boolean isSelected = false;//是否被选择
    private ImageBucket bucket;//持有一个相册类引用
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public ImageBucket getBucket() {
		return bucket;
	}
	public void setBucket(ImageBucket bucket) {
		this.bucket = bucket;
	}
    
}
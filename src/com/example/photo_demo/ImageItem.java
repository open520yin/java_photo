package com.example.photo_demo;

public class ImageItem {

    private String imageId;//ͼ��ID
    private String imagePath;//ͼ��·��
    private boolean isSelected = false;//�Ƿ�ѡ��
    private ImageBucket bucket;//����һ�����������
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
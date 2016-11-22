package com.myproject.entity;

public class GridItem {

		private String text;
		private int image;
		private int backColor;
		public GridItem(String text, int image, int backColor) {
			super();
			this.text = text;
			this.image = image;
			this.backColor = backColor;
		}
		public String getText() {
			return text;
		}
		public int getImage() {
			return image;
		}
		public int getBackColor() {
			return backColor;
		}
		public void setText(String text) {
			this.text = text;
		}
		public void setImage(int image) {
			this.image = image;
		}
		public void setBackColor(int backColor) {
			this.backColor = backColor;
		}
		@Override
		public String toString() {
			return "GridItem [text=" + text + ", image=" + image
					+ ", backColor=" + backColor + "]";
		}

}

package com.bolivia.verifica.WpModel;

public class Media_Details {

        int width;
        int height;
        String file;
        int filesize;
        Sizes sizes;

        public Media_Details(int width, int height, String file, int filesize, Sizes sizes) {
                this.width = width;
                this.height = height;
                this.file = file;
                this.filesize = filesize;
                this.sizes = sizes;
        }

        public int getWidth() {
                return width;
        }

        public void setWidth(int width) {
                this.width = width;
        }

        public int getHeight() {
                return height;
        }

        public void setHeight(int height) {
                this.height = height;
        }

        public String getFile() {
                return file;
        }

        public void setFile(String file) {
                this.file = file;
        }

        public int getFilesize() {
                return filesize;
        }

        public void setFilesize(int filesize) {
                this.filesize = filesize;
        }

        public Sizes getSizes() {
                return sizes;
        }

        public void setSizes(Sizes sizes) {
                this.sizes = sizes;
        }
}

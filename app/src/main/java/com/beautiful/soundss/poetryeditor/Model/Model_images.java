package com.beautiful.soundss.poetryeditor.Model;

/**
 * Created by deepshikha on 3/3/17.
 */

public class Model_images {
    String str_ImagePath;
    private boolean isSelected;
    String folderName;

    public Model_images() {
    }

    public Model_images(String str_ImagePath,String folderName) {
        this.str_ImagePath = str_ImagePath;
        this.folderName = folderName;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getStr_ImagePath() {
        return str_ImagePath;
    }

    public void setStr_ImagePath(String str_ImagePath) {
        this.str_ImagePath = str_ImagePath;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

package com.juankysoriano.materiallife.imageloader;

public enum ImageLoaderResult {
    GALLERY(23214),
    CAMERA(12321),
    UNKNOWN(-1);

    private int code;

    ImageLoaderResult(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ImageLoaderResult from(int code) {
        for (ImageLoaderResult imageLoaderResult : values()) {
            if (imageLoaderResult.code == code) {
                return imageLoaderResult;
            }
        }
        return UNKNOWN;
    }
}

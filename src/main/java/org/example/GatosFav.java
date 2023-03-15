package org.example;

public class GatosFav {
    private String id;
    private String image_id;
    private String apikey = "live_jf1Ih9aEnkIrZiH8fPDJJ0tLQpDv00FDgxJxymPno6TEGAhkAVpoTMN0hsZJvri1";
    private ImageX image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public ImageX getImage() {
        return image;
    }

    public void setImage(ImageX image) {
        this.image = image;
    }
}

package com.example.ehealth;

public class Upload {
   private String Description;
    private String ImageUrl;

    public Upload() {
    }

    public Upload(String description, String imageUrl) {
        if (description.trim().equals("")){
            description = "No description";
        }
        Description = description;
        ImageUrl = imageUrl;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}

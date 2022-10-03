package com.lee.hof.sys.bean.vo;


import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * Created by alex on 3/1/18.
 */

public class BrandResponse implements Serializable {

    public Map<String,List<Brand>> brands;

    public List<Brand> hot;


    public Map<String, List<Brand>> getBrands() {
        return brands;
    }

    public void setBrands(Map<String, List<Brand>> brands) {
        this.brands = brands;
    }

    public List<Brand> getHot() {
        return hot;
    }

    public void setHot(List<Brand> hot) {
        this.hot = hot;
    }

    public static class Brand {

        private int id;

        private String image;

        private String name;

        private String url;

        private List<Tag> tags;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<Tag> getTags() {
            return tags;
        }

        public void setTags(List<Tag> tags) {
            this.tags = tags;
        }
    }

    public static class Tag {

        private int id;

        private String image;

        private String name;

        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}



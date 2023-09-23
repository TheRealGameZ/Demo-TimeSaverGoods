package com.example.demo.models;

public class ProductData {
    
    public DefaultImage defaultImage;
    public Fields fields;
    public String id;
    public String unitPrice;

    public String getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }


    public class DefaultImage {
        public String url;

        public String geturl() {
        return this.url;
        }

        public void seturl(String url) {
            this.url = url;
        }
    }

    public class Fields {
        public String Description;
        public String Name;

        public String getdescription() {
        return this.Description;
        }

        public void setdescription(String description) {
            this.Description = description;
        }

        public String getname() {
        return this.Name;
        }

        public void setname(String name) {
            this.Name = name;
        }
    }


    public DefaultImage getDefaultImage() {
        return this.defaultImage;
    }

    public void setDefaultImage(DefaultImage defaultImage) {
        this.defaultImage = defaultImage;
    }

    public Fields getFields() {
        return this.fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
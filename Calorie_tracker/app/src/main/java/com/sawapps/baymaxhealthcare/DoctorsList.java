package com.sawapps.baymaxhealthcare;

public class DoctorsList {
    private String doc_name;
    private String doc_image;
    private String doc_address;

    public String get_doc_name(){
        return this.doc_name;
    }

    public String get_doc_img(){
        return this.doc_image;
    }

    public String get_doc_addr(){
        return this.doc_address;
    }

    public DoctorsList(String name, String image, String addr){
        this.doc_name = name;
        this.doc_image = image;
        this.doc_address = addr;
    }
}

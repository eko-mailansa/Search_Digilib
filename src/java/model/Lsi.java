/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author AnsaKhitara
 */
public class Lsi {
    private String title;
    private String alamat;

    public String getTitle() {
        return title;
    }

    public Lsi(String title, String alamat) {
        this.title = title;
        this.alamat = alamat;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
}

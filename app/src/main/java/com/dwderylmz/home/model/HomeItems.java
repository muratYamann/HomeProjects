package com.dwderylmz.home.model;

/**
 * Created by macbookpro on 19.05.2017.
 */

public class HomeItems {

    String ev_id;
    String ev_il;
    String ev_emlak_tip;
    String ev_alan;
    String ev_bina_yasi;
    String ev_oda_sayisi;
    String ev_bul_kat;
    String ev_fiyat;
    String ev_aciklama;
    String ev_resim_yolu;


    public  HomeItems(String ev_id,String ev_il,String ev_emlak_tip,String ev_alan,String ev_bina_yasi,String ev_oda_sayisi,String ev_bul_kat,String ev_fiyat,String ev_aciklama, String ev_resim_yolu){
        this.ev_id = ev_id;
        this.ev_il = ev_il;
        this.ev_emlak_tip = ev_emlak_tip;
        this.ev_alan= ev_alan;
        this.ev_bina_yasi= ev_bina_yasi;
        this.ev_oda_sayisi=ev_oda_sayisi;
        this.ev_bul_kat=ev_bul_kat;
        this.ev_fiyat=ev_fiyat;
        this.ev_aciklama=ev_aciklama;
        this.ev_resim_yolu=ev_resim_yolu;

    }






    public String getEv_id() {
        return ev_id;
    }

    public void setEv_id(String ev_id) {
        this.ev_id = ev_id;
    }

    public String getEv_il() {
        return ev_il;
    }

    public void setEv_il(String ev_il) {
        this.ev_il = ev_il;
    }

    public String getEv_emlak_tip() {
        return ev_emlak_tip;
    }

    public void setEv_emlak_tip(String ev_emlak_tip) {
        this.ev_emlak_tip = ev_emlak_tip;
    }

    public String getEv_alan() {
        return ev_alan;
    }

    public void setEv_alan(String ev_alan) {
        this.ev_alan = ev_alan;
    }

    public String getEv_bina_yasi() {
        return ev_bina_yasi;
    }

    public void setEv_bina_yasi(String ev_bina_yasi) {
        this.ev_bina_yasi = ev_bina_yasi;
    }

    public String getEv_oda_sayisi() {
        return ev_oda_sayisi;
    }

    public void setEv_oda_sayisi(String ev_oda_sayisi) {
        this.ev_oda_sayisi = ev_oda_sayisi;
    }

    public String getEv_bul_kat() {
        return ev_bul_kat;
    }

    public void setEv_bul_kat(String ev_bul_kat) {
        this.ev_bul_kat = ev_bul_kat;
    }

    public String getEv_fiyat() {
        return ev_fiyat;
    }

    public void setEv_fiyat(String ev_fiyat) {
        this.ev_fiyat = ev_fiyat;
    }

    public String getEv_aciklama() {
        return ev_aciklama;
    }

    public void setEv_aciklama(String ev_aciklama) {
        this.ev_aciklama = ev_aciklama;
    }

    public String getEv_resim_yolu() {
        return ev_resim_yolu;
    }

    public void setEv_resim_yolu(String ev_resimler) {
        this.ev_resim_yolu = ev_resimler;
    }
}

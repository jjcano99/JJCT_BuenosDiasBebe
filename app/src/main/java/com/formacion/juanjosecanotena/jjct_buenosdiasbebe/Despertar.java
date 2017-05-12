package com.formacion.juanjosecanotena.jjct_buenosdiasbebe;

import java.io.Serializable;

/**
 * Created by juanjosecanotena on 9/5/17.
 */

public class Despertar implements Serializable{

    private String Cita;
    private String Foto;
    private String Melodia;

    public Despertar() {
    }

    public Despertar(String cita, String foto, String melodia) {
        this.Cita = cita;
        this.Foto = foto;
        this.Melodia = melodia;
    }

    public String getCita() {
        return Cita;
    }

    public void setCita(String cita) {
        this.Cita = cita;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        this.Foto = foto;
    }

    public String getMelodia() {
        return Melodia;
    }

    public void setMelodia(String melodia) {
        this.Melodia = melodia;
    }
}

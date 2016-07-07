package br.ufc.dc.dspm.balancobrasil.WebService;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 07/07/16.
 */
public class Query {
    @SerializedName("tipo")
    private String tipo;
    @SerializedName("municipios")
    private String municipios;
    @SerializedName("setor")
    private String setor;
    @SerializedName("fonteFinalidade")
    private String fonteFinalidade;
    @SerializedName("valor")
    private String valor;
    @SerializedName("caps")
    private String caps;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getFonteFinalidade() {
        return fonteFinalidade;
    }

    public void setFonteFinalidade(String fonteFinalidade) {
        this.fonteFinalidade = fonteFinalidade;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getMunicipios() {
        return municipios;
    }

    public void setMunicipios(String municipios) {
        this.municipios = municipios;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCaps(String nameScape) {
        return caps;
    }

    public void setCaps(String caps) {
        this.caps = caps;
    }
}

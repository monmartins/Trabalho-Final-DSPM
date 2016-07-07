package br.ufc.dc.dspm.balancobrasil.Model;

import br.ufc.dc.dspm.balancobrasil.WebService.Query;

/**
 * Created by Vasco on 05/07/2016.
 */
public class Municipio {

    private String name;
    private double latitude;
    private double longitude;
    private String nameScape;
    private double valor;

    public Municipio(String name, double latitude, double longitude,String nameScape) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nameScape = nameScape;
    }

    public Municipio(String name, double latitude, double longitude,String nameScape, double valor) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nameScape = nameScape;
        this.valor = valor;
    }

    public Municipio() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Municipio{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public String getNameScape() {
        return nameScape;
    }

    public void setNameScape(String nameScape) {
        this.nameScape = nameScape;
    }

    public Query getQuery(){
        Query query = new Query();
        query.setTipo("1");
        query.getCaps(getNameScape());
        return query;

    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}

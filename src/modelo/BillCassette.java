/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author vbarrera
 */
public class BillCassette {

    int slotNumber;
    String date;
    int bill100k;
    int bill2k;
    int bill5k;
    int bill10k;
    int bill20k;
    int bill50k;
    int totalUnidad;
    int totalValor;

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBill100k() {
        return bill100k;
    }

    public void setBill100k(int bill100k) {
        this.bill100k = bill100k;
    }

    public int getBill2k() {
        return bill2k;
    }

    public void setBill2k(int bill2k) {
        this.bill2k = bill2k;
    }

    public int getBill5k() {
        return bill5k;
    }

    public void setBill5k(int bill5k) {
        this.bill5k = bill5k;
    }

    public int getBill10k() {
        return bill10k;
    }

    public void setBill10k(int bill10k) {
        this.bill10k = bill10k;
    }

    public int getBill20k() {
        return bill20k;
    }

    public void setBill20k(int bill20k) {
        this.bill20k = bill20k;
    }

    public int getBill50k() {
        return bill50k;
    }

    public void setBill50k(int bill50k) {
        this.bill50k = bill50k;
    }

    public int getTotalUnidad() {
        return totalUnidad;
    }

    public void setTotalUnidad(int totalUnidad) {
        this.totalUnidad = totalUnidad;
    }

    public int getTotalValor() {
        return totalValor;
    }

    public void setTotalValor(int totalValor) {
        this.totalValor = totalValor;
    }

   
}

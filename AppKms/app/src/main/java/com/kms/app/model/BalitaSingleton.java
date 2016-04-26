package com.kms.app.model;

/**
 * Created by arifcebe on 26/04/16.
 */
public class BalitaSingleton {
    private static BalitaSingleton ourInstance = new BalitaSingleton();
    private BalitaModel balita;

    public static BalitaSingleton getInstance() {
        return ourInstance;
    }

    private BalitaSingleton() {
    }

    public void setBalita(BalitaModel balita){
        this.balita = balita;
    }


    public BalitaModel getBalita() {
        return balita;
    }
}

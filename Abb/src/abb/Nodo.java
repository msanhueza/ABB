/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abb;

/**
 *
 * @author msanhuezal
 */
public class Nodo {

    private int valor;
    private Nodo izq;
    private Nodo der;
    
    public Nodo(int valor, Nodo izq, Nodo der) {
        this.valor = valor;
        this.izq = izq;
        this.der = der;
    }      
    
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Nodo getIzq() {
        return izq;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }

}

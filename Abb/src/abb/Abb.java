/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abb;

import java.util.ArrayList;

/**
 *
 * @author msanhuezal
 */
public class Abb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Nodo raiz = null;
        int[] valores = {5,4,6,8,9,1,2,3,7,10};
        for(int i=0; i<valores.length; i++){
            raiz = insertarNodoRecursivo(raiz, valores[i]);
        }
        System.out.println("encontrar " +  buscarNodoRecursivo(raiz, 3));
        eliminarNodo(2, raiz);
        imprimirPreOrden(raiz);
    }
    
/**
     * El metodo realiza la busqueda recursiva de un valor determinado
     * @param nodo se refiere al nodo actual
     * @param valor se refiere al valor a buscar
     * @return 
     */
    public static Nodo buscarNodoRecursivo(Nodo nodo, int valor){
        if(nodo == null){ // si es null el nodo
            return null;
        }
        else if (nodo.getValor() == valor){ // si el nodo contiene el valor
            return nodo;
        }
        else if(valor < nodo.getValor()){ // si el valor es menor al valor del nodo actual
            return buscarNodoRecursivo(nodo.getIzq(), valor);
        }
        else{ // si el valor es mayor al valor del nodo actual
            return buscarNodoRecursivo(nodo.getDer(), valor);
        }
    }
    
    /**
     * El metodo realiza la busqueda iterativa de un valor determinado
     * @param nodo se refiere al nodo actual
     * @param valor se refiere al valor a buscar
     * @return 
     */
    public static Nodo buscarNodoIterativo(Nodo nodo, int valor){
        
        while(nodo != null && valor != nodo.getValor()){
            if(valor < nodo.getValor()){
                nodo = nodo.getIzq();
            }
            else{
                nodo = nodo.getDer();
            }
        }
        return nodo;
    }    
    
    /**
     * El metodo inserta de manera recursiva un valor en el ABB de forma recursiva
     * @param nodo se refiere al nodo actual
     * @param valor se refiere al valor que se desea insertar
     * @return la raiz del nodo
     */
    public static Nodo insertarNodoRecursivo(Nodo nodo, int valor){
      if(nodo == null){
          return new Nodo(valor, null, null);
      }
      if (valor == nodo.getValor()){
        nodo.setValor(valor);
      }
      else if (valor < nodo.getValor()){
        nodo.setIzq(insertarNodoRecursivo(nodo.getIzq(), valor));
      }
      else{
        nodo.setDer(insertarNodoRecursivo(nodo.getDer(), valor));
      }
      
      return nodo;        
    }
    
    /**
     * El metodo inserta de manera iterativa un valor en el ABB de forma recursiva
     * @param nodo se refiere al nodo actual
     * @param valor se refiere al valor que se desea insertar
     * @return la raiz del nodo
     */
     public static Nodo insertarNodoIterativo (Nodo nodo, int valor){
          Nodo nuevo;
          nuevo = new Nodo (valor, null, null);

          if (nodo == null){
              nodo = nuevo;
          }
          else
          {
              Nodo anterior = null;
              Nodo reco = nodo;
              while (reco != null)
              {
                  anterior = reco;
                  if (valor < reco.getValor()){
                      reco = reco.getIzq();
                  }
                  else{
                      reco = reco.getDer();
                  }
              }
              if (valor < anterior.getValor()){
                  anterior.setIzq(nuevo);
              }
              else{
                  anterior.setDer(nuevo);
              }
              
          }
          return nodo;
        
      }    
    
       
    /**
     * El metodo elimina un nodo del ABB
     * @param valor se refiere al valor del nodo a eliminar
     * @param nodo se refiere al nodo a eliminar
     * @return la raiz del nodo
     */
    public static Nodo eliminarNodo(int valor, Nodo nodo) {
      if (nodo == null){
        return null;
      }
      if (valor == nodo.getValor()){
        return unir(nodo.getIzq(), nodo.getDer());
      }
      if (valor < nodo.getValor()){
        nodo.setIzq(eliminarNodo(valor, nodo.getIzq()));
      }
      else{
        nodo.setDer(eliminarNodo(valor, nodo.getDer()));
      }
      return nodo;
    }    
    
    /**
     * El metodo se encarga de unir las estructuras que se eliminan
     * @param izq se refiere al nodo izquierdo
     * @param der se refiere al nodo derecho
     * @return la estructura ya modificada
     */
    public static Nodo unir(Nodo izq, Nodo der) {
      if (izq == null){
        return der;
      }
      if (der == null){
        return izq;
      }
      Nodo centro= unir(izq.getDer(), der.getIzq());
      izq.setDer(centro);
      der.setIzq(izq);
      return der;
    }    
    
    /**
     * El metodo imprime el ABB en preOrden
     * @param nodo se refiere al nodo raiz
     */
    public static void imprimirPreOrden (Nodo nodo){
          if (nodo != null)
          {
              System.out.print(nodo.getValor() + " ");
              imprimirPreOrden(nodo.getIzq());
              imprimirPreOrden(nodo.getDer());
          }
      }
    

}

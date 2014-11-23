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
        ArrayList<Integer> numeros = generarNumeros(5);
        System.out.println(numeros);
        
//      GENERACION NORMAL        
//      raiz = abbNormal(raiz, numeros);
//      imprimirPreOrden(raiz);
        
//      GENERACION ALEATORIA        
//      raiz = abbAleatorio(raiz, numeros);
//      imprimirPreOrden(raiz);
        
    }
    
/**
     * El metodo realiza la busqueda recursiva de un valor determinado
     * @param nodo se refiere al nodo actual
     * @param valor se refiere al valor a buscar
     * @return la raiz del abb
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
     * @return la raiz del abb
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
     * @return la raiz del abb
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
     * @return la raiz del abb
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
     * @return la raiz del abb
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
    
    /**
     * El metodo se encarga de generar n numeros aleatorios sin repeticion
     * @param cant se refiere a la cantidad de numeros aleatorios que se desean generar desde el 1 al cant
     * @return un arrayList con los n numeros generados al azar sin repeticion
     */
    public static ArrayList<Integer> generarNumeros(int cant){
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        int n = cant;
        int N = 0;
        int M = cant+1; 
        int valor;
        for(int i=0; i<n; i++){
            do{
                valor = (int) Math.floor(Math.random()*(N-M+1)+M);
            }while(numeros.contains(valor));
            numeros.add(valor);
        }
        return numeros;
    
    }
    
    
    /**
     * El metodo se encarga de insertar los valores del arraylist de manera normal
     * @param raiz es la raiz del abb
     * @param numeros es el arrayList con los valores a insertar
     * @return la raiz del abb
     */    
    public static Nodo abbNormal(Nodo raiz, ArrayList<Integer> numeros){
        Nodo aux = raiz;
        for(int i=0; i<numeros.size(); i++){
            aux = insertarNodoRecursivo(aux, numeros.get(i));
        }
        return aux;
    }     

    
    /**
     * El metodo se encarga de insertar los valores del arraylist de manera aleatoria
     * @param raiz es la raiz del abb
     * @param numeros es el arrayList con los valores a insertar
     * @return la raiz del abb
     */
    public static Nodo abbAleatorio(Nodo raiz, ArrayList<Integer> numeros){
        Nodo aux = raiz;
        int n = numeros.size();
        int N = 0;
        int M;         
        int valor;
        for(int i=0; i<n; i++){
            M = numeros.size()-1;
            valor = (int) Math.floor(Math.random()*(N-M+1)+M); // indice del arrayList a eliminar 
            aux = insertarNodoRecursivo(aux, numeros.get(valor)); // agrego el valor elegido al azar
            numeros.remove(valor); // elimino el valor elegido al azar para no volver a elegirlo
        }
        return aux;
    }
    

}

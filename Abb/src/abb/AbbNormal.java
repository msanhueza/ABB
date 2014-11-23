/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abb;

/**
 *
 * @author msanhuezal
 */
public class AbbNormal {
    
    Nodo raiz = null;
    
    /**
     * El metodo realiza la busqueda de un valor determinado
     * @param nodo se refiere al nodo actual
     * @param valor se refiere al valor a buscar
     * @return 
     */
    public Nodo buscar(Nodo nodo, int valor){
        if(nodo == null){ // si es null el nodo
            return null;
        }
        else if (nodo.getValor() == valor){ // si el nodo contiene el valor
            return nodo;
        }
        else if(valor < nodo.getValor()){ // si el valor es menor al valor del nodo actual
            return buscar(nodo.getIzq(), valor);
        }
        else{ // si el valor es mayor al valor del nodo actual
            return buscar(nodo.getDer(), valor);
        }
    }
    
    /**
     * El metodo inserta un valor en el ABB de forma recursiva
     * @param nodo se refiere al nodo actual
     * @param valor se refiere al valor que se desea insertar
     * @return la raiz del nodo
     */
    public Nodo insertarNodoRecursivo(Nodo nodo, int valor){
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
     * El metodo inserta un valor en el ABB de forma iterativa
     * @param nodo se refiere al nodo actual
     * @param valor se refiere al valor que se desea insertar
     * @return la raiz del nodo
     */    
    public Nodo insertarNodoIterativo(Nodo nodo, int valor){
        if (nodo == null) {
           nodo = new Nodo(valor, null, null);
           return nodo;
        }
        Nodo auxNodo= nodo;
        while (true) {
          if (auxNodo.getValor() == valor) {
            auxNodo.setValor(valor);
            return auxNodo;
          }
          if (valor < auxNodo.getValor())
            if (auxNodo.getIzq() != null){
              auxNodo= auxNodo.getIzq();
            }
            else {
              auxNodo.setIzq(new Nodo (valor, null, null));
              return auxNodo;
            }
          else
            if (auxNodo.getDer() != null){
              auxNodo= auxNodo.getDer();
            }
            else {
              auxNodo.setDer(new Nodo (valor, null, null));
              return auxNodo;
            }
        }        
    }
    
    /**
     * El metodo elimina un nodo del ABB
     * @param valor se refiere al valor del nodo a eliminar
     * @param nodo se refiere al nodo a eliminar
     * @return la raiz del nodo
     */
    public Nodo eliminarNodo(int valor, Nodo nodo) {
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
    
    public Nodo unir(Nodo izq, Nodo der) {
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
            
    
}

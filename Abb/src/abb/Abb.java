/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abb;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author msanhuezal, Sebastian
 */
public class Abb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Random random = new Random();
        int n = 5;
        int inserciones = 5;
        int busquedas = 5;
        
        
        if(args.length >= 1)
        {
            random = new Random(Long.parseLong(args[0]));
        }
        if(args.length >= 2)
        {
            n = Integer.parseInt(args[1]);
        }
        if(args.length >= 3)
        {
            inserciones = Integer.parseInt(args[2]);
        }
        if(args.length >= 4)
        {
            busquedas = Integer.parseInt(args[3]);
        }
        
        Nodo raiz1 = null;
        Nodo raiz2 = null;
        
        ArrayList<Integer> numeros = generarNumeros(n,random);
        ArrayList<Integer> copiaNumeros = new ArrayList<>(numeros);
        //System.out.println("Arreglo a insertar: " + numeros);
        
//      GENERACION NORMAL
        System.out.println("");
        System.out.println("Normal");
        long normalTiempoInicio = System.nanoTime();
        raiz1 = abbNormal(raiz1, copiaNumeros);
        long normalTiempoFinal = System.nanoTime();
        //imprimirPreOrden(raiz1);
        

        //System.out.println("");
        System.out.println("Tiempo para crear: " + (normalTiempoFinal - normalTiempoInicio)/1000 + " nanosegundos");

      
        
//      GENERACION ALEATORIA   
        System.out.println("");
        System.out.println("Aleatorio");
        long aleatorioTiempoInicio = System.nanoTime();
        raiz2 = abbAleatorio(raiz2, copiaNumeros, random);
        long aleatorioTiempoFinal = System.nanoTime();
        //imprimirPreOrden(raiz2);
        
        //System.out.println("");
        System.out.println("Tiempo para crear: " + (aleatorioTiempoFinal - aleatorioTiempoInicio)/1000 + " nanosegundos");
        
        ArrayList<Integer> insertar = generarNumeros(inserciones, random);
        numeros.addAll(insertar);
        
        //INSERCION ARBOL NORMAL
        System.out.println("");
        System.out.println("Insertar en arbol normal " + inserciones + " elementos");
        normalTiempoInicio = System.nanoTime();
        for(Integer i : insertar)
            insertarNodoIterativo(raiz1, i);
        normalTiempoFinal = System.nanoTime();
        
        System.out.println("Tiempo para insertar: " + (normalTiempoFinal - normalTiempoInicio)/1000 + " nanosegundos");
        
        //INSERCION ARBOL ALEATORIO
        System.out.println("");
        System.out.println("Insertar en arbol aleatorio " + inserciones + " elementos");
        aleatorioTiempoInicio = System.nanoTime();
        int na = numeros.size();
        for(Integer i : insertar)
        {
            insert(random, i, raiz2, na);
            na++;
        }
        aleatorioTiempoFinal = System.nanoTime();
        
        System.out.println("Tiempo para insertar: " + (aleatorioTiempoFinal - aleatorioTiempoInicio)/1000 + " nanosegundos");
        
        //Generar lista de numeros a buscar
        ArrayList<Integer> buscar = new ArrayList<>();
        for(int b = 0 ; b < busquedas ; b++)
        {
            int ni = random.nextInt(numeros.size());
            buscar.add(numeros.get(ni));
            numeros.remove(ni);
        }
        
        //BUSQUEDA ARBOL NORMAL
        System.out.println("");
        System.out.println("Buscar en arbol normal " + busquedas + " elementos");
        normalTiempoInicio = System.nanoTime();
        for(Integer i : buscar)
            buscarNodoRecursivo(raiz1, i);
        normalTiempoFinal = System.nanoTime();
        
        System.out.println("Tiempo para buscar: " + (normalTiempoFinal - normalTiempoInicio)/1000 + " nanosegundos");
        
        //BUSQUEDA ARBOL ALEATORIO
        System.out.println("");
        System.out.println("Buscar en arbol aleatorio " + busquedas + " elementos");
        aleatorioTiempoInicio = System.nanoTime();
        for(Integer i : buscar)
            buscarNodoRecursivo(raiz2, i);
        aleatorioTiempoFinal = System.nanoTime();

        System.out.println("Tiempo para buscar: " + (aleatorioTiempoFinal - aleatorioTiempoInicio)/1000 + " nanosegundos");        
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
     * @param random Random creado para generar numeros.
     * @return un arrayList con los n numeros generados al azar sin repeticion
     */
    public static ArrayList<Integer> generarNumeros(int cant, Random random){
        ArrayList<Integer> numeros = new ArrayList<>();
        int n = cant;
        int N = 0;
        int M = cant+1; 
        int valor;
        for(int i=0; i<n; i++){
            valor = (int) Math.floor(random.nextFloat()*(N-M+1)+M);
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
        for (Integer numero : numeros) {
            aux = insertarNodoIterativo(aux, numero);
        }
        return aux;
    }     

    
    /**
     * El metodo se encarga de insertar los valores del arraylist de manera aleatoria
     * @param raiz es la raiz del abb
     * @param numeros es el arrayList con los valores a insertar
     * @param random Random creado para elegir un numero aleatorio
     * @return la raiz del abb
     */
    public static Nodo abbAleatorio(Nodo raiz, ArrayList<Integer> numeros, Random random){
        Nodo aux = raiz;
        int n = numeros.size();
        int N = 0;
        int M;
        int valor;
        for(int i=0; i<n; i++){
            M = numeros.size()-1;
            valor = (int) Math.floor(random.nextFloat()*(N-M+1)+M); // indice del arrayList a eliminar 
            aux = insert(random, numeros.get(valor), raiz,i); // agrego el valor elegido al azar
            numeros.remove(valor); // elimino el valor elegido al azar para no volver a elegirlo
        }
        return aux;
    }
    
    public static Nodo insert(Random random, int valor, Nodo T, int n){
        int r;
        r = random.nextInt(n+1);
        if(T == null){
            return new Nodo(valor, null, null);
        }
        if(r == n){
            return insertAtRoot(valor, T);
        }
        if(valor < T.getValor()){
            T.setIzq(insert(random, valor,T.getIzq(),n));
        }
        else{
            T.setDer(insert(random, valor,T.getDer(),n));
        }
        return T;
        
        
        
    }

    public static Nodo insertAtRoot(int valor, Nodo T) {
        Nodo S = null;
        Nodo G = null;
        
        split(valor, T, S, G);
        T.setValor(valor);
        T.setIzq(S);
        T.setDer(G);
        return T;
    }
    
    

    public static void split(int valor, Nodo T, Nodo S, Nodo G) {
        if(T == null){
            S = null;
            G = null;
            return;
        }
        if(valor < T.getValor()){
            G = T;
            split(valor, T.getIzq(), S, G.getIzq());
        }
        else{
            S = T;
            split(valor, T.getDer(), S.getDer(), G);
        }
        return;
    }
    

}

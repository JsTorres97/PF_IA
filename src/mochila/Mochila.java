/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mochila;
import java.util.*;




public class Mochila{

    public static void main(String[] args) {
        int NumeroMaxObjetos =5;
        int PesoMaximo = 15;  
        Random rnd = new Random();
        //el arreglo generador sirve para generar numeros mas grandes y la busqueda sea más precisa 
        int[] generador = new int[NumeroMaxObjetos+1];
        int[] peso = new int[NumeroMaxObjetos+1];

        // Este cíclo sirve para los aleatorios
        for (int n = 1; n <= NumeroMaxObjetos; n++) {
            generador[n] = rnd.nextInt(1000);
            peso[n] = rnd.nextInt(PesoMaximo);
        }

        int[][] opc = new int[NumeroMaxObjetos+1][PesoMaximo+1];
        boolean[][] sol = new boolean[NumeroMaxObjetos+1][PesoMaximo+1];

        for (int n = 1; n <= NumeroMaxObjetos; n++) {
            for (int w = 1; w <= PesoMaximo; w++) {

                // Almaceno para despues comparar
                int opc1 = opc[n-1][w];

                // Selecciono los minimos
                int opc2 = Integer.MIN_VALUE;
                if (peso[n] <= w){
                    opc2 = generador[n] + opc[n-1][w-peso[n]];
                }

                // Selecciono las mejores opciones
                opc[n][w] = Math.max(opc1, opc2);
                //El arreglo sol, se guardan 
                if (opc2 > opc1){
                    sol[n][w] = true;
                }else{
                    sol[n][w] = false;
                }
            }
        }

        // Determino que objetos llevar
        String[] llevar = new String[NumeroMaxObjetos+1];
        for (int n = NumeroMaxObjetos, w = PesoMaximo; n > 0; n--) {
            //Si están en el arreglo me los llevo
            if (sol[n][w]) {
                llevar[n] = "Si";
                w = w - peso[n];
            }
            else {
                llevar[n] = "No";
            }
        }

        // print results
        System.out.println("Objeto" + "\t" + "Peso" + "\t" + "¿Me los llevo?");
        for (int n = 1; n <= NumeroMaxObjetos; n++) {
            System.out.println(n + "\t" + peso[n] + "\t" + llevar[n]);
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mochila;
import java.util.*;




public class Mochila{

    public static void main(String[] args) {
        int NumeroMaxObjetos =10;
        int PesoMaximo = 15;  
        Random rnd = new Random();
        int[] profit = new int[NumeroMaxObjetos+1];
        int[] peso = new int[NumeroMaxObjetos+1];

        // generate random instance, items 1..N
        for (int n = 1; n <= NumeroMaxObjetos; n++) {
            profit[n] = rnd.nextInt(1000);
            peso[n] = rnd.nextInt(PesoMaximo);
        }

        // opt[n][w] = max profit of packing items 1..n with weight limit w
        // sol[n][w] = does opt solution to pack items 1..n with weight limit w include item n?
        int[][] opt = new int[NumeroMaxObjetos+1][PesoMaximo+1];
        boolean[][] sol = new boolean[NumeroMaxObjetos+1][PesoMaximo+1];

        for (int n = 1; n <= NumeroMaxObjetos; n++) {
            for (int w = 1; w <= PesoMaximo; w++) {

                // don't take item n
                int option1 = opt[n-1][w];

                // take item n
                int option2 = Integer.MIN_VALUE;
                if (peso[n] <= w){
                    option2 = profit[n] + opt[n-1][w-peso[n]];
                }

                // select better of two options
                opt[n][w] = Math.max(option1, option2);
                sol[n][w] = (option2 > option1);
            }
        }

        // determine which items to take
        String[] llevar = new String[NumeroMaxObjetos+1];
        for (int n = NumeroMaxObjetos, w = PesoMaximo; n > 0; n--) {
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
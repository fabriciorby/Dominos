import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Scanner;

public class Dominos {
    
    public static int[] solucao;
    public static int[] dominosUsados;
    public static int[][] totalDominos;
    
	public static HashSet<Integer> listaSolucao = new HashSet<Integer>();
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int k = sc.nextInt();
        
        totalDominos = new int[n][2];
        
        for (int i = 0; i < n; i++) {
            totalDominos[i][0] = sc.nextInt();
            totalDominos[i][1] = sc.nextInt();
        }
        
        dominosUsados = new int[n];
        
        solucao = new int[k*2];
        
        for (int i = 0; i < n; i++) {
            dominosUsados[i] = 0;
        }
        
        backtrack(n, k, 0);
        
    }
    
    public static boolean backtrack (int n, int k, int cont) {
        
        if (k == 0) {
            if (!listaSolucao.contains(Arrays.hashCode(solucao))){
                listaSolucao.add(Arrays.hashCode(solucao));
                imprimeSolucao(solucao);
            }
            return false;
        }
        
        for (int i = 0; i < n; i++) {
            
            if (dominosUsados[i] == 0) {
            
                int a = totalDominos[i][0];
                int b = totalDominos[i][1];
                
                if (ehSolucao(cont, solucao, a)) {
                    
                    dominosUsados[i] = 1;
                    solucao[2*cont]     = a;
                    solucao[2*cont + 1] = b;
                    
                    if (backtrack(n, k - 1, cont + 1) == true) {
                        return true;
                    } else {
                        dominosUsados[i] = 0;
                    }
                    
                }
                
                if (ehSolucao(cont, solucao, b)) {
                    
                    dominosUsados[i] = 2;
                    solucao[2*cont]     = b;
                    solucao[2*cont + 1] = a;
                    
                    if (backtrack(n, k - 1, cont + 1) == true) {
                        return true;
                    } else {
                        dominosUsados[i] = 0;
                    }
                    
                }
                
            }
            
        }
        
        return false;
        
    }
    
    public static boolean ehSolucao(int k, int[] solucao, int a) {
        
        if (k == 0) {
            return true;
        }
        
        if (solucao[2*k - 1] == a) {
            return true;
        }
        
        return false;
        
    }
    
    public static void imprimeSolucao(int[] solucao) {
        
        for (int i = 0; i < solucao.length; i++)
            System.out.print(solucao[i] + " ");
        System.out.println();
        
    }
    
}
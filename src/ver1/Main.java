package ver1;

import java.util.Scanner;
import DES.*;
import SHA512.*;
import LinearEquationSolver.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        QuestionsGenerator g = new QuestionsGenerator("questions.txt");
//        g.read();
//        String[] questions = g.get();
//        for (int i = 0; i < questions.length; i++) {
//            System.out.println(questions[i]);
//        }

//        System.out.print("D: ");
//        int d = sc.nextInt();
        System.out.print("n: ");
        int n = sc.nextInt();
        System.out.print("k: ");
        int k = sc.nextInt();

        int[] function = split(12,k);
        
        Function fcount = new Function(function);
        int[] fx = new int[n+1];
        for(int i = 0; i <= n; i++) {
            fx[i] = fcount.countFunction(i);
        }

        ArrayList<int[]> functions = new ArrayList<int[]>();
        for(int i = 1; i <= n; i++) {
            int[] f = new int[function.length];
            for(int x = 0; x < f.length; x++) {
                f[x] = (int)Math.pow(i, x);
            }
            functions.add(f);
        }
        
        printFunctions(functions, fx);

        System.out.println("Solving the equations to get secret");
        double[][] equation = new double[k][k];
        System.out.println("Please enter equations");
        for(int i = 0; i < equation.length; i++) {
            for(int j = 0; j < equation.length; j++) {
                equation[i][j] = sc.nextDouble();
            }
        }
        double[] solution = new double[k];
        System.out.println("Please enter solution for each equations");
        for(int i = 0; i < solution.length; i++) {
            solution[i] = sc.nextDouble();
        }
        EquationSolver es = new EquationSolver(equation, solution);
        int secret = (int)es.solve()[0];
        System.out.println("secret: " + secret);

        sc.close();
    }

    public static void printPolynom(int[] function) {
        System.out.print("f(x) = ");
        for(int i = 0; i < function.length; i++) {
            if(i == 0) {
                System.out.print(function[i] + " + ");
            }
            else if(i == function.length-1) {
                System.out.print(function[i] + "x^" + (i));
            } else {
                System.out.print(function[i] + "x^" + (i) + " + ");
            }
        }
        System.out.println();
    }

    public static void printFunctions(ArrayList<int[]> functions, int[] fx) {
        for(int i = 0; i < functions.size(); i++) {
            int[] func = functions.get(i);
            int ch = 96 + func.length;
            System.out.print("f(" + (i+1) + ") = ");
            for(int x = 0; x < func.length; x++) {
                if(func[x] == 1) {
                    if(x == func.length - 1) {
                        System.out.print(Character.toString((char)ch));
                    } else {
                        System.out.print(Character.toString((char)ch) + " + ");
                    }
                }
                else if(x == func.length - 1) {
                    System.out.print(func[x] + Character.toString((char)ch));
                }
                else {
                    System.out.print(func[x] + Character.toString((char)ch) + " + ");
                }
                ch--;
            }
            System.out.println(" = "+ fx[i+1]);
        }
    }

    public static int[] split(int secret, int share) {
        int[] arr = new int[share];
        arr[0] = secret;
        for(int i = 1; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*50) + 1;
        }
        return arr;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jeuxtrouverlemot.bean;

import java.util.Random;

/**
 *
 * @author darutydegrandprevincent
 */
public class Anagram implements Word{

    private static String[] tab = {"java" , "abstraction", "inheritance", "polymorphism", "overload", "override", "communication", "object",
    "class", "method", "attribute", "parameter", "argument", "interface"};
    private static int tabSize = tab.length;
    private static String theWord;
    private static char tab1[];
    private static char tab2[];
    private static int nbChar;

    public void init() {
        Random i = new Random();
        int ind = i.nextInt(tabSize);
        theWord = tab[ind];
        nbChar = theWord.length();
    }

    public String mixedWord() {                
        int wordLengh = theWord.length();        
        String mixedWord = "";
        tab2 = new char[wordLengh];

        for (int y = 1 ; y < wordLengh ; y += 2){
            tab2[y] = theWord.charAt(y);
            mixedWord += tab2[y];
        }
        for (int i = 0 ; i < wordLengh ; i += 2){
            tab1[i] = theWord.charAt(i);
            mixedWord += tab1[i];
        }
                                              
        return mixedWord;
    }

    public String mixedRandomWord() {
        String res ="";
        //on cree un tableau d'entier qui va stocker les entiers aleatoires.
        int tabInd[] = new int[nbChar];
        //on genere un Random
        Random x = new Random();
        //pour chaque element du tableau, on genere un numero aleatoire dans un interval compris entre 0 et le  nombre de caractere du mot selectionne.
        //chaque numero aleatoire genere doit etre compare a tous les elements precedents du tableau
        //si il est egual a un des autres elements alors on regenere un numero aleatoire qu'on recompare aux elements precedents du tableau jusqu'a ce qu'il soit different de tous les autres.
        for (int i = 0 ; i < tabInd.length ; i++){
            tabInd[i] = i;
        }

        for (int i = 0 ; i < tabInd.length ; i++){
            int random = x.nextInt(nbChar);
            int temp = tabInd[i];
            tabInd[i] = tabInd[random];
            tabInd[random] = temp;
        }

        tab1 = new char[nbChar];
        for (int i = 0 ; i < tab1.length ; i++){
            tab1[i] = theWord.charAt(tabInd[i]);
            res += tab1[i];
        }
        
        return res;
    }

    public boolean isEquals(String text){
        if (text.compareTo(theWord) == 0)
            return true;        
        else
            return false;
    }

    public static String getTheWord() {
        return theWord;
    }


}

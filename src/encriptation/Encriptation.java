/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package encriptation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;





/**
 *
 * @author jucaa
 */
public class Encriptation {
    public static ArrayList<Integer> codigoOri = new ArrayList<>();
    public static ArrayList<Integer> codigoRan = new ArrayList<>();
    public static ArrayList<Character> caracterASCII = new ArrayList<>();
    public static ArrayList<Integer> vecPos = new ArrayList<>();
    public static ArrayList<Integer> vecDir = new ArrayList<>();
    public static ArrayList<Character> srtTrash = new ArrayList<>();
    public static ArrayList<String> encryp = new ArrayList<>();
    public static ArrayList<Integer> vecPosTrash = new ArrayList<>();
    public static ArrayList<Integer> vecDirTrash = new ArrayList<>();
    public static int tamanio = 256;
    public static String parteAnterior;
    public static String partePosterior;
    public static String nuevaCadena;
    public static String textEncryp, textTrash;
    public static char nuevoCaracter;
    public static Hashtable<Character, Integer> c = new Hashtable<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        diccionary();
        int vueltas = 5;
        
        String frase = "Hola Mundo, como estas?\nJuCa estuvo aqui, y el Cris es un master...";
        
        // TODO code application logic here
        //funcion de llenado del codigo ASCII
        codAscii(codigoOri);
        codAscii(codigoRan);
        castearIntChar();
       
        System.out.println("Vector Original");
        System.out.println(codigoOri);
        suffle(vueltas);
        
        //si las listas son iguales repetir el suffle se vuelve hacer
        listRepeat(vueltas);
        
        //System.out.println(vecPos);
        //System.out.println(vecDir);
        
        //completeBinary(45);
        //System.out.println((int)frase.charAt(0));
        /*for(int i = 0; i < frase.length(); i++){
            System.out.println((int)frase.charAt(i)+" --> "+completeBinary((int)frase.charAt(i)));
        }*/
        
        trash();
        fillVecTrash(vueltas);
        
        listToString();
        binaryConversion(frase);
        System.out.println("EnBinario Primer y Ultimo Bit Cambiado:\n"+encryp);
        //System.out.println("PosVec 0 --> StringPos 0 --> "+encryp.get(0).charAt(0)+" StringPos end --> "+encryp.get(0).charAt(7));
        
        //listToString();
        for (int i = 0; i < textTrash.length(); i++) {
            System.out.print(i+": "+textTrash.charAt(i)+" | ");
        }
        System.out.println("\nTrashChange\n");
        listToString();
        System.out.println("");
        
    }
    
    // Diccionario ayuda para encriptar y desencriptar 
    public static void diccionary(){
        
        c.put('B',0);
        c.put('%',1);
        c.put('!',4);
        c.put('|',5);
        c.put('/',6);
        c.put('<',7);
        c.put(':',8);
        c.put('?',9);
        c.put('>',10);
        c.put('&',11);
        c.put('$',12);
        c.put('*',13);
        c.put(';',14);
        c.put('~',15);
        c.put('A',20);
        c.put('Z',21);
        c.put('T',22);
        c.put('Y',23);
        c.put('a',24);
        c.put('d',25);
        c.put('b',26);
        c.put('v',27);
        c.put('c',28);
        c.put('w',29);
        c.put('X',30);
        c.put('E',31);
        c.put('L',32);
        c.put('n',33);
        c.put('j',34);
        c.put('f',35);
        c.put('G',36);
        c.put('^',37);
        c.put('_',38);
        c.put('Z',39);
        c.put('K',40);
        c.put('p',41);
        c.put('s',42);
        c.put('q',43);
        c.put('o',44);
        c.put('i',45);
        c.put('.',46);
        c.put('-',47);
        c.put('@',48);
        c.put('#',49);
        c.put('=',50);
        
        //System.out.println(c.get('@'));
    }
    
    // Encontrar el caracter que hace alución al numero
    public static char findValueChar(int num){
        char claveBuscada = '0';

        // Recorrer la Hashtable y buscar la clave asociada al valor buscado  
        for (Map.Entry<Character, Integer> entry : c.entrySet()) {
            if (entry.getValue()==num) {
                claveBuscada = entry.getKey();
                break;
            }
        }
        if(claveBuscada == '0'){
            System.out.println("No se encontró ninguna clave asociada al valor");
        }
        return claveBuscada;
    }
    
    //Lleno los vectores en las posiciones que vamos a ocupar en el trash para poner 
    //el direccion y las posiciones de desplazamiento
    public static void fillVecTrash(int vueltas){
        char value = '0';
        char value2 = '0';
        // Llenado de vector hacia donde se fue izq o der encriptado
        vecDirTrash.add(25);
        vecDirTrash.add(17);
        vecDirTrash.add(12);
        vecDirTrash.add(278);
        vecDirTrash.add(146);
        vecDirTrash.add(200);
        vecDirTrash.add(85);
        vecDirTrash.add(250);
        vecDirTrash.add(129);
        vecDirTrash.add(99);
        vecDirTrash.add(111);
        
        // Llenado del vector de cuanto se desplazo encriptado
        vecPosTrash.add(184);
        vecPosTrash.add(300);
        vecPosTrash.add(296);
        vecPosTrash.add(149);
        vecPosTrash.add(40);
        vecPosTrash.add(320);
        vecPosTrash.add(70);
        vecPosTrash.add(60);
        vecPosTrash.add(13);
        vecPosTrash.add(38);
        vecPosTrash.add(244);
        
        for(int i = 0; i < vueltas; i++){
            value = findValueChar((int)vecDir.get(i));
            value2 = findValueChar((int)vecPos.get(i));
            
            srtTrash.set(vecDirTrash.get(i), value);
            srtTrash.set(vecPosTrash.get(i), value2);
            
           System.out.println("Dir "+vecDirTrash.get(i)+"-> "+value+"["+vecDir.get(i)+"] --- Pos "+vecPosTrash.get(i)+"->"+value2+"["+vecPos.get(i)+"]");
        }
    }
    
    
    //Hace la conversion de la letra a binario
    public static void binaryConversion(String letter){
        for(int i = 0; i < letter.length(); i++){
            System.out.println("");
            encryp.add(completeBinary((int)letter.charAt(i)));
        }
        System.out.println("");
    }
    
    
    //De listas a String del texto ya encriptado
    public static void listToString(){
        textEncryp = "";
        textTrash = "";
        for(int i = 0; i < encryp.size(); i++){
            textEncryp += encryp.get(i);
        }
        System.out.println("Concatenado Binario:\n"+textEncryp);
        
        for(int j = 0; j < srtTrash.size(); j++){
            textTrash += srtTrash.get(j);
        }
        /*for (int i = 0; i < srtTrash.size(); i++) {
            System.out.println(i+": "+srtTrash.get(i)+" | ");
        }*/
        System.out.println("Concatenado Trash:\n"+textTrash);
    }
    
    //agrega que sea de 8 bits EXACTAMENTE mi numero decimal
    public static String completeBinary(int decimal){
        
        System.out.println("NumOriginal -> "+decimal+"  ---  NumNew -> "+codigoRan.get(decimal));
        
        String binario = Integer.toBinaryString(codigoRan.get(decimal)); // Convertir a binario
        // Completar con ceros a la izquierda si es necesario
        while (binario.length() < 8) {
            binario = "0" + binario;
        }
        //Modificar el Primer Bit del caracter 
        if(binario.charAt(0)=='1'){
            nuevoCaracter = '0';
            // Obtener la parte después de la posición a modificar
            partePosterior = binario.substring(0 + 1);
             // Crear la nueva cadena concatenando las partes y el nuevo carácter
             binario = nuevoCaracter + partePosterior;
             
        }else if(binario.charAt(0)=='0'){
            nuevoCaracter = '1';
            // Obtener la parte después de la posición a modificar
            partePosterior = binario.substring(0 + 1);
            binario = nuevoCaracter + partePosterior;
            
        }
        
        //Modificar el Ultimo Bit del caracter 
        if(binario.charAt(7)=='1'){
            nuevoCaracter = '0';
            // Obtener la parte antes de la posición a modificar
            parteAnterior = binario.substring(0, 7);
            
            // Crear la nueva cadena concatenando las partes y el nuevo carácter
            binario = parteAnterior+nuevoCaracter;
            
        }else if(binario.charAt(7)=='0'){
            nuevoCaracter = '1';
            // Obtener la parte antes de la posición a modificar
            parteAnterior = binario.substring(0, 7);
            
            // Crear la nueva cadena concatenando las partes y el nuevo carácter
            binario = parteAnterior+nuevoCaracter;
            
        }
        
        return binario;
    }
    
    //castear el entero por el char
    public static void castearIntChar(){
        for(int i = 0; i < tamanio; i++){
            caracterASCII.add(i, (char)codigoOri.get(i).intValue());
        }
        //System.out.println(caracterASCII);
    }
    public static void formatPretty(){
        
        for(int i = 0; i < codigoRan.size(); i++){
            System.out.print(i+": "+codigoRan.get(i)+" | ");
        }
        System.out.println("");
    }
    //llenado del vector en codigo ASCII
    private static void codAscii(ArrayList<Integer> lista) {
        for(int i = 0; i<tamanio; i++){
            lista.add(i, i);
        }
    }
    
    private static void listRepeat(int c){
        while(codigoOri.equals(codigoRan)){
            suffle(c);
        }
        /*if (!codigoOri.equals(codigoRan)) {
            System.out.println("Las listas no son iguales");
        } else {
            System.out.println("Las listas son iguales");
        }*/
    }
    
    //funcion de caracteres basura
    public static void trash(){
        srtTrash.clear();//Limpia el buffer de basura
        int numR;
        for(int i = 0; i < 350; i++){
            numR = (int)(Math.random() * ((126 - 32) + 1)) + 32;//posiciones a recorrer
            srtTrash.add((char)numR);
        }
        //System.out.println(srtTrash);
    }
    
    
    //Mueve a un nuevo vector Random
    private static void suffle(int ciclos){
        for(int i = 0; i<ciclos; i++){
            int j = 0;
            
            int dir = (int)(Math.random() * ((1 - 0) + 1)) + 0;//Direccion a tomar 0 = izq 1 = der
            int pos = (int)(Math.random() * ((50 - 25) + 1)) + 25;//posiciones a recorrer
            int temp;
            
            vecPos.add(pos);//Posicion
            vecDir.add(dir);//Direccion
            
            if(dir == 0){//recorrido izquierda
                while(j < pos){
                    // Mover los valores una posición a la izquierda
                   temp = codigoRan.get(0); // Guardar el primer elemento
                   for (int z = 0; z < codigoRan.size() - 1; z++) {
                        codigoRan.set(z, codigoRan.get(z + 1)); // Mover el elemento hacia la izquierda
                    }

                    codigoRan.set(codigoRan.size() - 1, temp); // Asignar el primer elemento en la última posición
                    
                    j++;
                }
                System.out.println("Lista desplazada a la izquierda: pos = "+pos);
                
            }else if(dir == 1){// recorrido derecha 
                //Recorro el vector las posiciones a la derecha
                while(j < pos){
                    
                    // Mover los valores una posición a la derecha
                    temp = codigoRan.get(codigoRan.size() - 1); // Guardar el último elemento

                    for (int z = codigoRan.size() - 1; z > 0; z--) {
                        codigoRan.set(z, codigoRan.get(z - 1)); // Mover el elemento hacia la derecha
                    }

                    codigoRan.set(0, temp); // Asignar el último elemento en la primera posición
                    j++;
                }
                System.out.println("Lista desplazada a la derecha: pos = "+pos);
                
            }
            formatPretty();
        }
    }
    
}

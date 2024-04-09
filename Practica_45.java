import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Practica_45 {

    /**
     * Este metodo pide por teclado los datos necesarios para agrega un nuevo usuario al txt y lo agrega
     * @param scanner El scannner que se inicia en la funcion menu()
     * @param fichero La ruta del fichero
     */
    public static void agregar(Scanner scanner , String fichero){
        System.out.println("Introduzca el dni: ");
        String dni = scanner.nextLine();
        System.out.println("Introduzca el nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Introduzca los apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.println("Introduzca el correo: ");
        String correo = scanner.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichero, true));){
            String linea = (dni + ", " + nombre + ", " + apellidos + ", " + correo);
            writer.write(linea);
            writer.newLine();
        }
        catch (IOException e) {
            System.out.print("Error al leer el archivo");
        }
    }
    
    /**
     * Este metodo borra un usuario mediante el dni pedido por teclado
     * @param scanner El scannner que se inicia en la funcion menu()
     * @param fichero La ruta del fichero
     */
    public static void eliminar(Scanner scanner , String fichero){
        // se crea un archivo temporal
        File arhchivoOriginal = new File(fichero);
        File archivoTemp = new File("temp.txt");
        System.out.println("Introduzca el dni: ");
        String dni = scanner.nextLine();
        String linea = null;
        try (FileReader fileReader = new FileReader(fichero); BufferedReader bufferedReader = new BufferedReader(fileReader); 
        BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"))){
        // se escribe en el archivo temporal todo el contenido del original menos la linea de la que coincida el dni
        while((linea=bufferedReader.readLine()) != null) {
            if (!linea.substring(0, 9).equals(dni)){
                writer.write(linea);
                writer.newLine();
            }
        }  
        }
        catch (IOException e) {
            System.out.print("Error al leer el archivo");
        }
        // se borra el archivo original y se renombra el archivo temporal
        arhchivoOriginal.delete();
        archivoTemp.renameTo(arhchivoOriginal);
    }
    

    /**
     * Este metodo pide el dni por teclado , busca en el fichero la linea con ese dni , y devuelve la linea
     * @param scanner El scannner que se inicia en la funcion menu()
     * @param fichero La ruta del fichero
     * @return 
     */
    public static String busqueda_dni(Scanner scanner , String fichero){
        System.out.println("Introduzca el dni: ");
        String dni = scanner.nextLine();
        String linea = null;
        try (FileReader fileReader = new FileReader(fichero);
        BufferedReader bufferedReader = new BufferedReader(fileReader);){
        while((linea=bufferedReader.readLine()) != null) {
            if (linea.substring(0, 9).equals(dni)){
                return linea;
            }
        }
        System.out.print("El dni introducido no existe");
        return linea;
        }
        catch (IOException e) {
            System.out.print("Error al leer el archivo");
            return linea;
        }
    }

    /**
     * Este metodo imprime los datos de la linea que se le pasa
     * @param linea La linea que va a imprimir
     */
    public static void imprimir_datos(String linea){
        String[] linea_c = linea.split(", ");
        System.out.println("nombre: " + linea_c[1]);
        System.out.println("apellidos: " + linea_c[2]);
        System.out.println("correo: " + linea_c[3]);
    } 


    /**
     * El metodo menu es el metodo que se ejecuta al ejecutar el programa y es un menu que le permite al usuario usar las funcionalidades del programa
     */
    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            String fichero = "usuarios.txt";
            System.out.println("\n--- Opciones: ---");
            System.out.println("1. Buscar usuario por DNI");
            System.out.println("2. Agregar usuario");
            System.out.println("3. Eliminar usuario");
            System.out.println("4. cerrar programa");
            System.out.println("Seleccione una opcion: ");
            String opcion = scanner.nextLine();
            if (opcion.equals("1")) {
                String linea = busqueda_dni(scanner , fichero);
                if (linea == null){
                    System.out.println("\nVuelva a elejir una opcion");
                }
                else {
                    imprimir_datos(linea);
                }
            }
            else if(opcion.equals("2")){
                agregar(scanner , fichero);
            }
            else if (opcion.equals("3")){
                eliminar(scanner , fichero);
            }
            else if(opcion.equals("4")){
                scanner.close();
                return;
            }
        }
    }

    public static void main(String[] args){
        menu();
    }
}
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Practica_45 {


    public static void agregar(String fichero){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el dni: ");
        String dni = scanner.nextLine();
        System.out.println("Introduzca el nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Introduzca los apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.println("Introduzca el correo: ");
        String correo = scanner.nextLine();
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fichero, true));
            String linea = (dni + ", " + nombre + ", " + apellidos + ", " + correo);
            writer.write(linea);
            writer.newLine();
        }
        catch (IOException e) {
            System.out.print("Error al leer el archivo");
            scanner.close();
        }
        finally {
            if (writer != null) {
                try {
                    writer.close(); 
                } catch (IOException e) {
                    System.out.print("Error al cerrar el archivo");
                }
            }
        }
    }
    

    public static String busqueda_dni(String fichero){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el dni: ");
        String dni = scanner.nextLine();
        String linea = null;
        try (FileReader fileReader = new FileReader(fichero);
        BufferedReader bufferedReader = new BufferedReader(fileReader);){
        while((linea=bufferedReader.readLine()) != null) {
            if (linea.substring(0, 9).equals(dni)){
                scanner.close();
                return linea;
            }
        }
        System.out.print("El dni introducido no existe");
        scanner.close();
        return linea;
        }
        catch (IOException e) {
            System.out.print("Error al leer el archivo");
            scanner.close();
            return linea;
        }
    }

    public static void imprimir_datos(String linea){
        String[] linea_c = linea.split(", ");
        System.out.println("nombre: " + linea_c[1]);
        System.out.println("apellidos: " + linea_c[2]);
        System.out.println("correo: " + linea_c[3]);
    } 



    public static void menu(){
        while (true){
            String fichero = "usuarios.txt";
            System.out.println("\n--- Opciones: ---");
            System.out.println("1. Buscar usuario por DNI");
            System.out.println("2. Agregar usuario");
            System.out.println("3. cerrar programa");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Seleccione una opcion: ");
            String opcion = scanner.nextLine();
            if (opcion.equals("1")) {
                String linea = busqueda_dni(fichero);
                if (linea == null){
                    System.out.println("\nVuelva a elejir una opcion");
                }
                else {
                    imprimir_datos(linea);
                }
            }
            else if(opcion.equals("2")){
                agregar(fichero);
            }
            else if(opcion.equals("3")){
                scanner.close();
                return;
            }
        }
    }

    public static void main(String[] args){
        menu();
    }
}
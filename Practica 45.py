import os


def agregar():
    """
    Este metodo pide por teclado los datos necesarios para agrega un nuevo usuario al txt y lo agrega
    """
    with open('usuarios.txt' , 'a' , encoding='utf-8') as fichero:
        dni = str(input("Introduzca el dni: "))
        nombre = str(input("Introduzca el nombre: "))
        apellidos = str(input("Introduzca los apellidos "))
        correo = str(input("Introduzca el correo: "))
        linea = (dni + ", " + nombre + ", " + apellidos + ", " + correo)
        fichero.write(linea)

def eliminar(fichero):
    """
    Este metodo borra un usuario mediante el dni pedido por teclado
    """
    with open('temp.txt' , 'a' , encoding='utf-8') as fichero_escritura:
        dni = str(input("Introduzca el dni que quieres buscar: "))
        for linea in fichero:
            if dni != linea[:9]:
                fichero_escritura.write(linea)
        fichero.close()
        fichero_escritura.close()
        # se elemina el archivo original y se renombra el temporal
        os.remove('usuarios.txt')
        os.rename('temp.txt' , 'usuarios.txt')
        

def busqueda_dni(fichero):
    """
    Este metodo pide el dni por teclado , busca en el fichero la linea con ese dni , y devuelve la linea 
    """
    dni = str(input("Introduzca el dni que quieres buscar: "))
    for linea in fichero:
        # se establece un condicional que compara el dni introducido con el dni de cada linea del txt
        if dni == linea[:9]:
            return linea
    print("\nEl dni introducido no existe")

def imprimir_datos(linea):
    """
    Este metodo imprime los datos de la linea que se le pasa
    """
    linea_c = linea.split(",")
    nombre = linea_c[1]
    apellidos = linea_c[2]
    correo = linea_c[3]
    print("nombre: " , nombre)
    print("apellidos: " , apellidos)
    print("correo: " , correo)
    

def menu():
    """
    El metodo menu es el metodo que se ejecuta al ejecutar el programa y es un menu que le permite al usuario usar las funcionalidades del programa
    """
    while True:
        with open('usuarios.txt' , 'r' , encoding='utf-8') as fichero:
            print("\n--- Opciones: ---")
            print("1. Buscar usuario por DNI")
            print("2. Agregar registro")
            print("3. Eliminar registro")
            print("4. cerrar programa")
            opcion = input("Seleccione una opción: ")
            # si se elije la opcion 1 se llama al metodo de buscar dni y luego al metodo de imprimir los datos
            if opcion == '1':
                linea_actual = busqueda_dni(fichero)
                # se comprueba que la salida del metodo busqued_dni no sea null por haber introducido un dni inexistente
                if linea_actual != None:
                    imprimir_datos(linea_actual)
                else:
                    print("\nVuelva a elejir una opcion")
            elif opcion == '2':
                agregar()
            elif opcion == '3':
                eliminar(fichero)
            elif opcion == '4':
                fichero.close()
                return


menu()
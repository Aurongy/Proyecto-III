import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        MatrizVehiculos lista = new MatrizVehiculos();

        int opcion = 0;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Insertar");
            System.out.println("2. Buscar");
            System.out.println("3. Eliminar");
            System.out.println("4. Mostrar");
            System.out.println("5. Salir");
            System.out.print("Opcion: ");

            if (!entrada.hasNextInt()) {
                System.out.println("Ingrese un número válido");
                entrada.nextLine();
                continue;
            }

            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {

                case 1:

                    int fila, columna;

                    // 🔹 VALIDAR FILA
                    while (true) {

                        System.out.print("Fila: ");

                        if (entrada.hasNextInt()) {

                            fila = entrada.nextInt();

                            if (fila >= 0) {
                                break;
                            }
                        } else {
                            entrada.next();
                        }

                        System.out.println("Fila inválida");
                    }

                    // 🔹 VALIDAR COLUMNA
                    while (true) {

                        System.out.print("Columna: ");

                        if (entrada.hasNextInt()) {

                            columna = entrada.nextInt();

                            if (columna >= 0) {
                                break;
                            }

                        } else {
                            entrada.next();
                        }

                        System.out.println("Columna inválida");
                    }

                    entrada.nextLine();

                    // 🔹 VALIDAR PLACA
                    System.out.print("Placa: ");
                    String placa = entrada.nextLine().toUpperCase();

                    if (!validarPlaca(placa)) {
                        System.out.println("Placa inválida");
                        break;
                    }

                    System.out.print("Color: ");
                    String color = entrada.nextLine();

                    System.out.print("Linea: ");
                    String linea = entrada.nextLine();

                    System.out.print("Modelo: ");
                    String modelo = entrada.nextLine();

                    System.out.print("Propietario: ");
                    String propietario = entrada.nextLine();

                    lista.insertar(
                            fila,
                            columna,
                            placa,
                            color,
                            linea,
                            modelo,
                            propietario
                    );

                    break;

                case 2:

                    System.out.print("Buscar (puede ser cualquier dato): ");
                    String valor = entrada.nextLine();

                    if (valor.isEmpty()) {
                        System.out.println("Debe ingresar un valor");
                        break;
                    }

                    lista.buscar(valor);

                    break;

                case 3:

                    System.out.print("Placa a eliminar: ");
                    String placaEliminar = entrada.nextLine();

                    if (placaEliminar.isEmpty()) {
                        System.out.println("Debe ingresar una placa");
                        break;
                    }

                    lista.eliminar(placaEliminar);

                    break;

                case 4:

                    lista.mostrar();

                    break;

                case 5:

                    System.out.println("Saliendo...");

                    break;

                default:

                    System.out.println("Opción inválida");
            }

        } while (opcion != 5);

        entrada.close();
    }

    // Parte de la validacion de numero de placa
    public static boolean validarPlaca(String placa) {

        if (placa == null) {
            return false;
        }

        // elimina espacios al inicio y final
        placa = placa.trim().toUpperCase();

        // longitud mínima y máxima
        if (placa.length() < 6 || placa.length() > 8) {
            return false;
        }

        // primera posición debe ser letra
        if (!Character.isLetter(placa.charAt(0))) {
            return false;
        }

        boolean tieneNumero = false;
        boolean tieneLetraFinal = false;

        for (int i = 1; i < placa.length(); i++) {

            char c = placa.charAt(i);

            // validar números
            if (Character.isDigit(c)) {

                // no permitir números después de letras
                if (tieneLetraFinal) {
                    return false;
                }

                tieneNumero = true;
            }

            // validar letras
            else if (Character.isLetter(c)) {

                // las letras deben ir después de números
                if (!tieneNumero) {
                    return false;
                }

                tieneLetraFinal = true;
            }

            // símbolos inválidos
            else {
                return false;
            }
        }

        return tieneNumero;
    }
}
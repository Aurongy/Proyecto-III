public class MatrizVehiculos {

    private NodoCabecera filas;
    private NodoCabecera columnas;


    // 🔹 INSERTAR POR POSICIÓN REAL
    public void insertar(int fila, int columna,
                         String placa, String color,
                         String linea, String modelo, String propietario) {

        // VALIDACIONES PRIMERO
        if (existePlaca(placa)) {
            System.out.println("Placa duplicada");
            return;
        }

        if (existePosicion(fila, columna)) {
            System.out.println("Ya existe un vehículo en esa posición");
            return;
        }

        NodoCabecera filaCab = obtenerCabeceraFila(fila);
        NodoCabecera colCab = obtenerCabeceraColumna(columna);

        VehiculoNodo nuevo = new VehiculoNodo(fila, columna, placa, color, linea, modelo, propietario);

        // 🔹 INSERTAR EN FILA (horizontal)
        if (filaCab.acceso == null) {
            filaCab.acceso = nuevo;
        } else {
            VehiculoNodo actual = filaCab.acceso;
            VehiculoNodo anterior = null;

            while (actual != null && actual.getColumna() < columna) {
                anterior = actual;
                actual = actual.getDerecha();
            }

            if (anterior == null) {
                nuevo.setDerecha(filaCab.acceso);
                filaCab.acceso = nuevo;
            } else {
                anterior.setDerecha(nuevo);
                nuevo.setDerecha(actual);
            }
        }

        // 🔹 INSERTAR EN COLUMNA (vertical)
        if (colCab.acceso == null) {
            colCab.acceso = nuevo;
        } else {
            VehiculoNodo actual = colCab.acceso;
            VehiculoNodo anterior = null;

            while (actual != null && actual.getFila() < fila) {
                anterior = actual;
                actual = actual.getAbajo();
            }

            if (anterior == null) {
                nuevo.setAbajo(colCab.acceso);
                colCab.acceso = nuevo;
            } else {
                anterior.setAbajo(nuevo);
                nuevo.setAbajo(actual);
            }
        }

        System.out.println("Insertado correctamente");
    }

    private NodoCabecera obtenerCabeceraFila(int fila) {

        if (filas == null) {
            filas = new NodoCabecera(fila);
            return filas;
        }

        NodoCabecera actual = filas;
        NodoCabecera anterior = null;

        while (actual != null && actual.indice < fila) {
            anterior = actual;
            actual = actual.siguiente;
        }

        if (actual != null && actual.indice == fila) {
            return actual;
        }

        NodoCabecera nueva = new NodoCabecera(fila);

        if (anterior == null) {
            nueva.siguiente = filas;
            filas = nueva;
        } else {
            nueva.siguiente = actual;
            anterior.siguiente = nueva;
        }

        return nueva;
    }

    private NodoCabecera obtenerCabeceraColumna(int columna) {

        if (columnas == null) {
            columnas = new NodoCabecera(columna);
            return columnas;
        }

        NodoCabecera actual = columnas;
        NodoCabecera anterior = null;

        while (actual != null && actual.indice < columna) {
            anterior = actual;
            actual = actual.siguiente;
        }

        if (actual != null && actual.indice == columna) {
            return actual;
        }

        NodoCabecera nueva = new NodoCabecera(columna);

        if (anterior == null) {
            nueva.siguiente = columnas;
            columnas = nueva;
        } else {
            nueva.siguiente = actual;
            anterior.siguiente = nueva;
        }

        return nueva;
    }

    public void buscar(String valor) {

        NodoCabecera fila = filas;
        boolean encontrado = false;

        while (fila != null) {

            VehiculoNodo actual = fila.acceso;

            while (actual != null) {

                if (actual.coincideCon(valor)) {
                    System.out.println(actual);
                    encontrado = true;
                }

                actual = actual.getDerecha();
            }

            fila = fila.siguiente;
        }

        if (!encontrado) {
            System.out.println("No encontrado");
        }
    }
    public void eliminar(String placa) {

        NodoCabecera fila = filas;

        while (fila != null) {

            VehiculoNodo actual = fila.acceso;
            VehiculoNodo anterior = null;

            while (actual != null) {

                if (actual.esPlaca(placa)) {

                    // 🔹 eliminar en fila
                    if (anterior == null) {
                        fila.acceso = actual.getDerecha();
                    } else {
                        anterior.setDerecha(actual.getDerecha());
                    }

                    // 🔹 eliminar en columna
                    NodoCabecera col = columnas;
                    while (col != null) {

                        VehiculoNodo actCol = col.acceso;
                        VehiculoNodo antCol = null;

                        while (actCol != null) {
                            if (actCol == actual) {

                                if (antCol == null) {
                                    col.acceso = actCol.getAbajo();
                                } else {
                                    antCol.setAbajo(actCol.getAbajo());
                                }

                                break;
                            }

                            antCol = actCol;
                            actCol = actCol.getAbajo();
                        }

                        col = col.siguiente;
                    }

                    System.out.println("Eliminado correctamente");
                    return;
                }

                anterior = actual;
                actual = actual.getDerecha();
            }

            fila = fila.siguiente;
        }

        System.out.println("No encontrado");
    }
    public void mostrar() {

        NodoCabecera fila = filas;

        while (fila != null) {

            VehiculoNodo actual = fila.acceso;

            while (actual != null) {
                System.out.print(actual + " ");
                actual = actual.getDerecha();
            }

            System.out.println();
            fila = fila.siguiente;
        }
    }
    private boolean existePlaca(String placa) {

        NodoCabecera fila = filas;

        while (fila != null) {

            VehiculoNodo actual = fila.acceso;

            while (actual != null) {
                if (actual.esPlaca(placa)) return true;
                actual = actual.getDerecha();
            }

            fila = fila.siguiente;
        }

        return false;
    }
    private boolean existePosicion(int fila, int columna) {

        NodoCabecera filaCab = filas;

        while (filaCab != null) {

            if (filaCab.indice == fila) {

                VehiculoNodo actual = filaCab.acceso;

                while (actual != null) {
                    if (actual.getColumna() == columna) {
                        return true;
                    }
                    actual = actual.getDerecha();
                }
            }

            filaCab = filaCab.siguiente;
        }

        return false;
    }
}
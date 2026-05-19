public class VehiculoNodo {

    private String placa, color, linea, modelo, propietario;
    private int fila, columna;

    private VehiculoNodo derecha;
    private VehiculoNodo abajo;

    public VehiculoNodo(int fila, int columna,
                        String placa, String color,
                        String linea, String modelo, String propietario) {

        this.fila = fila;
        this.columna = columna;
        this.placa = placa;
        this.color = color;
        this.linea = linea;
        this.modelo = modelo;
        this.propietario = propietario;
    }

    public int getFila() { return fila; }
    public int getColumna() { return columna; }

    public VehiculoNodo getDerecha() { return derecha; }
    public VehiculoNodo getAbajo() { return abajo; }

    public void setDerecha(VehiculoNodo d) { this.derecha = d; }
    public void setAbajo(VehiculoNodo a) { this.abajo = a; }

    public boolean coincideCon(String valor) {
        if (valor == null) return false;
        valor = valor.toLowerCase();

        return contiene(placa, valor) ||
                contiene(color, valor) ||
                contiene(linea, valor) ||
                contiene(modelo, valor) ||
                contiene(propietario, valor);
    }

    private boolean contiene(String campo, String valor) {
        return campo != null && campo.toLowerCase().contains(valor);
    }

    public boolean esPlaca(String placa) {
        return this.placa != null && this.placa.equalsIgnoreCase(placa);
    }

    @Override
    public String toString() {
        return "[" + fila + "," + columna + " " + placa + "]";
    }
}
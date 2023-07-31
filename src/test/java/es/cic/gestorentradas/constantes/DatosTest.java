package es.cic.gestorentradas.constantes;

public class DatosTest {

    public static final String contenidoCine = "<pre>CINE_1\t\tSALA_1\t\tSESION_1: 17:00, Film1\t\t Entradas disponibles: 100\n" +
            "CINE_1\t\tSALA_1\t\tSESION_2: 20:00, Film1\t\t Entradas disponibles: 100\n" +
            "CINE_1\t\tSALA_1\t\tSESION_3: 22:30, Film2\t\t Entradas disponibles: 100\n" +
            "CINE_1\t\tSALA_2\t\tSESION_4: 17:00, Film3\t\t Entradas disponibles: 50\n" +
            "CINE_1\t\tSALA_2\t\tSESION_5: 20:00, Film2\t\t Entradas disponibles: 50\n" +
            "CINE_1\t\tSALA_2\t\tSESION_6: 22:30, Film4\t\t Entradas disponibles: 50\n" +
            "CINE_1\t\tSALA_3\t\tSESION_7: 17:00, Film5\t\t Entradas disponibles: 20\n" +
            "CINE_1\t\tSALA_3\t\tSESION_8: 20:00, Film3\t\t Entradas disponibles: 20\n" +
            "CINE_1\t\tSALA_3\t\tSESION_9: 22:30, Film5\t\t Entradas disponibles: 20\n" +
            "\n" + "{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=510, Ocupacion=0.0, Entradas vendidas=0}" +
            "</pre>\n";

    public static final String contenidoSala1 = "<pre>CINE_1\t\tSALA_1\t\tSESION_1: 17:00, Film1\t\t Entradas disponibles: 100\n" +
            "CINE_1\t\tSALA_1\t\tSESION_2: 20:00, Film1\t\t Entradas disponibles: 100\n" +
            "CINE_1\t\tSALA_1\t\tSESION_3: 22:30, Film2\t\t Entradas disponibles: 100\n" +
            "</pre>\n" +
            "{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=300, Ocupacion=0.0, Entradas vendidas=0}\n" +
            "</pre>\n";

    public static final String respuestaVerCrear = "Venta Nº VENTA_1\tSESION_2\n" +
            "\tEntradas 50\tPrecio 5.0\ttotal 250.0\tDescuento 25.0\ttotalPagar 225.0\n" +
            "\tVenta cancelada false\t\tEntradas Canceladas 0" + "\n";


    public static final String respuestaModificarEntradasSesion = "Venta Nº VENTA_1\tSESION_7\n" +
            "\tEntradas 2\tPrecio 5.0\ttotal 10.0\tDescuento 0.0\ttotalPagar 10.0\n" +
            "\tVenta cancelada false\t\tEntradas Canceladas 0" + "\n";

    public static final String respuestaModificarSesion = "Venta Nº VENTA_1\tSESION_7\n" +
            "\tEntradas 4\tPrecio 5.0\ttotal 20.0\tDescuento 0.0\ttotalPagar 20.0\n" +
            "\tVenta cancelada false\t\tEntradas Canceladas 0" + "\n";

    public static final String respuestaVerSesion = "<pre>CINE_1\t\tSALA_3\t\tSESION_7: 17:00, Film5\t\t Entradas disponibles: 20\n" +
            "\n" + "{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=20, Ocupacion=0.0, Entradas vendidas=0}</pre>\n";


    public static final String respuestaModificarEntradas = "Venta Nº VENTA_1\tSESION_2\n" +
            "\tEntradas 48\tPrecio 5.0\ttotal 240.0\tDescuento 24.0\ttotalPagar 216.0\n" +
            "\tVenta cancelada false\t\tEntradas Canceladas 2" + "\n";

    public static final String contenidoEstadisticas = "CINE_1\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=510, Ocupacion=0.0, Entradas vendidas=0}\n" +
            "\tSALA_1\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=300, Ocupacion=0.0, Entradas vendidas=0}\n" +
            "\t\tSESION_1: 17:00, Film1\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=100, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 100\n" +
            "\t\tSESION_2: 20:00, Film1\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=100, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 100\n" +
            "\t\tSESION_3: 22:30, Film2\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=100, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 100\n" +
            "\tSALA_2\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=150, Ocupacion=0.0, Entradas vendidas=0}\n" +
            "\t\tSESION_4: 17:00, Film3\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=50, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 50\n" +
            "\t\tSESION_5: 20:00, Film2\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=50, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 50\n" +
            "\t\tSESION_6: 22:30, Film4\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=50, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 50\n" +
            "\tSALA_3\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=60, Ocupacion=0.0, Entradas vendidas=0}\n" +
            "\t\tSESION_7: 17:00, Film5\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=20, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 20\n" +
            "\t\tSESION_8: 20:00, Film3\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=20, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 20\n" +
            "\t\tSESION_9: 22:30, Film5\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=20, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 20\n";

    public static final String respuestaEliminadaOK = "Venta eliminada correctamente\n";
}

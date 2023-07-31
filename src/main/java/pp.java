import static es.cic.gestorentradas.gestion.CineDatos.CINE_1;
import static es.cic.gestorentradas.gestion.EstadisticasDatos.datosSesion;
import static es.cic.gestorentradas.gestion.GestorVentasCines.getSalasCine;
import static es.cic.gestorentradas.gestion.GestorVentasCines.pintarDatos;
import static es.cic.gestorentradas.gestion.SesionDatos.SESION_3;

public class pp {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("<pre>");
        getSalasCine(CINE_1.getId()).forEach(sala ->
                sala.getSesiones().forEach(sesion -> {
                    if (sesion.getId().equalsIgnoreCase(SESION_3.getId())) {
                        pintarDatos(sb, sala, sesion, CINE_1);
                    }
                }));
        sb.append("\n").append(datosSesion(getSalasCine(CINE_1.getId()), SESION_3.getId())).append("</pre>");
        sb.append("\n");
        System.out.println(sb);
    }
}

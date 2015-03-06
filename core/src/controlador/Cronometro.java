package controlador;

/**
 * Created by Oscar on 04/03/2015.
 */
public class Cronometro {

    private static  int segundos = 59;
    private static  int minutos = 2;
    private static float time;


    public static void cuentaSegundos(float delta){
        if (time >= 0) {
            if ( (time += delta) >= 1) {
                segundos -=(int)time;
                time = 0;
                if (segundos ==0) {
                    segundos = 59;
                    minutos -= 1;
                }
            }
        }
    }

    public static int getSegundos() {
        return segundos;
    }

    public static int getMinutos() {
        return minutos;
    }
}

package es.cic.gestorentradas.validadores;

public class enumUtil {

    public static <T extends Enum<T>> boolean isEnumValue(String value, Class<T> enumClass) {
        T[] enumValues = enumClass.getEnumConstants();
        for (T enumValue : enumValues) {
            if (enumValue.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}

package wolox.albumes.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

public class ValidatorUtil {

    private static final String NUMERO_POSITIVO_ERROR = "El valor del parametro debe ser negativo.";
    private static final String EL_FORMATO_ES_INCORRECTO = "El valor del parametro debe ser numerico.";

    public static ResponseEntity validateNumber(String paramKey, String number) {
        return ResponseEntity.ok("");
/*
        try {
            int number = Integer.parseInt(numberStr);
            if(number < 1) return ResponseEntity.badRequest().body(NUMERO_POSITIVO_ERROR + getKey(paramKey));
            return ResponseEntity.ok("");
        } catch (NumberFormatException e){
            return ResponseEntity.badRequest().body(EL_FORMATO_ES_INCORRECTO + getKey(paramKey));
        }*/
    }

    private static String getKey(String paramKey){
        return " (" + paramKey + ")";
    }

    public static boolean filterApplied(Object value){
        return value != null && StringUtils.hasText(value.toString());
    }
}

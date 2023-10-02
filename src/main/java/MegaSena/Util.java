package MegaSena;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Util {
	
	public static BigDecimal formataStringParaBigDecimal(String valor) {
        valor = valor.replaceAll("[^0-9.,]", "")
                     .replace(".", "")  
                     .replace(",", ".");

        try {
            return new BigDecimal(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato monetário inválido: " + valor, e);
        }
    }
    public static String formataBigDecimalParaValorMonetario(BigDecimal valor) {
        Locale locale = new Locale("pt", "BR");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);

        return format.format(valor);
    }
    
    public static String formataIntParaString(int numero) {
        DecimalFormat format = new DecimalFormat("#,###");

        return format.format(numero);
    }
	
	public static int formataStringParaInt(String valor) {
		return (int)Float.parseFloat(valor);
	}
	
	public static List<Integer> formataListStringParaListInt(List<String> valores) {
		List<Integer> listaValoresConvertidos = new ArrayList<>();
		valores.forEach(x -> listaValoresConvertidos.add((int)Float.parseFloat(x)));
		
		return listaValoresConvertidos;
	}
	
	private static Scanner scanner = new Scanner(System.in);

    public static int recebeInt() {
        int retorno = 0;
        boolean validou = false;

        while (!validou) {
            try {
                retorno = scanner.nextInt();
                validou = true;
            } catch (InputMismatchException ex) {
                System.out.println("Entrada inválida, por favor digite o número corretamente.");
                scanner.next();
            }
        }

        return retorno;
    }

    public static int recebeIntComLimite(int limite) {
        int retorno;
        do {
            retorno = recebeInt();
            if (retorno < 1 || retorno > limite) 
                System.out.println("Digite um número de 1 a " + limite + ".");

        } while (retorno < 1 || retorno > limite);

        return retorno;
    }
}

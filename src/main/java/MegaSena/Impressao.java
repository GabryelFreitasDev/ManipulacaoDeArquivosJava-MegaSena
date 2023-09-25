package MegaSena;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Impressao {
	private static int contador = 1;
	private static Scanner scanner = new Scanner(System.in);
	
	protected static void imprimeQuantosConcursosNaoHouveApostadorQueAcertou6Dezenas(Leitor leitor) {
		System.out.println(leitor.getQuantosConcursosNaoHouveApostadorQueAcertou6Dezenas());
	}
	
	protected static void imprimeQuantidadeVezesNumeroFoiSorteado(Leitor leitor, int numero) {
		if(numero != 0)
			leitor.getQuantidadeVezesNumeroFoiSorteado().stream().filter(x -> x.getKey() == numero).forEach(numeros -> {
			    System.out.println("Numero: "+ numeros.getKey() +" - Quantidade: " + numeros.getValue());
			});
		else 
			leitor.getQuantidadeVezesNumeroFoiSorteado().forEach(numeros -> {
			    System.out.println("Numero: "+ numeros.getKey() +" - Quantidade: " + numeros.getValue());
			});
	}
	
	protected static void imprimeMenorValorPagoParaApostasComXDezenasSorteadas(Leitor leitor, int quantidadeDezenas) {
		System.out.println("Menor valor pago para apostas com "+ quantidadeDezenas +" dezenas sorteadas: " + leitor.getMaiorMenorValorPagoParaApostasComXDezenasSorteadas(quantidadeDezenas, false));
	}
	
	protected static void imprimeMaiorValorPagoParaApostasComXDezenasSorteadas(Leitor leitor, int quantidadeDezenas) {
		System.out.println("Maior valor pago para apostas com "+ quantidadeDezenas +" dezenas sorteadas: "+ leitor.getMaiorMenorValorPagoParaApostasComXDezenasSorteadas(quantidadeDezenas, true));
	}
	
	protected static void imprimeQuantidadeGanhadoresComXDezenas(Leitor leitor, int quantidadeDezenas) {
		System.out.println("Quantidades ganhadores "+ quantidadeDezenas +" acertos: " + leitor.getQuantidadeGanhadoresComXDezenas(quantidadeDezenas));
	}
	
	protected static List<Integer> recebeDezenasParaSorteio(Leitor leitor) {
		List<Integer> dezenas = new ArrayList<>();
		
		int valor = 0;
		System.out.println("Digite as 6 dezenas: (1 a 60)");
		System.out.println("Dezena 1:");
		valor = scanner.nextInt();
		dezenas.add(valor);
		System.out.println("Dezena 2:");
		valor = scanner.nextInt();
		dezenas.add(valor);
		System.out.println("Dezena 3:");
		valor = scanner.nextInt();
		dezenas.add(valor);
		System.out.println("Dezena 4:");
		valor = scanner.nextInt();
		dezenas.add(valor);
		System.out.println("Dezena 5:");
		valor = scanner.nextInt();
		dezenas.add(valor);
		System.out.println("Dezena 6:");
		valor = scanner.nextInt();
		dezenas.add(valor);
		
		return dezenas;
	}
	
	protected static void imprimeDezenasJaSorteadas(Leitor leitor, List<Integer> dezenas) {
		List<Dados> ConcursosDezenasSorteadas = leitor.getDezenasJaSorteadas(dezenas);
		
		if(ConcursosDezenasSorteadas != null && ConcursosDezenasSorteadas.size() > 0) {
			ConcursosDezenasSorteadas.forEach(x -> {
				System.out.println("As suas dezenas já foram sorteadas no(s) seguinte(s) consurso(s)");
				System.out.println("Concurso: " + x.getConcurso());
				System.out.println("Data Sorteio: " + x.getDataSorteio());
			});
		}
		else{
			System.out.println("As suas dezenas ainda não foram sorteadas! ");
		}
	}
	
	protected static void imprimeDezenasAleatoriasParaSorteio(Leitor leitor) {
		List<Integer> DezenasAleatoriasParaSorteio = leitor.getDezenasAleatoriasParaSorteio();
		contador = 1;
		DezenasAleatoriasParaSorteio.forEach(x -> {
			System.out.println("Dezena "+ contador + ": " + x);
			contador++;
		});
		imprimeDezenasJaSorteadas(leitor, DezenasAleatoriasParaSorteio);
	}
}

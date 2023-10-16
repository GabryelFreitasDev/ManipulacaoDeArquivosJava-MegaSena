package MegaSena;

import java.util.ArrayList;
import java.util.List;

public class Impressao {
	private static Leitor leitor;
	
	public Impressao(Leitor leitor){
		Impressao.leitor = leitor;
	}
	
	protected void imprimeQuantidadeConcursosNaoHouveApostadorQueAcertou6Dezenas() {
		System.out.println("\nQuantidade de concursos em que não houve apostador que acertou as 6 dezenas: " + leitor.getQuantidadeConcursosNaoHouveApostadorQueAcertou6Dezenas() + " concursos");
	}
	
	protected void imprimeQuantidadeVezesNumeroFoiSorteado(int numero) {
		if(numero != 0 && numero >= 1 && numero <= 60)
			leitor.getQuantidadeVezesNumeroFoiSorteado().stream().filter(x -> x.getKey() == numero).forEach(numeros -> {
			    System.out.println("\nNumero "+ numeros.getKey() +": " + numeros.getValue() + " vezes");
			});
		else 
			leitor.getQuantidadeVezesNumeroFoiSorteado().forEach(numeros -> {
			    System.out.println("Numero "+ numeros.getKey() +": " + numeros.getValue() + " vezes");
			});
	}
	
	protected void imprimeMenorValorPagoParaApostasComXDezenasSorteadas(int quantidadeDezenas) {
		System.out.println("\nMenor valor pago para apostas com "+ quantidadeDezenas +" dezenas sorteadas: " 
				+ Util.formataBigDecimalParaValorMonetario(leitor.getMaiorMenorValorPagoParaApostasComXDezenasSorteadas(quantidadeDezenas, false)));
	}
	
	protected void imprimeMaiorValorPagoParaApostasComXDezenasSorteadas(int quantidadeDezenas) {
		System.out.println("\nMaior valor pago para apostas com "+ quantidadeDezenas +" dezenas sorteadas: "
				+ Util.formataBigDecimalParaValorMonetario(leitor.getMaiorMenorValorPagoParaApostasComXDezenasSorteadas(quantidadeDezenas, true)));
	}
	
	protected void imprimeQuantidadeGanhadoresComXDezenas(int quantidadeDezenas) {
		System.out.println("\nQuantidades ganhadores "+ quantidadeDezenas +" acertos: " 
				+ Util.formataIntParaString(leitor.getQuantidadeGanhadoresComXDezenas(quantidadeDezenas)));
	}
	
	protected List<Integer> recebeDezenasParaSorteio() {
		List<Integer> dezenas = new ArrayList<>();
		System.out.println("\nDigite as dezenas que deseja verificar: ");
		for(int dezena = 1; dezena <= 6; dezena++) {
			System.out.print(dezena + "° dezena:");
			dezenas.add(Util.recebeIntComLimite(60));
		}
		
		return dezenas;
	}
	
	protected void imprimeDezenasJaSorteadas(List<Integer> dezenas) {
		imprimeDezenas(dezenas);
		List<Dados> ConcursosDezenasSorteadas = leitor.getDezenasJaSorteadas(dezenas);
		
		if(ConcursosDezenasSorteadas != null && ConcursosDezenasSorteadas.size() > 0) {
			System.out.println("\nParabéns! suas dezenas já foram sorteadas!");
			ConcursosDezenasSorteadas.forEach(x -> {
				System.out.println("Concurso: " + Util.formataStringParaInt(x.getConcurso()));
				System.out.println("Data Sorteio: " + x.getDataSorteio());
			});
		}
		else{
			System.out.println("\nSuas dezenas ainda não foram sorteadas! ");
		}
	}
	
	protected void imprimeDezenasAleatoriasParaSorteio() {
		List<Integer> dezenasAleatoriasParaSorteio = leitor.getDezenasAleatoriasParaSorteio();
		System.out.print("\nDezenas geradas aleatoriamente: ");
		imprimeDezenasJaSorteadas(dezenasAleatoriasParaSorteio);
	}
	
	protected void imprimeTodasAsInformacoes() {
		imprimeQuantidadeConcursosNaoHouveApostadorQueAcertou6Dezenas();
		imprimeQuantidadeVezesNumeroFoiSorteado(0);
		imprimeMenorValorPagoParaApostasComXDezenasSorteadas(4);
		imprimeMenorValorPagoParaApostasComXDezenasSorteadas(5);
		imprimeMenorValorPagoParaApostasComXDezenasSorteadas(6);
		imprimeMaiorValorPagoParaApostasComXDezenasSorteadas(4);
		imprimeMaiorValorPagoParaApostasComXDezenasSorteadas(5);
		imprimeMaiorValorPagoParaApostasComXDezenasSorteadas(6);
		imprimeQuantidadeGanhadoresComXDezenas(4);
		imprimeQuantidadeGanhadoresComXDezenas(5);
		imprimeQuantidadeGanhadoresComXDezenas(6);
	}
	
	private static void imprimeDezenas(List<Integer> dezenas) {
		for (int dezena = 0; dezena < dezenas.size(); dezena++)
		    System.out.print(dezenas.get(dezena) + (dezena < dezenas.size() - 1 ? " - " : ""));
	}
}

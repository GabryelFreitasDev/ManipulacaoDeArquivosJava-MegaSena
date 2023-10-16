package MegaSena;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MegaSena {
	private static final Logger log = LogManager.getLogger(MegaSena.class);
	
	public static void main(String[] args) {
		log.info("Iniciando o sistema...");
		Scanner scanner = new Scanner(System.in);
		Boolean continuar = true;
		try {
			ClassLoader classLoader = MegaSena.class.getClassLoader();
	        InputStream inputStream = classLoader.getResourceAsStream("Mega-Sena.xlsx");
			BufferedInputStream bufMegaSena = new BufferedInputStream(inputStream);
			log.info("Arquivo .xlsx da Mega Sena aberto.");
			
			Leitor leitor = new Leitor(bufMegaSena);
			Impressao impressao = new Impressao(leitor);
			
			log.info("Sistema iniciado.");
			System.out.println("\n** MEGA SENA **");
			while(continuar) {
				System.out.println("\nDigite o número referente da informação que deseja receber:"
						+ "\n1 - A quantidade de vezes que cada número foi sorteado."
						+ "\n2 - Quantos concursos não houve apostador que acertou as 6 dezenas."
						+ "\n3 - Menor valor pago para apostas com 4 acertos."
						+ "\n4 - Menor valor pago para apostas com 5 acertos."
						+ "\n5 - Menor valor pago para apostas com 6 acertos."
						+ "\n6 - Maior valor pago para apostas com 4 acertos."
						+ "\n7 - Maior valor pago para apostas com 5 acertos."
						+ "\n8 - Maior valor pago para apostas com 6 acertos."
						+ "\n9 - Quantidade de ganhadores com 4 dezenas."
						+ "\n10 - Quantidade de ganhadores com 5 dezenas."
						+ "\n11 - Quantidade de ganhadores com 6 dezenas."
						+ "\n12 - Todas as informações acima.");
				
				System.out.println("\nOu referente ao teste que deseja fazer:"
						+ "\n13 - Digitar minhas dezenas e verificar se a combinação já foi sorteada."
						+ "\n14 - Gerar dezenas aleatórias e verificar se a combinação já foi sorteada.");
				
				int escolha = Util.recebeInt();
				
				switch(escolha) {
					case 1:
						System.out.println("\nComo deseja prosseguir:"
								+ "\n1 - Imprima a quantidade de sorteios de todos os números."
								+ "\n2 - De um número específico.");
						int escolhaQuantidadeNumeros = Util.recebeIntComLimite(2);
						switch(escolhaQuantidadeNumeros) {
						case 1:
							impressao.imprimeQuantidadeVezesNumeroFoiSorteado(0);
							break;
						case 2:
							System.out.println("\nDigite o número que deseja saber:");
							int numeroParaQuantidade = Util.recebeIntComLimite(60);
							impressao.imprimeQuantidadeVezesNumeroFoiSorteado(numeroParaQuantidade);
							break;
						}
						break;
					case 2:
						impressao.imprimeQuantidadeConcursosNaoHouveApostadorQueAcertou6Dezenas();
						break;
					case 3:
						impressao.imprimeMenorValorPagoParaApostasComXDezenasSorteadas(4);
						break;
					case 4:
						impressao.imprimeMenorValorPagoParaApostasComXDezenasSorteadas(5);
						break;
					case 5:
						impressao.imprimeMenorValorPagoParaApostasComXDezenasSorteadas(6);
						break;
					case 6:
						impressao.imprimeMaiorValorPagoParaApostasComXDezenasSorteadas(4);
						break;
					case 7:
						impressao.imprimeMaiorValorPagoParaApostasComXDezenasSorteadas(5);
						break;
					case 8:
						impressao.imprimeMaiorValorPagoParaApostasComXDezenasSorteadas(6);
						break;
					case 9:
						impressao.imprimeQuantidadeGanhadoresComXDezenas(4);
						break;
					case 10:
						impressao.imprimeQuantidadeGanhadoresComXDezenas(5);
						break;
					case 11:
						impressao.imprimeQuantidadeGanhadoresComXDezenas(6);
						break;
					case 12:
						impressao.imprimeTodasAsInformacoes();
						break;
					case 13:
						List<Integer> dezenas = impressao.recebeDezenasParaSorteio();
						impressao.imprimeDezenasJaSorteadas(dezenas);
						break;
					case 14:
						impressao.imprimeDezenasAleatoriasParaSorteio();
						break;
					default:
						log.info("\nSistema encerrado.");
						continuar = false;
						scanner.close();
						break;
				}
			}
		}
		catch(Exception ex) {
			log.debug("\nOcorreu um erro: " +  ex.getMessage());
			scanner.close();
		}
	}

}

package MegaSena;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;

public class MegaSena {
	//private static final Logger log = LogManager.getLogger(MegaSena.class);
	
	/* Melhorias
	 * Estudar forma melhor de usar o File
	 * Estudar uma forma de melhorar os números e as casas decimais
	 * Validar entradas
	 * Usar os logs
	 * Arrumar impressão do 1 
	 * Melhorar a do 2
	 * Verificação para imprimir menu novamente ou sair
	 * Melhorar mensagens do 13 e 14
	 * */	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		File xlsxMegaSena = new File("C:/Users/Gabryel/Downloads/Mega-Sena.xlsx");
		Boolean continuar = true;
		try {
			BufferedInputStream bufMegaSena = new BufferedInputStream(new FileInputStream(xlsxMegaSena));
			Leitor leitor = new Leitor(bufMegaSena);
			System.out.println("** MEGA SENA **");
			while(continuar) {
				System.out.println("\nDigite o número referente da informação que deseja receber:"
						+ "\n1 - Quantidade de concursos em que não houve acertador de 6 dezenas."
						+ "\n2 - Quantidade de vezes em que os números foram sorteados."
						+ "\n3 - Menor valor pago para apostas com 4 acertos."
						+ "\n4 - Menor valor pago para apostas com 5 acertos."
						+ "\n5 - Menor valor pago para apostas com 6 acertos."
						+ "\n6 - Maior valor pago para apostas com 4 acertos."
						+ "\n7 - Maior valor pago para apostas com 5 acertos."
						+ "\n8 - Maior valor pago para apostas com 6 acertos."
						+ "\n9 - Quantidade de ganhadores com 4 dezenas."
						+ "\n10 - Quantidade de ganhadores com 5 dezenas."
						+ "\n11 - Quantidade de ganhadores com 6 dezenas."
						+ "\n12 - Todas as informações acima");
				
				System.out.println("\nOu referente ao teste que deseja fazer:"
						+ "\n13 - Digitar minhas dezenas e verificar se a combinação já foi sorteada."
						+ "\n14 - Gerar dezenas aleatórias e verificar se a combinação já foi sorteada.");
				
				int escolha = scanner.nextInt();
				switch(escolha) {
					case 1:
						Impressao.imprimeQuantosConcursosNaoHouveApostadorQueAcertou6Dezenas(leitor);
						break;
					case 2:
						System.out.println("\nComo deseja prosseguir:"
								+ "\n1 - Imprima a quantidade de sorteio de todos os números."
								+ "\n2 - De um número específico.");
						int escolhaQuantidadeNumeros = scanner.nextInt();
						switch(escolhaQuantidadeNumeros) {
						case 1:
							Impressao.imprimeQuantidadeVezesNumeroFoiSorteado(leitor, 0);
							break;
						case 2:
							System.out.println("\nDigite o número que deseja saber:");
							int numeroParaQuantidade = scanner.nextInt();
							Impressao.imprimeQuantidadeVezesNumeroFoiSorteado(leitor, numeroParaQuantidade);
							break;
						}
						break;
					case 3:
						Impressao.imprimeMenorValorPagoParaApostasComXDezenasSorteadas(leitor, 4);
						break;
					case 4:
						Impressao.imprimeMenorValorPagoParaApostasComXDezenasSorteadas(leitor, 5);
						break;
					case 5:
						Impressao.imprimeMenorValorPagoParaApostasComXDezenasSorteadas(leitor, 6);
						break;
					case 6:
						Impressao.imprimeMaiorValorPagoParaApostasComXDezenasSorteadas(leitor, 4);
						break;
					case 7:
						Impressao.imprimeMaiorValorPagoParaApostasComXDezenasSorteadas(leitor, 5);
						break;
					case 8:
						Impressao.imprimeMaiorValorPagoParaApostasComXDezenasSorteadas(leitor, 6);
						break;
					case 9:
						Impressao.imprimeQuantidadeGanhadoresComXDezenas(leitor, 4);
						break;
					case 10:
						Impressao.imprimeQuantidadeGanhadoresComXDezenas(leitor, 5);
						break;
					case 11:
						Impressao.imprimeQuantidadeGanhadoresComXDezenas(leitor, 6);
						break;
					case 12:
						Impressao.imprimeQuantosConcursosNaoHouveApostadorQueAcertou6Dezenas(leitor);
						Impressao.imprimeQuantidadeVezesNumeroFoiSorteado(leitor, 0);
						Impressao.imprimeMenorValorPagoParaApostasComXDezenasSorteadas(leitor, 4);
						Impressao.imprimeMenorValorPagoParaApostasComXDezenasSorteadas(leitor, 5);
						Impressao.imprimeMenorValorPagoParaApostasComXDezenasSorteadas(leitor, 6);
						Impressao.imprimeMaiorValorPagoParaApostasComXDezenasSorteadas(leitor, 4);
						Impressao.imprimeMaiorValorPagoParaApostasComXDezenasSorteadas(leitor, 5);
						Impressao.imprimeMaiorValorPagoParaApostasComXDezenasSorteadas(leitor, 6);
						Impressao.imprimeQuantidadeGanhadoresComXDezenas(leitor, 4);
						Impressao.imprimeQuantidadeGanhadoresComXDezenas(leitor, 5);
						Impressao.imprimeQuantidadeGanhadoresComXDezenas(leitor, 6);
						break;
					case 13:
						List<Integer> dezenas = Impressao.recebeDezenasParaSorteio(leitor);
						Impressao.imprimeDezenasJaSorteadas(leitor, dezenas);
						break;
					case 14:
						Impressao.imprimeDezenasAleatoriasParaSorteio(leitor);
						break;
					default:
						continuar = false;
						scanner.close();
						break;
				}
				
			}
		}
		catch(Exception ex) {
			scanner.close();
		}
	}

}

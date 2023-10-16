package MegaSena;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map.Entry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ImpressaoTest {
	private Leitor leitor;
	private Impressao impressao;
	
	@BeforeEach
	public void setUp() {
		ClassLoader classLoader = MegaSena.class.getClassLoader();
	    InputStream inputStream = classLoader.getResourceAsStream("Mega-Sena.xlsx");
		BufferedInputStream bufMegaSena = new BufferedInputStream(inputStream);
		leitor = new Leitor(bufMegaSena);
		impressao = new Impressao(leitor);
	}
	
	@Test
	@DisplayName("imprimeQuantidadeVezesNumeroFoiSorteado() funcionando.")
	void imprimeQuantidadeVezesNumeroFoiSorteado_Funcionando() {
		Assertions.assertDoesNotThrow(() -> impressao.imprimeQuantidadeVezesNumeroFoiSorteado(0));
	}
	
	@Test
	@DisplayName("imprimeMenorValorPagoParaApostasComXDezenasSorteadas() funcionando.")
	void imprimeMenorValorPagoParaApostasComXDezenasSorteadas_Funcionando() {
		Assertions.assertDoesNotThrow(() -> impressao.imprimeMenorValorPagoParaApostasComXDezenasSorteadas(4));
		Assertions.assertDoesNotThrow(() -> impressao.imprimeMenorValorPagoParaApostasComXDezenasSorteadas(5));
		Assertions.assertDoesNotThrow(() -> impressao.imprimeMenorValorPagoParaApostasComXDezenasSorteadas(6));
	}
	
	@Test
	@DisplayName("imprimeMaiorValorPagoParaApostasComXDezenasSorteadas() funcionando.")
	void imprimeMaiorValorPagoParaApostasComXDezenasSorteadas_Funcionando() {
		Assertions.assertDoesNotThrow(() -> impressao.imprimeMaiorValorPagoParaApostasComXDezenasSorteadas(4));
		Assertions.assertDoesNotThrow(() -> impressao.imprimeMaiorValorPagoParaApostasComXDezenasSorteadas(5));
		Assertions.assertDoesNotThrow(() -> impressao.imprimeMaiorValorPagoParaApostasComXDezenasSorteadas(6));
	}
	
	
	@Test
	@DisplayName("imprimeQuantidadeGanhadoresComXDezenas_4Dezenas_Funcionando(4) retornou dados")
	void imprimeQuantidadeGanhadoresComXDezenas_4Dezenas_Funcionando() {
		Assertions.assertDoesNotThrow(() -> impressao.imprimeQuantidadeGanhadoresComXDezenas(4));
		Assertions.assertDoesNotThrow(() -> impressao.imprimeQuantidadeGanhadoresComXDezenas(5));
		Assertions.assertDoesNotThrow(() -> impressao.imprimeQuantidadeGanhadoresComXDezenas(6));
	}
	
	@Test
	@DisplayName("imprimeDezenasJaSorteadas() funcionando.")
	void imprimeDezenasJaSorteadas_Funcionando() {
		List<Integer> listDezenasSorteada = List.of(4, 5, 30, 33, 41, 52);
		List<Integer> listDezenasNaoSorteada = List.of(1, 1, 1, 1, 1, 1);
		Assertions.assertDoesNotThrow(() -> impressao.imprimeDezenasJaSorteadas(listDezenasSorteada));
		Assertions.assertDoesNotThrow(() -> impressao.imprimeDezenasJaSorteadas(listDezenasNaoSorteada));
	}
	
	@Test
	@DisplayName("imprimeDezenasAleatoriasParaSorteio() funcionando.")
	void imprimeDezenasAleatoriasParaSorteio_Funcionando() {
		Assertions.assertDoesNotThrow(() -> impressao.imprimeDezenasAleatoriasParaSorteio());
	}
	
	@Test
	@DisplayName("imprimeTodasAsInformacoes() funcionando.")
	void imprimeTodasAsInformacoes_Funcionando() {
		Assertions.assertDoesNotThrow(() -> impressao.imprimeTodasAsInformacoes());
	}
	
}

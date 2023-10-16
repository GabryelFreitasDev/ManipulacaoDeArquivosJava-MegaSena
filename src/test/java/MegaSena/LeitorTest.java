package MegaSena;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map.Entry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LeitorTest {
	private Leitor leitor;
	
	@BeforeEach
	public void setUp() {
		ClassLoader classLoader = MegaSena.class.getClassLoader();
	    InputStream inputStream = classLoader.getResourceAsStream("Mega-Sena.xlsx");
		BufferedInputStream bufMegaSena = new BufferedInputStream(inputStream);
		leitor = new Leitor(bufMegaSena);
	}
	
	@Test
	@DisplayName("getQuantidadeVezesNumeroFoiSorteado() retornou dados")
	void getQuantidadeVezesNumeroFoiSorteado_RetornouDados() {
		List<Entry<Integer, Integer>> quantidadeVezesNumeroFoiSorteado = leitor.getQuantidadeVezesNumeroFoiSorteado();
		Assertions.assertTrue(quantidadeVezesNumeroFoiSorteado != null && quantidadeVezesNumeroFoiSorteado.size() > 0);
	}
	
	@Test
	@DisplayName("getQuantidadeVezesNumeroFoiSorteado() retornou TODOS os dados")
	void getQuantidadeVezesNumeroFoiSorteado_RetornouTodosOsDados() {
		List<Entry<Integer, Integer>> quantidadeVezesNumeroFoiSorteado = leitor.getQuantidadeVezesNumeroFoiSorteado();
		Assertions.assertEquals(quantidadeVezesNumeroFoiSorteado.size(), 60);
	}
	
	@Test
	@DisplayName("getQuantidadeConcursosNaoHouveApostadorQueAcertou6Dezenas() retornou TODOS os dados")
	void getQuantidadeConcursosNaoHouveApostadorQueAcertou6Dezenas_NotNull() {
		Assertions.assertNotNull(leitor.getQuantidadeConcursosNaoHouveApostadorQueAcertou6Dezenas());
	}
	
	@Test
	@DisplayName("getMaiorMenorValorPagoParaApostasComXDezenasSorteadas_MenorDezenas() retornou dados")
	void getMaiorMenorValorPagoParaApostasComXDezenasSorteadas_MenorDezenas_NotNull() {
		Assertions.assertNotNull(leitor.getMaiorMenorValorPagoParaApostasComXDezenasSorteadas(4, false), "4 dezenas não retornou dados");
		Assertions.assertNotNull(leitor.getMaiorMenorValorPagoParaApostasComXDezenasSorteadas(5, false), "5 dezenas não retornou dados");
		Assertions.assertNotNull(leitor.getMaiorMenorValorPagoParaApostasComXDezenasSorteadas(6, false), "6 dezenas não retornou dados");
	}
	
	@Test
	@DisplayName("getMaiorMenorValorPagoParaApostasComXDezenasSorteadas_MaiorDezenas() retornou dados")
	void getMaiorMenorValorPagoParaApostasComXDezenasSorteadas_MaiorDezenas_NotNull() {
		Assertions.assertNotNull(leitor.getMaiorMenorValorPagoParaApostasComXDezenasSorteadas(4, true), "4 dezenas não retornou dados");
		Assertions.assertNotNull(leitor.getMaiorMenorValorPagoParaApostasComXDezenasSorteadas(5, true), "5 dezenas não retornou dados");
		Assertions.assertNotNull(leitor.getMaiorMenorValorPagoParaApostasComXDezenasSorteadas(6, true), "6 dezenas não retornou dados");
	}
	
	@Test
	@DisplayName("getQuantidadeGanhadoresComXDezenas() retornou dados")
	void getQuantidadeGanhadoresComXDezenas_Dezenas_NotNull() {
		Assertions.assertNotNull(leitor.getQuantidadeGanhadoresComXDezenas(4), "4 dezenas não retornou dados");
		Assertions.assertNotNull(leitor.getQuantidadeGanhadoresComXDezenas(5), "5 dezenas não retornou dados");
		Assertions.assertNotNull(leitor.getQuantidadeGanhadoresComXDezenas(6), "6 dezenas não retornou dados");
	}
	
	@Test
	@DisplayName("getDezenasJaSorteadas() retornou dados")
	void getDezenasJaSorteadas_NotNull() {
		List<Integer> listDezenas = List.of(4, 5, 30, 33, 41, 52);
		Assertions.assertNotNull(leitor.getDezenasJaSorteadas(listDezenas));
	}
	
	@Test
	@DisplayName("getDezenasJaSorteadas() retornou dezena sorteada")
	void getDezenasJaSorteadas_RetornouDezenaSorteada() {
		List<Integer> listDezenas = List.of(4, 5, 30, 33, 41, 52);
		Assertions.assertFalse(leitor.getDezenasJaSorteadas(listDezenas).size() == 0, String.valueOf(leitor.getDezenasJaSorteadas(listDezenas).size()));
	}
	
	@Test
	@DisplayName("getDezenasJaSorteadas() retornou dados")
	void getDezenasAleatoriasParaSorteio_NotNull() {
		Assertions.assertNotNull(leitor.getDezenasAleatoriasParaSorteio());
	}
	
	@Test
	@DisplayName("getDezenasJaSorteadas() retornou dezenas aleatorias")
	void getDezenasAleatoriasParaSorteio_RetornouDezenasAleatorias() {
		Assertions.assertTrue(leitor.getDezenasAleatoriasParaSorteio().size() == 6);
	}

}

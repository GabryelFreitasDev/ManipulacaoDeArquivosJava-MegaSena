package MegaSena;

import java.io.BufferedInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Leitor {
	//private final Logger log = LogManager.getLogger(Leitor.class);
	private BufferedInputStream bufXlsxMegaSena;
	private List<Dados> listaDados = new ArrayList<>();
	private int quantidadeGanhadores = 0;
	private Random random = new Random();
	
	public Leitor(BufferedInputStream bufXlsxMegaSena) {
		this.bufXlsxMegaSena = bufXlsxMegaSena;
		inicializaXlsxMegaSena();
	}
	
	public void inicializaXlsxMegaSena() {
		 try {
			 	XSSFWorkbook workBook = new XSSFWorkbook(bufXlsxMegaSena);
	            Sheet sheet = workBook.getSheetAt(0);
	            
	            //log.info("Aberto arquivo .xlsx da Mega Sena.\nSerá iniciada a leitura de registro a registro");
	            Iterator<?> linhas = sheet.rowIterator();
	            boolean primeiraLinha = true;
	            while (linhas.hasNext()) {
	            	XSSFRow linha = (XSSFRow) linhas.next();
	                Iterator<?> celulas = linha.cellIterator();
	                if (primeiraLinha) {
	                    primeiraLinha = false;
	                    continue; 
	                }
	                
	                Dados dado = new Dados();
	                
	                Map<Integer, Consumer<String>> colunaParaAtributo = new HashMap<>();
	                colunaParaAtributo.put(0, value -> dado.setConcurso(value));
	                colunaParaAtributo.put(1, value -> dado.setDataSorteio(value));
	                colunaParaAtributo.put(8, value -> dado.setGanhadores6Acertos(value));
	                colunaParaAtributo.put(9, value -> dado.setCidadeUF(value));
	                colunaParaAtributo.put(10, value -> dado.setRateio6Acertos(value));
	                colunaParaAtributo.put(11, value -> dado.setGanhadores5Acertos(value));
	                colunaParaAtributo.put(12, value -> dado.setRateio5Acertos(value));
	                colunaParaAtributo.put(13, value -> dado.setGanhadores4Acertos(value));
	                colunaParaAtributo.put(14, value -> dado.setRateio4Acertos(value));
	                colunaParaAtributo.put(15, value -> dado.setAcumulado6Acertos(value));
	                colunaParaAtributo.put(16, value -> dado.setArrecadacaoTotal(value));
	                colunaParaAtributo.put(17, value -> dado.setEstimativaPremio(value));
	                colunaParaAtributo.put(18, value -> dado.setAcumuladoSorteioMegaDaVirada(value));
	                colunaParaAtributo.put(19, value -> dado.setObservacao(value));
		            
	                List<String> bolas = new ArrayList<>();
	                int posicaoCelula = 0;
	                
	                while (celulas.hasNext()) {
	                	XSSFCell celula = (XSSFCell) celulas.next();
	                    String valorCelula = celula.toString();
	                    
	                    if (posicaoCelula >= 2 && posicaoCelula <= 7)
	                        bolas.add(valorCelula);
	                    else if (colunaParaAtributo.containsKey(posicaoCelula))
	                        colunaParaAtributo.get(posicaoCelula).accept(valorCelula);
	                        
	                    posicaoCelula++;
	                }
	                dado.setBolas(bolas);
	                listaDados.add(dado);
	            }
	            workBook.close();
	            //log.info("Leitura dos dados da Mega Sena concluído com sucesso.");
	        } catch (Exception e) {
	            //JOptionPane.showMessageDialog(null, e);
	            System.out.println(e);
	            //log.debug("Deu erro no arquivo XLS dos Analistas");
	            //log.debug(e.getMessage());
	        }
	}
	
	public List<Entry<Integer, Integer>>  getQuantidadeVezesNumeroFoiSorteado() {
		List<Entry<Integer, Integer>> listaNumeroQuantidade = new ArrayList<>();
		
		listaDados.forEach(dado -> {
		    dado.getBolas().forEach(bola -> {
				int numeroBola = (int) Float.parseFloat(bola);
				if (!listaNumeroQuantidade.stream().anyMatch(x -> x.getKey().equals(numeroBola)))
					listaNumeroQuantidade.add(new AbstractMap.SimpleEntry<>(numeroBola, 1));
				else
					listaNumeroQuantidade.stream().filter(x -> x.getKey().equals(numeroBola))
						.forEach(x -> x.setValue(x.getValue() + 1));
		    });
		});
		
		return listaNumeroQuantidade.stream()
		        .sorted((a, b) -> a.getKey().compareTo(b.getKey()))
		        .collect(Collectors.toList());
	}
	
	public int getQuantosConcursosNaoHouveApostadorQueAcertou6Dezenas() {
		return (int)listaDados.stream().filter(x -> (int)Float.parseFloat(x.getGanhadores6Acertos()) == 0).count();
	}
	
	public BigDecimal getMaiorMenorValorPagoParaApostasComXDezenasSorteadas(int QuantidadeDezenas, Boolean maior) {
		List<BigDecimal> valoresPagos = new ArrayList<>();
		switch(QuantidadeDezenas) {
			case 4:
				listaDados.forEach(x -> valoresPagos.add(trataValorBigDecimal(x.getRateio4Acertos())));
				break;
			case 5:
				listaDados.forEach(x -> valoresPagos.add(trataValorBigDecimal(x.getRateio5Acertos())));
				break;
			case 6:
				listaDados.forEach(x -> valoresPagos.add(trataValorBigDecimal(x.getRateio6Acertos())));
				break;
		}			
		
		if(maior)
			return Collections.max(valoresPagos.stream().filter(x -> x.compareTo(BigDecimal.ZERO) > 0).toList());
		else
			return Collections.min(valoresPagos.stream().filter(x -> x.compareTo(BigDecimal.ZERO) > 0).toList());
	}
	
	public int getQuantidadeGanhadoresComXDezenas(int QuantidadeDezenas) {
		quantidadeGanhadores = 0;
		switch(QuantidadeDezenas) {
			case 4:
				listaDados.forEach(x -> quantidadeGanhadores += (trataValorInt(x.getGanhadores4Acertos())));
				break;
			case 5:
				listaDados.forEach(x -> quantidadeGanhadores += (trataValorInt(x.getGanhadores5Acertos())));
				break;
			case 6:
				listaDados.forEach(x -> quantidadeGanhadores += (trataValorInt(x.getGanhadores6Acertos())));
				break;
		}
		
		return quantidadeGanhadores;
	}
	
	public List<Dados> getDezenasJaSorteadas(List<Integer> dezenas) {
		List<Dados> dezenaSorteada = listaDados.stream().filter(x -> trataValoresInt(x.getBolas()).equals(dezenas)).toList();
		return dezenaSorteada;
	}
	
	public List<Integer> getDezenasAleatoriasParaSorteio() {
		List<Integer> dezenasAleatoriasParaSorteio = new ArrayList<>();
		
		dezenasAleatoriasParaSorteio.add(random.nextInt(60) + 1);
		dezenasAleatoriasParaSorteio.add(random.nextInt(60) + 1);
		dezenasAleatoriasParaSorteio.add(random.nextInt(60) + 1);
		dezenasAleatoriasParaSorteio.add(random.nextInt(60) + 1);
		dezenasAleatoriasParaSorteio.add(random.nextInt(60) + 1);
		dezenasAleatoriasParaSorteio.add(random.nextInt(60) + 1);
		
		return dezenasAleatoriasParaSorteio;
	}
	
	private BigDecimal trataValorBigDecimal(String valor) {
		valor = valor.replace("R$", "").replace(".", "").replace(",", ".");
		return BigDecimal.valueOf(Double.valueOf(valor));	
	}
	
	private int trataValorInt(String valor) {
		return (int)Float.parseFloat(valor);
	}
	private List<Integer> trataValoresInt(List<String> valores) {
		List<Integer> listaValoresConvertidos = new ArrayList<>();
		valores.forEach(x -> listaValoresConvertidos.add((int)Float.parseFloat(x)));
		
		return listaValoresConvertidos;
	}
}

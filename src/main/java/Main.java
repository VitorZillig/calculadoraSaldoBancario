import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Path caminhoOperacoes = Paths.get("operac.csv");
        try {
        List<String> arquivo = new ArrayList<>(Files.readAllLines(caminhoOperacoes));
        String cabecalho = arquivo.get(0);
        List<String> arrayCategorias= new ArrayList<String>(Arrays.asList(cabecalho.split(",")));
        List<ContaBancaria> contasBancarias = new ArrayList<>();
        Map<String,Set<Operacao>> mapPorId = new HashMap<>();
            for (int i = 1; i < arquivo.size(); i++) {
                String linha = arquivo.get(i);
                List<String> linhaArray= new ArrayList<>(Arrays.asList(linha.split(",")));
                Map<String,String> linhaMap = new HashMap<>();
                for (int j = 0; j < linhaArray.size(); j++) {
                    linhaMap.put(arrayCategorias.get(j),linhaArray.get(j));
                }

                ContaBancaria contaBancaria = new ContaBancaria(linhaMap.get("ID_DA_CONTA"),linhaMap.get("NOME_DO_BANCO"),linhaMap.get("NUMERO_DA_AGENCIA"),linhaMap.get("NUMERO_DA_CONTA"));
                Operacao operacao = new Operacao(linhaMap.get("DATAHORAOPERACAO"),linhaMap.get("OPERADOR"),linhaMap.get("TIPO"),linhaMap.get("VALOR"),contaBancaria);

                if(!mapPorId.containsKey(linhaMap.get("ID_DA_CONTA"))) {
                    contaBancaria.adicionarOperacao(operacao);
                    mapPorId.put(linhaMap.get("ID_DA_CONTA"), contaBancaria.getOperacoes());
                    contasBancarias.add(contaBancaria);
                } else {
                    mapPorId.get(linhaMap.get("ID_DA_CONTA")).add(operacao);
                 }

            }
            for(ContaBancaria conta:contasBancarias){
                conta.getExtratoBancario();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

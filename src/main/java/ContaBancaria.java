import lombok.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ContaBancaria implements OperacoesContaBancaria{
    Double saldo = 0d;
    String banco;
    String id;
    String agencia;
    String conta;
    Set<Operacao> operacoes = new TreeSet<>();

    public ContaBancaria(String id, String banco,String agencia,String conta){
        this.id = id;
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContaBancaria)) return false;
        ContaBancaria that = (ContaBancaria) o;
        return getSaldo().equals(that.getSaldo()) && getBanco().equals(that.getBanco()) && getId().equals(that.getId()) && getAgencia().equals(that.getAgencia()) && getConta().equals(that.getConta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSaldo(), getBanco(), getId(), getAgencia(), getConta());
    }
    @Override
    public void getExtratoBancario() throws IOException {
        getSaldoTotal();
        StringBuilder sb = new StringBuilder();
        sb.append("ID da conta: "+this.getId()+"\n")
                .append("Banco: "+this.getBanco()+"\n")
                .append("Conta: "+this.getConta()+"\n")
                .append("Agência: "+this.getAgencia()+"\n")
                .append("SALDO: R$ "+this.getSaldo()+"\n");
        File file = new File("Extrato - "+this.id+".txt");
        FileUtils.write(file, sb,true);
        FileUtils.writeLines(file,getOperacoes(),true);
    }

    @Override
    public void adicionarOperacao(Operacao operacao) {
        this.operacoes.add(operacao);
    }

    @Override
    public void getSaldoTotal() {
        for(Operacao operacao:getOperacoes()){
            double valor = Double.parseDouble(operacao.getValor());
            if(operacao.getTipo().equalsIgnoreCase("SAQUE")){
                saldo-=valor;
            } else {
                saldo+=valor;
            }
    }




}
}

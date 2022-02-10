import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Operacao implements Comparable<Operacao> {
    String dataHoraOperacao;
    String operador;
    String tipo;
    String valor;
    ContaBancaria contaBancaria;

    public String getDataHoraOperacao(){
        return this.dataHoraOperacao.replace("T", " ");
    }

    @SneakyThrows
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operacao)) return false;
        Operacao operacao = (Operacao) o;
        return getDataHoraOperacao().equals(operacao.getDataHoraOperacao()) && getOperador().equals(operacao.getOperador()) && getTipo().equals(operacao.getTipo()) && getValor().equals(operacao.getValor()) && getContaBancaria().equals(operacao.getContaBancaria());
    }

    @SneakyThrows
    @Override
    public int hashCode() {
        return Objects.hash(getDataHoraOperacao(), getOperador(), getTipo(), getValor(), getContaBancaria());
    }

    @Override
    public int compareTo(Operacao o) {
        Integer compareDataHoraOperacao = this.getDataHoraOperacao().compareTo(o.getDataHoraOperacao());
        if(compareDataHoraOperacao!=0){
            return compareDataHoraOperacao;
        }
        Integer compareOperador = this.getOperador().compareTo(o.getOperador());
        if(compareOperador!=0){
            return compareOperador;
        }
        Integer compareTipo = this.getTipo().compareTo(o.getTipo());
        if(compareTipo!=0){
            return compareTipo;
        }
        Integer compareValor = this.getValor().compareTo(o.getValor());
        if(compareValor!=0){
            return compareValor;
        }

        return 0;

    }

@Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[")
                .append("Data e hora da Operação = ").append(this.getDataHoraOperacao()+" ")
                .append("| Operador = ").append(this.getOperador()+" ")
                .append("| Tipo = ").append(this.getTipo()+" ")
                .append("| Valor = R$ ").append(this.getValor()+" ")
                .append("] \n");
        return sb.toString();
}
//    @SneakyThrows
//    @Override
//    public int compareTo(Object o) {
//        Operacao data = (Operacao) o;
//        return this.getDataHoraOperacao().before(data.getDataHoraOperacao())?0:1;
//    }
}

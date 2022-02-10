import java.io.IOException;

public interface OperacoesContaBancaria {
    public void getExtratoBancario() throws IOException;
    public void getSaldoTotal();
    public void adicionarOperacao(Operacao operacao);
}

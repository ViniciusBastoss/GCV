import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
    private Calculadora calculadora;

    @BeforeEach
    void setup(){
        calculadora = new Calculadora();
    }
    @Test
    @DisplayName("#add > Quando Dois Numeros Positivos Deve Retornar Um Numero Positivo")
    void addQuandoDoisNumerosPositivosDeveRetornarUmNumeroPositivo(){
        int result = calculadora.add(3,4);
        Assertions.assertEquals(7, result);
    }

    @Test
    void addQuandoUmNumeroforNegativoEUmNumeroForZero(){
        int result = calculadora.add(-3,0);
        int secondResult = calculadora.add(0,-4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(-3, result),
                () -> Assertions.assertEquals(-4, secondResult)
        );
    }

    @Test
    void addQuandoDoisNumerosForemNegativosRetornaUmNumeroNegativo(){
        int result = calculadora.add(-3, -4);
        Assertions.assertEquals(-7, result);

    }

    @Test
    void addUmPositivoEUmNegativoRetornaUmNumeroPositivo(){
        int result = calculadora.add(-3,4);
        Assertions.assertEquals(1, result);
    }

    @Test
    void addUmNumeroPositivoEUmNegativoRetornaUmNumeroNegativo(){
        int result = calculadora.add(3,-4);
        Assertions.assertEquals(-1, result);
    }

    @Test
    void addQuandoUmNumeroForZeroRetornaOSegundoNumero(){
        int result = calculadora.add(3,0);
        int secondResult = calculadora.add(0, 4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(3,result),
                () -> Assertions.assertEquals(4,secondResult)
        );
    }

    @Test
    void addSedoisNumerosSaoZerosRetornaZero(){
        int result = calculadora.add(0,0);
        Assertions.assertEquals(0, result);
    }

    @Test
    @DisplayName("#divide > Quando o divisor for zero")
    void divideQuandoODivisorForZero(){
        Assertions.assertThrows(ArithmeticException.class, () -> {
            calculadora.divide(5,0);
        });


    }
}


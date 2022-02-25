package uniandes.cupi2.simuladorBancario.mundo;

public class Transaccion {
    /**
     * Tipos de transaccion.
     */
	protected static final int ENTRADA = 0;
	protected static final int SALIDA = 1;


	protected static final int AHORROS = 0;
	protected static final int CORRIENTE = 1;
	protected static final int CDT_INVERSIONES = 2;
	
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	// Valores de referencia
	private int consecutivo;
	private double valor;
	
	// Tipados
	private int cuenta;
	private int tipo;
	
	public Transaccion( int pConsecutivo, double pValor, int pTipo, int pCuenta ) {
		
		this.consecutivo = pConsecutivo;
		this.valor = pValor; 

		this.tipo = pTipo;
		this.cuenta = pCuenta;
		
	}
	
	public int darConsecutivo() {
		return this.consecutivo;
	}

	public double darValor() {
		return this.valor;
	}

	public int darTipoTransaccion() {
		return this.tipo;
	}
	
	public int darTipoCuenta() {
		return this.cuenta;
	}
}

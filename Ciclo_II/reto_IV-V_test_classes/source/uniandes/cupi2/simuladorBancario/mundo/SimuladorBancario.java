/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n1_simuladorBancario
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.simuladorBancario.mundo;
import java.util.ArrayList;

/**
 * Clase que representa el simulador bancario para las tres cuentas de un cliente.
 */
public class SimuladorBancario
{
	
	public static final double INVERSION_MAXIMO = 100000000;
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    private double interesGenerado;
	
    /**
     * Cédula del cliente.
     */
    private String cedula;

    /**
     * Nombre del cliente.
     */
    private String nombre;

    /**
     * Mes actual.
     */
    private int mesActual;

    /**
     * Cuenta corriente del cliente.
     */
    private CuentaCorriente corriente;

    /**
     * Cuenta de ahorros del cliente.
     */
    private CuentaAhorros ahorros;

    /**
     * CDT del cliente.
     */
    private CDT inversion;
    
    /**
     * Transacciones del cliente.
     */
    private ArrayList<Transaccion> transaccion;
    
    private int consecutivo;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa el simulador con la información del cliente. <br>
     * <b>post: </b> El mes fue inicializado en 1, y las tres cuentas (CDT, corriente y de ahorros) fueron inicializadas como vacías. <br>
     * @param pCedula Cédula del nuevo cliente. pCedula != null && pCedula != "".
     * @param pNombre Nombre del nuevo cliente. pNombre != null && pNombre != "".
     */
    public SimuladorBancario( String pCedula, String pNombre )
    {
        // Inicializa los atributos personales del cliente
        nombre = pNombre;
        cedula = pCedula;
        // Inicializa el mes en el 1
        mesActual = 1;
        // Inicializa las tres cuentas en vacío
        corriente = new CuentaCorriente( );
        ahorros = new CuentaAhorros( );
        inversion = new CDT( );
        // Inicializa de las transacciones
        transaccion = new ArrayList<Transaccion>();
        consecutivo = 0;
    }
    

    public double darInteresGenerado() {
    	return interesGenerado + ahorros.darInteresGenerado();
    }
    
    public boolean esPositivo() {
    	return (corriente.darSaldo( ) >= 0 || ahorros.darSaldo( ) >= 0 || inversion.calcularValorPresente( this.mesActual ) >= 0);
    }
    
    public boolean esCero() {
    	return consecutivo >= 0;
    }
    
    public boolean esNull() {
        return (corriente != null && ahorros != null && inversion != null && transaccion != null);
    }
    
    public void verificarInvariante() {
    	assert !(darInteresGenerado() > INVERSION_MAXIMO): "ERROR: SE SUPERÓ EL MONTO MÁXIMO DE INVERSIÓN";
    	assert esPositivo();
    	assert esNull();
    	assert esCero();
    }


    /**
     * Retorna el nombre del cliente.
     * @return Nombre del cliente.
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna la cédula del cliente.
     * @return Cédula del cliente.
     */
    public String darCedula( )
    {
        return cedula;
    }

    /**
     * Retorna la cuenta corriente del cliente.
     * @return Cuenta corriente del cliente.
     */
    public CuentaCorriente darCuentaCorriente( )
    {
        return corriente;
    }

    /**
     * Retorna el CDT del cliente.
     * @return CDT del cliente.
     */
    public CDT darCDT( )
    {
        return inversion;
    }

    /**
	 * Retorna la cuenta de ahorros del cliente.
	 * @return Cuenta de ahorros del cliente.
	 */
	public CuentaAhorros darCuentaAhorros( )
	{
	    return ahorros;
	}

	/**
     * Retorna el mes en el que se encuentra la simulación.
     * @return Mes actual.
     */
    public int darMesActual( )
    {
        return mesActual;
    }

    /**
     * Calcula el saldo total de las cuentas del cliente.
     * @return Saldo total de las cuentas del cliente.
     */
    public double calcularSaldoTotal( )
    {
    	this.verificarInvariante();
        return corriente.darSaldo( ) + ahorros.darSaldo( ) + inversion.calcularValorPresente( mesActual );
    }
    
    public void actualizarTransacciones(double pMonto, int tipoEntrada, int tipoTransaccion) {
    	this.verificarInvariante();
    	
    	int tipo = 0;
		switch(tipoEntrada) {
			case 1: { tipo = Transaccion.SALIDA; break; }
			case 0: { tipo = Transaccion.ENTRADA; break; }
		}
		
		int nuevaCuenta = 0;
		switch(tipoTransaccion) {
			case 0: { nuevaCuenta = Transaccion.AHORROS; break; }
			case 1: { nuevaCuenta = Transaccion.CORRIENTE; break; }
			case 2: { nuevaCuenta = Transaccion.CDT_INVERSIONES; break; }
		}
		
		this.consecutivo ++;
		Transaccion nuevaTransaccion = new Transaccion(consecutivo, pMonto, tipo, nuevaCuenta);
		this.transaccion.add(nuevaTransaccion);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Invierte un monto de dinero en un CDT. <br>
     * <b>post: </b> Invirtió un monto de dinero en un CDT.
     * @param pMonto Monto de dinero a invertir en un CDT. pMonto > 0.
     * @param pInteresMensual Interés del CDT elegido por el cliente.
     */
    public void invertirCDT( double pMonto, String pInteresMensual ) throws Exception
    {
    	double interesMensual;
    	try {
    		interesMensual = Double.parseDouble(pInteresMensual);
    	} catch(Exception e) {
    		interesMensual = Double.parseDouble(pInteresMensual.replace(",", "."));
    	}
    	
    	double pInteres =  interesMensual / 100.0;
    	inversion.invertir( pMonto, pInteres, mesActual );
        
        this.actualizarTransacciones(pMonto, 0, 2);
        
        this.verificarInvariante();
    	
    }

    /**
     * Consigna un monto de dinero en la cuenta corriente. <br>
     * <b>post: </b> Consignó un monto de dinero en la cuenta corriente.
     * @param pMonto Monto de dinero a consignar en la cuenta. pMonto > 0.
     */
    public void consignarCuentaCorriente( double pMonto )
    {
        corriente.consignarMonto( pMonto );
        
        this.actualizarTransacciones(pMonto, 0, 1);
        
    	this.verificarInvariante();
    }

    /**
     * Consigna un monto de dinero en la cuenta de ahorros. <br>
     * * <b>post: </b> Consignó un monto de dinero en la cuenta de ahorros.
     * @param pMonto Monto de dinero a consignar en la cuenta. pMonto > 0.
     */
    public void consignarCuentaAhorros( double pMonto )
    {
        ahorros.consignarMonto( pMonto );
        
        this.actualizarTransacciones(pMonto, 0, 0);
        
    	this.verificarInvariante();
    }

    /**
     * Retira un monto de dinero de la cuenta corriente. <br>
     * <b>pre: </b> La cuenta corriente ha sido inicializada
     * <b>post: </b> Si hay saldo suficiente, entonces este se redujo en el monto especificado.
     * @param pMonto Monto de dinero a retirar de la cuenta. pMonto > 0.
     * @throws Exception 
     */
    public void retirarCuentaCorriente( double pMonto )
    {
    	if(this.corriente.darSaldo() >= pMonto) {
            corriente.retirarMonto( pMonto );
            
            this.actualizarTransacciones(pMonto, 1, 1);
            
        	this.verificarInvariante();
    	}
    }

    /**
     * Retira un monto de dinero de la cuenta de ahorros. <br>
     * <b>post: </b> Se redujo el saldo de la cuenta en el monto especificado.
     * @param pMonto Monto de dinero a retirar de la cuenta. pMonto > 0.
     */
    public void retirarCuentaAhorros( double pMonto )
    {
        ahorros.retirarMonto( pMonto );
        
        this.actualizarTransacciones(pMonto, 1, 0);
        
    	this.verificarInvariante();
    }
    

    /**
     * Avanza en un mes la simulación. <br>
     * <b>post: </b> Se avanzó el mes de la simulación en 1. Se actualizó el saldo de la cuenta de ahorros.
     */
    public void avanzarMesSimulacion( )
    {
        mesActual += 1;
        ahorros.actualizarSaldoPorPasoMes( );
        
        if(this.ahorros.darSaldo() > 0) {
        	this.actualizarTransacciones(this.interesGenerado, 0, 0);
        }
        
        if(this.inversion.darValorInvertido() > 0) {
        	double inversionMes = this.inversion.darValorInvertido() * this.inversion.darInteresMensual();
        	this.actualizarTransacciones(inversionMes, 0, 2);
        }
    }

    /**
     * Cierra el CDT, pasando el saldo a la cuenta corriente. <br>
     * <b>pre: </b> La cuenta corriente y el CDT han sido inicializados. <br>
     * <b>post: </b> El CDT quedó cerrado y con valores en 0, y la cuenta corriente aumentó su saldo en el valor del cierre del CDT.
     */
    public void cerrarCDT( )
    {
        interesGenerado += inversion.darInteresGenerado(mesActual);
        double valorCierreCDT = inversion.cerrar( mesActual );
        corriente.consignarMonto( valorCierreCDT );

        this.actualizarTransacciones(valorCierreCDT, 1, 2);
        
        this.actualizarTransacciones(valorCierreCDT, 0, 1);
    }
    
    /**
     * Retrira el saldo total la cuenta de ahorros, pasandolo a la cuenta corriente. <br>
     * <b>pre: </b> La cuenta corriente y el la cuenta de ahorros han sido inicializados. <br>
     * <b>post: </b> La cuenta de ahorros queda vacia ( con valores en 0 ), y la cuenta corriente aumentó su saldo en el valor del saldo total que tenia la cuenta de ahorros.
     */
    public void pasarAhorrosToCorriente()
    {
    	double cantidad = ahorros.darSaldo();
    	ahorros = new CuentaAhorros();
    	corriente.consignarMonto(cantidad);
    	this.verificarInvariante();
    	
        this.actualizarTransacciones(cantidad, 1, 0);
        
        this.actualizarTransacciones(cantidad, 0, 1);
    }

    /**
     * Avanza la simulción un numero de meses dado por parámetro.
     * @param pMeses numero de meses a avanzar
     * <b>post: </b> Se avanzaron los meses de la simulación. Se actualizaron los saldos.
     */
    public void metodo1( int pMeses )
    {
    	mesActual += pMeses;
    	ahorros.actualizarSaldoMeses(pMeses);
    	
    	for(int i = 0; i < pMeses; i++) {
    		
    		if(this.ahorros.darSaldo() > 0) {
            	this.actualizarTransacciones(this.interesGenerado, 0, 0);
            }
            
            if(this.inversion.darValorInvertido() > 0) {
            	double inversionMes = this.inversion.darValorInvertido() * this.inversion.darInteresMensual();
            	this.actualizarTransacciones(inversionMes, 0, 2);
            }
    	}
    }

    /**
     * Reinicia la simulación.
     * @return interes total generado por la simulación.
     */
    public double metodo2( )
    {	
    	cerrarCDT();
    	this.corriente.cerrarCuenta();
    	
    	double respuesta = interesGenerado + ahorros.darInteresGenerado();
    	
    	this.ahorros.cerrarCuenta();
    	this.interesGenerado = 0;
    	this.consecutivo = 0;
    	this.mesActual = 1;
    	
    	this.transaccion.clear();
    	
        return respuesta;
    }

	public int metodo3(int pTipo, int pCuenta) {
		double valorMaximo = 0;
		int respuesta = 0;
		
		for(Transaccion transaccionActual : this.transaccion) {
			if(transaccionActual.darTipoTransaccion() == pTipo && transaccionActual.darTipoCuenta() == pCuenta) {
				
				if(transaccionActual.darValor() > valorMaximo) {
					valorMaximo = transaccionActual.darValor();
					respuesta = transaccionActual.darConsecutivo();
				}
			}
		}
		return respuesta;
	}
}
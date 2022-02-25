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

/**
 * Clase que representa el simulador bancario para las tres cuentas de un cliente.
 */
public class SimuladorBancario{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

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
     * Interes acumulado en la cuenta de ahorros.
     */
    private double interesAcumuladoAhorros;

    /**
     * Interes acumulado en el CDT.
     */
    private double interesAcumuladoCDT;
    

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa el simulador con la información del cliente. <br>
     * <b>post: </b> El mes fue inicializado en 1, y las tres cuentas (CDT, corriente y de ahorros) fueron inicializadas como vacías. <br>
     * @param pCedula Cédula del nuevo cliente. pCedula != null && pCedula != "".
     * @param pNombre Nombre del nuevo cliente. pNombre != null && pNombre != "".
     */
    public SimuladorBancario( String pCedula, String pNombre )    {
        // Inicializa los atributos personales del cliente
        nombre = pNombre;
        cedula = pCedula;
        inizializar();
    }
    
    public void inizializar() {
        // Inicializa las variables del software
        mesActual = 1;
        interesAcumuladoCDT = 0;
        interesAcumuladoAhorros = 0;
        // Inicializa las tres cuentas en vacío
        corriente = new CuentaCorriente( );
        ahorros = new CuentaAhorros( );
        inversion = new CDT( );
    }

    /**
     * Retorna el nombre del cliente.
     * @return Nombre del cliente.
     */
    public String darNombre( )    {
        return nombre;
    }

    /**
     * Retorna la cédula del cliente.
     * @return Cédula del cliente.
     */
    public String darCedula( )    {
        return cedula;
    }

    /**
     * Retorna la cuenta corriente del cliente.
     * @return Cuenta corriente del cliente.
     */
    public CuentaCorriente darCuentaCorriente( )    {
        return corriente;
    }

    /**
     * Retorna la cuenta de ahorros del cliente.
     * @return Cuenta de ahorros del cliente.
     */
    public CuentaAhorros darCuentaAhorros( )    {
        return ahorros;
    }

    /**
     * Retorna el CDT del cliente.
     * @return CDT del cliente.
     */
    public CDT darCDT( )    {
        return inversion;
    }

    /**
     * Retorna el mes en el que se encuentra la simulación.
     * @return Mes actual.
     */
    public int darMesActual( )    {
        return mesActual;
    }

    /**
     * Calcula el saldo total de las cuentas del cliente.
     * @return Saldo total de las cuentas del cliente.
     */
    public double calcularSaldoTotal( )    {
        return corriente.darSaldo( ) + ahorros.darSaldo( ) + inversion.calcularValorPresente( mesActual );
    }

    /**
     * Invierte un monto de dinero en un CDT. <br>
     * <b>post: </b> Invirtió un monto de dinero en un CDT.
     * @param pMonto Monto de dinero a invertir en un CDT. pMonto > 0.
     * @param pInteresMensual Interés del CDT elegido por el cliente. pInteresMensual > 0.
     */
    public void invertirCDT( double pMonto, double pInteresMensual )    {
        inversion.invertir( pMonto, pInteresMensual, mesActual );
    }

    /**
     * Consigna un monto de dinero en la cuenta corriente. <br>
     * <b>post: </b> Consignó un monto de dinero en la cuenta corriente.
     * @param pMonto Monto de dinero a consignar en la cuenta. pMonto > 0.
     */
    public void consignarCuentaCorriente( double pMonto )    {
        corriente.consignarMonto( pMonto );
    }

    /**
     * Consigna un monto de dinero en la cuenta de ahorros. <br>
     * * <b>post: </b> Consignó un monto de dinero en la cuenta de ahorros.
     * @param pMonto Monto de dinero a consignar en la cuenta. pMonto > 0.
     */
    public void consignarCuentaAhorros( double pMonto )    {
        ahorros.consignarMonto( pMonto );
    }

    /**
     * Retira un monto de dinero de la cuenta corriente. <br>
     * <b>post: </b> Se redujo el saldo de la cuenta en el monto especificado.
     * @param pMonto Monto de dinero a retirar de la cuenta. pMonto > 0.
     */
    public void retirarCuentaCorriente( double pMonto )    {
        corriente.retirarMonto( pMonto );
    }

    /**
     * Retira un monto de dinero de la cuenta de ahorros. <br>
     * <b>post: </b> Se redujo el saldo de la cuenta en el monto especificado.
     * @param pMonto Monto de dinero a retirar de la cuenta. pMonto > 0.
     */
    public void retirarCuentaAhorros( double pMonto )    {
        ahorros.retirarMonto( pMonto );
    }

    /**
     * Avanza en un mes la simulación. <br>
     * <b>post: </b> Se avanzó el mes de la simulación en 1. Se actualizó el saldo de la cuenta de ahorros.
     */
    public void avanzarMesSimulacion( )    {
        mesActual += 1;
        double saldoMesAnterior = this.ahorros.darSaldo();
        ahorros.actualizarSaldoPorPasoMes( );
        this.interesAcumuladoAhorros += this.ahorros.darSaldo() - saldoMesAnterior;
    }

    /**
     * Cierra el CDT, pasando el saldo a la cuenta corriente. <br>
     * <b>pre: </b> La cuenta corriente y el CDT han sido inicializados. <br>
     * <b>post: </b> El CDT quedó cerrado y con valores en 0, y la cuenta corriente aumentó su saldo en el valor del cierre del CDT.
     */
    public void cerrarCDT( )    {
    	double valorInicioCDT = this.inversion.darValorInvertido();
        double valorCierreCDT = inversion.cerrar( mesActual );
        this.interesAcumuladoCDT += valorCierreCDT - valorInicioCDT;
        corriente.consignarMonto( valorCierreCDT );
    }

    /**
     * Avanza la simulción un numero de meses dado por parámetro.
     * @param pMeses numero de meses a avanzar
     * <b>post: </b> Se avanzaron los meses de la simulación. Se actualizaron los saldos.
     */
    public void metodo1( int pMeses )    {
    	double ahorroInteres = this.ahorros.darSaldo()*Math.pow(1+this.ahorros.darInteresMensual(), pMeses);
    	ahorroInteres -= ahorros.darSaldo();
    	this.interesAcumuladoAhorros += ahorroInteres;
    	this.ahorros.consignarMonto(ahorroInteres);
    	this.mesActual += pMeses;
    	this.calcularSaldoTotal();
    }

    /**
     * Reinicia la simulación.
     * @return interes total generado por la simulación.
     */
    public double metodo2( )    {	
    	this.cerrarCDT();
    	double interesAcumunlado = (double) interesAcumuladoAhorros + interesAcumuladoCDT;
        inizializar();
        return interesAcumunlado;
    }
}














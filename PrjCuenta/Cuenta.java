import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Cuenta
{
    private String codCuenta = "cta-";
    private double saldo;
    private String nombreCuentaHabiente;
    private String fechaCreacion; 
    private int cantDepositosRealizados;
    private int cantRetirosExitososRealizados;
    private static int cantCuentasCreadas = 0;

    public Cuenta(String nombreCuentaHabiente, double saldo)
    {
     this.nombreCuentaHabiente = nombreCuentaHabiente;
     this.saldo = saldo;
     this.fechaCreacion = establecerFechaCreacionCuenta();
     cantCuentasCreadas ++; 
     codCuenta += cantCuentasCreadas; 
     cantDepositosRealizados = 0;
     cantRetirosExitososRealizados= 0;
    }
    
    public Cuenta (double saldo) {
        this("Pendiente", saldo);
    }

    private String establecerFechaCreacionCuenta() {
    Date fecha = new Date(System.currentTimeMillis());
    DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    return formatoFecha.format(fecha);
    }
    
    public void setNombreCuentaHabiente (String pNombreCuentaHabiente){
        this.nombreCuentaHabiente = pNombreCuentaHabiente;
    }
    
    public String getCodCuenta(){
        return this.codCuenta;
    }
    
    public double getSaldo(){
        return this.saldo;
    }
    
    public static int getCantCuentasCreadas(){
        return cantCuentasCreadas;
    }
    
    public double depositar (double monto){
        if (monto > 0){
            saldo += monto;
        }
        return saldo;
    }
    
    private boolean validarRetiro (double monto){
        return saldo >= monto;
    }

    public double retirar(double monto){
        if (monto > 0 && validarRetiro(monto)) {
        saldo -= monto;
        }
        return saldo;
    }
    
    public String toString(){
        return " | Codigo Cuenta:" + codCuenta  + " | Nombre de la Cuenta:" + nombreCuentaHabiente + " | Saldo Actual:" + saldo +
        " | Fecha de Creacion:" + fechaCreacion + " | Cantidad de depositos realizados:" + cantDepositosRealizados + 
        " | Cantidad de retiros exitosos realizados:" + cantRetirosExitososRealizados;
    }
}
    
    
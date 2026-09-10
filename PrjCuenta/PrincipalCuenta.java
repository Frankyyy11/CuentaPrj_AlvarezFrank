import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PrincipalCuenta
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int actual = -1; //indice de la cuenta seleccionada
        ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
        
        System.out.println("======================================");
        System.out.println("   CLI de Prueba - Clase Cuenta");
        System.out.println("======================================");
        
        boolean salir = false;
        
        while (!salir){
            System.out.println("\nMenú principal");
            System.out.println("1) Crear Cuenta");
            System.out.println("2) Conocer la cantidad de cuentas creadas");
            System.out.println("3) Listar cuentas");
            System.out.println("4) Seleccionar cuenta actual");
            System.out.println("5) Asignar el nombre del cuenta habiente (de la cuenta actual) ");
            System.out.println("6) Depositar (en la cuenta actual) ");
            System.out.println("7) Retirar (de la cuenta actual)");
            System.out.println("8) Consultar saldo (de la cuenta actual). ");
            System.out.println("9) Consultar estado de la cuenta (actual)");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();
            
            switch (op){
                
                case "1": {//Caso crear cuenta
                    System.out.print("Saldo a depositar: ");
                    String saldoTexto = sc.nextLine().trim();
                    double saldo;
                    try {
                        saldo = Double.parseDouble(saldoTexto);
                    } catch (NumberFormatException e) {
                        System.out.println("Saldo inválido. No se creó la cuenta.");
                        break;
                    }
                    
                    System.out.print ("Nombre de la cuenta: (Enter para dejarlo pendiente)");
                    String nombre = sc.nextLine().trim();
                    
                    Cuenta c;

                    if (nombre.isEmpty()) {
                        c = new Cuenta(saldo);
                    } else {
                        c = new Cuenta(nombre,saldo);
                    }
                    cuentas.add(c);
                    actual = cuentas.size() - 1;
                    System.out.println("Cuenta creada y seleccionado (índice " + actual + ").");
                    break;
                }
                
                case "2":{//Conocer cantidad de cuentas creadas
                    System.out.println("Cantidad de cuentas creadas :" + Cuenta.getCantCuentasCreadas());
                    break;
                }
                
                case "3" :{//Listar Cuentas
                    if (cuentas.isEmpty()){
                        System.out.println (" No hay cuentas creadas");
                    } else {
                        for (int i = 0; i < cuentas.size(); i++) {
                            Cuenta c = cuentas.get(i);
                            System.out.println("Índice " + i + ": " + c.toString());
                        }
                    }
                    break; 
                }
                
                case "4":{//Seleccionar cuenta
                    if (cuentas.isEmpty()){
                        System.out.println("Cree una cuenta primero");
                        break;
                    } else{ 
                        System.out.print("Índice de la cuenta a seleccionar: ");
                        String idxS = sc.nextLine().trim();
                        try {
                            int idx = Integer.parseInt(idxS);
                            if (idx >= 0 && idx < cuentas.size()) {
                                actual = idx;
                                System.out.println("Cuenta índice " + actual + " seleccionado.");
                            } else {
                                System.out.println("Índice fuera de rango.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Índice inválido.");
                        }
                        break;
                    }
                }
                
                case "5" :{
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    String nombre = sc.nextLine().trim();
                    cuentas.get(actual).setNombreCuentaHabiente(nombre);
                    System.out.println ("Se le asigno el nombre : " + nombre + " a la cuenta seleccionada " + actual);
                    break;
                }
                
                case "6":{
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.print("Monto a depositar: ");
                    String montoTexto = sc.nextLine().trim();
                    double monto;
                    try {
                        monto = Double.parseDouble(montoTexto);
                    } catch (NumberFormatException e) {
                        System.out.println("Monto inválido. No se logro depositar.");
                        break;
                    }
                    double nuevoSaldo = cuentas.get(actual).depositar(monto);
                    System.out.println("Depósito exitoso. Nuevo saldo: " + nuevoSaldo);
                    break; 
                
                }
                
                case "7":{
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.print("Monto a retirar: ");
                    String montoTexto = sc.nextLine().trim();
                    double monto;
                    try {
                        monto = Double.parseDouble(montoTexto);
                    } catch (NumberFormatException e) {
                        System.out.println("Monto inválido. No se logro retirar.");
                        break;
                    }
                    
                    double saldoAntes = cuentas.get(actual).getSaldo();
                    double saldoDespues = cuentas.get(actual).retirar(monto);
                    
                    if (saldoDespues < saldoAntes) {
                        System.out.println("Retiro exitoso. Nuevo saldo: " + saldoDespues);
                    } else {
                        System.out.println("Fondos insuficientes. Saldo actual: " + saldoDespues);
                    }
                    break;
                }
                
                case "8" :{
                    if (actual <0 || cuentas.isEmpty()){
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;    
                    }
                    double saldo = cuentas.get(actual).getSaldo();
                    System.out.println("El saldo actual de la cuenta es : " + saldo);
                    break;
                }
                
                case "9" :{
                    if (actual <0 || cuentas.isEmpty()){
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;    
                    }
                    String estadoCuenta = cuentas.get(actual).toString();
                    System.out.println ("El estado actual de la cuenta es:" + estadoCuenta);
                    break;
                }
                
                case "0": {
                        salir = true;
                        System.out.println("¡Hasta luego!");
                        break;
                }
                default:
                System.out.println("Opción inválida.");
}
}
}
}
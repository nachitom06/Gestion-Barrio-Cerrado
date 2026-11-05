# Sistema Gestion Barrio Cerrado "Hogar Segura"

# Descripcion del sistema
Sistema desarrollado en Java para la administracion de barrios cerrados, se pueden gestionar expensas, pagos, reclamos de vecinos, control de acceso, creacion de autorizaciones.

**Version Java**
JDK: Oracle OpenJDK 25 "loom-ea-25"
**IDE Utilizado**
IntelliJ IDEA

**Instrucciones para ejecución**
1. Abrir IntelliJ IDEA.
2. Abrir el repositorio.
3. Fijarse las rutas absolutas de los archivos y de ser necesario modificarlas para que se adapten a tu computadora.
4. En caso de querer utilizar la creacion de autorizaciones  
     Entrar al apartado vistasAutorizaciones ejecute la clase e ingrese segun corresponda los siguientes datos  
        - nombre: String.  
        - documento: String donde pones 8 numeros.  
        - numero de lote: int, representa el numero de lote de los 150 existentes.  
        - franja horaria: String, ejemplo: 0900 a 2100.  
       Tocar boton de registrar autorizacion
     
   En caso de querer utilizar el control de acceso  
     Entrar al apartado VistasAcceso ejecute la clase e ingrese segun corresponda los siguientes datos  
        - documento: String, si es valido y coincide con algun documento dentro de las autorizaciones y esa autorizacion no esta utilizada, el ingreso es valido.  
       Tocar boton de buscar autorizacion
     
   En caso de querer utilizar la creacion de reclamos  
     Entrar al apartado de vistasReclamos ejecute la clase e ingrese segun corresponda los siguientes datos  
        - tipo de reclamo: TipoReclamo SEGURIDAD, MANTENIMIENTO, CONVIVENCIA, ADMINISTRATIVO.  
        - descripcion: String describiendo la situacion.  
       Tocar boton de registrar reclamo
     
   En caso de querer utilizar el gestor de expensas para pagos  
     Entrar al apartado de vistasExpensas ejecute la clase e ingrese segun corresponda los siguientes datos  
        - numero de lote: int, representa el numero de lote de los 150 existentes.  
       Tocar boton de buscar deuda  
       Si tiene una deuda se pasara al siguiente panel donde te muestra los datos de la deuda y se abren los campos  
        - metodo pago: JComboBox, EFECTIVO, DEBITO, CREDITO, BILLETERAVIRTUAL.  
        - importe: int.  
       Tocar boton de pago en cuotas o pago directo segun corresponda.  

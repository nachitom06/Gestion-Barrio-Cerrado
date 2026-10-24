package Clases;

import Interfaces.Notificador;

public class NotificadorMail implements Notificador {

    @Override
    public boolean notificar(Persona persona){
        String mail=persona.getMail();
        System.out.println("Se ha enviado una notificacion al correo: "+mail);
        return true;
    }
}

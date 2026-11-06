package Clases;

public class Persona {
    private String nombre;
    private String apellido;
    private String documento;
    private String telefono;
    private String mail;

    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public Persona(String nombre, String apellido, String documento, String telefono, String mail) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.telefono = telefono;
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

}

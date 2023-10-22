package Modelos;

public class ModeloClient {

    private Integer id;
    private String name;
    private String last_name;
    private String type_document;
    private String number_document;
    private String cell_phone;
    private String email;
    private String status;

    public ModeloClient() {
        // Constructor vacío
    }

    public ModeloClient(Integer id, String name, String last_name, String type_document, String number_document,
            String cell_phone, String email, String status) {
        super();
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.type_document = type_document;
        this.number_document = number_document;
        this.cell_phone = cell_phone;
        this.email = email;
        this.status = status;
    }

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getType_document() {
        return type_document;
    }

    public void setType_document(String type_document) {
        this.type_document = type_document;
    }

    public String getNumber_document() {
        return number_document;
    }

    public void setNumber_document(String number_document) {
        this.number_document = number_document;
    }

    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String data = "[" + this.id;
        data += ", " + this.name;
        data += ", " + this.last_name;
        data += ", " + this.type_document;
        data += ", " + this.number_document;
        data += ", " + this.cell_phone;
        data += ", " + this.email;
        data += ", " + this.status + "]";
        return data;
    }
}

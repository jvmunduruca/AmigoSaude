package br.com.amigosaude.app.amigosaude;

public class Tel {
    private int id;
    private String descricao, telefone;
    private int image;

    public Tel(int id, String descricao, String telefone, int image) {
        this.id = id;
        this.descricao = descricao;
        this.telefone = telefone;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

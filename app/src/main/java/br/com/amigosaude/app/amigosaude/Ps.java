package br.com.amigosaude.app.amigosaude;

/**
 * Created by LAB on 05/06/2018.
 */

public class Ps {

    private int image;
    private String nome,endereco;

    public Ps(int image, String nome, String endereco) {
        this.image = image;
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

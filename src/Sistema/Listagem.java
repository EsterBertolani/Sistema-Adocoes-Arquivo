package Sistema;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Classes.Adocao;
import Classes.Animal;
import Classes.Cachorro;
import Classes.Gato;
import Classes.Outro;

public class Listagem {


    // LISTAR ANIMAIS DISPONÍVEIS

    public static ArrayList<Animal> listarAnimaisDisponiveis(String caminho) {
        ArrayList<Animal> todos = ControleSistema.listarAnimais(caminho);
        ArrayList<Animal> disponiveis = new ArrayList<>();

        for (Animal a : todos) {
            if (!a.isAdotado()) {
                disponiveis.add(a);
            }
        }
        if(disponiveis.isEmpty()) {
        	System.out.println("Nenhum animal disponivel para adoção");
        }
        return disponiveis;
    }

    // LISTAR GATOS

    public static ArrayList<Gato> listarGatosDisponiveis(String caminho) {
        ArrayList<Animal> todos = ControleSistema.listarAnimais(caminho);
        ArrayList<Gato> gatos = new ArrayList<>();

        for (Animal a : todos) {
            if (a instanceof Gato && !a.isAdotado()) {
                gatos.add((Gato) a);
            }
        }
        if(gatos.isEmpty()) {
        	System.out.println("Nenhum Gato disponivel para adoção");
        }
        return gatos;
    }

    // LISTAR CAHORROS

    public static ArrayList<Cachorro> listarCachorrosDisponiveis(String caminho) {
        ArrayList<Animal> todos = ControleSistema.listarAnimais(caminho);
        ArrayList<Cachorro> cachorros = new ArrayList<>();

        for (Animal a : todos) {
            if (a instanceof Cachorro && !a.isAdotado()) {
                cachorros.add((Cachorro) a);
            }
        }
        if(cachorros.isEmpty()) {
        	System.out.println("Nenhum Cachorro disponivel para adoção");
        }
        return cachorros;
    }

    // LISTAR OUTROS

    public static ArrayList<Outro> listarOutrosDisponiveis(String caminho) {
        ArrayList<Animal> todos = ControleSistema.listarAnimais(caminho);
        ArrayList<Outro> outros = new ArrayList<>();

        for (Animal a : todos) {
            if (a instanceof Outro && !a.isAdotado()) {
                outros.add((Outro) a);
            }
        }
        if(outros.isEmpty()) {
        	System.out.println("Nenhum animal classificado como Outros disponivel para adoção");
        }
        return outros;
    }

    // LISTAR ANIMAIS CASTRADOS

    public static ArrayList<Animal> listarAnimaisCastrados(String caminho) {
        ArrayList<Animal> todos = ControleSistema.listarAnimais(caminho);
        ArrayList<Animal> castrados = new ArrayList<>();

        for (Animal a : todos) {
            if (a.isCastrado()) {
                castrados.add(a);
            }
        }
        if(castrados.isEmpty()) {
        	System.out.println("Nenhum animal castrado disponivel para adoção");
        }
        return castrados;
    }

    // LISTAR POR GÊNERO

    public static ArrayList<Animal> listarAnimaisPorGenero(String especie, char genero, String caminho) {
        ArrayList<Animal> todos = ControleSistema.listarAnimais(caminho);
        ArrayList<Animal> filtrados = new ArrayList<>();

        especie = especie.toLowerCase();

        for (Animal a : todos) {
            if (a.getGenero() != genero) {
                continue;
            }

            if ((a instanceof Gato) && ("gato".contains(especie))) {
                filtrados.add(a);

            } else if ((a instanceof Cachorro) && ("cachorro".contains(especie))) {
                filtrados.add(a);

            } else if (a instanceof Outro) {
                Outro outraEspecie = (Outro) a;

                if (outraEspecie.getEspecie().toLowerCase().contains(especie)) {
                    filtrados.add(outraEspecie);
                }
            }
        }
        if(filtrados.isEmpty()) {
        	System.out.println("Nenhum animal do genero selecionado foi encontrado");
        }
        return filtrados;
    }

    // LISTAR ADOÇÕES

    public static ArrayList<Adocao> listarAdocoes(String caminho) {
        ArrayList<Adocao> listaAdocoes = new ArrayList<>();

        try (BufferedReader ler = new BufferedReader(new FileReader(caminho))) {
            String linha;

            while ((linha = ler.readLine()) != null) {
                Adocao a = Adocao.fromCSV(linha);
                listaAdocoes.add(a);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        if(listaAdocoes.isEmpty()) {
        	System.out.println("Nenhuma adoção encontrada");
        }

        return listaAdocoes;

    }

}

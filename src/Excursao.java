import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;


public class Excursao {
    private int codigo;
    private double preco;
    private int max;
    private ArrayList<String> reservas;
    
    
    public Excursao(int codigo) {
        this.codigo = codigo;
        this.preco = 0;
        this.max = 0;
        this.reservas = new ArrayList<>();
    }
    

    public Excursao(int codigo, double preco, int max) {
        if (codigo <= 0 || max <= 0 || preco <= 0) {
            throw new ExcursaoInvalidaException("Código, preço ou máximo de reservas inválidos.");
        }

        this.codigo = codigo;
        this.preco = preco;
        this.max = max;
        this.reservas = new ArrayList<>();
        
    }



    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public double getPreco() {
        return preco;
    }
	public void setPreco(double preco) {
		this.preco = preco;
	}


    public int getMax() {
        return max;
    }
	public void setMax(int max) {
		this.max = max;
	}

	
    public ArrayList<String> getReservas() {
        return reservas;
    }
	public void setReservas(ArrayList<String> reservas) {
		this.reservas = reservas;
	}




	/**
	 * Adiciona uma reserva "cpf/nome"
	 * */
    public void criarReserva(String cpf, String nome) {
        String reserva = cpf + "/" + nome;

		/*
		 * if (reservas.size() >= max) { throw new
		 * LimiteReservasExcedidoException("Limite máximo de reservas atingido para esta excursão."
		 * ); }
		 */

        for (String existente : reservas) {
            String[] parts = existente.split("/");
            String existentName = parts[1];
            if (existentName.equals(nome)) {
                throw new NomeDuplicadoException("O nome já foi reservado para esta excursão.");
            }
        }

        reservas.add(reserva);
        System.out.println("Reserva adicionada com sucesso: " + reserva);
    }



	/**
	 * Remove uma reserva "cpf/nome"
	 * */
    public void cancelarReserva(String cpf, String nome) {
        String reserva = cpf + "/" + nome;

        if (!reservas.contains(reserva)) {
            throw new ReservaNaoEncontradaException("Reserva não encontrada.");
        }

        reservas.remove(reserva);
        System.out.println("Reserva cancelada com sucesso: " + reserva);
    }


    
	/**
	 * Remove todas as reservas do cpf
	 * */
    public void cancelarReserva(String cpf) {
        reservas.removeIf(reserva -> reserva.startsWith(cpf + "/"));
    }



	/**
	 * Retorna as reservas dos cpfs que contém os dígitos (ou retorna todas as reservas caso dígitos seja vazio) 
	 * */
    public ArrayList<String> listarReservasPorCpf(String digitos) {
        ArrayList<String> reservasPorCpf = new ArrayList<>();

        for (String reserva : reservas) {
            if (digitos.isEmpty() || reserva.contains(digitos)) {
                reservasPorCpf.add(reserva);
            }
        }
        return reservasPorCpf;
    }


	/**
	 * Retorna as reservas dos nomes que contém o texto (ou retorna todas as reservas caso texto seja vazio) 
	 * */
    public ArrayList<String> listarReservasPorNome(String texto) {
        ArrayList<String> reservasPorNome = new ArrayList<>();

        for (String reserva : reservas) {
            String[] reservaParts = reserva.split("/");
            String nome = reservaParts[1];
            if (texto.isEmpty() || nome.contains(texto)) {
                reservasPorNome.add(reserva);
            }
        }
        return reservasPorNome;
    }


	/**
	 * Calcular valor total da excursão = preço * qde de reservas
	 * */
    public double calcularValorTotal() {
        return getPreco() * reservas.size();
    }



	/**
	 * Retorna codigo, preço, max e total de reservas 
	 * */
    @Override
    public String toString() {
        return "Excursao " +
                "Codigo: " + codigo +
                ", Preco: " + preco +
                ", Max: " + max +
                ", Total de reservas:" + reservas.size() + reservas;
    }


	/**
	 * Gravar no arquivo “codigo.txt” o preço, max e as reservas
	 * */
    public void salvar() {
        String nomeArquivo = codigo + ".txt";

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(nomeArquivo));
            writer.write("Preço: " + preco);
            writer.newLine();
            writer.write("Máximo de reservas: " + max);
            writer.newLine();

            for (String reserva : reservas) {
                writer.write(reserva);
                writer.newLine();
            }

            System.out.println("Informações salvas no arquivo " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar as informações no arquivo: " + e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.err.println("Erro ao fechar o BufferedWriter: " + e.getMessage());
            }
        }
    }



	/**
	 * Ler do arquivo “codigo.txt” o preço, max e as reservas
	 * */
    public void carregar() {
        String nomeArquivo = codigo + ".txt";

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));
            String linha = reader.readLine();
            preco = Double.parseDouble(linha.split(": ")[1]);

            linha = reader.readLine();
            max = Integer.parseInt(linha.split(": ")[1]);

            reservas.clear();

            while ((linha = reader.readLine()) != null) {
                reservas.add(linha);
            }

            System.out.println("Informações carregadas do arquivo " + nomeArquivo);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar as informações do arquivo: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Erro ao fechar o BufferedReader: " + e.getMessage());
            }
        }
    }


} // Final da class Excursao

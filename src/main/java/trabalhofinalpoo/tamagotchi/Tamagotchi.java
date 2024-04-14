/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package trabalhofinalpoo.tamagotchi;

import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author negreirosdrummer, deyvideugenio, Antony2804, raphassoares
 */

class Tamagotchi2 {
    private final String nome;
    private int fome;
    private int felicidade;
    private int energia;
    private int higiene;
    private int saude;
    private int idade;
    /* Atributos com nome "Cont" são usadas como tolerância para o Tamagotchi.
    Quando um atributo chega ao seu limite (muita fome, muito cansado, etc),
    1 ponto é adicionado à respectiva variável contadora.
    Quando uma delas chega a 5, o Tamagotchi morre
    */
    int fomeCont = 0;
    int felicidadeCont = 0;
    int energiaCont = 0;
    int higieneCont = 0;
    int saudeCont = 0;
    /*
    booleanos para informar aos timers se o Tamagotchi está realizando
    alguma ação (para não exibir mensagens e avisos enquanto
    estiver comendo, dormindo, jogando, etc)
    */
    private boolean comendo;
    private boolean passeando;
    private boolean brincando;
    private boolean limpando;
    private boolean dormindo;
    private boolean medicando;
    private boolean jogando;
    private boolean status;
    private String fase;
    private Timer aniversario;
    private Timer passarTempo;
    private Timer passarDia;
    private int contDias;
    
    // Construtor
    Tamagotchi2(String nome) {
        this.nome = nome;
        fome = 5;
        felicidade = 5;
        energia = 5;
        higiene = 5;
        saude = 5;
        idade = 0;
        fase = "Bebê";
        
        // Timer para chamar o método aniversario() a cada 60 segundos
        this.aniversario = new Timer();
        aniversario.scheduleAtFixedRate(new TimerTask() {
            /* Verifica se o Tamagotchi está realizando alguma ação
            Caso esteja, não chama a função aniversário
            */
            @Override
            public void run() {
                if (!comendo && !passeando && !brincando && !limpando
                        && !dormindo && !medicando && !jogando && !status) {
                    aniversario();
                }
            }
        }, 60000, 60000); // Intervalo de tempo = 60000ms (60 segundos)
        
        // Timer para chamar o método passarDia() a cada 30.04 segundos
        this.passarDia = new Timer();
        passarDia.scheduleAtFixedRate(new TimerTask() {
            /* Verifica se o Tamagotchi está realizando alguma ação
            Caso esteja, não chama a função passarDia
            */
            @Override
            public void run() {
                if (!comendo && !passeando && !brincando && !limpando
                        && !dormindo && !medicando && !jogando && !status) {
                    passarDia();
                }
            }
        }, 30040, 30040); // Intervalo de tempo = 30040ms (30.04 segundos)

        // Timer para chamar o método passarTempo() a cada 20.02 segundos
        this.passarTempo = new Timer();
        passarTempo.scheduleAtFixedRate(new TimerTask() {
            /* Verifica se o Tamagotchi está realizando alguma ação
            Caso esteja, não chama a função passarTempo
            */
            @Override
            public void run() {
                if (!comendo && !passeando && !brincando && !limpando
                        && !dormindo && !medicando && !jogando && !status) {
                    passarTempo();
                }
            }
        }, 20020, 20020); // Intervalo de tempo = 20020ms (20.02 segundos)
    }
    
    // Método para suspender a execução do programa durante um determinado tempo
    // (para dar uma pausa ao exibir mensagens na tela, por exemplo)
    public static void sleep(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Métodos para interagir com o Tamagotchi
    public void alimentar() {
        // Checa se está com fome
        if (this.fome < 2) {
            System.out.println(this.nome + " não está com fome!");
            sleep(1000);
        } else {
            /* booleano para informar aos timers que o Tamagotchi está comendo
            (para não exibir mensagens na tela durante a ação)
            */
            comendo = true;
            System.out.print(this.nome + " está comendo");
            sleep(1000);
            for (int i = 3; i > 0; i--) {
                System.out.print(".");
                sleep(1000);
            }
            System.out.println("\n" + this.nome + " comeu!");
            System.out.println("Fome: -5\n");
            sleep(1000);
            this.fome -= 5;
            /* O método validaStatus verifica se os atributos do Tamagotchi
            estão maiores que 10 ou menores que 0
            */
            validaStatus();
            comendo = false;
            // Redefine a tolerância máxima da fome ao valor inicial
            fomeCont = 0;
        }
    }
    
    public void passear() {
        // Checa se está cansado para passear
        if (this.energia <= 2) {
            System.out.println(this.nome + " está muito cansado para passear...");
        } else {
            /* booleano para informar aos timers que o Tamagotchi está passeando
            (para não exibir mensagens na tela durante a ação)
            */
            passeando = true;
            System.out.print(this.nome + " está passeando");
            sleep(1000);
            for (int i = 3; i > 0; i--) {
                System.out.print(".");
                sleep(1000);
            }
            System.out.println("\n" + this.nome + " passeou!");
            System.out.println("Felicidade: +3\nEnergia: -2\n");
            sleep(1000);
            this.felicidade += 3;
            this.energia -= 2;
            /* O método validaStatus verifica se os atributos do Tamagotchi
            estão maiores que 10 ou menores que 0
            */
            validaStatus();
            passeando = false;
            /* Redefine a tolerância máxima de felicidade e energia aos
            valores iniciais
            */
            felicidadeCont = 0;
            energiaCont = 0;
        }
    }
    
    public void brincar() {
        // Checa se está feliz
        if (this.felicidade > 8) {
            System.out.println(this.nome + " não quer brincar com os amigos!");
            sleep(1000);
        } else {
            /* booleano para informar aos timers que o Tamagotchi está brincando
            (para não exibir mensagens na tela durante a ação)
            */
            brincando = true;
            System.out.print(this.nome + " está brincando com seus amigos");
            sleep(1000);
            for (int i = 3; i > 0; i--) {
                System.out.print(".");
                sleep(1000);
            }
            System.out.println("\n" + this.nome + " brincou com seus amigos!");
            System.out.println("Felicidade: +5\n");
            sleep(1000);
            this.felicidade += 5;
            /* O método validaStatus verifica se os atributos do Tamagotchi
            estão maiores que 10 ou menores que 0
            */
            validaStatus();
            brincando = false;
            // Redefine a tolerância máxima da felicidade ao valor inicial
            felicidadeCont = 0;
        }
    }
    
    public void limpar() {
        // Checa se está sujo
        if (this.higiene > 5) {
            System.out.println(this.nome + " não está sujo!");
            sleep(1000);
        } else {
            /* booleano para informar aos timers que o Tamagotchi está sendo limpo
            (para não exibir mensagens na tela durante a ação)
            */
            limpando = true;
            System.out.print("Limpando " + this.nome);
            sleep(1000);
            for (int i = 3; i > 0; i--) {
                System.out.print(".");
                sleep(1000);
            }
            System.out.println("\n" + this.nome + " está limpo!");
            System.out.println("Higiene: +10\n");
            sleep(1000);
            this.higiene = 10;
            limpando = false;
            // Redefine a tolerância máxima da higiene ao valor inicial
            higieneCont = 0;
        }
    }
    
    public void dormir() {
        // Checa se está cansado
        if (this.energia > 5) {
            System.out.println(this.nome + " não está com sono!");
            sleep(1000);
        } else {
            /* booleano para informar aos timers que o Tamagotchi está dormindo
            (para não exibir mensagens na tela durante a ação)
            */
            dormindo = true;
            System.out.println(this.nome + " está dormindo...");
            sleep(1000);
            for (int i = 10; i > 0; i--) {
                System.out.print("z");
                sleep(1000);
                System.out.print("Z");
                sleep(1000);
                if (i == 6) {
                    System.out.println();
                }
            }
            System.out.println("\n" + this.nome + " acordou!");
            System.out.println("Energia: 10\nFome: 8\n");
            sleep(1000);
            this.energia = 10;
            this.fome = 8;
            dormindo = false;
            // Redefine a tolerância máxima da fome ao valor inicial
            energiaCont = 0;
        }
    }
    
    public void medicar() {
        // Checa se está doente
        if (this.saude > 4) {
            System.out.println(this.nome + " não está doente!");
            sleep(1000);
        } else {
            /* booleano para informar aos timers que o Tamagotchi está tomando remédio
            (para não exibir mensagens na tela durante a ação)
            */
            medicando = true;
            System.out.print(this.nome + " está tomando remédio");
            sleep(1000);
            for (int i = 3; i > 0; i--) {
                System.out.print(".");
                sleep(1000);
            }
            System.out.println("\n" + this.nome + " tomou remédio!");
            System.out.println("Saúde: +5\nFelicidade: -8\n");
            sleep(1000);
            this.saude += 5;
            this.felicidade -= 8;
            /* O método validaStatus verifica se os atributos do Tamagotchi
            estão maiores que 10 ou menores que 0
            */
            validaStatus();
            medicando = false;
            // Redefine a tolerância máxima da fome ao valor inicial
            saudeCont = 0;
        }
    }
    
    public void minigame() {
        /* booleano para informar aos timers que o Tamagotchi está jogando
        (para não exibir mensagens na tela durante a ação)
        */
        jogando = true;
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("PEDRA PAPEL E TESOURA");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=");
        
        // Lista com o nome das opções (só será usada para exibir a opção escolhida)
        String[] lista = {"Pedra", "Papel", "Tesoura"};
        System.out.println("1 - PEDRA\n2 - PAPEL\n3 - TESOURA");
        System.out.print("\nDigite o número da opção desejada: ");
        
        // Solicita a opção ao usuário e gera uma opção aleatória para o Tamagotchi
        Scanner scanner = new Scanner(System.in);
        int jogador = scanner.nextInt() - 1;
        int tamagotchi = new Random().nextInt(3);

        System.out.println("\nJO");
        sleep(500);
        System.out.println("KEN");
        sleep(500);
        System.out.println("PO!\n");
        sleep(500);

        // Exibe a opção dos dois jogadores (pegando o nome da opção da lista)
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Você escolheu " + lista[jogador]);
        System.out.println(this.nome + " escolheu " + lista[tamagotchi]);
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        // Faz as verificações para definir quem ganhou
        if (jogador == tamagotchi) {
            System.out.println("\nEMPATE!\n");
        } else if ((jogador == 0 && tamagotchi == 2) ||
                   (jogador == 1 && tamagotchi == 0) ||
                   (jogador == 2 && tamagotchi == 1)) {
            System.out.println("\nVOCÊ GANHOU! Felicidade: -1\n");
            this.felicidade -= 1;
            validaStatus();
        } else {
            System.out.print("\n" + this.nome.toUpperCase() + " GANHOU! ");
            System.out.println("Felicidade: +1\n");
            this.felicidade += 1;
            /* O método validaStatus verifica se os atributos do Tamagotchi
            estão maiores que 10 ou menores que 0
            */
            validaStatus();
            // Redefine a tolerância máxima da felicidade ao valor inicial
            felicidadeCont = 0;
        }
        sleep(4000);
        jogando = false;
    }
    
    public void status() {
        status = true;

        // Exibe o status do Tamagotchi baseado nos valores de seus atributos
        System.out.print("Energia: " + this.energia);
        if (this.energia == 0) {
            System.out.println(" (Exausto)");
        } else if (this.energia <= 2) {
            System.out.println(" (Muito cansado)");
        } else if (this.energia <= 5) {
            System.out.println(" (Cansado)");
        } else if (this.energia > 5) {
            System.out.println(" (Disposto)");
        }
        System.out.print("Fome: " + this.fome);
        if (this.fome >= 7) {
            System.out.println(" (Faminto)");
        } else if (this.fome > 2) {
            System.out.println(" (Sem fome)");
        } else if (this.fome <= 2) {
            System.out.println(" (Muito cheio)");
        }
        System.out.print("Higiene: " + this.higiene);
        if (this.higiene <= 2) {
            System.out.println(" (Muito sujo)");
        } else if (this.higiene <= 5) {
            System.out.println(" (Sujo)");
        } else if (this.higiene > 5) {
            System.out.println(" (Limpo)");
        }
        System.out.print("Felicidade: " + this.felicidade);
        if (this.felicidade > 7) {
            System.out.println(" (Feliz)");
        } else if (this.felicidade >= 4) {
            System.out.println(" (Ok)");
        } else if (this.felicidade < 4) {
            System.out.println(" (Triste)");
        }
        System.out.print("Saúde: " + this.saude);
        if (this.saude > 8) {
            System.out.println(" (Saudável)");
        } else if (this.saude >= 4) {
            System.out.println(" (Ok)");
        } else if (this.saude < 4) {
            System.out.println(" (Doente)");
        }
        
        // Exibe a fase em que o Tamagotchi se encontra baseado na sua idade
        System.out.println("Idade: " + this.idade);
        if (this.idade < 4) {
            this.fase = "Bebê";
            System.out.println("Fase: " + this.fase);
        } else if (this.idade < 12) {
            this.fase = "Criança";
            System.out.println("Fase: " + this.fase);
        } else if (this.idade < 18) {
            this.fase = "Adolescente";
            System.out.println("Fase: " + this.fase);
        } else if (this.idade < 60) {
            this.fase = "Adulto";
            System.out.println("Fase: " + this.fase);
        } else if (this.idade >= 60) {
            this.fase = "Idoso";
            System.out.println("Fase: " + this.fase);
        }
        status = false;
        System.out.println();
        sleep(3000);
    }
    
    // Método para fazer com que o Tamagotchi morra e o jogo seja encerrado
    // Esse método recebe a causa da morte como parâmetro
    private void morrer(String causa) {
        System.out.println(this.nome + " se foi :(");
        System.out.println("Morreu de " + causa + "...");
        System.exit(0);
    }
    
    // Método para fazer aniversario (chamado através do Timer)
    private void aniversario() {
        this.idade++;
        this.felicidade += 1;
        validaStatus();
        System.out.println(this.nome + " fez aniversário! Idade: " + this.idade);
        System.out.println("Felicidade: +1\n");
        System.out.println();
    }
    
    // Método para deteriorar os atributos do Tamagotchi (chamado através do Timer)
    private void passarTempo() {
        fome += 1;
        felicidade -= 1;
        energia -= 1;
        higiene -= 1;
        validaStatus();
        
        // Faz com que o Tamagotchi morra quando chega a 130 anos
        if (idade == 130) {
            morrer("velhice");
        }
        
        // Condicionais para exibir avisos com relação ao status do Tamagotchi
        if (fome >= 8) {
            System.out.println(this.nome + " está faminto!");
            System.out.println("Considere alimentá-lo!\n");
            /* Verifica se a fome está no limite máximo e adiciona 1 ponto ao
            contador de tolerância da fome
            */
            if (fome == 10) {
                fomeCont++;
                // Se o contador chegar a 5, o Tamagochi morre
                if (fomeCont == 5) {
                morrer("fome");
                }
            }
            
        }
        if (felicidade <= 2) {
            System.out.println(this.nome + " está triste!");
            System.out.println("Considere passear, brincar ou jogar com ele!\n");
            /* Verifica se a felicidade está no limite mínimo e adiciona 1 ponto ao
            contador de tolerância da felicidade
            */
            if (felicidade == 0) {
                felicidadeCont++;
                // Se o contador chegar a 5, o Tamagochi morre
                if (felicidadeCont == 5) {
                    morrer("tristeza");
                }
            }
        }
        
        if (energia <= 2) {
            System.out.println(this.nome + " está muito cansado!");
            System.out.println("Considere colocá-lo para dormir\n");
            /* Verifica se a energia está no limite mínimo e adiciona 1 ponto ao
            contador de tolerância da energia
            */
            if (energia == 0) {
                energiaCont++;
                // Se o contador chegar a 5, o Tamagochi morre
                if (energiaCont == 5) {
                    morrer("exaustão");
                }
            }
        }
        
        if (higiene <= 2) {
            System.out.println(this.nome + " está muito sujo!");
            System.out.println("Considere limpá-lo\n");
            /* Verifica se a higiene está no limite mínimo e adiciona 1 ponto ao
            contador de tolerância da higiene
            */
            if (higiene == 0) {
                higieneCont++;
                // Se o contador chegar a 5, o Tamagochi morre
                if (higieneCont == 5) {
                    morrer("infecção devido à falta de higiene");
                }
            }
        }
        
        if (saude <= 2) {
            System.out.println(this.nome + " está doente!");
            System.out.println("Considere medicá-lo\n");
            /* Verifica se a saúde está no limite mínimo e adiciona 1 ponto ao
            contador de tolerância da saúde
            */
            if (saude == 0) {
                saudeCont++;
                // Se o contador chegar a 5, o Tamagochi morre
                if (saudeCont == 5) {
                    morrer("infecção devido à falta de cuidados médicos");
                }
            }
        }        
    }
    
    // Método para fazer passar um dia e definir se vai chover ou fazer sol
    public void passarDia() {
        // Incrementa o contador de dias para exibir quantos dias se passaram
        contDias++;
        System.out.println("Dia " + contDias + ":");
        
        // Decrementa a saúde do Tamagotchi em 1 ponto a cada 3 dias
        if (contDias % 3 == 0) {
            saude -= 1;
        }
        
        /*
        Gera um número aleatório entre 0 e 100 e
        atribui esse valor à variável chanceChuva
        */
        Random chuva = new Random();
        int chanceChuva = chuva.nextInt(100);

        // Define a probabilidade de um dia ser chuvoso (nesse exemplo, 50%)
        int chanceDiaChuvoso = 50;

        // Verifica se a chance de chover está dentro da probabilidade
        if (chanceChuva < chanceDiaChuvoso) {
            // Se for um dia chuvoso, diminui a felicidade e a energia
            System.out.println("Hoje está chovendo!");
            System.out.println("Felicidade: -2\nEnergia: -3\n");
            felicidade -= 2;
            energia -= 3;
            validaStatus();
        } else {
            // Se não, aumenta a felicidade e a energia
            System.out.println("Hoje está ensolarado!");
            System.out.println("Felicidade: +1\nEnergia: +1\n");
            felicidade += 1;
            energia += 1;
            validaStatus();
        }
        
        /*
        Gera um número aleatório entre 0 e 100 e
        atribui esse valor à variável chanceDoente
        */
        Random doenca = new Random();
        int chanceDoente = doenca.nextInt(100);

        // Define uma probabilidade de 5% de ficar doente nesse dia
        int chanceDoenca = 5;

        // Verifica se a chance de ficar doente está dentro da probabilidade
        if (chanceDoente < chanceDoenca) {
            // Se estiver doente, diminui a felicidade, a energia e a saúde
            System.out.println(this.nome + " está doente hoje!");
            System.out.println("Felicidade: -4\nEnergia: -4\nSaúde: -8\n");
            felicidade -= 4;
            energia -= 4;
            saude -= 8;
            validaStatus();
        } 
    }
    
    /* O método validaStatus verifica se os atributos do Tamagotchi estão
    maiores que 10 ou menores que 0.
    Caso isso aconteça, os valores maiores que 10 são definidos como 10 e os
    valores menores que 0 são definidos como 0.
    */
    private void validaStatus() {
        if (this.fome > 10) {
            this.fome = 10;
        } else if (this.fome < 0) {
            this.fome = 0;
        }
        if (this.felicidade > 10) {
            this.felicidade = 10;
        } else if (this.felicidade < 0) {
            this.felicidade = 0;
        }
        if (this.energia > 10) {
            this.energia = 10;
        } else if (this.energia < 0) {
            this.energia = 0;
        }
        if (this.higiene > 10) {
            this.higiene = 10;
        } else if (this.higiene < 0) {
            this.higiene = 0;
        }
        if (this.saude > 10) {
            this.saude = 10;
        } else if (this.saude < 0) {
            this.saude = 0;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Parabéns, você acaba de adquirir um Tamagotchi!");
        System.out.print("Digite o nome do seu novo amigo: ");
        String nome = sc.nextLine();
        System.out.println();
        Tamagotchi2 t1 = new Tamagotchi2(nome);
        // Variável "op" armazena a opção escolhida no menu
        int op;
        do {
            System.out.println("Selecione uma opção: ");
            System.out.println("1 - Alimentar");
            System.out.println("2 - Passear");
            System.out.println("3 - Brincar");
            System.out.println("4 - Jogar");
            System.out.println("5 - Limpar");
            System.out.println("6 - Dormir");
            System.out.println("7 - Medicar");
            System.out.println("8 - Exibir Status");
            System.out.println("0 - Sair");
            op = sc.nextInt();
            System.out.println();
            switch (op) {
                // Cancela os Timers para que o programa possa ser fechado
                case 0:
                    t1.aniversario.cancel();
                    t1.passarTempo.cancel();
                    t1.passarDia.cancel();
                    break;
                case 1:
                    t1.alimentar();
                    break;
                case 2:
                    t1.passear();
                    break;
                case 3:
                    t1.brincar();
                    break;
                case 4:
                    t1.minigame();
                    break;    
                case 5:
                    t1.limpar();
                    break;
                case 6:
                    t1.dormir();
                    break;
                case 7:
                    t1.medicar();
                    break;
                case 8:
                    t1.status();
                    break;
                case 9:
                    t1.morrer("");
                    break;
                default:
                    System.out.println("Digite uma opção válida!");
                    break;
            }
        } while (op != 0);
        
        sc.close();
        
    }
}
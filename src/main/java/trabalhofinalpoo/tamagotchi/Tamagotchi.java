/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package trabalhofinalpoo.tamagotchi;

import java.util.Scanner;

/**
 *
 * @author negreirosdrummer, deyvideugenio, Antony2804, raphassoares
 */

class Tamagotchi {
    private String nome;
    private int fome;
    private int felicidade;
    private int energia;
    private int higiene;
    private int saude;
    private int idade;
    private String fase;
    
    // Construtor
    Tamagotchi(String nome) {
        this.nome = nome;
        fome = 5;
        felicidade = 5;
        energia = 10;
        higiene = 10;
        saude = 10;
        idade = 0;
        fase = "Bebê";
    }
    
    // Método para suspender a execução do programa
    // (ao exibir mensagens na tela, por exemplo)
    public static void sleep(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Métodos para interagir com o Tamagotchi
    public void alimentar() {
        if (this.fome < 2) {
            System.out.println(this.nome + " não está com fome!");
            sleep(1000);
        } else {
            System.out.print(this.nome + " está comendo");
            sleep(1000);
            for (int i = 3; i > 0; i--) {
                System.out.print(".");
                sleep(1000);
            }
            System.out.println("\n" + this.nome + " comeu!\n");
            sleep(1000);
            this.fome -= 5;
            if (this.fome < 0) {
                this.fome = 0;
            }
        }
    }
    
    public void passear() {
        // TODO: se ferir ao passear (probabilidade 20%)
        System.out.print(this.nome + " está passeando");
        sleep(1000);
        for (int i = 3; i > 0; i--) {
            System.out.print(".");
            sleep(1000);
        }
        System.out.println("\n" + this.nome + " passeou!\n");
        sleep(1000);
        this.felicidade += 3;
        this.energia -= 2;
        
        if (this.felicidade > 10) {
            this.felicidade = 10;
        }
        
        if (this.energia <= 0) {
            this.energia = 0;
            System.out.println(this.nome + " está muito cansado e "
                    + "precisa dormir!");
            dormir();
        }
    }
    
    public void brincar() {
        // TODO: se ferir ao brincar (probabilidade 10%)
        System.out.print(this.nome + " está brincando");
        sleep(1000);
        for (int i = 3; i > 0; i--) {
            System.out.print(".");
            sleep(1000);
        }
        System.out.println("\n" + this.nome + " brincou!\n");
        sleep(1000);
        this.felicidade += 5;
        if (this.felicidade > 10) {
            this.felicidade = 10;
        }
    }
    
    public void limpar() {
        if (this.higiene > 5) {
            System.out.println(this.nome + " não está sujo!");
            sleep(1000);
        } else {       
            System.out.print("Limpando " + this.nome);
            sleep(1000);
            for (int i = 3; i > 0; i--) {
                System.out.print(".");
                sleep(1000);
            }
            System.out.println("\n" + this.nome + " está limpo!\n");
            sleep(1000);
            this.higiene = 10;
        }
    }
    
    public void dormir() {
        if (this.energia > 5) {
            System.out.println(this.nome + " não está com sono!");
            sleep(1000);
        } else {
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
            System.out.println("\n" + this.nome + " acordou!\n");
            sleep(1000);
            this.energia = 10;
            this.fome = 8;
        }
    }
    
    public void medicar() {
        if (this.saude > 4) {
            System.out.println(this.nome + " não está doente!");
            sleep(1000);
        } else {
            System.out.print(this.nome + " está tomando remédio");
            sleep(1000);
            for (int i = 3; i > 0; i--) {
                System.out.print(".");
                sleep(1000);
            }
            System.out.println("\n" + this.nome + " tomou remédio!\n");
            System.out.println("Saúde +5\nFelicidade -2");
            sleep(1000);
            this.saude += 5;
            if (this.saude > 10) {
                this.saude = 10;
            }
            this.felicidade -= 8;
            if (this.felicidade < 0) {
                this.felicidade = 0;
            }
            this.fome += 8;
            if (this.fome > 10) {
                this.fome = 10;
            }
        }
        // TODO: esperar 30 segundos para medicar novamente
    }
    
    /* TODO: minigame
    
    public void minigame() {
        
    }*/
    
    public void status() {

        System.out.print("Energia: " + this.energia);
        if (this.energia == 0) {
            System.out.println(" (Dormindo)");
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
        System.out.println();
        sleep(2000);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Vamos começar?\nDigite o nome do seu Tamagotchi: ");
        String nome = sc.nextLine();
        Tamagotchi t1 = new Tamagotchi(nome);
        int op;
        do {
            System.out.println("Selecione uma opção: ");
            System.out.println("1 - Alimentar");
            System.out.println("2 - Passear");
            System.out.println("3 - Brincar");
            System.out.println("4 - Limpar");
            System.out.println("5 - Dormir");
            System.out.println("6 - Medicar");
            System.out.println("7 - Exibir Status");
            System.out.println("0 - Sair");
            op = sc.nextInt();
            System.out.println();
            switch (op) {
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
                    t1.limpar();
                    break;
                case 5:
                    t1.dormir();
                    break;
                case 6:
                    t1.medicar();
                    break;
                case 7:
                    t1.status();
                    break;
                default:
                    System.out.println("Digite uma opção válida!");
                    break;
            }
        } while (op != 0);
    }
}
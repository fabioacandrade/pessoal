import java.util.*;
class Paises{
    public String nome;
    public int ouro,prata,bronze;
}

class PT02{
    public static void main(String[] args){
        Paises[] array = new Paises[1000];
        int countPaises=0,countArray=0;

        Scanner sc = new Scanner(System.in);

        //preenchendo o array
        sc.nextLine();
        while(sc.hasNext()){
            boolean resp = false;
            String nome = sc.nextLine();
            for(int i=0;i<array.length;i++){
                if(array[i] != null){
                    if(nome.compareTo(array[i].nome)==0){
                        resp = true;
                        array[i].ouro++;
                    }
                }
            }
            if(!resp){
                array[countArray] = new Paises();
                array[countArray].nome = nome;
                array[countArray].ouro++;
                countArray++;
            }

            resp = false;
            nome = sc.nextLine();

            for(int i=0;i<array.length;i++){
                if(array[i] != null){
                    if(nome.compareTo(array[i].nome)==0){
                        resp = true;
                        array[i].prata++;
                    }
                }
            }
            if(!resp){
                array[countArray] = new Paises();
                array[countArray].nome = nome;
                array[countArray].prata++;
                countArray++;
            }

            resp = false;
            nome = sc.nextLine();

            for(int i=0;i<array.length;i++){
                if(array[i] != null){
                    if(nome.compareTo(array[i].nome)==0){
                        resp = true;
                        array[i].bronze++;
                    }
                }
            }
            if(!resp){
                array[countArray] = new Paises();
                array[countArray].nome = nome;
                array[countArray].bronze++;
                countArray++;
            }
            sc.nextLine();
            //count++;
        }

        System.out.println("Quadro de Medalhas");
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length;j++){
                if(array[i]!=null && array[j]!=null){
                    if(array[i].ouro<array[j].ouro){
                        Paises tmp = new Paises();
                        tmp = array[i];
                        array[i]=array[j];
                        array[j]=tmp;
                    }
                }
            }
        }

        for(int i=0;i<array.length;i++){
            if(array[i]!=null)
            System.out.println(array[i].nome + " " + array[i].ouro + " " + array[i].prata + " " + array[i].bronze);
        }

    }
}
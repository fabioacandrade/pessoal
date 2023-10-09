#include <iostream>
#include <string.h>
#define TAM 1000
using namespace std;
class Veiculo{
public:
    string placa;
    string marca;                     //DECLARAÇÃO DA CLASSE
    string modelo;
    string tipo;
    string observacao;
    string ano;
    string preco;

    Veiculo(string placa,string marca,string modelo,string ano, string preco,string tipo,string observacao){
        this->placa=placa;
        this->marca=marca;
        this->modelo=modelo;
        this->tipo=tipo;
        this->observacao=observacao;
        this->ano=ano;
        this->preco=preco;
    }
    void imprimirveiculo(){
        cout<<"Placa:"<<placa<<endl;
        cout<<"Marca:"<<marca<<endl;
        cout<<"Modelo:"<<modelo<<endl;
        cout<<"Ano:"<<ano<<endl;
        cout<<"Preco:"<<preco<<endl;
        cout<<"Tipo:"<<tipo<<endl;
        cout<<"Observacao:"<<observacao<<endl;
    }
    Veiculo();
};
void atualizartxt(Veiculo* arrayv[],int Count){
    FILE* arquivo;
    arquivo=fopen("veiculos.txt","w");    //ATUALIZA O TXT SEMPRE QUE NECESSARIO
    for(int i=0;i<Count;i++){
        fprintf(arquivo,"\n%s;%s;%s;%s;%s;%s;%s;",arrayv[i]->placa.c_str(),arrayv[i]->marca.c_str(),arrayv[i]->modelo.c_str(),arrayv[i]->ano.c_str(),arrayv[i]->preco.c_str(),arrayv[i]->tipo.c_str(),arrayv[i]->observacao.c_str());
    }
    fclose(arquivo);
}
int main()
{
    int Count=0;
    Veiculo* arrayveiculos[TAM];
    FILE* arquivo;
    arquivo = fopen("veiculos.txt","r");
    while(!feof(arquivo)){

        char leituralinha[1000];
        char token[] = ";";
        fscanf(arquivo," %[^\n]",leituralinha);
        char *tokenplaca = strtok(leituralinha,token);
        string placa=tokenplaca;
        char *tokenmarca = strtok(NULL,token);
        string marca=tokenmarca;
        char *tokenmodelo = strtok(NULL,token);          //FAZ A LEITURA INICIAL DO ARQUIVO
        string modelo=tokenmodelo;
        char *tokenano = strtok(NULL,token);
        string ano=tokenano;
        char *tokenpreco = strtok(NULL,token);
        string preco=tokenpreco;
        char *tokentipo = strtok(NULL,token);
        string tipo=tokentipo;
        char *tokenobse = strtok(NULL,token);
        string obs=tokenobse;

        arrayveiculos[Count]=new Veiculo(placa,marca,modelo,ano,preco,tipo,obs);
        Count++;
    }
    fclose(arquivo);


    int escolha,obser,resposta,fechar=0;
    char placa[7],marca[20],modelo[20],ano[4],preco[20],tipo[20],obs[200];
    do{
    cout<<"Bem vindo ao sistema veicular\n1.Listar todos os veiculos\n2.Pesquisar um veiculo\n3.Cadastrar um veiculo\n4.Editar um veiculo\n5.Excluir um veiculo\n6.Sair do programa\nDigite o que deseja fazer:";
    cin>>escolha;                    //MENU PRINCIPAL
    switch(escolha){
    case 1:
        for(int i=0;i<Count;i++){
            arrayveiculos[i]->imprimirveiculo();
        }
        break;
    case 2:
        cout<<"Digite a placa correspondente:"<<endl;
        cin>>placa;
        for(int i=0;i<Count;i++){
            if(arrayveiculos[i]->placa==placa)
                arrayveiculos[i]->imprimirveiculo();
        }
        break;
    case 3:                                                                   //CADA CASE REPRESENTA UMA ESCOLHA
        cout<<"Digite a placa:"<<endl;
        scanf(" %[^\n]",placa);
        cout<<"Digite a marca:"<<endl;
        scanf(" %[^\n]",marca);
        cout<<"Digite o modelo:"<<endl;
        scanf(" %[^\n]",modelo);
        cout<<"Digite o ano:"<<endl;
        scanf(" %[^\n]",ano);
        cout<<"Digite o preco:"<<endl;
        scanf(" %[^\n]",preco);
        cout<<"Digite o tipo:"<<endl;
        scanf(" %[^\n]",tipo);
        do{
            cout<<"Deseja inserir observacao? (1.SIM/2.NAO)"<<endl;
            cin>>obser;
            if(obser==1){
                cout<<"Digite a observacao:"<<endl;
                scanf(" %[^\n]",obs);
            }
            else if(obser!=2)
                cout<<"NUMERO INVALIDO"<<endl;
        }while(!(obser==1 || obser==2));
        arquivo = fopen("veiculos.txt","a");
        fprintf(arquivo,"\n%s;%s;%s;%s;%s;%s;%s;",placa,marca,modelo,ano,preco,tipo,obs);
        fclose(arquivo);
        Count++;
        arrayveiculos[Count]=new Veiculo(placa,marca,modelo,ano,preco,tipo,obs);
        break;
    case 4:
        cout<<"Digite a placa correspondente:"<<endl;
        cin>>placa;
        cout<<"O que deseja mudar?\n1.marca\n2.modelo\n3.ano\n4.preco\n5.tipo\n6.observacao"<<endl;
        cin>>resposta;
        if(resposta==1){
            string marcanova;
            cout<<"digite a nova marca:"<<endl;
            cin>>marcanova;
            for(int i=0;i<Count;i++){
                if(placa==arrayveiculos[i]->placa){
                    arrayveiculos[i]->marca=marcanova;
                    //editar=1;
                }
            }
        }
        else if(resposta==2){
            string modelonova;
            cout<<"digite o novo modelo:"<<endl;
            cin>>modelonova;
            for(int i=0;i<Count;i++){
                if(placa==arrayveiculos[i]->placa){
                    arrayveiculos[i]->modelo=modelonova;
                    //editar=1;
                }
            }
        }
        else if(resposta==3){
            string anonova;
            cout<<"digite o novo ano:"<<endl;
            cin>>anonova;
            for(int i=0;i<Count;i++){
                if(placa==arrayveiculos[i]->placa){
                    arrayveiculos[i]->ano=anonova;
                    //editar=1;
                }
            }
        }
        else if(resposta==4){
            string preconova;
            cout<<"digite o novo preco:"<<endl;
            cin>>preconova;
            for(int i=0;i<Count;i++){
                if(placa==arrayveiculos[i]->placa){
                    arrayveiculos[i]->preco=preconova;
                    //editar=1;
                }
            }
        }
        else if(resposta==5){
            string tiponova;
            cout<<"digite o novo tipo:"<<endl;
            cin>>tiponova;
            for(int i=0;i<Count;i++){
                if(placa==arrayveiculos[i]->placa){
                    arrayveiculos[i]->tipo=tiponova;
                    //editar=1;
                }
            }
        }
        else if(resposta==6){
            string obsnova;
            cout<<"digite a nova observacao:"<<endl;
            cin>>obsnova;
            for(int i=0;i<Count;i++){
                if(placa==arrayveiculos[i]->placa){
                    arrayveiculos[i]->observacao=obsnova;
                    //editar=1;
                }
            }
        }
        atualizartxt(arrayveiculos, Count);
        break;
    case 5:
        cout<<"Digite a placa correspondente:"<<endl;
        cin>>placa;
        for(int i=0;i<Count;i++){
            if(placa==arrayveiculos[i]->placa)
                delete arrayveiculos[i];
        }
        Count--;
        atualizartxt(arrayveiculos,Count);
        break;
    case 6:
        cout<<"OBRIGADO POR UTILIZAR NOSSO SISTEMA"<<endl;
        fechar=1;
    }
    }while(fechar!=1);
    return 0;
}

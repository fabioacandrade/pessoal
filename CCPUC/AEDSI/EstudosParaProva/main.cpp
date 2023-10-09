#include <iostream>
#include <string>
using namespace std;

class Animal {
protected:
    string nome;
    string raca;

public:
    Animal(string nome, string raca) : nome(nome), raca(raca) {}

    virtual ~Animal() {}

    virtual void imprimir() {
        cout << "nome: " << nome << endl << "raça: " << raca << endl;
    }

    void caminha() {
        cout << "caminhandoo" << endl;
    }
};

class Cachorro : public Animal {
public:
    Cachorro(string nome, string raca) : Animal(nome, raca) {}

    void imprimir() {
        cout << "auau" << endl;
    }
};

class Gato : public Animal {
public:
    Gato(string nome, string raca) : Animal(nome, raca) {}

    void imprimir() {
        cout << "miauuuuuu" << endl;
    }
};

void imprimirAnimal(Animal* animal) {
    animal->imprimir();
}

int main() {
    Cachorro cach1("minie", "shitzu");
    imprimirAnimal(&cach1);

    return 0;
}

import 'package:flutter/material.dart';

class CarrinhoApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: SistemaDeCarrinho(),
    );
  }
}

class Item {
  String codigo;
  String descricao;
  double valor;
  double total;
  double acrescimo;
  double desconto;
  int quantidade;

  Item({
    required this.codigo,
    required this.descricao,
    required this.valor,
    this.quantidade = 1,
  })  : total = valor,
        acrescimo = 0,
        desconto = 0;
}

class SistemaDeCarrinho extends StatefulWidget {
  @override
  _SistemaDeCarrinhoState createState() => _SistemaDeCarrinhoState();
}

class _SistemaDeCarrinhoState extends State<SistemaDeCarrinho> {
  List<Item> itensCarrinho = [];
  final TextEditingController valorController = TextEditingController();
  final TextEditingController acrescimoController = TextEditingController();
  final TextEditingController descontoController = TextEditingController();
  final TextEditingController quantidadeController = TextEditingController();

  final Map<String, String> produtos = {
    '0': 'Arroz',
    '1': 'Feijão',
    '2': 'Batata Frita',
    '3': 'Carne',
    '4': 'Frango',
    '5': 'Macarrão',
    '6': 'Leite',
    '7': 'Queijo',
    '8': 'Maçã',
    '9': 'Banana',
    '10': 'Pão'
  };

  String? codigoSelecionado;
  String descricaoProduto = '';

  void adicionarItem() {
    if (codigoSelecionado != null &&
        valorController.text.isNotEmpty &&
        quantidadeController.text.isNotEmpty) {
      double valor = double.parse(valorController.text);
      int quantidade = int.parse(quantidadeController.text);
      double acrescimo = double.parse(
          acrescimoController.text.isEmpty ? '0' : acrescimoController.text);
      double desconto = double.parse(
          descontoController.text.isEmpty ? '0' : descontoController.text);

      setState(() {
        itensCarrinho.add(Item(
            codigo: codigoSelecionado!,
            descricao: descricaoProduto,
            valor: valor,
            quantidade: quantidade)
          ..acrescimo = acrescimo
          ..desconto = desconto
          ..total = (valor + acrescimo - desconto) * quantidade);
        codigoSelecionado = null;
        descricaoProduto = '';
        valorController.clear();
        acrescimoController.clear();
        descontoController.clear();
        quantidadeController.clear();
      });
    }
  }

  void mostrarAviso(String tipo, double valor) {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        backgroundColor: Colors.orange,
        title: Text(
          '$tipo total',
          style: TextStyle(color: Colors.black),
        ),
        content: Text(
          '$tipo de valor: R\$ ${valor.toStringAsFixed(2)}',
          style: TextStyle(color: Colors.black),
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(context),
            child: Text('OK', style: TextStyle(color: Colors.black)),
          ),
        ],
      ),
    );
  }

  void finalizarVenda() {
    double totalDesconto = 0;
    double totalAcrescimo = 0;
    double total = 0;

    for (var item in itensCarrinho) {
      totalDesconto += item.desconto;
      totalAcrescimo += item.acrescimo;
      total += item.total;
    }

    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: Text('Venda Finalizada'),
        content: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          mainAxisSize: MainAxisSize.min,
          children: [
            ...itensCarrinho.map((item) => Text(
                'Código: ${item.codigo}, Descrição: ${item.descricao}, Quantidade: ${item.quantidade}, Total: ${item.total.toStringAsFixed(2)}')),
            SizedBox(height: 10),
            Text('Desconto total: ${totalDesconto.toStringAsFixed(2)}'),
            Text('Acréscimo total: ${totalAcrescimo.toStringAsFixed(2)}'),
            Text('Valor total do carrinho: ${total.toStringAsFixed(2)}'),
          ],
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(context),
            child: Text('OK'),
          ),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Seu Carrinho'),
        backgroundColor: Color.fromARGB(255, 193, 113, 52),
        centerTitle: true,
      ),
      body: Container(
        decoration: BoxDecoration(
            image: DecorationImage(
                image: AssetImage('assets/background.png'), fit: BoxFit.cover)),
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: ListView(
            children: [
              Padding(
                padding: const EdgeInsets.only(bottom: 15),
                child: SizedBox(
                    width: 200,
                    height: 200,
                    child: Image.asset('assets/icon.png')),
              ),
              DropdownButtonFormField<String>(
                value: codigoSelecionado,
                onChanged: (String? novoValor) {
                  setState(() {
                    codigoSelecionado = novoValor;
                    descricaoProduto = produtos[codigoSelecionado!]!;
                  });
                },
                items: produtos.keys.map((String codigo) {
                  return DropdownMenuItem<String>(
                    value: codigo,
                    child: Text('$codigo - ${produtos[codigo]}'),
                  );
                }).toList(),
                decoration: InputDecoration(
                  labelText: 'Código do Item',
                  border: OutlineInputBorder(),
                ),
              ),
              SizedBox(height: 10),
              TextField(
                controller: TextEditingController(text: descricaoProduto),
                decoration: InputDecoration(
                  labelText: 'Descrição do Item',
                  border: OutlineInputBorder(),
                ),
                readOnly: true,
              ),
              SizedBox(height: 10),
              TextField(
                controller: valorController,
                decoration: InputDecoration(
                  labelText: 'Valor do Item',
                  border: OutlineInputBorder(),
                ),
                keyboardType: TextInputType.number,
              ),
              SizedBox(height: 10),
              TextField(
                controller: quantidadeController,
                decoration: InputDecoration(
                  labelText: 'Quantidade',
                  border: OutlineInputBorder(),
                ),
                keyboardType: TextInputType.number,
              ),
              SizedBox(height: 10),
              TextField(
                controller: acrescimoController,
                decoration: InputDecoration(
                  labelText: 'Acréscimo do Item',
                  border: OutlineInputBorder(),
                ),
                keyboardType: TextInputType.number,
              ),
              SizedBox(height: 10),
              TextField(
                controller: descontoController,
                decoration: InputDecoration(
                  labelText: 'Desconto do Item',
                  border: OutlineInputBorder(),
                ),
                keyboardType: TextInputType.number,
              ),
              SizedBox(height: 20),
              ElevatedButton(
                onPressed: adicionarItem,
                child: Text('Inserir item ao carrinho'),
                style: ElevatedButton.styleFrom(
                  backgroundColor: const Color.fromARGB(255, 193, 113, 52),
                  foregroundColor: Colors.black
                ),
              ),
              SizedBox(height: 10),
              ElevatedButton(
                onPressed: () {
                  double acrescimo = double.parse(
                      acrescimoController.text.isEmpty ? '0' : acrescimoController.text);
                  mostrarAviso('Acréscimo', acrescimo);
                },
                child: Text('Acréscimo total'),
                style: ElevatedButton.styleFrom(
                  backgroundColor: const Color.fromARGB(255, 193, 113, 52),
                  foregroundColor: Colors.black
                ),
              ),
              SizedBox(height: 10),
              ElevatedButton(
                onPressed: () {
                  double desconto = double.parse(
                      descontoController.text.isEmpty ? '0' : descontoController.text);
                  mostrarAviso('Desconto', desconto);
                },
                child: Text('Desconto total'),
                style: ElevatedButton.styleFrom(
                    backgroundColor: Color.fromARGB(255, 193, 113, 52),
                    foregroundColor: Colors.black,
                    overlayColor: Colors.greenAccent),
              ),
              SizedBox(height: 10),
              ElevatedButton(
                onPressed: finalizarVenda,
                child: Text('Finalizar venda'),
                style: ElevatedButton.styleFrom(
                  backgroundColor: Color.fromARGB(255, 193, 113, 52),
                  foregroundColor: Colors.black
                ),
              ),
              Divider(),
              Text(
                'Itens no Carrinho:',
                style: TextStyle(fontWeight: FontWeight.bold),
              ),
              ...itensCarrinho.map((item) => ListTile(
                    title: Text('Código: ${item.codigo} - ${item.descricao}'),
                    subtitle: Text(
                        'Valor: R\$ ${item.valor.toStringAsFixed(2)} | Quantidade: ${item.quantidade} | Total: R\$ ${item.total.toStringAsFixed(2)}'),
                    trailing: Row(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        IconButton(
                          icon: Icon(Icons.edit),
                          onPressed: () {
                            TextEditingController acrescimoController =
                                TextEditingController(text: item.acrescimo.toString());
                            TextEditingController descontoController =
                                TextEditingController(text: item.desconto.toString());
                            TextEditingController quantidadeController =
                                TextEditingController(text: item.quantidade.toString());

                            showDialog(
                              context: context,
                              builder: (context) => AlertDialog(
                                title: Text('Editar Item'),
                                content: Column(
                                  children: [
                                    TextField(
                                      controller: acrescimoController,
                                      decoration: InputDecoration(
                                        labelText: 'Acréscimo',
                                        border: OutlineInputBorder(),
                                      ),
                                      keyboardType: TextInputType.number,
                                    ),
                                    SizedBox(height: 10),
                                    TextField(
                                      controller: descontoController,
                                      decoration: InputDecoration(
                                        labelText: 'Desconto',
                                        border: OutlineInputBorder(),
                                      ),
                                      keyboardType: TextInputType.number,
                                    ),
                                    SizedBox(height: 10),
                                    TextField(
                                      controller: quantidadeController,
                                      decoration: InputDecoration(
                                        labelText: 'Quantidade',
                                        border: OutlineInputBorder(),
                                      ),
                                      keyboardType: TextInputType.number,
                                    ),
                                  ],
                                ),
                                actions: [
                                  TextButton(
                                    onPressed: () {
                                      double acrescimo = double.parse(
                                          acrescimoController.text.isEmpty
                                              ? '0'
                                              : acrescimoController.text);
                                      double desconto = double.parse(
                                          descontoController.text.isEmpty
                                              ? '0'
                                              : descontoController.text);
                                      int quantidade = int.parse(
                                          quantidadeController.text.isEmpty
                                              ? '1'
                                              : quantidadeController.text);
                                      setState(() {
                                        item.acrescimo = acrescimo;
                                        item.desconto = desconto;
                                        item.quantidade = quantidade;
                                        item.total = (item.valor + acrescimo - desconto) * quantidade;
                                      });
                                      Navigator.pop(context);
                                    },
                                    child: Text('Salvar'),
                                  ),
                                  TextButton(
                                    onPressed: () => Navigator.pop(context),
                                    child: Text('Cancelar'),
                                  ),
                                ],
                              ),
                            );
                          },
                        ),
                        IconButton(
                          icon: Icon(Icons.delete),
                          onPressed: () {
                            setState(() {
                              itensCarrinho.remove(item);
                            });
                          },
                        ),
                      ],
                    ),
                  )),
            ],
          ),
        ),
      ),
    );
  }
}

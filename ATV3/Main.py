def adicionar_item(carrinho, codigo, descricao, valor):
    carrinho[codigo] = {'descricao': descricao, 'valor': valor, 'acrescimo': 0, 'desconto': 0}
    print(f"\nItem adicionado: Código: {codigo}, Descrição: {descricao}, Valor: {valor:.2f}")
    return codigo


def aplicar_acrescimo_item(carrinho, codigo, acrescimo):
    if codigo in carrinho:
        carrinho[codigo]['acrescimo'] += acrescimo
        carrinho[codigo]['valor'] += acrescimo


def aplicar_desconto_item(carrinho, codigo, desconto):
    if codigo in carrinho:
        carrinho[codigo]['desconto'] += desconto
        carrinho[codigo]['valor'] -= desconto


def aplicar_acrescimo_total(carrinho, acrescimo_total):
    quantidade_itens = len(carrinho)
    acrescimo_por_item = acrescimo_total / quantidade_itens
    for codigo in carrinho:
        carrinho[codigo]['acrescimo'] += acrescimo_por_item
        carrinho[codigo]['valor'] += acrescimo_por_item


def aplicar_desconto_total(carrinho, desconto_total):
    quantidade_itens = len(carrinho)
    desconto_por_item = desconto_total / quantidade_itens
    for codigo in carrinho:
        carrinho[codigo]['desconto'] += desconto_por_item
        carrinho[codigo]['valor'] -= desconto_por_item


def finalizar_venda(carrinho):
    desconto_total = sum(item['desconto'] for item in carrinho.values())
    acrescimo_total = sum(item['acrescimo'] for item in carrinho.values())
    valor_total = sum(item['valor'] for item in carrinho.values())

    print("\nItens no carrinho:")
    for codigo, item in carrinho.items():
        print(
            f"Código: {codigo}, Descrição: {item['descricao']}, Valor: {item['valor']:.2f}, Acréscimo: {item['acrescimo']:.2f}, Desconto: {item['desconto']:.2f}")

    print(f"\nDesconto total: {desconto_total:.2f}")
    print(f"Acréscimo total: {acrescimo_total:.2f}")
    print(f"Valor total: {valor_total:.2f}")


def menu():
    carrinho = {}
    while True:
        print("\nMenu:")
        print("1. Inserir item ao carrinho")
        print("2. Acréscimo de item")
        print("3. Desconto de item")
        print("4. Acréscimo total")
        print("5. Desconto total")
        print("6. Finalizar venda")
        print("0. Sair")

        escolha = input("Escolha uma opção: ")

        if escolha == '1':
            codigo = input("Código: ")
            descricao = input("Descrição: ")
            valor = float(input("Valor: "))
            codigo_item = adicionar_item(carrinho, codigo, descricao, valor)

            desconto = float(input("Desconto inicial para o item (ou 0 para nenhum): "))
            acrescimo = float(input("Acréscimo inicial para o item (ou 0 para nenhum): "))

            aplicar_desconto_item(carrinho, codigo_item, desconto)
            aplicar_acrescimo_item(carrinho, codigo_item, acrescimo)

            print(
                f"\nApós ajustes, o item agora é: Código: {codigo_item}, Descrição: {carrinho[codigo_item]['descricao']}, Valor: {carrinho[codigo_item]['valor']:.2f}, Acréscimo: {carrinho[codigo_item]['acrescimo']:.2f}, Desconto: {carrinho[codigo_item]['desconto']:.2f}")

        elif escolha == '2':
            codigo = input("Código do item: ")
            acrescimo = float(input("Valor do acréscimo: "))
            aplicar_acrescimo_item(carrinho, codigo, acrescimo)

        elif escolha == '3':
            codigo = input("Código do item: ")
            desconto = float(input("Valor do desconto: "))
            aplicar_desconto_item(carrinho, codigo, desconto)

        elif escolha == '4':
            acrescimo_total = float(input("Valor do acréscimo total: "))
            aplicar_acrescimo_total(carrinho, acrescimo_total)

        elif escolha == '5':
            desconto_total = float(input("Valor do desconto total: "))
            aplicar_desconto_total(carrinho, desconto_total)

        elif escolha == '6':
            finalizar_venda(carrinho)
            break

        elif escolha == '0':
            break

        else:
            print("Opção inválida. Tente novamente.")


if __name__ == "__main__":
    menu()

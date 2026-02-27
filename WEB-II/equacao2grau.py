print ("Equação de Segundo Grau")
a = float(input("Digite valo de a:"))
b = float(input("Digite valo de b:"))
c = float(input("Digite valo de c:"))

if a == 0:
    print("Não é equação de segundo grau, a deve ser diferente de 0")
else:
    delta = b*b-(4*a*c)

    if delta > 0:
        x1 = (-b + pow(delta, 0.5)) / (2*a)
        x2 = (-b - pow(delta, 0.5)) / (2*a)
        print(f"x1 = {x1:.2f}")
        print(f"x2 = {x2:.2f}")
    elif delta == 0:
        x= (-b)/(2*a)
        print(f"x = {x:.2f} (Delta igual a zero)")
    else:
        print ("Erro: Delta < 0")